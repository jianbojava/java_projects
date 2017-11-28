package com.cocosh.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 * @author jerry
 */
public class DateUtil {

	/**
	 * 日期加或减n天
	 * 
	 * @return
	 */
	public static final String addDay(int dayAmount, String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.stringToDate(date));
		cal.add(Calendar.DATE, dayAmount);
		return formatDate(cal.getTime(), "yyyy-MM-dd");
	}

	public static final String addDay(int dayAmount, Date date,String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, dayAmount);
		return formatDate(cal.getTime(), format);
	}

	/**
	 * 获得两日期差
	 * 
	 * @return
	 */
	public static final int getIntervalDays(String date1, String date2) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(stringToDate(date1));
		caled.setTime(stringToDate(date2));
		// 设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;
		return (days+1);
	}
//	public static final int getIntervalDays(String date1, String date2) {
//		return (int) ((stringToDate(date2).getTime() - stringToDate(date1)
//				.getTime()) / 1000 / 60 / 60 / 24);
//	}
	
	/**
	 * 获得两日期差
	 * 
	 * @return
	 */
	public static final int getIntervalDays(Date date1, Date date2) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(date1);
		caled.setTime(date2);
		// 设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;
		return (days+1);
	}
//	public static final int getIntervalDays(Date date1, Date date2) {
//		double hours=(double)(date2.getTime()-date1.getTime())/1000/60/60;
//		return hours%24!=0?(int)hours/24+1:(int)hours/24;
//	}

	public static final Date stringToDate(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static final Date stringToDate(String dateStr, Object... pattern) {
		if (dateStr == null || dateStr.equals(""))
			return null;
		DateFormat format = null;
		if (pattern != null && pattern.length > 0) {
			format = new SimpleDateFormat(pattern[0].toString());
		} else {
			format = new SimpleDateFormat("yyyy-MM-dd");
		}
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatDate(Date date, Object... pattern) {
		if (date == null)
			return "";
		String formatDate = null;

		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 获取当时日期，格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentDate() {
		Calendar current = Calendar.getInstance();
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		int day = current.get(Calendar.DATE);
		String currentDate = year + "-" + (month < 10 ? ("0" + month) : month)
				+ "-" + (day < 10 ? ("0" + day) : day);
		return currentDate;
	}

	/**
	 * 获取当月的第一天，格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentMonthDate() {
		Calendar current = Calendar.getInstance();
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		String currentDate = year + "-" + (month < 10 ? ("0" + month) : month)
				+ "-01";
		return currentDate;
	}

	/**
	 * 获取下月的第一天，格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String nextMonthDate() {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.MONTH, 1);
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		String currentDate = year + "-" + (month < 10 ? ("0" + month) : month)
				+ "-01";
		return currentDate;
	}

	/**
	 * 获取当月的最后一天，格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentMonthLastDate() {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.MONTH, 1);
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		current.add(Calendar.DATE, -1);
		int day = current.get(Calendar.DATE);
		String currentDate = year + "-" + (month < 10 ? ("0" + month) : month)
				+ "-" + (day < 10 ? ("0" + day) : day);
		return currentDate;
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static int currentYear() {
		Calendar current = Calendar.getInstance();
		return current.get(Calendar.YEAR);
	}

	public static Date addDate(Date date, int mount) {
		Calendar current = Calendar.getInstance();
		current.setTime(date);
		current.add(Calendar.DATE, mount);
		return current.getTime();
	}

	/**
	 * 获取当前年月，格式为yyyy-MM
	 * 
	 * @return
	 */
	public static String currentYearMonth() {
		Calendar current = Calendar.getInstance();
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		String yearMonth = year + "-" + (month < 10 ? ("0" + month) : month);
		return yearMonth;
	}

	/**
	 * 获取当前某月的年月，格式为yyyy-MM
	 * 
	 * @return
	 */
	public static String getYearMonth(int stepMonth) {
		Calendar current = Calendar.getInstance();
		int year = current.get(Calendar.YEAR);
		current.add(Calendar.MONTH, stepMonth);
		int month = current.get(Calendar.MONTH) + 1;
		return year + "-" + (month < 10 ? ("0" + month) : month);
	}

	/**
	 * 获取当时日期，格式为yyyy年MM月dd日
	 * 
	 * @return
	 */
	public static String currentFormatDate() {
		Calendar current = Calendar.getInstance();
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		int day = current.get(Calendar.DATE);
		String currentDate = year + "年" + (month < 10 ? ("0" + month) : month)
				+ "月" + (day < 10 ? ("0" + day) : day) + "日";
		return currentDate;
	}

	/**
	 * 日期格式，格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String formatDate(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		String currentDate = year + "-" + (month < 10 ? ("0" + month) : month)
				+ "-" + (day < 10 ? ("0" + day) : day);
		return currentDate;
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date currentTime() {
		Calendar current = Calendar.getInstance();
		return current.getTime();
	}
	
	/**
	 * 获取几个月以后的第一天
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public static Date numMonthTime(int nummonth) {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.MONTH, nummonth);
		int year = current.get(Calendar.YEAR);
		int month = current.get(Calendar.MONTH) + 1;
		String currentDate = year + "-" + (month < 10 ? ("0" + month) : month)
				+ "-01"+" 00:00:00";
		 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		 Date date=null;
		 try {
			date= sdf.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return date;
		
	}
	
	// 计算差多少分钟
	public static long dateMinuteDiff(Date startTime, Date endTime){
		long diff = endTime.getTime() - startTime.getTime();
		long min = diff / (1000 * 60);
		return min;
	}
	
	public static boolean isSameDay(Date day1, Date day2) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String ds1 = sdf.format(day1);
	    String ds2 = sdf.format(day2);
	    if (ds1.equals(ds2)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public static List<Date> findDates(Date dBegin, Date dEnd) {  
        List<Date> lDate = new ArrayList<Date>(); 
        lDate.add(dBegin);  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后    
        while (dEnd.after(calBegin.getTime())) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
            lDate.add(calBegin.getTime());  
        }  
        return lDate;  
    }  
	
	public static int getMonthSpace(Date date1, Date date2){

        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result)+1;

    }
	
	public static int getWeek(Date date){
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int w = cal.get(Calendar.DAY_OF_WEEK)-1;
		return w;
	}
	
	/**
	 * 获得年龄
	 * 
	 * @return
	 */
	public static final int getAge(Date date) {
		Calendar current = Calendar.getInstance();
		int nyear= current.get(Calendar.YEAR);
		current.setTime(date);
		int byear=current.get(Calendar.YEAR);
		return nyear-byear;
	}
	
	public static void main(String[] args) {
//		System.out.println(DateUtil.addDay(-1,new Date(),"yyyyMMdd"));
		getAge(new Date());
	}
}
