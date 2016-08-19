package com.ngm.ocr.ocr;

import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.ngm.ocr.AppContext;

public class TesstwoOCR {

    private static final String TAG = "TesstwoOCR";

    private TessBaseAPI ocr_eng;
    private TessBaseAPI ocr_chi;

    public TesstwoOCR() {
        Log.v(TAG, "BaseApi initializing...");

        ocr_eng = new TessBaseAPI();
        ocr_eng.setDebug(true);
        ocr_eng.init(AppContext.DATA_PATH, AppContext.LANG_EN);

//        ocr_chi = new TessBaseAPI();
//        ocr_chi.setDebug(true);
//        ocr_chi.init(AppContext.DATA_PATH, AppContext.LANG_ZH);
    }

    public String doOCR(Bitmap bitmap, String lang) {

        String result = "";

        if (lang.equals(AppContext.LANG_EN)) {
            ocr_eng.setImage(bitmap);
            result = ocr_eng.getUTF8Text();
        } else if (lang.equals(AppContext.LANG_ZH)) {
            ocr_chi.setImage(bitmap);
            result = ocr_chi.getUTF8Text();
        } else {
            //nothing
        }

        return result.trim();
    }
}
