package com.palmshe.newcoolweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.palmshe.newcoolweather.model.City;
import com.palmshe.newcoolweather.model.County;
import com.palmshe.newcoolweather.model.Province;

/**
 * @author songx
 * 数据库操作类
 */
public class CwDBOperator {
	
	private static final int INIT_VERSION= 1;
	private static final String DB_NAME= "cw";
	private SQLiteDatabase db;
	private CwDBOperator dbOperator;
	
	private CwDBOperator(Context context){
		CwSqliteOpenHelper sqliteOpenHelper= new CwSqliteOpenHelper(context, DB_NAME, null, INIT_VERSION);
		db= sqliteOpenHelper.getWritableDatabase();
	}
	
	/**
	 * 通过对私有属性的判断，形成单例
	 * @param context
	 * @return
	 */
	public synchronized CwDBOperator getInstance(Context context){
		if (dbOperator==null) {
			dbOperator= new CwDBOperator(context);
		}
		return dbOperator;
	}
	
	/**
	 * 保存到数据库
	 * @param province
	 * @return
	 */
	public boolean saveProvince(Province province){//此类直接向数据库中操作数据，所有参数应该得到正确数据
		
		if (province!= null) {
			ContentValues content = new ContentValues();
			content.put("province_name", province.getName());
			content.put("province_code", province.getCode());
			long result = db.insert("Provice", null, content);
			content.clear();//ContentValues清空
			if (result> 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取全部Province信息
	 * @return
	 */
	public List<Province> getAllProvinceInfo(){//向上返回可读数据
		Cursor cursor= db.rawQuery("select id, province_name, province_code from Province", null);
		if (cursor!= null&& cursor.moveToFirst()) {
			List<Province> provinceList= new ArrayList<Province>();
			Province province= null;
			do {
				province= new Province();
				province.setId(cursor.getInt(1));//对应列
				province.setName(cursor.getString(2));
				provinceList.add(province);
			} while (cursor.moveToNext());//do-while使用
			cursor.close();//释放cursor
			return provinceList;
		}
		return null;
	}
	
	/**
	 * 保存到数据库
	 * @param province
	 * @return
	 */
	public boolean saveCity(City city){//此类直接向数据库中操作数据，所有参数应该得到正确数据
		
		if (city!= null) {
			ContentValues content = new ContentValues();
			content.put("city_name", city.getName());
			content.put("city_code", city.getCode());
			content.put("province_id", city.getProvinceId());
			long result = db.insert("City", null, content);
			content.clear();//ContentValues清空
			if (result> 0) {
				return true;
			}
		}
		return false;
	}
	
	public List<City> getAllCityInProvince(int provinceId){
		Cursor cursor= db.rawQuery("select id, city_name, city_code from City where province_id= ?", new String []{String.valueOf(provinceId)});
		if (cursor!= null && cursor.moveToFirst()) {
			List<City> cityList= new ArrayList<City>();
			City city= null;
			do {
				city= new City();
				city.setId(cursor.getInt(1));
				city.setName(String.valueOf(cursor.getInt(2)));
				city.setCode(cursor.getInt(3));
				city.setProvinceId(provinceId);
				cityList.add(city);
			} while (cursor.moveToNext());
			cursor.close();
			return cityList;
		}
		return null;
	}
	
	/**
	 * 保存到数据库
	 * @param province
	 * @return
	 */
	public boolean saveCounty(County county){//此类直接向数据库中操作数据，所有参数应该得到正确数据
		
		if (county!= null) {
			ContentValues content = new ContentValues();
			content.put("county_name", county.getName());
			content.put("county_code", county.getCode());
			content.put("city_id", county.getCityId());
			long result = db.insert("County", null, content);
			content.clear();//ContentValues清空
			if (result> 0) {
				return true;
			}
		}
		return false;
	}
	
	public List<County> getAllCountyInCity(int cityId){
		Cursor cursor= db.rawQuery("select id, county_name, county_code from County where city_id= ?", new String []{String.valueOf(cityId)});
		if (cursor!= null && cursor.moveToFirst()) {
			List<County> countyList= new ArrayList<County>();
			County county= null;
			do {
				county= new County();
				county.setId(cursor.getInt(1));
				county.setName(String.valueOf(cursor.getInt(2)));
				county.setCode(cursor.getInt(3));
				county.setCityId(cityId);
				countyList.add(county);
			} while (cursor.moveToNext());
			cursor.close();
			return countyList;
		}
		return null;
	}
}
