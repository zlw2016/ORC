package com.ngm.ocr.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewCustom extends TextView {

    public TextViewCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewCustom(Context context) {
        super(context);
    }

    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(TypeFaces.getTypeFace(getContext(), "Helvetica.ttf"));
    }

}