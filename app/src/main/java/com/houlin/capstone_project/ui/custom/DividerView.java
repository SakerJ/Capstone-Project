package com.houlin.capstone_project.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.houlin.capstone_project.R;
import com.houlin.capstone_project.utils.ScreenUtils;

/**
 * 上下带有阴影效果的分隔区
 * Created by houlin on 2017/12/5.
 */

public class DividerView extends View {

    public DividerView(Context context) {
        super(context);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint mPaint;
    private float line;

    {
        line = ScreenUtils.dp2px(1);// 阴影为1dp

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        // 上下阴影线
        mPaint.setColor(Color.rgb(240, 240, 240));
        mPaint.setStrokeWidth(line);
        canvas.drawLine(0, line / 2, getWidth(), line / 2, mPaint);
        canvas.drawLine(0, getHeight() - line / 2, getWidth(), getHeight() - line / 2, mPaint);

        // 填充剩余空间
        mPaint.setColor(getResources().getColor(R.color.divider));
        canvas.drawRect(0, line, getWidth(), getHeight() - line, mPaint);
    }
}
