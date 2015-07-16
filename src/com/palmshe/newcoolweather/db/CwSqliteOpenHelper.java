package com.palmshe.newcoolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * @author songx
 * CoolWeather数据库帮助类
 */
public class CwSqliteOpenHelper extends SQLiteOpenHelper {
	
	private static final String CREATE_PROVINCE= "create table Province (create table Province (id integer primary key autoincrement, province_name varchar, province_id integer)";
	private static final String CREATE_CITY= "create table City (id integer primary key autoincrement, city_name varchar, city_code integer, province_id integer)";
	private static final String CREATE_COUNTY= "create table County (id integer primary key autoincrement, county_name varchar, county_code integer, city_id integer)";

	private Context context;
	
	public CwSqliteOpenHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);//构造函数只能在首行
		this.context= context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
		Toast.makeText(context, "db create successfully", Toast.LENGTH_SHORT).show();//TODO  need delete
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists Province");
		db.execSQL("drop table if exists City");
		db.execSQL("drop table if exists County");
		onCreate(db);
		Toast.makeText(context, "db update successfully", Toast.LENGTH_SHORT).show();//TODO need delete
	}

}
