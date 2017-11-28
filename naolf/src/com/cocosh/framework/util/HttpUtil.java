package com.cocosh.framework.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	/**
	 * IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String connectURL(String url,String dataStr) throws Exception{
		String strResult = "";
//		System.out.println(url+"?"+dataStr);
		
		StringBuffer params = new StringBuffer();
		params.append(dataStr);
		URL console = null;
		HttpURLConnection conn = null;
		console = new URL(url); // 根据数据的发送地址构建URL
		conn = (HttpURLConnection) console.openConnection(); // 打开链接
		conn.setConnectTimeout(30000); // 链接超时设置为30秒
		conn.setReadTimeout(30000); // 读取超时设置30秒
		conn.setRequestMethod("POST"); // 链接相应方式为post
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(params.length()));
		conn.setDoInput(true);

		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
				"UTF-8");
		out.write(params.toString());
		out.flush();
		out.close();

		int code = conn.getResponseCode();
		if (code != 200) {
			System.out.println("ERROR===" + code);
		} else {
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					strResult = strResult
							+ new String(ret.getBytes("ISO-8859-1"), "UTF-8");
				}
			}
		}
		conn.disconnect();
		return strResult;
	}
	
	public static String connectPost(String url,String appkey,String timestamp,String sn,String transJson){
		HttpClient client = new HttpClient();
		PostMethod method = null;
		BufferedReader br=null;
		try {
			method = new PostMethod(url);
			//设置header
			method.setRequestHeader("AppKey", appkey);
			method.setRequestHeader("TimeStamp", timestamp);
			method.setRequestHeader("sn", sn);
			method.setRequestHeader("Content-Type","application/json");
			//设置body数据
			RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
			method.setRequestEntity(se);
			//使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,30000);
            int result=client.executeMethod(method);
            if (result==201) {
            	br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null)br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			method.releaseConnection();
		}
		return null;
	}
	
	public static String connectPut(String url,String appkey,String timestamp,String sn,String transJson){
		HttpClient client = new HttpClient();
		PutMethod method = null;
		BufferedReader br=null;
		try {
			method = new PutMethod(url);
			//设置header
			method.setRequestHeader("AppKey", appkey);
			method.setRequestHeader("TimeStamp", timestamp);
			method.setRequestHeader("sn", sn);
			method.setRequestHeader("Content-Type","application/json");
			//设置body数据
			RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
			method.setRequestEntity(se);
			//使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,30000);
            int result=client.executeMethod(method);
            if (result==201) {
            	br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null)br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			method.releaseConnection();
		}
		return null;
	}
	
	public static String connectGet(String url,String appkey,String timestamp,String sn){
		HttpClient client = new HttpClient();
		GetMethod method = null;
		BufferedReader br=null;
		try {
			method = new GetMethod(url);
			//设置header
			method.setRequestHeader("AppKey", appkey);
			method.setRequestHeader("TimeStamp", timestamp);
			method.setRequestHeader("sn", sn);
			method.setRequestHeader("Content-Type","text/plain");
			//使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,30000);
            int result=client.executeMethod(method);
            if (result==200) {
				br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null)br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			method.releaseConnection();
		}
		return null;
	}
	
	public static JSONObject chargePost(String url,String token,String paramJson){
		HttpClient client = new HttpClient();
		PostMethod method = null;
		try {
			method = new PostMethod(url);
			//设置header
			if (token!=null)method.setRequestHeader("Authorization","Bearer"+token);
			method.setRequestHeader("Content-Type","application/json;charset=utf-8");
			String dataStr=AESUtil.Encrypt(paramJson,CommonUtil.OperatorSecret);
			String timestamp=DateUtil.formatDate(new Date(),"yyyyMMddHHmmss");
			String sigStr=HMacMD5.getHmacMd5Str(CommonUtil.OperatorSecret,CommonUtil.OperatorID+dataStr+timestamp+"0001");
			//设置body数据
			JSONObject d=new JSONObject();
			d.put("OperatorID",CommonUtil.OperatorID);
			d.put("Data", dataStr);
			d.put("TimeStamp",timestamp);
			d.put("Seq", "0001");
			d.put("Sig", sigStr);
			RequestEntity se = new StringRequestEntity(d.toString(), "application/json", "UTF-8");
			method.setRequestEntity(se);
			//使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,30000);
            int result=client.executeMethod(method);
            if (result==200) {
            	JSONObject retJson=JSON.parseObject(method.getResponseBodyAsString());
            	return retJson;
            }
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}
	
	public static String getReqBody(HttpServletRequest req){
		String dataJson = "";
		try {
			BufferedReader br = req.getReader();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				dataJson += inputLine;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataJson;
	}
	
	public static void main(String[] args) throws Exception{
		String timestamp=String.valueOf(new Date().getTime());
		String sn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
		String result=HttpUtil.connectGet(CommonUtil.ILLEGALINFO+"?time=20160810","zhixingjia",timestamp,sn);
		if (result!=null) {
			System.out.println(result);
		}
	}
}
