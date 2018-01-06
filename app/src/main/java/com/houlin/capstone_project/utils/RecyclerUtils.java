package com.houlin.capstone_project.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.houlin.capstone_project.R;

/**
 * Created by houlin on 2017/12/7.
 */

public class RecyclerUtils {

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        public static final int RECYCLERVIEW_SPACE = 10;
        private int mSpace;

        public SpaceItemDecoration(int space) {
            mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = mSpace;
            }
//            if (parent.getChildAdapterPosition(view) == 0) {
//            outRect.top = mSpace;
//            }
            outRect.right = mSpace;
            outRect.bottom = mSpace;
        }
    }

    public static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Paint mPaint;

        public DividerItemDecoration() {
            mPaint = new Paint();
            mPaint.setStrokeWidth(2);
        }

//        @Override
//        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            super.onDraw(c, parent, state);
//            mPaint.setColor(parent.getContext().getResources().getColor(R.color.item_divider));
//            // left与RecyclerView的paddingLeft对齐，right与RecyclerView的right对齐
////            int left = parent.getPaddingLeft();
////            int right = parent.getRight();
//            int left = parent.getContext().getResources().getDimensionPixelSize(R.dimen.recycler_margin);
//            int right = parent.getRight();
//            for (int i = 0; i < parent.getChildCount(); i++) {
//                View view = parent.getChildAt(i);
//                c.drawLine(left, view.getBottom(), right, view.getBottom(), mPaint);
//            }
//        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            mPaint.setColor(parent.getContext().getResources().getColor(R.color.gray_d4));
            // left与RecyclerView的paddingLeft对齐，right与RecyclerView的right对齐
//            int left = parent.getPaddingLeft();
//            int right = parent.getRight();
            int left = parent.getContext().getResources().getDimensionPixelSize(R.dimen.recycler_margin);
            int right = parent.getRight();
            for (int i = 0; i < parent.getChildCount(); i++) {
                View view = parent.getChildAt(i);
                c.drawLine(left, view.getBottom(), right, view.getBottom(), mPaint);
            }
        }
    }
}
