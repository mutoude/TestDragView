package com.example.caixiaolei.testdragview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * ...
 * Created by caixiaolei on 2017/11/1.
 */

public class DragView extends LinearLayout {

    private ViewDragHelper.Callback callback;
    private ViewDragHelper viewDragHelper;

    public DragView(Context context) {
        super(context);
        initDragViewHelper();
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDragViewHelper();
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDragViewHelper();
    }

    private void initDragViewHelper() {

        callback = new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftPadding = getPaddingLeft();
                final int rightPadding = getWidth() - child.getWidth() - leftPadding;
                final int newLeft = Math.min(Math.max(left, leftPadding), rightPadding);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                final int topPadding = getPaddingTop();
                final int bottomPadding = getHeight() - child.getHeight() - topPadding;
                final int newTop = Math.min(Math.max(top, topPadding), bottomPadding);
                return newTop;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {//拖动结束后
                super.onViewReleased(releasedChild, xvel, yvel);

            }

        };

        viewDragHelper = ViewDragHelper.create(DragView.this, 1.0f, callback);

    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
}
