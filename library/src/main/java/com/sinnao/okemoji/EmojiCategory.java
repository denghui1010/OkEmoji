package com.sinnao.okemoji;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * 表情类别
 * Created by Liu Denghui on 16/8/8.
 */
public class EmojiCategory {
    private EmojiIcon[] mEmojiIcons;
    private Drawable mCategoryDrawable;
    private int mCategoryDrawableRes = -1;
    private String mCategoryName;
    private int mRowCount, mColumnCount;
    public static final String LOCAL_EMOJI = "local";
    public static final String LOCAL_UNICODE = "unicode";

    public EmojiCategory(String categoryName, Drawable categoryDrawable, EmojiIcon[] emojiIcons, int rowCount, int columnCount) {
        mCategoryName = categoryName;
        mCategoryDrawable = categoryDrawable;
        mEmojiIcons = emojiIcons;
        mRowCount = rowCount;
        mColumnCount = columnCount;
    }

    public EmojiCategory(String categoryName, @DrawableRes int categoryDrawableRes, EmojiIcon[] emojiIcons, int rowCount, int columnCount) {
        mCategoryName = categoryName;
        mCategoryDrawableRes = categoryDrawableRes;
        mEmojiIcons = emojiIcons;
        mRowCount = rowCount;
        mColumnCount = columnCount;
    }

    public int getCategoryDrawableRes() {
        return mCategoryDrawableRes;
    }

    public void setCategoryDrawableRes(int categoryDrawableRes) {
        mCategoryDrawableRes = categoryDrawableRes;
    }

    public Drawable getCategoryDrawable() {
        return mCategoryDrawable;
    }

    public void setCategoryDrawable(Drawable categoryDrawable) {
        mCategoryDrawable = categoryDrawable;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public EmojiIcon[] getEmojiIcons() {
        return mEmojiIcons;
    }

    public void setEmojiIcons(EmojiIcon[] emojiIcons) {
        mEmojiIcons = emojiIcons;
    }

    public int getRowCount() {
        return mRowCount;
    }

    public void setRowCount(int rowCount) {
        mRowCount = rowCount;
    }

    public int getColumnCount() {
        return mColumnCount;
    }

    public void setColumnCount(int columnCount) {
        mColumnCount = columnCount;
    }
}
