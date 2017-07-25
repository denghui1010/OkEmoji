package com.sinnao.okemoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sinnao.okemoji.view.EmojiPanel;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 支持表情的EditText
 * Created by Liu Denghui on 16/8/16.
 */
public class EmojiEditText extends AppCompatEditText {
    private static final String TAG = "EmojiEditText";
    private static Pattern EMOJI_PATTERN = Pattern.compile("\\[(.*?)\\]");
    private EmojiPanel mEmojiPanel;
    private LinkedList<EmojiIcon> mEmojiIcons = new LinkedList<>();
    private boolean isConvertEnable = true;

    public EmojiEditText(Context context) {
        super(context);
        init();
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public boolean isConvertEnable() {
        return isConvertEnable;
    }

    public void setConvertEnable(boolean convertEnable) {
        isConvertEnable = convertEnable;
    }

    private void init() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, int before, int count) {
                if (!isConvertEnable) {
                    return;
                }
                try {
                    CharSequence addCharSequence = s.subSequence(start, start + count);
                    Matcher matcher = EMOJI_PATTERN.matcher(addCharSequence);
                    while (matcher.find()) {
                        final int emojiStart = matcher.start();
                        final int emojiEnd = matcher.end();
                        String emojiName = matcher.group(0);
                        final EmojiIcon emojiIcon = mEmojiPanel.getEmojiIcon(emojiName);
                        if (emojiIcon != null) {
                            if (s instanceof Editable) {
                                final Editable editable = (Editable) s;
                                if (emojiIcon.getResId() != 0) {
                                    ImageSpan imageSpan = new ImageSpan(getContext(), emojiIcon.getResId(), ImageSpan.ALIGN_BASELINE);
                                    editable.setSpan(imageSpan, start + emojiStart, start + emojiStart + emojiEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    mEmojiIcons.add(emojiIcon);
                                } else if (emojiIcon.getLocalPath() != null) {
                                    final int size = getResources().getDimensionPixelOffset(R.dimen.emojiSizeSmall);
                                    RequestOptions requestOptions = new RequestOptions().dontAnimate().placeholder(R.drawable.emoji_default).override(size, size);
                                    Glide.with(getContext()).load(emojiIcon.getLocalPath()).apply(requestOptions).into(new SimpleTarget<Drawable>(size, size) {
                                        @Override
                                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                            resource.setBounds(0, 0, size, size);
                                            ImageSpan imageSpan = new ImageSpan(resource, ImageSpan.ALIGN_BASELINE);
                                            ImageSpan[] spans = editable.getSpans(start + emojiStart, start + emojiEnd, ImageSpan.class);
                                            for (ImageSpan span : spans) {
                                                ((Editable) s).removeSpan(span);
                                            }
                                            editable.setSpan(imageSpan, start + emojiStart, start + emojiEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                            mEmojiIcons.add(emojiIcon);
                                        }

                                        @Override
                                        public void onLoadStarted(@Nullable Drawable placeholder) {
                                            super.onLoadStarted(placeholder);
                                            if (placeholder != null) {
                                                placeholder.setBounds(0, 0, size, size);
                                                ImageSpan imageSpan = new ImageSpan(placeholder, ImageSpan.ALIGN_BASELINE);
                                                ImageSpan[] spans = editable.getSpans(start + emojiStart, start + emojiEnd, ImageSpan.class);
                                                for (ImageSpan span : spans) {
                                                    editable.removeSpan(span);
                                                }
                                                editable.setSpan(imageSpan, start + emojiStart, start + emojiEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                                mEmojiIcons.add(emojiIcon);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 绑定表情面板
     *
     * @param emojiPanel 表情面板
     */
    public void bindEmojiPanel(EmojiPanel emojiPanel) {
        mEmojiPanel = emojiPanel;
        emojiPanel.addOnEmojiOperateListener(new EmojiPanel.OnEmojiOperateListener() {
            @Override
            public void onEmojiClick(int position, EmojiIcon emojiIcon, EmojiCategory category) {
                appendEmoji(emojiIcon);
            }

            @Override
            public void onDeleteClick() {
                deleteOne();
            }
        });
    }

    /**
     * 添加表情
     *
     * @param emojiIcon 表情
     */
    public void appendEmoji(final EmojiIcon emojiIcon) {
        append(emojiIcon.getName());
    }

    /**
     * 清空内容
     */
    public void clear() {
        mEmojiIcons.clear();
        setText("");
    }

    /**
     * 按下删除键一次
     */
    public void deleteOne() {
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
        dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_DEL && !mEmojiIcons.isEmpty()) {
            EmojiIcon last = mEmojiIcons.getLast();
            Editable text = getText();
            if (last != null && text.toString().endsWith(last.getName())) {
                //如果是表情
                EmojiIcon emojiIcon = mEmojiIcons.removeLast();
                text.delete(text.length() - emojiIcon.getName().length(), text.length());
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 获取当前内容
     *
     * @return 当前内容
     */
    public String getValueText() {
        Editable text = getText();
        ImageSpan[] spans = text.getSpans(0, text.length(), ImageSpan.class);
        String s = text.toString();
        if (spans != null && mEmojiIcons != null && spans.length == mEmojiIcons.size()) {
            for (EmojiIcon emojiIcon : mEmojiIcons) {
                s = s.replace(emojiIcon.getName(), emojiIcon.getValue());
            }
        } else {
            Log.e(TAG, "something went wrong");
        }
        return text.toString();
    }
}
