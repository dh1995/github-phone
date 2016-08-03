package com.feicuiedu.gitdroid.favorite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.feicuiedu.gitdroid.favorite.model.RepoGroup;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by dh1 on 2016/8/3.
 */
public class DBHelp extends OrmLiteSqliteOpenHelper{

    private static final String TABLE_NAME = "repo_favorite.db";

    private static final int VERSION = 1;

    private static DBHelp dbHelp;

    public static synchronized DBHelp getInstance(Context context){
        if (dbHelp == null){
            dbHelp = new DBHelp(context.getApplicationContext());
        }
        return dbHelp;
    }
    private Context context;

    public DBHelp(Context context) {
        super(context, TABLE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //创建表
        try {
            TableUtils.createTableIfNotExists(connectionSource, RepoGroup.class);
            //将本地的默认数据添加到表里
            new RepoGroupDao(this).createOrUpdate(RepoGroup.getDefaultGroups(context));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,RepoGroup.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
