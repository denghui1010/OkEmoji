package com.sinnao.okemoji.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinnao.okemoji.R;


/**
 * 表情类别, 可以显示一个分类的图标和名称
 * Created by Liu Denghui on 16/8/1.
 */
public class CategoryItem extends RelativeLayout {

    private ImageView mCategoryImage;
    private TextView mCategoryText;

    public CategoryItem(Context context) {
        super(context);
        init();
    }

    public CategoryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CategoryItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CategoryItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_categoty_item, this);
        mCategoryImage = (ImageView) findViewById(R.id.categoryItemImage);
        mCategoryText = (TextView) findViewById(R.id.categoryItemText);
    }

    public void setCategoryImage(Drawable drawable) {
        mCategoryImage.setImageDrawable(drawable);
    }

    public void setCategoryImage(@DrawableRes int resId) {
        mCategoryImage.setImageResource(resId);
    }

    public void setCategoryText(String text) {
        mCategoryText.setText(text);
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            setBackgroundColor(Color.parseColor("#eeeeee"));
        } else {
            setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
