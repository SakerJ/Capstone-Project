package com.houlin.capstone_project.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by houlin on 2017/12/7.
 */

public class RecyclerUtils {

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

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
}
