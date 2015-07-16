package com.palmshe.newcoolweather.utils;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author songx
 * @category utils
 * 基本活动类，与ActivityCollecor配合使用
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ActivityCollector.addActivity(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
}
