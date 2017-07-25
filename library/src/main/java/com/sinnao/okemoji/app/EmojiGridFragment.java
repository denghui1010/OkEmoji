package com.sinnao.okemoji.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sinnao.okemoji.EmojiCategory;
import com.sinnao.okemoji.EmojiIcon;
import com.sinnao.okemoji.R;
import com.sinnao.okemoji.view.CategoryItem;
import com.sinnao.okemoji.view.EmojiGridView;
import com.sinnao.okemoji.view.PointLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示表情的Fragment
 * Created by Liu Denghui on 16/7/29.
 */
public class EmojiGridFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private List<EmojiCategory> mCategories;
    private ViewPager mCategoryViewPager;
    private LinearLayout mCategoryRoot;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private OnEmojiOperateListener mOnEmojiOperateListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_emoji_grid, container);
        mCategoryViewPager = (ViewPager) view.findViewById(R.id.fragEmojiViewPager);
        ImageButton emojiDeleteBtn = (ImageButton) view.findViewById(R.id.fragEmojiDelete);
        emojiDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmojiOperateListener != null) {
                    mOnEmojiOperateListener.onDeleteClick();
                }
            }
        });
        mCategoryPagerAdapter = new CategoryPagerAdapter();
        mCategoryViewPager.setAdapter(mCategoryPagerAdapter);
        mCategoryViewPager.addOnPageChangeListener(this);
        mCategoryRoot = (LinearLayout) view.findViewById(R.id.fragEmojiCategory);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mCategoryRoot.getChildCount(); i++) {
            CategoryItem item = (CategoryItem) mCategoryRoot.getChildAt(i);
            item.setSelected(i == position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public OnEmojiOperateListener getOnEmojiOperateListener() {
        return mOnEmojiOperateListener;
    }

    public void setOnEmojiOperateListener(OnEmojiOperateListener onEmojiOperateListener) {
        mOnEmojiOperateListener = onEmojiOperateListener;
    }

    /**
     * 添加一个类别的表情
     *
     * @param category 类别
     */
    public void addCategory(EmojiCategory category) {
        if (mCategories == null) {
            mCategories = new ArrayList<>();
        }
        mCategories.add(category);
        mCategoryPagerAdapter.notifyDataSetChanged();
        final CategoryItem categoryItem = new CategoryItem(getContext());
        if (category.getCategoryDrawableRes() != -1) {
            categoryItem.setCategoryImage(category.getCategoryDrawableRes());
        } else {
            categoryItem.setCategoryImage(category.getCategoryDrawable());
        }
        categoryItem.setTag(mCategories.size() - 1);
        categoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                mCategoryViewPager.setCurrentItem(tag);
            }
        });
        if (mCategoryRoot.getChildCount() == 0) {
            categoryItem.setSelected(true);
        }
        mCategoryRoot.addView(categoryItem);
    }

    /**
     * 表情操作监听
     */
    public interface OnEmojiOperateListener {
        /**
         * 当表情被点击时会被调用
         *
         * @param position  表情所处分类中的位置
         * @param emojiIcon 表情
         * @param category  表情所属分类
         */
        void onEmojiClick(int position, EmojiIcon emojiIcon, EmojiCategory category);

        /**
         * 删除键被点击时会被调用
         */
        void onDeleteClick();
    }

    private class CategoryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mCategories == null ? 0 : mCategories.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_category_viewpager_item, container, false);
            ViewPager emojiViewPager = (ViewPager) view.findViewById(R.id.emojiViewpager);
            PointLayout pointLayout = (PointLayout) view.findViewById(R.id.emojiPointLayout);
            EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(mCategories.get(position));
            emojiViewPager.setAdapter(emojiPagerAdapter);
            pointLayout.setViewPager(emojiViewPager);
            container.addView(view);
            return view;
        }
    }

    private class EmojiPagerAdapter extends PagerAdapter {
        private EmojiIcon[] mEmojiIcons;
        private int mRowCount, mColumnCount;
        private EmojiCategory mEmojiCategory;

        public EmojiPagerAdapter(EmojiCategory category) {
            mEmojiCategory = category;
            mEmojiIcons = category.getEmojiIcons();
            mRowCount = category.getRowCount();
            mColumnCount = category.getColumnCount();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return (int) Math.ceil((double) mEmojiIcons.length / (mRowCount * mColumnCount));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final EmojiGridView emojiGridView = new EmojiGridView(getContext());
            //计算准备GridView的数据
            int pagerCount = mRowCount * mColumnCount;
            int realCount, start, stop;
            if (position == getCount() - 1) {
                //如果是最后一页
                realCount = mEmojiIcons.length - pagerCount * (position);
                start = pagerCount * position;
                stop = start + realCount;
            } else {
                //如果不是最后一页
                realCount = pagerCount;
                start = pagerCount * position;
                stop = start + realCount;
            }
            final EmojiIcon[] emojiIcons = new EmojiIcon[realCount];
            System.arraycopy(mEmojiIcons, start, emojiIcons, 0, stop - start);
            emojiGridView.setData(emojiIcons);
            emojiGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mOnEmojiOperateListener != null) {
                        mOnEmojiOperateListener.onEmojiClick(position, emojiIcons[position], mEmojiCategory);
                    }
                }
            });
            if (mRowCount == 3) {
                emojiGridView.setMode(EmojiGridView.EmojiModeSmall);
            } else if (mRowCount == 2) {
                emojiGridView.setMode(EmojiGridView.EmojiModeLarge);
            }
            container.addView(emojiGridView);
            return emojiGridView;
        }
    }
}
