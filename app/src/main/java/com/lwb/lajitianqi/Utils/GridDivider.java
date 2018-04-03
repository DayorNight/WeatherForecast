package com.lwb.lajitianqi.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lwb on 2018/4/3.
 */
public class GridDivider extends RecyclerView.ItemDecoration {

    private Drawable mDividerDarwable;
    private int mDividerHight = 1;
    private Paint mColorPaint;


    public final int[] ATRRS = new int[]{android.R.attr.listDivider};

    public GridDivider(Context context) {
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDividerDarwable = ta.getDrawable(0);
        ta.recycle();
    }

    /*
     int dividerHight 分割线的线宽
     int dividerColor 分割线的颜色
     */
    public GridDivider(Context context, int dividerHight, int dividerColor) {
        this(context);
        mDividerHight = dividerHight;
        mColorPaint = new Paint();
        mColorPaint.setColor(dividerColor);
    }

    /*
     int dividerHight 分割线的线宽
     Drawable dividerDrawable 图片分割线
     */
    public GridDivider(Context context, int dividerHight, Drawable dividerDrawable) {
        this(context);
        mDividerHight = dividerHight;
        mDividerDarwable = dividerDrawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //画水平和垂直分割线
        drawHorizontalDivider(c, parent);
        drawVerticalDivider(c, parent);
    }

    public void drawVerticalDivider(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;

            int left = 0;
            int right = 0;

            //左边第一列
            if ((i % 3) == 0) {
                //item左边分割线
                left = child.getLeft();
                right = left + mDividerHight;
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
                if (mColorPaint != null) {
                    c.drawRect(left, top, right, bottom, mColorPaint);
                }
                //item右边分割线
                left = child.getRight() + params.rightMargin - mDividerHight;
                right = left + mDividerHight;
            } else {
                //非左边第一列
                left = child.getRight() + params.rightMargin - mDividerHight;
                right = left + mDividerHight;
            }
            //画分割线
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }

        }
    }

    public void drawHorizontalDivider(Canvas c, RecyclerView parent) {

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin - mDividerHight;
            final int right = child.getRight() + params.rightMargin;
            int top = 0;
            int bottom = 0;

            // 最上面一行
            if ((i / 3) == 0) {
                //当前item最上面的分割线
                top = child.getTop();
                //当前item下面的分割线
                bottom = top + mDividerHight;
                mDividerDarwable.setBounds(left, top, right, bottom);
                mDividerDarwable.draw(c);
                if (mColorPaint != null) {
                    c.drawRect(left, top, right, bottom, mColorPaint);
                }
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerHight;
            } else {
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerHight;
            }
            //画分割线
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }
        }
    }
}