package com.cocosh.wap.api;

import com.cocosh.framework.util.StringUtil;



public class HttpSenderTest {
	public static void main(String[] args) {

		String code = StringUtil.getRandom(3);
		String url = "http://sms.253.com/msg/";// 应用地址
		String un = "N4276342";// 账号
		String pw = "N2GZ1L8Bfic2";// 密码
		String phone = "15038330254";// 手机号码，多个号码使用","分割
		String msg = "【253云通讯】您好，您的验证码是"+code;;// 短信内容
		String rd = "1";// 是否需要状态报告，需要1，不需要0
		String ex = null;// 扩展码
		
		try {
			String returnString = HttpSender.batchSend(phone,msg);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
}