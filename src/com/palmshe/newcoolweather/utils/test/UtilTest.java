package com.palmshe.newcoolweather.utils.test;

import android.test.AndroidTestCase;
import com.palmshe.newcoolweather.utils.DES;

/**
 * @author Administrator
 * @category utils
 * 工具类测试器
 */
public class UtilTest extends AndroidTestCase {
	
	/**
	 * 测试DES加密key指是否任意
	 * @throws Exception
	 * 测试结果：key值必须是长度为16位字节任意字符串（不能是中文）
	 * 注意：authcode的执行标识
	 */
	public void testDes() throws Exception{
		DES des= new DES();
		String encodeResult= des.authcode("我正在上课。。。", "ENCODE", "1988-10-19111111");
		System.out.println("加密结果为："+ encodeResult);
		String decodeResult= des.authcode("Y/wX/F4kmPb5WzHpPERHVCCFc2bNZWOLSOz2RzS8Wb0=", "DECODE", "1988-10-19111111");
		System.out.println("解密结果为："+ decodeResult);
	}
}
