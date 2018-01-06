package com.houlin.capstone_project.ui.custom;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.houlin.capstone_project.R;

/**
 * @author houlin
 */

public class LoadingView extends View {

    private Bitmap mBitmap;// 正方形图形
    private Paint mPaint;
    private Rect mRect;
    private int width;
    private int height;
    private PathEffect mPathEffect;
    private int radius;
    private int lineWidth = 10;
    private float degrees;
    private ObjectAnimator mAnimator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRect = new Rect();
        mPathEffect = new DashPathEffect(new float[]{50, 15}, 50);// phase是虚线与起点的偏移
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.loading);
        radius = mBitmap.getWidth() / 2 + 20;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineWidth);

        // 动画
        mAnimator = ObjectAnimator.ofFloat(this, "degrees",
                0, 359);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setDuration(2000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 1. 绘制图片
        int centerX = (width + getPaddingLeft() - getPaddingRight()) / 2;
        int centerY = (height + getPaddingTop() - getPaddingBottom()) / 2;
        // mRect将图片定位在view中央
        mRect.left = centerX - mBitmap.getWidth() / 2;
        mRect.top = centerY - mBitmap.getHeight() / 2;
        mRect.right = mRect.left + mBitmap.getWidth();
        mRect.bottom = mRect.top + mBitmap.getHeight();
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);// src为null则绘制整个图片

        // 2. 绘制圆环
        canvas.save();
        canvas.rotate(degrees, centerX, centerY);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 完全自己计算尺寸，不调用super.onMeasure
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 宽
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + getPaddingRight() + radius * 2 + lineWidth;
        }

        // 高
        mode = MeasureSpec.getMode(heightMeasureSpec);
        size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + getPaddingBottom() + radius * 2 + lineWidth;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            mAnimator.start();
        } else {
            mAnimator.cancel();
        }
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
        invalidate();// 刷新view
    }

}
