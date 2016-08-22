package com.ngm.ocr;

import android.app.Application;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.ngm.ocr.gen.DaoMaster;
import com.ngm.ocr.gen.DaoSession;

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
    public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/" + ALBUM + "/";
    private static final String TESSDATA = "tessdata";
    private static final String DATA_PATH_TESSDATA = DATA_PATH + TESSDATA + "/";
    public static String LANG_EN = "eng";
    public static String LANG_ZH = "chi_sim";
    public static String lang;

    public String type;

    private String TAG = AppContext.class.getSimpleName();

    private static AppContext app;

    public AppContext() {
        app = this;
    }

    public static synchronized AppContext getInstance() {
        if (app == null) {
            app = new AppContext();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        // check and copy files
        checkAndCopyFiles();

        setDatabase();
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


    /**
     * 设置greenDao
     */
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
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
