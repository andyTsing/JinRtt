package test.bwie.com.jinrtt;


import android.app.Application;

import org.xutils.x;

import test.bwie.com.jinrtt.topnewgrid.db.SQLHelper;
import test.bwie.com.jinrtt.utilcode.Utils;

public class AppApplication extends Application {
	private static AppApplication mAppApplication;
	private SQLHelper sqlHelper;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		x.Ext.init(this);
		x.Ext.setDebug(false);
		Utils.init(this);
		mAppApplication = this;
	}

	/** 获取Application */
	public static AppApplication getApp() {
		return mAppApplication;
	}

	/** 获取数据库Helper */
	public SQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new SQLHelper(mAppApplication);
		return sqlHelper;
	}

	/** 摧毁应用进程时候调用 */
	public void onTerminate() {
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
	}

	public void clearAppCache() {
	}
}
