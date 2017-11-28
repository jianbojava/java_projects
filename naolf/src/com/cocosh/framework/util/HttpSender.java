package com.cocosh.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpSender {
	private static final String url = "http://222.73.117.158/msg/";// 应用地址
	private static final String account = "zhixingj1";// 账号
	private static final String pswd = "Tch301040";// 密码
	private static final boolean needstatus = false;// 是否需要状态报告，需要true，不需要false
	private static final String product = null;// 产品ID
	private static final String extno = null;// 扩展码
	
	/**
	 * 
	 * @param url 应用地址，类似于http://ip:port/msg/
	 * @param account 账号
	 * @param pswd 密码
	 * @param mobile 手机号码，多个号码使用","分割
	 * @param msg 短信内容
	 * @param needstatus 是否需要状态报告，需要true，不需要false
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
	public static String send(String mobile, String msg){
		/**(注释掉短信)
		if (StringUtil.isEmpty(mobile)) {
			return null;
		}
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("account", account),
					new NameValuePair("pswd", pswd), 
					new NameValuePair("mobile", mobile),
					new NameValuePair("needstatus", String.valueOf(needstatus)), 
					new NameValuePair("msg", msg),
					new NameValuePair("product", product), 
					new NameValuePair("extno", extno), 
				});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8").split(",")[1];
			} else {
				System.err.println("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		*/
		return null;
	}
	
	public static void main(String[] args) {
		String mobile = "15800930334";// 手机号码，多个号码使用","分割
		String msg = "您好，您的验证码是"+StringUtil.getRandom(6);// 短信内容

		String returnString = HttpSender.send(mobile, msg);
		System.out.println(returnString);
	}
}
