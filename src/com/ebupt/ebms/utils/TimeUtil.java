package com.ebupt.ebms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {

	/**
	 * 获取过期时间
	 * 
	 * @param expireTime
	 * @return
	 */
	public static Date getExpireDate(int expireTime) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.SECOND, expireTime);
		return cal.getTime();
	}

	public static String getTime() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		GregorianCalendar gc = new GregorianCalendar();
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static String getHHmm(){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HHmm");
		GregorianCalendar gc = new GregorianCalendar();
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static String getFormatTime() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gc = new GregorianCalendar();
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static String getFormatHour() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
		GregorianCalendar gc = new GregorianCalendar();
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static Date getParseTime(String time){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormatter.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getParseDate(String time){
		if(time.length() >= 8){
			return time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6,8);
		}
		return time;
	}

	public static String getDate() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar gc = new GregorianCalendar();
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static String getDateHour() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHH");
		GregorianCalendar gc = new GregorianCalendar();
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static String getBeforeDate(int date) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DATE, 0 - date);
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static String getAfterDate(int date) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DATE, date);
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}

	public static String getExpireTime(int expireTime) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.SECOND, expireTime);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String olddate = dateFormatter.format(cal.getTime());

		return olddate;
	}

	public static String getAfterTime(int hours, int minutes, int seconds) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.HOUR, hours);
		gc.add(Calendar.MINUTE, minutes);
		gc.add(Calendar.SECOND, seconds);
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}

	public static String getBeforeTime(int hours, int minutes, int seconds) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.HOUR, 0 - hours);
		gc.add(Calendar.MINUTE, 0 - minutes);
		gc.add(Calendar.SECOND, 0 - seconds);
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}

	public static String getAfterTime(String time, int minutes) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("HHmm");
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
		gc.set(Calendar.MINUTE, Integer.parseInt(time.substring(2, 4)));
		gc.add(Calendar.MINUTE, minutes);
		String olddate = dateFormatter.format(gc.getTime());

		return olddate;
	}
	
	public static int getActualMaximum(){
		GregorianCalendar gc = new GregorianCalendar();
		return gc.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static void main(String[] args){
//		Date date = getParseTime("2010-06-19 13:27:00");
//		Date nowdate = new Date();
//		System.out.println(date.getTime());
//		System.out.println(nowdate.getTime());
//		System.out.println(System.currentTimeMillis());
//		System.out.println(nowdate.getTime() - date.getTime());
//		
//		System.out.println("*************");
//		GregorianCalendar gc = new GregorianCalendar();
//		System.out.println(gc.getTime());
//		System.out.println(gc.get(Calendar.HOUR_OF_DAY));
//		
//		System.out.println("##############");
//		System.out.println(getDateHour());
		System.out.println(getFormatHour());
	}
}
