package com.ngm.ocr.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ngm.ocr.R;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by zhy on 15/12/8.
 */
public class AutoCardView extends CardView {

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoCardView(Context context) {
        super(context);
    }

    public AutoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init(attrs);
    }

    public AutoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public AutoFrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoFrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int selectbg;
    int _selectbg;

    public void init(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.AutoCardView);
        TypedArray t2 = getContext().obtainStyledAttributes(attrs, R.styleable.CardView);
        selectbg = t.getColor(R.styleable.AutoCardView_selectbg, 0x00000000);
        _selectbg = t2.getColor(R.styleable.CardView_cardBackgroundColor, 0x00000000);
        this.setOnTouchListener(touch);
    }

    View.OnTouchListener touch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = MotionEventCompat.getActionMasked(event);
            AutoCardView cardView = (AutoCardView) v;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    cardView.setCardBackgroundColor(selectbg);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    return false;
                case MotionEvent.ACTION_UP:
                    cardView.setCardBackgroundColor(_selectbg);
                    return true;
            }
            return false;
        }
    };

}