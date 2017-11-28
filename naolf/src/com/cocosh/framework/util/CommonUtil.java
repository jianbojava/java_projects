package com.cocosh.framework.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.cocosh.framework.json.DateJsonValueProcessor;

import net.sf.json.JsonConfig;

public class CommonUtil {
	/**
	 * 常量说明：
	 * 此处定义的常量需要持久化，可以保存在数据库中，在需要的地方读取。
	 * 在多企业号中，最好以每个应用来定义。
	 */
	public static Properties property = new Properties();
	public static String SERVERDOMAIN;
	public static String RESDOMAIN;
	public static int PAGESIZE ;
	public static int PAGESHOWNUM;
	public static int SCOREPERCENT;
	public static Double ACHIEVEPERCENT;
	public static String USERTICKETNAME1;
	public static String USERTICKETNAME2;
	public static String USERTICKETNAME3;
	
	//脑立方积分提成关键字
	public static String ZHIS_TJBM="ZHIS_TJBM";//直售部门提成
	public static String ZIS_TJR="ZIS_TJR";//自售推荐人提成
	
	//API接口SECRET_KEY
	public static final String SECRET_KEY = "zxj_app2016_en";
	//调度员角色ID
	public static final String ROLE_DISPATCH="94d80755922e4a7793315e3a5abffb9f";
	//销售角色ID
	public static final String ROLE_SALE="5db1459fae064eceadf6a4d060a338fb";
	
	/*********Evcard***********/
	//同步Evcard接口地址
	public static final String SYNC_EVCARD_MEM="http://58.32.252.60:8085/EvcardRest/members";
	//解绑Evcard接口地址
	public static final String UNBIND_EVCARD_MEM="http://58.32.252.60:8085/EvcardRest/members/unbindMembers";
	//冻结会员卡接口地址
	public static final String FREEZE_MEMBERS="http://58.32.252.60:8085/EvcardRest/members/freezeMembers";
	//解冻会员卡接口地址
	public static final String UNFREEZE_MEMBERS="http://58.32.252.60:8085/EvcardRest/members/unfreezeMembers";
	//换卡接口地址
	public static final String CHANGE_MEM_CARD="http://58.32.252.60:8085/EvcardRest/members/modifyMemCard";
	//违章查询接口地址
	public static final String ILLEGALINFO="http://58.32.252.60:8085/EvcardRest/illegalInfo";
	
	/*********特来电***********/
	public static final String OperatorID="MA1K370P7";
	public static final String OperatorSecret="1234567890abcdef";
	public static final String IvParameterSpec="1234567890abcdef";
	//获取token
	public static final String query_token="http://api.wyqcd.cn:9101/evcs/v20161110/query_token";
	//查询充电站信息
	public static final String query_stations_info="http://api.wyqcd.cn:9101/evcs/v20161110/query_stations_info";
	//获取电站状态
	public static final String query_station_status="http://api.wyqcd.cn:9101/evcs/v20161110/query_station_status";
	//查询统计信息
	public static final String query_station_stats="http://api.wyqcd.cn:9101/evcs/v20161110/query_station_stats";
	//开始充电
	public static final String query_start_charge="http://api.wyqcd.cn:9101/evcs/v20161110/query_start_charge";
	//结束充电
	public static final String query_stop_charge="http://api.wyqcd.cn:9101/evcs/v20161110/query_stop_charge";
	//请求设备认证
	public static final String query_equip_auth="http://api.wyqcd.cn:9101/evcs/v20161110/query_equip_auth";
	//查询业务策略信息
	public static final String query_equip_business_policy="http://api.wyqcd.cn:9101/evcs/v20161110/query_equip_business_policy";
	//查询充电状态
	public static final String query_equip_charge_status="http://api.wyqcd.cn:9101/evcs/v20161110/query_equip_charge_status";
	
	//API接口返回值定义
	public final static String SUCCESS_CODE = "0";
	public final static String ERROR_CODE = "1";
	public final static String ERROR_CREDENTIAL = "-1";
	public final static String CREDENTIAL_INVALID = "签名失效，请重新登录";
	public final static String SUCCESS_MSG = "";
	public final static String ERROR_PARAMS = "接口参数出错";
	static{
		try {
			property.load(CommonUtil.class.getResourceAsStream("/common.properties"));
			SERVERDOMAIN = property.getProperty("server.domain");
			RESDOMAIN=property.getProperty("res.domain");
			PAGESIZE=Integer.parseInt(property.getProperty("page.size"));
			PAGESHOWNUM=Integer.parseInt(property.getProperty("page.show"));
			SCOREPERCENT=Integer.parseInt(property.getProperty("score_percent"));
			ACHIEVEPERCENT=Double.parseDouble(property.getProperty("achieve_percent"));
			USERTICKETNAME1=property.getProperty("user_ticket_name1");
			USERTICKETNAME2=property.getProperty("user_ticket_name2");
			USERTICKETNAME3=property.getProperty("user_ticket_name3");
		} catch (IOException e) {
			System.out.println("not found properties");
		}
    }
	
	@SuppressWarnings("rawtypes")
	public static String reflectMethod(Object obj, String fileds []) {//通过属性名，获取属性值
		StringBuffer security = new StringBuffer();
		Class clazz = obj.getClass();
		try {
			for (int i = 0; i < fileds.length; i++) {
				PropertyDescriptor pd = new PropertyDescriptor(fileds[i], clazz);
				Method getMethod = pd.getReadMethod();//获得get方法
				if (pd != null) {
					Object o = getMethod.invoke(obj);//执行get方法返回一个Object
					if(o == null){
						security.append("");
					}else{
						security.append(o);
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return security.toString();
	}
	
	public static boolean dataValidate(String summary, String summaryMD5) {//验证加密数据
		return !StringUtils.isEmpty(summary) && !StringUtils.isEmpty(summaryMD5) && summary.equals(summaryMD5);
	}
	
	public static JsonConfig createExcludes(String [] excludes){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor());
		jsonConfig.setExcludes(excludes);
		return jsonConfig;
	}
	
}
