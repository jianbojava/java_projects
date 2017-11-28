package com.cocosh.framework.payment.wxap;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;



public class HttpsUtil {
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
	/**
	 * 调用接口
	 * @param url
	 * @param dataStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject callInterface(String url, String dataStr) throws Exception {
		String strResult = "";
		StringBuffer params = new StringBuffer();
		params.append(dataStr);

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());
		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setReadTimeout(30000);
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(params.length()));
		conn.setDoInput(true);
		conn.connect();

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
		JSONObject postJson = JSONObject.fromObject(strResult);
		return postJson;
	}
	
	/**
	 * 获取动态数据（通过url）
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getDsynSource(String url){
		try {
			String strResult = "";
			URL console = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			conn.connect();
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
			conn.disconnect();
			return strResult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
