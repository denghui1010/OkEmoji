package com.sinnao.okemoji.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.sinnao.okemoji.EmojiIcon;
import com.sinnao.okemoji.R;

/**
 * 弹出一个窗口来显示表情
 * Created by Liu Denghui on 16/7/29.
 */
public class EmojiShowView extends LinearLayout {

    private ImageView mEmojiShow;

    public EmojiShowView(Context context) {
        super(context);
        init();
    }

    public EmojiShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmojiShowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_emoji_show, this, true);
        mEmojiShow = (ImageView) findViewById(R.id.emojiShowImageView);
    }

    public void setEmoji(EmojiIcon emojiIcon) {
        Glide.with(getContext()).load(emojiIcon.getResId()).into(mEmojiShow);
    }
}
