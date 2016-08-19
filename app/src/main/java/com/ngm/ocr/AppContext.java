package com.ngm.ocr;

import android.app.Application;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhangliangwei on 2016/8/18.
 */
public class AppContext extends Application {

    public static final String ALBUM = "OCR";
    public static final String IMAGENAME = "ocr.jpg";
    public static final String DATA_PATH = Environment
            .getExternalStorageDirectory().toString() + "/" + ALBUM + "/";
    private static final String TESSDATA = "tessdata";
    private static final String DATA_PATH_TESSDATA = DATA_PATH + TESSDATA + "/";
    public static String LANG_EN = "eng";
    public static String LANG_ZH = "chi_sim";
    public static String lang;
    private String TAG = AppContext.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        // check and copy files
        checkAndCopyFiles();
    }

    private void init() {
        lang = LANG_EN;
        if (lang.equals(LANG_EN)) {
            Toast.makeText(this, "OCR in " + LANG_EN, Toast.LENGTH_SHORT).show();
        } else if (lang.equals(LANG_ZH)) {
            Toast.makeText(this, "OCR in " + LANG_ZH, Toast.LENGTH_SHORT).show();
        } else {
            // nothing now
        }
    }

    private void checkAndCopyFiles() {

        String[] paths = new String[]{DATA_PATH, DATA_PATH_TESSDATA};
        for (String path : paths) {
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.e(TAG, "ERROR: Creation of directory " + path + " on sdcard failed");
                    return;
                } else {
                    Log.i(TAG, "Created directory " + path + " on sdcard");
                }
            }
        }


        String traineddata_path = DATA_PATH_TESSDATA + lang + ".traineddata";

        String asset_tessdata = "tessdata/" + lang + ".traineddata";

        if (!(new File(traineddata_path)).exists()) {
            try {
                AssetManager assetManager = getAssets();
                InputStream in = assetManager.open(asset_tessdata);
                OutputStream os = new FileOutputStream(traineddata_path);

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    os.write(buf, 0, len);
                }
                in.close();
                os.close();

                Log.v(TAG, "Copied " + lang + " traineddata");
            } catch (IOException e) {
                Log.e(TAG, "unable to copy " + lang + " traineddata " + e.toString());
            }
        }
    }
}
