package com.example.lisuke.mytest;

import android.app.Application;
import android.os.Environment;
import android.support.design.widget.Snackbar;

import com.example.lisuke.mytest.DBUtils.DatabaseHelper;
import com.example.lisuke.mytest.Model.Hero;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by lisuke on 17-6-15.
 */

public class MyApplication extends Application {

    private static  String iconPath;

    public MyApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initFileSavePath();
        initDatabase();
        initIconPath();
    }

    public static StringBuffer appBasePath = new StringBuffer();
    public static Boolean fileSavePermission = false;

    public static StringBuffer getAppBasePath() {
        return appBasePath;
    }


    void initFileSavePath(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            String base = Environment.getExternalStorageDirectory().getAbsolutePath();


            if (Environment.getExternalStorageDirectory().canWrite())
            {// external sdcard can write
                fileSavePermission = true;
                System.out.println("find external sdcard");
                appBasePath.append(base);
                if (! new File(appBasePath.append("/").append(getResources().getString(R.string.file_path)).toString()).exists()){
                    new File(appBasePath.toString()).mkdir();
                    System.out.println("created");
                }
                System.out.println(appBasePath);
                return;
            }
        }
        /*
        if (!fileSavePermission) {
            if (!getApplicationContext().getFilesDir().exists()) {
                getApplicationContext().getDir("/PVPHelper",MODE_ENABLE_WRITE_AHEAD_LOGGING).mkdirs();
                System.out.println(getApplicationContext().getDir("/PVPHelper",MODE_ENABLE_WRITE_AHEAD_LOGGING));
            }
            System.out.println(basePath);
            //basePath.append(basePath.append(getApplicationContext().getFilesDir().getAbsolutePath()));
        }
        */
        /*
        //test
        File file = new File(basePath+"/test.txt");
        System.out.println(file.getAbsolutePath());
        */
    }

    public static String getDatabasePath() {
        return databasePath;
    }

    public static void setDatabasePath(String databasePath) {
        MyApplication.databasePath = databasePath;
    }

    public static Dao getDao(Class clazz) throws SQLException {
        return databaseHelper.getDao(clazz);
    }

    public static DatabaseHelper databaseHelper = null;
    public static String databasePath = null;

    void initDatabase(){
        databasePath = appBasePath + this.getResources().getString(R.string.database_path);
        databaseHelper = new DatabaseHelper(this.getApplicationContext(),databasePath);
        System.out.println(databasePath);
        /*
        ArrayList<Hero> test = null;
        try {
            Dao heroTest = databaseHelper.getDao(Hero.class);
            test = (ArrayList<Hero>) heroTest.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0;i<test.size();i++){
            System.out.println(test.get(i));
        }
        */
        if (!new File(databasePath).exists()){
            // start download ;

        }
    }

    void initIconPath() {
        iconPath = appBasePath + this.getResources().getString(R.string.icon_path);
        if (!new File(iconPath).exists())
            new File(iconPath).mkdirs();

    }

    public static String getIconPath() {
        return iconPath;
    }
}
