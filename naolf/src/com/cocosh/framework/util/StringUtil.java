package com.cocosh.framework.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class StringUtil {
	public static DecimalFormat decimalFormat = new DecimalFormat();
	private static final char hexDigits [] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

	// 除去null
	public static String setString(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "").trim();
		str = str.replaceAll(" ", "");
		str = str.replaceAll("\\n", "").replaceAll("\\r", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "……";
		}
		return str;
	}
	
	public static String splitAndFilterString(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "").trim();
		str = str.replaceAll(" ", "");
		str = str.replaceAll("\\n", "").replaceAll("\\r", "");
		return str;
	}

	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandom(int length) {
		// 生成随机类
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < length; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}

	public static String getSn() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 9)
				.toUpperCase();
	}

	/**
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * 判断是否为中文字符
	 * 
	 * @param c
	 * @return
	 */
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static String string2Unicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("\\");
				out.append("u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);

				out.append(str);
				out.append(str1);

			}
			return out.toString().toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	static String unicode2String(String unicodeStr) {
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("U");
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(""))
				continue;
			char c = (char) Integer.parseInt(str[i].trim(), 16);
			sb.append(c);
		}
		return sb.toString();
	}

	public static int getRandom() {
		Random ran = new Random();
		return ran.nextInt(100);
	}

	public static String long2Date(String time) {
		if (isEmpty(time)) {
			return getLocalDataTime();
		}
		long searcherInput = Long.parseLong(time);
		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(searcherInput * 1000);
		return df.format(date);
	}

	public static String getLocalDataTime() {
		java.text.DateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		Date date = new Date();
		return df.format(date);
	}
	
	public static String getLocalData() {
		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return df.format(date);
	}
	
	public static String getLocalData(Date date) {
		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String getLocalDataM(Date date) {
		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(date);
	}

	public static boolean isEmpty(String string) {
		return (string == null || "".equals(string.trim()));
	}

	public static String getRegularStr(String str) {
		if (!isEmpty(str)) {
			return str.trim();
		} else {
			return str;
		}
	}

	public static String getRandomNum(int x) {
		Random random = new Random();
		return String.valueOf(random.nextInt(x));
	}

	public static String suitableEncoding(String str) {
		boolean needChange = false;
		try {
			for (byte b : str.getBytes("iso-8859-1")) {
				if (b < 0) {
					needChange = true;
					break;
				}
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (needChange) {
			try {
				str = new String(str.getBytes("iso-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 获取时间字符串（yyyyMMdd）
	 * 
	 * @return
	 */
	public static String yyyyMMdd() {
		return "/"+DateUtil.currentDate().replaceAll("-", "")+"/";
	}
	
	public static String yyyyMMdd_() {
		return DateUtil.currentDate().replaceAll("-", "")+"/";
	}
	
	/**
	 * 去除数组中重复元素
	 * 
	 * @param array
	 * @return
	 */
	public static String[] cleanRepeat(String[] array) {
		Set<String> set = new TreeSet<String>();
		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}
		return (String[]) set.toArray(new String[0]);
	}
	
	/**
	 * 去除集合中重复元素
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> cleanRepeat(List<String> list) {
		Set<String> set = new TreeSet<String>();
		for (int i = 0; i < list.size(); i++) {
			set.add(list.get(i));
		}
		return new ArrayList<String>(set);
	}
	
	public static String buildOrderSn(){
		return DateUtil.formatDate(new Date(), "yyMMddHHmmss")+StringUtil.getRandom(3);
	}
	
	/**
	 * 创建签名
	 * @return
	 */
	public static String buildSign(){
		int max = 255;
		int min = 200;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		Random rd = new Random();
		StringBuffer randBuf = new StringBuffer();
		for (int i = 0; i < s; i++) {
			randBuf.append(hexDigits[rd.nextInt(hexDigits.length-1)]);
		}
		return randBuf.toString();
	}
	
	public static String getTimeStamp(){
		return DateUtil.formatDate(new Date(), "yyyyMMddhhmmss");
	}
	
	/**
	 * 格式化 20160804180916
	 * @param date
	 * @return
	 */
	public static String formatDateString(String date){
		return date.substring(0, 4)+"-"+date.substring(4, 6)+"-"+date.substring(6, 8)+" "+date.substring(8, 10)+":"+date.substring(10, 12)+":"+date.substring(12, 14);
	}

	public static void main(String[] args) {
		// Integer a = 1;
		// Integer b = 3;
		// System.out.println(a.intValue()/(double)b.intValue());
//		System.out.println(StringUtil.getUuid());
		// for (int i = 0; i < 7; i++) {
		// System.out.print("hasGrade"+(i+1)+" = "+(i+1)+",");
		// }
		// 生成随机类
//		Random random = new Random();
//		String sRand = "";
//		for (int i = 0; i < 7; i++) {
//			String rand = String.valueOf(random.nextInt(10));
//			sRand += rand;
//		}
//		System.out.println(sRand);
//		System.out.println(buildSign());
//		System.out.println(StringUtil.getTimeStamp());
		
		/*
		Map<String,String> checkMap = new HashMap<String,String>();
		//Mandatory parameters for SetExpressCheckout API call
		
		checkMap.put("PAYMENTREQUEST_0_AMT", "22.00");//总价
		checkMap.put("PAYMENTREQUEST_0_PAYMENTACTION", "Sale");//交易方式
		checkMap.put("RETURNURL", "returnURL");//回调路径
		checkMap.put("CANCELURL", "cancelURL");//回调路径
//		checkMap.put("PAYMENTREQUEST_0_SELLERPAYPALACCOUNTID", this.getSellerEmail());
		checkMap.put("PAYMENTREQUEST_0_CURRENCYCODE","USD");//币种
		checkMap.put("PAYMENTREQUEST_0_ITEMAMT","15");//商品总价
		checkMap.put("PAYMENTREQUEST_0_TAXAMT","2");//税额
		checkMap.put("PAYMENTREQUEST_0_SHIPPINGAMT","5.00");//物流费用
		checkMap.put("PAYMENTREQUEST_0_HANDLINGAMT","1.00");//手续费
		checkMap.put("PAYMENTREQUEST_0_SHIPDISCAMT","-3.00");//物流折扣
		checkMap.put("PAYMENTREQUEST_0_INSURANCEAMT","2.00");//保费
		
		checkMap.put("L_PAYMENTREQUEST_0_NAME0","DSLR Camera");//商品总价
		checkMap.put("L_PAYMENTREQUEST_0_NUMBER0","A0123");//商品编号
		checkMap.put("L_PAYMENTREQUEST_0_DESC0","Autofocus Camera");//商品描述
		checkMap.put("L_PAYMENTREQUEST_0_AMT0","10");//商品单价
		checkMap.put("L_PAYMENTREQUEST_0_QTY0","1");//商品数量
		
		checkMap.put("LOGOIMG","http://localhost/EckStatic/static/themes/img/logo.jpg");//Logo图
		checkMap.put("ADDROVERRIDE","1");
		
		
		// Shipping parameters for API call
		checkMap.put("PAYMENTREQUEST_0_SHIPTONAME","Alegra + Valava");
		checkMap.put("PAYMENTREQUEST_0_SHIPTOSTREET", "55 East 52nd Street");//地址
		checkMap.put("PAYMENTREQUEST_0_SHIPTOSTREET2","21st Floor");//地址
		checkMap.put("PAYMENTREQUEST_0_SHIPTOCITY","New York");//市
		checkMap.put("PAYMENTREQUEST_0_SHIPTOSTATE","NY");//省
		checkMap.put("PAYMENTREQUEST_0_SHIPTOZIP","10022");//邮编
		checkMap.put("PAYMENTREQUEST_0_SHIPTOCOUNTRY","Afghanistan");//国家
		checkMap.put("PAYMENTREQUEST_0_SHIPTOPHONENUM","15021933598");//手机号码
		
		StringBuffer postStr = new StringBuffer();
		for (Iterator iterator = checkMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			postStr.append("&"+key+checkMap.get(key));
		}
		System.out.println(postStr);
		
		
		int width = 60,height = 10,bigWidth = 100,bigHeight = 30;
		double widthRate = 0,heightRate = 0,rate = 1;
		widthRate = (double)bigWidth/width;
		heightRate = (double)bigHeight/height;
		if(width < bigWidth && height < bigHeight){
			if(widthRate < heightRate){
				rate = widthRate;
			}else{
				rate = heightRate;
			}
			System.out.println("width:"+width * rate );
			System.out.println("height:"+height * rate);
		}else if(width >= bigWidth && height >= bigHeight){
			if(widthRate < heightRate){
				rate = widthRate;
			}else{
				rate = heightRate;
			}
			System.out.println("width:"+rate*width);
			System.out.println("height:"+rate*height);
		}else if(width >= bigWidth && height <= bigHeight){
			if(widthRate < heightRate){
				rate = widthRate;
			}else{
				rate = heightRate;
			}
			System.out.println("width:"+rate*width);
			System.out.println("height:"+rate*height);
		}else if(width <= bigWidth && height >= bigHeight){
			if(widthRate < heightRate){
				rate = widthRate;
			}else{
				rate = heightRate;
			}
			System.out.println("width:"+rate*width);
			System.out.println("height:"+rate*height);
		}
		*/
		System.out.println(getNumber(9));
		System.out.println(buildOrderSn());
	}
	
	
	
	private static double rad(String d){
		double myd=Double.valueOf(d);
	    return myd * Math.PI / 180.0;
	}
	
	/**
	 * 根据两点间经纬度坐标，计算两点间距离，单位为米
	 * @param lng1(经度)
	 * @param lat1(纬度)
	 * @param lng2(经度)
	 * @param lat2(纬度)
	 * @return
	 */
	 public static double GetDistance(String lng1, String lat1, String lng2, String lat2)
	 {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	 
	 /**
		 * 针对字符串中含有{0}{1}...这类进行替换成相应的值,通常用于查询语句中HQL或SQL中
		 * 
		 * @param str
		 * @param args
		 * @return
		 */
		public static String format(String str, Object... args) {

			// 这里用于验证数据有效性
			if (str == null || "".equals(str))
				return "";
			if (args.length == 0) {
				return str;
			}

			/*
			 * 如果用于生成SQL语句，这里用于在字符串前后加单引号 for(int i=0;i<args.length;i++) { String
			 * type="java.lang.String";
			 * if(type.equals(args[i].getClass().getName()))
			 * args[i]="'"+args[i]+"'"; }
			 */

			String result = str;

			// 这里的作用是只匹配{}里面是数字的子字符串
			java.util.regex.Pattern p = java.util.regex.Pattern
					.compile("\\{(\\d+)\\}");
			java.util.regex.Matcher m = p.matcher(str);

			while (m.find()) {
				// 获取{}里面的数字作为匹配组的下标取值
				int index = Integer.parseInt(m.group(1));

				// 这里得考虑数组越界问题，{1000}也能取到值么？？
				if (index < args.length) {

					// 替换，以{}数字为下标，在参数数组中取值
					String argValue = "";
					if (args[index] != null) {
						argValue = args[index].toString();
					}
					result = result.replace(m.group(), argValue);
				}
			}
			return result;
		}
		
	public static String getNumber(int num){
			num++;
			String result = "";
			switch ((num+"").length()) {
			case 1:
				result = "0000" + num;
				break;
			case 2:
				result = "000" + num;
				break;
			case 3:
				result = "00" + num;
				break;
			case 4:
				result = "0" + num;
				break;
			case 5:
				result = "" + num;
				break;
		    // 此处代表编号已经超过了9999，从0重新开始
			default:
				result = "00000";
				break;
			}
			return result;
		}
	/*手机号码格式化*/
	public static String mobilehide(String mobile){
		if(!StringUtil.isEmpty(mobile)&&(mobile.length()>=7)){
			mobile=mobile.substring(0,3) + "****" + mobile.substring(7, mobile.length());
		}
		return mobile;
	}
}
