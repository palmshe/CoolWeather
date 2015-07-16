package com.palmshe.newcoolweather.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * @author songx
 * @category utils
 * 获取网络信息工具
 */
public class NetUtils {
	
	/**
	 * 查看网络，需要查看网络状态权限
	 */
	public static boolean checkNet(Context context){
		//判断：wifi连接
		boolean isWifi= isWifiConnection(context);
		//判断：mobile连接，如果是mobile连接就需要继续判断是哪个APN被选中
		boolean isMobile= isMobileConnection(context);
		if (isMobile) {
			readAPN(context);
		}
		
		if (!isMobile&&!isWifi) {
			//TODO 弹出网络设置对话框
			return false;
		}
		return true;
	}

	/**
	 * APN连接，查看APN中的数据，是wap连接，还是net连接
	 * @param context
	 * @return port and proxy 
	 * @throws SecurityException 权限屏蔽
	 */
	private static String readAPN(Context context) throws SecurityException{
		/* 
		 * 1.查看系统源码得到 
		 * 2.4.0模拟器屏蔽了该权限，经测试android.permission.WRITE_APN_SETTINGS权限在4.0以上的机型已经被屏蔽了，即使屏蔽了编译器的错误，但是真机可以访问
		 * 3.需要最新的系统应用源码
		 * 4.测试阶段不需要捕捉异常，测试过后应添加异常处理
		 */
		Uri preferredApnUri= Uri.parse("content://telephony/carriers/preferapn");
		ContentResolver cr= context.getContentResolver();
		Cursor cursor= cr.query(preferredApnUri, null, null, null, null);//因为是单选按钮，只会有一个返回值
		if (cursor!= null&& cursor.moveToFirst()) {
			String proxy= cursor.getString(cursor.getColumnIndex("proxy"));
			int port= cursor.getInt(cursor.getColumnIndex("port"));
			return  "proxy="+ proxy+"&port="+ port;
		}
		return null;
	}

	/**
	 * 判断是否为移动连接
	 * @param context
	 * @return
	 */
	private static boolean isMobileConnection(Context context) {
		ConnectivityManager cm= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni= cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (ni!= null) {
			return ni.isConnected();
		}
		return false;
	}

	/**
	 * 判断是否为wifi连接
	 * @param context
	 * @return
	 */
	private static boolean isWifiConnection(Context context) {
		ConnectivityManager cm= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni= cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (ni!= null) {
			return ni.isConnected();
		}
		return false;
	}
}
