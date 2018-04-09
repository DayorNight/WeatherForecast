package com.lwb.lajitianqi;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lwb.lajitianqi.Utils.ImageLoaderUtil;
import com.lwb.lajitianqi.Utils.SettingUtil;
import com.lwb.lajitianqi.gen.DaoMaster;
import com.lwb.lajitianqi.gen.DaoSession;
import com.lwb.lajitianqi.gen.MySQLiteOpenHelper;



/**Application示例
 * @use extends BaseApplication
 */
public class BaseApplication extends Application {
	private static final String TAG = "BaseApplication";
	private SQLiteDatabase db;
	private DaoMaster mDaoMaster;
	private DaoSession mDaoSession;
	private static BaseApplication instances;
	private MySQLiteOpenHelper mHelper;
	private static RequestQueue queues;
	private static LocalBroadcastManager localBroadcast;

	@Override
	public void onCreate() {
		super.onCreate();
		instances = this;
		System.out.println("项目启动");
		ImageLoaderUtil.init(instances);
		SettingUtil.init(instances);
		setDatabase();
//		全局请求对例
		queues = Volley.newRequestQueue(getApplicationContext());

		initBrodCast();
	}

	private void initBrodCast() {
		localBroadcast = LocalBroadcastManager.getInstance(this);
	}

	public static LocalBroadcastManager getLocalBroadcast(){
		return localBroadcast;
	}

	public static RequestQueue getHttpQueues(){
		return queues;
	}

	public static BaseApplication getInstances(){
		return instances;
	}

	public String getAppName() {
		return getResources().getString(R.string.app_name);
	}

	/**
	 * 应用包名，在AndroidManifest文件内申明
	 */
	public static String PACKAGE_NAME = "zuo.biao.library";

	/**
	 * 作为launcher的activity是否活着（已启动且未被销毁）
	 */
	public static boolean isMainActivityAlive = false;

	/**
	 * 设置greenDao
	 */
	private void setDatabase() {
		// 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
		// 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
		// 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
		// 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
		mHelper = new MySQLiteOpenHelper(this, "test.db",null);
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
}

