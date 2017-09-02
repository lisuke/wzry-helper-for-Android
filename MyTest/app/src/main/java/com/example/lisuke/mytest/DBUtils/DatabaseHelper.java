package com.example.lisuke.mytest.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lisuke.mytest.Model.Equip;
import com.example.lisuke.mytest.Model.EquipCate;
import com.example.lisuke.mytest.Model.Hero;
import com.example.lisuke.mytest.Model.HeroCate;
import com.example.lisuke.mytest.Model.HeroEquipSuggest;
import com.example.lisuke.mytest.Model.HeroSkill;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lisuke on 17-6-14.
 */


public  class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
    private static String DatabasePath = "sqlite.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    public DatabaseHelper(Context context)
    {
        this(context, DatabasePath);
    }

    public DatabaseHelper(Context context,String DatabasePath)
    {
        super(context, DatabasePath, null, 4);
    }

    public static String getDatabasePath() {
        return DatabasePath;
    }

    public static void setDatabasePath(String databasePath) {
        DatabasePath = databasePath;
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTableIfNotExists(connectionSource, HeroCate.class);
            TableUtils.createTableIfNotExists(connectionSource, Hero.class);
            TableUtils.createTableIfNotExists(connectionSource, EquipCate.class);
            TableUtils.createTableIfNotExists(connectionSource, Equip.class);
            TableUtils.createTableIfNotExists(connectionSource, HeroEquipSuggest.class);
            TableUtils.createTableIfNotExists(connectionSource, HeroSkill.class);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, HeroCate.class, true);
            TableUtils.dropTable(connectionSource, Hero.class, true);
            TableUtils.dropTable(connectionSource, EquipCate.class, true);
            TableUtils.dropTable(connectionSource, Equip.class, true);
            TableUtils.dropTable(connectionSource, HeroEquipSuggest.class, true);
            TableUtils.dropTable(connectionSource, HeroSkill.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;



    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context)
    {
        context = context.getApplicationContext();
        if (instance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    @Override
    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}