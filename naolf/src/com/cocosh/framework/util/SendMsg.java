package com.cocosh.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SendMsg {
	private final static String CORPID="V02572";//账户名
	private final static String PWD="696989";//密码
	/*
	 * 发送方法  其他方法同理
	 */
	public static int sendSMS(String mobile,String content){
		try {
			content=URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");//发送内容
			URL url = new URL("http://www.512688.com/WS/Send.aspx?CorpID="+CORPID+"&Pwd="+PWD+"&Mobile="+mobile+"&Content="+content);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			int inputLine = new Integer(in.readLine()).intValue();
			return inputLine;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -9;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.out.println(new SendMsg().sendSMS("15800930334","尊敬的用户：您的验证码是"+StringUtil.getRandom(6)+"【椰果网络】"));
	}

}
