package com.lwb.lajitianqi.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;


/**
 * Created by lwb on 2017/6/17.
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        MigrationHelper.migrate(db,TestDataDao.class,TestData2Dao.class,TestData3Dao.class);
//        MigrationHelper.migrate(db,UserBeanDao.class);//把所有要迁移的数据表都填加进去  Dao结尾文件
    }
}
