package com.houlin.capstone_project.ui.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.houlin.capstone_project.R;

/**
 * 向左偏移一个屏幕的宽度，开始画波浪，相当于两个屏幕的波浪
 * 动画重复向右移动，距离为屏幕的宽度。即可出现波浪效果
 *
 * @author houlin
 */

public class FooterWave extends View {

    private int waveHeight = 100;
    private int waveOffset = 50;// 波动高度
    private float translateX;// canvas右移距离
    private float width;
    private float height;
    private int waveColor = getResources().getColor(R.color.colorPrimary);

    private Paint mPaint;
    private Path mPath;

    public FooterWave(Context context) {
        this(context, null);
    }

    public FooterWave(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterWave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.FooterWave, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.FooterWave_waveHeight:
                    waveHeight = typedArray.getDimensionPixelSize(index, waveHeight);
                    break;
                case R.styleable.FooterWave_waveOffset:
                    waveOffset = typedArray.getDimensionPixelSize(index, waveOffset);
                    break;
                case R.styleable.FooterWave_waveColor:
                    waveColor = typedArray.getColor(index, waveColor);
                    break;
            }
        }
        typedArray.recycle();

        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initPath();
        startAnim();
    }

    private void initPath() {
        float waveY = height - waveHeight;// 波浪与原点的距离
        mPath.moveTo(-width, waveY);
        mPath.quadTo(-width * 3 / 4, waveY - waveOffset, -width / 2, waveY);
        mPath.quadTo(-width / 4, waveY + waveOffset, 0, waveY);

        mPath.quadTo(width / 4, waveY - waveOffset, width / 2, waveY);
        mPath.quadTo(width * 3 / 4, waveY + waveOffset, width, waveY);
        // 封口
        mPath.lineTo(width, height);
        mPath.lineTo(-width, height);
        mPath.close();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(waveColor);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(translateX, 0);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, width);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                translateX = (float) animation.getAnimatedValue();
                invalidate();// 更新视图
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000).start();
    }
}
