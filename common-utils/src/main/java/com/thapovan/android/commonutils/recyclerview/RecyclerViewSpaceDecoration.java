package com.thapovan.android.commonutils.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class RecyclerViewSpaceDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public RecyclerViewSpaceDecoration(int space) {
        this.space = space;
    }

    public RecyclerViewSpaceDecoration(Context context){
        this.space = dipToPixels(context,15f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = space;
        }
    }

    private int dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
