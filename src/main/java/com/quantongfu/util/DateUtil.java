package com.quantongfu.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期操作工具�?
 * @author pei.luo
 *
 */
public class DateUtil {
	public static final String yyyyMMddFormat = "yyyyMMdd";
	public static final String yyyyMMddStandFormat = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmssFormat = "yyyyMMddHHmmss";
	public static final String yyyyMMddHHmmssFormat2 = "yyyy-MM-dd HH:mm:ss";
	private static long[] timeFor5Minute = new long[288];
	
	private static long[] yesterdayTimeFor5Minute = new long[288];
	
	private static long[] statisTimeFor5Minute = new long[288];
	
	public static String parseDate(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	public static String parseDate(long datetime,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(datetime);
	}
	public static String yyyyMMddFormat(long datetime){
		return new SimpleDateFormat(yyyyMMddFormat).format(new Date(datetime));
	}
	public static String yyyyMMddStandFormat(long datetime){
		return new SimpleDateFormat(yyyyMMddStandFormat).format(new Date(datetime));
	}
	public static String yyyyMMddHHmmssFormat(long datetime){
		return new SimpleDateFormat(yyyyMMddHHmmssFormat).format(new Date(datetime));
	}
	public static String yyyyMMddHHmmssFormat2(long datetime){
		return new SimpleDateFormat(yyyyMMddHHmmssFormat2).format(new Date(datetime));
	}
	public static int getHours(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	public static int getMinutes(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}
	public static int getHours(long timemillis){
		Date date = new Date(timemillis);
		return getHours(date);
	}
	public static int getMinutes(long timemillis){
		Date date = new Date(timemillis);
		return getMinutes(date);
	}
	public static long getBeginTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	public static long getHourBeginTime(long timemillis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(timemillis));
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	public static long getHoursMillis(long timemillis){
		return timemillis - getBeginTime(new Date(timemillis));
	}
	public static long getMinutesMillis(long timemillis){
		Date date = new Date(timemillis);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return timemillis - calendar.getTimeInMillis();
	}
	
	/**
	 * 获取当前时间字符串，格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(getCurrentDate());
	}
	/**
	 * 获取日期时间字符串，格式pattern
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 获取昨天时间字符串，格式pattern
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getYesterdayString(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(DateUtils.addDays(getCurrentDate(), -1));
	}
	/**
	 * 获取当前时间字符串，格式pattern
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateString(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(getCurrentDate());
	}
	/**
	 * 获取当前时间对象
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 *获取当天�?始时�?24:00:00:00
	 * @return
	 */
	public static Long getTodayStartTime(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}
	/**
	 *获取当天结束时间23:59:59:999
	 * @return
	 */
	public static Long getTodayEndTime(){
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}
	/**
	 * 获取昨天�?始时�?
	 * @return
	 */
	public static Long getYestodayStartTime(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}
	/**
	 * 获取昨天结束时间
	 * @return
	 */
	public static Long getYestodayEndTime(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime().getTime();
	}
	/**
	 *获取当天�?始时�?24:00:00:00
	 * @return
	 */
	public static Long getTodayStartTime(Long createTime){
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTimeInMillis(createTime);;
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}
	/**
	 *获取当天结束时间23:59:59:999
	 * @return
	 */
	public static Long getTodayEndTime(Long createTime){
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTimeInMillis(createTime);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}
	/**
	 * 获取昨天�?始时�?
	 * @return
	 */
	public static Long getYestodayStartTime(Long createTime){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(createTime);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}
	/**
	 * 获取昨天结束时间
	 * @return
	 */
	public static Long getYestodayEndTime(Long createTime){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(createTime);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime().getTime();
	}
	/**
	 * 获取大前天开始时�?
	 * @return
	 */
	public static Long getTheDayBeforeYestodayStartTime(Long createTime){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(createTime);
		cal.add(Calendar.DAY_OF_YEAR, -2);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}
	/**
	 * 获取大前天结束时�?
	 * @return
	 */
	public static Long getTheDayBeforeYestodayEndTime(Long createTime){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(createTime);
		cal.add(Calendar.DAY_OF_YEAR, -2);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime().getTime();
	}
	
	/**
	 * 获取当周�?始时间周�?
	 * @return
	 */
	public static Long getWeekStartTime(){
		Calendar weekStart = Calendar.getInstance();
		weekStart.setFirstDayOfWeek(Calendar.MONDAY);  
		weekStart.set(Calendar.HOUR_OF_DAY, 0);  
		weekStart.set(Calendar.MINUTE, 0);  
		weekStart.set(Calendar.SECOND, 0);
		weekStart.set(Calendar.MILLISECOND,0);
		weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
		return weekStart.getTime().getTime();
	}
	/**
	 * 获取当周结束时间
	 * @return
	 */
	public static Long getWeekEndTime(){
		Calendar weekEnd = Calendar.getInstance();
		weekEnd.setFirstDayOfWeek(Calendar.MONDAY);  
		weekEnd.set(Calendar.HOUR_OF_DAY, 23);  
		weekEnd.set(Calendar.MINUTE, 59);  
		weekEnd.set(Calendar.SECOND, 59);  
		weekEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		return weekEnd.getTime().getTime();
	}
	
	/**
	 * 获取当前小时时间
	 * @return
	 */
	public static Long getHourStartTime(){
		Calendar cal=Calendar.getInstance(); 
		Long yestodayStartTime = getTodayStartTime();
		int t=cal.get(Calendar.HOUR_OF_DAY);
		Long hour=yestodayStartTime+(t*60*60*1000);
		return hour;
	}
	/**
	 * 获取当月的第�?天开始时�?
	 * @return
	 */
	public static Long getMonthStartTime(){
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.DAY_OF_MONTH, 1);
		monthStart.set(Calendar.HOUR_OF_DAY, 0);  
		monthStart.set(Calendar.MINUTE, 0);  
		monthStart.set(Calendar.SECOND, 0); 
		monthStart.set(Calendar.MILLISECOND, 0);
		return monthStart.getTime().getTime();
	}
	/**
	 * 获取当月�?后一天的时间
	 * @return
	 */
	public static Long getMonthEndTime(){
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.add(Calendar.MONTH, 1);
		monthEnd.add(Calendar.DATE, -1);
		monthEnd.set(Calendar.DAY_OF_MONTH, 0);
		monthEnd.set(Calendar.HOUR_OF_DAY, 23);  
		monthEnd.set(Calendar.MINUTE, 59);  
		monthEnd.set(Calendar.SECOND, 59); 
		monthEnd.set(Calendar.MILLISECOND, 0);
		return monthEnd.getTime().getTime();
	}
	/**
	 * 获取月份的第�?天开始时�?
	 * @return
	 */
	public static Long getMonthStartTime(int year,int month){
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.YEAR, year);
		monthStart.set(Calendar.MONTH, month-1);
		monthStart.set(Calendar.DAY_OF_MONTH, 1);
		monthStart.set(Calendar.HOUR_OF_DAY, 0);  
		monthStart.set(Calendar.MINUTE, 0);  
		monthStart.set(Calendar.SECOND, 0); 
		monthStart.set(Calendar.MILLISECOND, 0);
		System.out.println(monthStart.getTime());
		return monthStart.getTime().getTime();
	}
	/**
	 * 获取月份�?后一天的时间
	 * @return
	 */
	public static Long getMonthEndTime(int year,int month){
		Calendar monthEnd = Calendar.getInstance();
		System.out.println(monthEnd.getTime());
		monthEnd.set(Calendar.YEAR, year);
		monthEnd.set(Calendar.MONTH, month);
//		monthEnd.add(Calendar.DATE, -1);
		monthEnd.set(Calendar.DAY_OF_MONTH, 0);
		monthEnd.set(Calendar.HOUR_OF_DAY, 23);  
		monthEnd.set(Calendar.MINUTE, 59);  
		monthEnd.set(Calendar.SECOND, 59); 
		monthEnd.set(Calendar.MILLISECOND, 999);
		System.out.println(monthEnd.getTime());
		return monthEnd.getTime().getTime();
	}
	public static int getDayOfMonth(long timemillis){
		Date date = new Date(timemillis);
		return getDayOfMonth(date);
	}
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
    public static Date getDateFromString(String dateString, String format) {
    	try {
    	    SimpleDateFormat sdf = new SimpleDateFormat(format);
    	    return sdf.parse(dateString);
    	}catch (ParseException e) {
    	    e.printStackTrace();
    	}
    	return null;
    }
    public static Long getDateLongTimeString(String dateString, String format) {
    	try {
    	    SimpleDateFormat sdf = new SimpleDateFormat(format);
    	    return sdf.parse(dateString).getTime();
    	}catch (ParseException e) {
    	    e.printStackTrace();
    	}
    	return null;
    }
    public static int getDaysInMonth(int year,int month){
    	 Calendar  calendar  =  Calendar.getInstance(); 
		 int days =1;
		 calendar.set(year,month-1,days);
		 int dayst =calendar.getActualMaximum(calendar.DAY_OF_MONTH);
    	 return dayst;
    }
    public static Long[] getDayListInMonth(int year,int month){
    	Calendar  calendar  =  Calendar.getInstance(); 
    	int days =1;
    	calendar.set(year,month-1,days);
    	int dayst =calendar.getActualMaximum(calendar.DAY_OF_MONTH);
    	Long dayArray[] = new Long[dayst];
    	
    	for(int i=0;i<dayst;i++){
    		dayArray[i]=i+1l;
		}
    	return dayArray;
    }
    public static int getDay(Date date){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static int getWeek(Date date){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    public static int getMonth(Date date){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
    }
    public static int getYear(Date date){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
    }
    
    public static Date getDateAddFromCurrentDate(int days){
	Calendar calendar=Calendar.getInstance();
	calendar.setTime(new Date());
	calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+days);
	return calendar.getTime();
    }
    
    public static Date getDateAddFromAppointDate(Date date, int days) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
	return calendar.getTime();
    }
    
    public static long[] getDateFor5Minute() {
    	long todayStartTime = getTodayStartTime();
    	if (timeFor5Minute[0] != todayStartTime) {
    		for (int i = 0; i < timeFor5Minute.length; i++) {
    			timeFor5Minute[i] = todayStartTime + i*5*60*1000;
    		}
		}
    	return timeFor5Minute;
    }
    
    public static long[] getYesterdayDateFor5Minute() {
    	long yesterdayStartTime = getYestodayStartTime();
    	if (yesterdayTimeFor5Minute[0] != yesterdayStartTime) {
    		for (int i = 0; i < yesterdayTimeFor5Minute.length; i++) {
    			yesterdayTimeFor5Minute[i] = yesterdayStartTime + i*5*60*1000;
    		}
		}
    	return yesterdayTimeFor5Minute;
    }
    
	public static long[] getStatisTimeFor5Minute(String statisDay) {
		long statisTime=parseDate(statisDay, "yyyy-MM-dd").getTime();
		if (statisTimeFor5Minute[0] != statisTime) {
    		for (int i = 0; i < statisTimeFor5Minute.length; i++) {
    			statisTimeFor5Minute[i] = statisTime + i*5*60*1000;
    		}
		}
    	return statisTimeFor5Minute;
	}
    
    public static int getIndexFor5Minute() {
    	return (int) Math.ceil((System.currentTimeMillis()- DateUtil.getTodayStartTime())/300000);
    }
    public static long getTimeStampForMinute(long createTime) {
    	long dayStartTime=getTodayStartTime(createTime);
    	int index=(int) Math.ceil((createTime-dayStartTime)/60000);
    	return dayStartTime+index*60*1000;
    }
    public static long getTimeStampFor5Minute(long createTime) {
    	long dayStartTime=getTodayStartTime(createTime);
    	int index=(int) Math.ceil((createTime-dayStartTime)/300000);
    	return dayStartTime+index*5*60*1000;
    }
    
    public static long getTimeStampForHour(long createTime) {
    	long dayStartTime=getTodayStartTime(createTime);
    	int index=(int) Math.ceil((createTime-dayStartTime)/(300000*12));
    	return dayStartTime+index*12*5*60*1000;
    }
    
    public static int getWeeksByYear(int year){
    	int result = 52;//�?年至少有52�?
    	Calendar cal = Calendar.getInstance();  
        cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第�?天为星期�?  
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//每周从周�?�?�?  
//      上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周�?  
        cal.setMinimalDaysInFirstWeek(7);  //设置每周�?少为7�?       
        cal.set(Calendar.YEAR, year);  
        cal.set(Calendar.WEEK_OF_YEAR, 53);
        if(yyyyMMddFormat(cal.getTime().getTime()).substring(0, 4).equals(year+"")){
        	result=53;
        }
    	return result;
    }
    
    public static boolean isToday(Long todayTime){
    	boolean isToday =false;
    	Long todayStartTime = getTodayStartTime();
    	Long todayEndTime = getTodayEndTime();
    	if(todayTime>=todayStartTime&&todayStartTime<=todayEndTime){
    		isToday = true;
    	}
    	return isToday;
    }
    
    public static List<String> getSeasonMonths(String year, int seasonNum) {
	List<String> rlist = new ArrayList<String>();
	for (int i = 3 * (seasonNum - 1) + 1; i <= 3 * (seasonNum - 1) + 3; i++) {
	    if (seasonNum < 4) {
		rlist.add(year + "-0" + i);
	    }else {
		rlist.add(year + "-" + i);
	    }
	}
	return rlist;
    }
    
    public static int findMaxDayInMonth(int year, int month) {
	int maxday = 0;
	Calendar calendar = Calendar.getInstance();
	// 在set之前�?要先clear，否则有时会默认系统时间
	calendar.clear();
	if (year > 0) {
	    calendar.set(Calendar.YEAR, year);
	}
	if (month > 0) {
	    // 注意：calendar�?1月为0，所以set时应�?-1
	    calendar.set(Calendar.MONTH, month - 1);
	}
	if (calendar != null) {
	    maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	return maxday;
    }
    
    public static Date parseDate(String date, String format){
    	SimpleDateFormat sf = new SimpleDateFormat(format);
    	try {
			return sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public static String formatHighchartDate(Date date){
    	StringBuffer sb = new StringBuffer();
    	sb.append("[Date.UTC(");
    	sb.append(getYear(date));
    	sb.append(",");
    	sb.append(getMonth(date));
    	sb.append(",");
    	sb.append(getDayOfMonth(date));
    	sb.append(")]");
    	return sb.toString();
    }
    
    /**
     * 获取完后图推算日期的毫秒�?
     * @param num
     * @return
     */
    public static long getLongAddDaysFromToday(int num){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.DAY_OF_YEAR, num);
    	return calendar.getTime().getTime();
    }
    
    public static String formatByTemplete(Date date, String templete){
    	SimpleDateFormat sft = new SimpleDateFormat(templete);
    	return sft.format(date);
    }
    
    public static int getQuarterByDate(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int month = c.get(Calendar.MONTH) + 1;
    	return ( month / 4 ) + 1;
    }
    
	public static void main(String[] args) {
//		System.out.println(getMonthStartTime());
//		System.out.println(getDayOfMonth(new Date()));
		System.out.println(getTodayStartTime());
		System.out.println(getYestodayStartTime());
//		System.out.println(getDateFromString("2014-07-21 16:15","yyyy-MM-dd HH:mm"));
//		System.out.println(getDaysInMonth(2014,8));
//		System.out.println(getMonth(new Date()));
//		System.out.println(getYear(new Date()));
//		System.out.println(yyyyMMddHHmmssFormat(getDateAddFromCurrentDate(-30).getTime()));
//		System.out.println(java.net.URLEncoder.encode("2014-07-25 20:54"));
//		System.out.println(getTodayEndTime());
//		System.out.println(getTodayEndTime(1418918400000L));
//		System.out.println(getYestodayEndTime());
//		System.out.println(getYestodayEndTime(1418918400000L));
//		System.out.println(getDay(new Date()));
//		System.out.println(getMonth(new Date()));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
	        //System.out.println(findMaxDayInMonth(2015,2));
	        
//	        System.out.println(parseDate("2015-11-12", "yyyy-MM-dd").getTime());
//		System.out.println(getLongAddDaysFromToday(1));
		System.out.println(
				DateUtil.formatByTemplete(new Date(1459958400000L), "yyyy-MM-dd"));
		String s="abc";
		System.out.print(s==s.intern());
	}

}
