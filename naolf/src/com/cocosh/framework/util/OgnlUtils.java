package com.cocosh.framework.util;

import org.apache.commons.lang.StringUtils;


public class OgnlUtils {
	public static boolean isNotEmpty(String ognl){
		return !StringUtils.isEmpty(ognl);
	}
	public static boolean isNotNull(Object ognl){
		return ognl != null;
	}
	public static void main(String[] args) {
		System.out.println(OgnlUtils.isNotEmpty("1"));
		System.out.println(OgnlUtils.isNotEmpty(null));
		System.out.println(OgnlUtils.isNotNull(null));
	}
}
