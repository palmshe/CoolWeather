package com.palmshe.newcoolweather.utils;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

/**
 * @author songx
 * @category utils
 * 活动收集管理器
 */
public class ActivityCollector {
	
	private static List<Activity> activityCollector= new ArrayList<Activity>();
	
	public static void addActivity(Activity act){
		if (act!= null) {
			activityCollector.add(act);
		}else {
			throw new NullPointerException("添加失败，activity为空");
		}
	}
	
	public static void removeActivity(Activity act){
		if (activityCollector.contains(act)) {
			activityCollector.remove(act);
		}else {
			throw new MyException("移除失败，不存在该活动");
		}
	}
	
	public static void finished(){
		for (Activity activity : activityCollector) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}
	
	static class  MyException extends RuntimeException{
		public MyException(){
			super();
		}
		public MyException(String msg){
			super(msg);
		}
	}
}
