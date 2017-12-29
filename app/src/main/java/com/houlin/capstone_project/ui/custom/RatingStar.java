package com.houlin.capstone_project.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.houlin.capstone_project.R;

/**
 * @author houlin
 */

public class RatingStar extends View {

    // TODO: 2017/12/21 onDraw和onMeasure都增加padding，并规范化。如其他标准view

    private Paint mPaint;
    private Path mPath;
    private int radius = 15;
    private int dividerWidth = 5;
    private int starCount = 5;
    private int totalRating = 10;// 总分数
    private float rating = 9;// 评分

    public RatingStar(Context context) {
        this(context, null);
    }

    public RatingStar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingStar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义属性
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.RatingStar, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.RatingStar_starRadius:
                    radius = typedArray.getDimensionPixelSize(index, radius);
                    break;
                case R.styleable.RatingStar_starDividerWidth:
                    dividerWidth = typedArray.getDimensionPixelSize(index, dividerWidth);
                    break;
                case R.styleable.RatingStar_starCount:
                    starCount = typedArray.getInt(index, starCount);
                    break;
            }
        }
        typedArray.recycle();

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.item_text));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

        mPath = new Path();
    }

    public void setRating(float rating) {
        this.rating = rating;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.set(getStarPath(radius));

        // 空心星
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < starCount; i++) {
            canvas.save();
            canvas.translate((dividerWidth + 2 * radius) * i, 0);
            canvas.drawPath(mPath, mPaint);
            canvas.restore();
        }

        // 填充评分
        mPaint.setStyle(Paint.Style.FILL);
        float ratingStar = (float) totalRating / starCount;// 每个星代表的分数
        // 需要填充的星星个数
        int fillCount = (int) (rating % ratingStar == 0 ? rating / ratingStar : rating / ratingStar + 1);
        for (int i = 0; i < fillCount; i++) {
            canvas.save();
            canvas.translate((dividerWidth + 2 * radius) * i, 0);
            float rectWidth;
            if (i == fillCount - 1 && rating != totalRating) {// 最后一颗星且评分不是满分
                rectWidth = (rating % ratingStar / ratingStar) * (2 * radius);
            } else {
                rectWidth = 2 * radius;
            }
            canvas.clipPath(mPath);
            canvas.drawRect(0, 0, rectWidth, 2 * radius, mPaint);
            canvas.restore();
        }

    }

    private Path getStarPath(int radius) {
        Path path = new Path();

        float radian = 36;// 36为五角星的角度
        float radius_in = radius * sin(radian / 2) / cos(radian); // 中间五边形的半径

        path.moveTo(radius * cos(radian / 2), 0);// 此点为多边形的起点
        path.lineTo(radius * cos(radian / 2) + radius_in * sin(radian),
                radius - radius * sin(radian / 2));
        path.lineTo(radius * cos(radian / 2) * 2,
                radius - radius * sin(radian / 2));
        path.lineTo(radius * cos(radian / 2) + radius_in * cos(radian / 2),
                radius + radius_in * sin(radian / 2));
        path.lineTo(radius * cos(radian / 2) + radius * sin(radian),
                radius + radius * cos(radian));
        path.lineTo(radius * cos(radian / 2), radius + radius_in);
        path.lineTo(radius * cos(radian / 2) - radius * sin(radian),
                radius + radius * cos(radian));
        path.lineTo(radius * cos(radian / 2) - radius_in * cos(radian / 2),
                radius + radius_in * sin(radian / 2));
        path.lineTo(0, radius - radius * sin(radian / 2));
        path.lineTo(radius * cos(radian / 2) - radius_in * sin(radian),
                radius - radius * sin(radian / 2));
        path.close();// 使这些点构成封闭的多边形
        return path;
    }

    private float sin(float degree) {
        return (float) Math.sin(degree * Math.PI / 180);
    }

    private float cos(float degree) {
        return (float) Math.cos(degree * Math.PI / 180);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // onDraw中没有计算padding，所以onMeasure也不计算padding
        // 只测量了wrap_content，所以该view需要使用radius和dividerWidth属性来控制宽高
        int width = dividerWidth * (starCount - 1) + 2 * radius * starCount;
        int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);

        int height = radius * 2;
        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
        setMeasuredDimension(widthSpec, heightSpec);
    }
}
