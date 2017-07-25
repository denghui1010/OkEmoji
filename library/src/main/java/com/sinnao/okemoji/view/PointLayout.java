package com.sinnao.okemoji.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sinnao.okemoji.R;

/**
 * 显示小圆点用的布局
 * Created by Liu Denghui on 16/7/30.
 */
public class PointLayout extends LinearLayout implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;

    public PointLayout(Context context) {
        super(context);
        init();
    }

    public PointLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PointLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
//        setBackgroundColor(Color.GRAY);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public void setViewPager(ViewPager viewPager) {
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
        }
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        PagerAdapter adapter = mViewPager.getAdapter();
        if (adapter == null) {
            throw new RuntimeException("the ViewPager you set on PointLayout did not have an adapter");
        }
        removeAllViews();
        int count = adapter.getCount();
        if (count == 1) {
            setVisibility(GONE);
        }
        for (int i = 0; i < count; i++) {
            RoundImageView roundImageView = new RoundImageView(getContext());
            if (i == 0) {
                roundImageView.setImageDrawable(new ColorDrawable(Color.GRAY));
            } else {
                roundImageView.setImageDrawable(new ColorDrawable(Color.LTGRAY));
            }
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.pointLayoutHeight);
            LayoutParams layoutParams = new LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            layoutParams.setMargins(dimensionPixelOffset / 2, 0, dimensionPixelOffset / 2, 0);
            roundImageView.setLayoutParams(layoutParams);
            addView(roundImageView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            ImageView roundImageView = (ImageView) getChildAt(i);
            if (i != position) {
                roundImageView.setImageDrawable(new ColorDrawable(Color.LTGRAY));
            } else {
                roundImageView.setImageDrawable(new ColorDrawable(Color.GRAY));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
