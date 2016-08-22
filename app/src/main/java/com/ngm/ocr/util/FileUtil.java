package com.ngm.ocr.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.ngm.ocr.AppContext;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private static final String TAG = "FileUtil";
    private static final File parentPath = Environment.getExternalStorageDirectory();
    private static final String DST_FOLDER_NAME = "PlayCamera";
    public static String jpegName;
    private static String storagePath = "";

    /**
     * 初始化保存路径
     *
     * @return
     */
    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = parentPath.getAbsolutePath() + "/" + DST_FOLDER_NAME;
            File f = new File(storagePath);
            if (!f.exists()) {
                f.mkdir();
            }
        }
        return storagePath;
    }

    /**
     * 保存Bitmap到sdcard
     *
     * @param b
     */
    public static void saveBitmap(Bitmap b) {

        String path = initPath();
        jpegName = path + "/" + AppContext.IMAGENAME;
        Log.i(TAG, "saveBitmap:jpegName = " + jpegName);
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            Log.i(TAG, "saveBitmap成功");
        } catch (IOException e) {
            Log.i(TAG, "saveBitmap:失败");
            e.printStackTrace();
        }
    }

    public static String savefile(Uri uri) {
        String sourceFilename = uri.getPath();
        long dataTake = System.currentTimeMillis();
        String destinationFilename = android.os.Environment.getExternalStorageDirectory().getPath() + File.separatorChar + "OCR" + File.separatorChar + "img" + File.separatorChar + dataTake + ".jpg";
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return destinationFilename;
    }
}
