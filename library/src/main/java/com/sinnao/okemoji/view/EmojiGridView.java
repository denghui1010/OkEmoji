package com.sinnao.okemoji.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sinnao.okemoji.EmojiIcon;
import com.sinnao.okemoji.R;

/**
 * 表情GridView, 有两种尺寸模式, small 3*7 large 2*4
 * Created by Liu Denghui on 16/7/30.
 */
public class EmojiGridView extends GridView {
    public static final int EmojiModeSmall = 0;
    public static final int EmojiModeLarge = 1;
    private EmojiIcon[] mEmojiIcons;
    private int mEmojiMode = EmojiModeSmall;
    private PopupWindow mPopupWindow;
    private MyAdapter mMyAdapter;

    public EmojiGridView(Context context) {
        super(context);
        init();
    }

    public EmojiGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmojiGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        final EmojiShowView emojiShowView = new EmojiShowView(getContext());
        int emojiShowSmall = getResources().getDimensionPixelOffset(R.dimen.emojiShowSmall);
        mPopupWindow = new PopupWindow(emojiShowView, emojiShowSmall, emojiShowSmall);
        setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int[] location = new int[2];
                view.getLocationInWindow(location);
                emojiShowView.setEmoji(mEmojiIcons[position]);
                mPopupWindow.showAtLocation(view, Gravity.START | Gravity.TOP, location[0] + (view.getWidth() - mPopupWindow.getWidth()) / 2, location[1] - mPopupWindow.getHeight() - 10);
                return false;
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (mPopupWindow.isShowing() && (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)) {
                    mPopupWindow.dismiss();
                }
                return false;
            }
        });
        mMyAdapter = new MyAdapter();
        setAdapter(mMyAdapter);
    }

    public void setMode(int mode) {
        mEmojiMode = mode;
        if (mEmojiMode == EmojiModeSmall) {
            setNumColumns(7);
        } else if (mEmojiMode == EmojiModeLarge) {
            setNumColumns(4);
        }
    }

    public void setData(EmojiIcon[] emojiIcons) {
        mEmojiIcons = emojiIcons;
        mMyAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mEmojiIcons == null ? 0 : mEmojiIcons.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_emoji_item, parent, false);
            final ImageView imageView = (ImageView) view.findViewById(R.id.emojiItemImageView);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            ViewGroup.LayoutParams viewLayoutParams = view.getLayoutParams();
            if (mEmojiMode == EmojiModeSmall) {
//                ViewGroup.LayoutParams itemRoot = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeight() / 3);
                viewLayoutParams.width = -1;
                viewLayoutParams.height = getHeight()/3;
                view.setLayoutParams(viewLayoutParams);
                int size = getResources().getDimensionPixelOffset(R.dimen.emojiSizeSmall);
                layoutParams.width = size;
                layoutParams.height = size;
                imageView.setLayoutParams(layoutParams);
            } else {
//                ViewGroup.LayoutParams itemRoot = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeight() / 2);
                viewLayoutParams.width = -1;
                viewLayoutParams.height = getHeight()/2;
                view.setLayoutParams(viewLayoutParams);
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                imageView.setLayoutParams(layoutParams);
            }
            EmojiIcon emojiIcon = mEmojiIcons[position];
            final RequestOptions requestOptions = new RequestOptions();
            requestOptions.dontAnimate();
            requestOptions.placeholder(R.drawable.emoji_default);
            if(emojiIcon.getResId() != 0) {
//                Glide.with(getContext()).load(emojiIcon.getResId()).apply(requestOptions).into(imageView);
            } else if(emojiIcon.getLocalPath() != null){
                int size = getResources().getDimensionPixelOffset(R.dimen.emojiSizeSmall);
//                if(emojiIcon.getLocalPath().equals("file:///android_asset/emoji/emoji_01.png")){
                    Glide.with(getContext()).load(emojiIcon.getLocalPath()).apply(requestOptions).into(new SimpleTarget<Drawable>(size,size) {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            resource.setBounds(0,0,90,90);
                            imageView.setImageDrawable(resource);
                        }

                        @Override
                        public void onLoadStarted(@Nullable Drawable placeholder) {
                            super.onLoadStarted(placeholder);
                            if(placeholder != null) {
                                placeholder.setBounds(0, 0, 90, 90);
                                imageView.setImageDrawable(placeholder);
                            }
                        }
                    });
//                }
            }
            return view;
        }
    }
}
