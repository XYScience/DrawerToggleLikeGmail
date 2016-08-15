package com.science.drawertogglelikegmail;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * @author 幸运Science
 * @description
 * @email chentushen.science@gmail.com,274240671@qq.com
 * @data 2016/8/14
 */
public class ActionBarDrawerArrowToggle {

    private FragmentActivity mActivity;
    private DrawerLayout mDrawerLayout;
    private ValueAnimator mValueAnimator;
    private DrawerToggle mSlider;

    public ActionBarDrawerArrowToggle(FragmentActivity activity, Toolbar toolbar, DrawerLayout drawerLayout) {
        mActivity = activity;
        mDrawerLayout = drawerLayout;
        mValueAnimator = new ValueAnimator();
        animDrawerArrow();
        mSlider = new DrawerArrowDrawableToggle(toolbar.getContext());
        mSlider.setPosition(0);
        toolbar.setNavigationIcon((Drawable) mSlider);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle(true);
            }
        });
    }

    public void toggle() {
        toggle(false);
    }

    /**
     * ActionBarDrawerArrow开关控制
     *
     * @param isOpenDrawer 是否打开DrawerLayout
     */
    private void toggle(boolean isOpenDrawer) {
        if (mSlider.getPosition() == 0) {
            if (isOpenDrawer) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            } else {                            // 汉堡->箭头
                mValueAnimator.setFloatValues(0, 1);
                mValueAnimator.start();
            }
        } else if (mSlider.getPosition() == 1) { // 箭头->汉堡
            mValueAnimator.setFloatValues(1, 0);
            mValueAnimator.start();
            mActivity.getSupportFragmentManager().popBackStack();
        }
    }

    private void animDrawerArrow() {
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                mSlider.setPosition(slideOffset);
            }
        });
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.setDuration(350);
    }

    public float getPosition() {
        return mSlider.getPosition();
    }

    static class DrawerArrowDrawableToggle extends DrawerArrowDrawable implements DrawerToggle {

        public DrawerArrowDrawableToggle(Context themedContext) {
            super(themedContext);
        }

        // 设置菜单按钮状态，0：汉堡；1：箭头
        public void setPosition(float position) {
            if (position == 1f) {
                setVerticalMirror(true);
            } else if (position == 0f) {
                setVerticalMirror(false);
            }
            setProgress(position);
        }

        public float getPosition() {
            return getProgress();
        }
    }

    /**
     * Interface for toggle drawables. Can be public in the future
     */
    static interface DrawerToggle {

        public void setPosition(float position);

        public float getPosition();
    }

    public void onDestroy() {
        mValueAnimator = null;
        mSlider = null;
    }
}
