package com.example.projectbut;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int dividerHeight;
    private Drawable divider;

    public DividerItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        divider = a.getDrawable(0);

        dividerHeight = divider.getIntrinsicHeight();
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){
        super.onDraw(c, parent, state);

        final int lineLeft = parent.getPaddingLeft();
        final int lineRight = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for(int i=0; i<childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int childTransitionY = Math.round(ViewCompat.getTranslationY(child));
            final int top = child.getBottom() + params.bottomMargin + childTransitionY;
            final int bottom = top + dividerHeight;

            divider.setBounds(lineLeft, top, lineRight, bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        outRect.set(0, 0, 0, dividerHeight);
    }
}

