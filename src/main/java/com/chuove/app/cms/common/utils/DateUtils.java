package com.chuove.app.cms.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
	 private static final ThreadLocal<Calendar> calendar = new ThreadLocal<Calendar>();
	  
	  public static int compare(Date d1, Date d2)
	  {
	    return new LocalDate(d1.getTime()).compareTo(new LocalDate(d2.getTime()));
	  }
	  
	  public static Date toDate(String str)
	  {
	    if ((str == null) || ((str.length() != 8) && (str.length() != 14) && (str.length() != 10) && (str.length() != 19) && (str.length() != 20))) {
	      return null;
	    }
	    String pattern = null;
	    if (str.length() == 8)
	    {
	      pattern = "yyyyMMdd";
	    }
	    else if (str.length() == 14)
	    {
	      pattern = "yyyyMMddHHmmss";
	    }
	    else if ((str.length() == 10) || (str.length() == 19) || (str.length() == 20))
	    {
	      if (str.contains("-")) {
	        pattern = "yyyy-MM-dd";
	      } else if (str.contains("/")) {
	        pattern = "yyyy/MM/dd";
	      } else if (str.contains("\\")) {
	        pattern = "yyyy\\MM\\dd";
	      }
	      if (str.length() == 19) {
	        pattern = pattern + " HH:mm:ss";
	      } else if (str.length() == 20) {
	        pattern = pattern + "'T'HH:mm:ss'Z'";
	      }
	    }
	    DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
	    return DateTime.parse(str, dtf).toDate();
	  }
	  
	  public static String toString(Date date)
	  {
	    if (date == null) {
	      return null;
	    }
	    return new DateTime(date).toString("yyyy-MM-dd");
	  }
	  
	  public static String toString(Date date, String pattern)
	  {
	    if (date == null) {
	      return null;
	    }
	    return new DateTime(date).toString(pattern);
	  }
	  
	  public static String toLongString(Date date)
	  {
	    if (date == null) {
	      return null;
	    }
	    return new DateTime(date).toString("yyyy-MM-dd HH:mm:ss");
	  }
	  
	  public static String toLongString(Date date, String pattern)
	  {
	    if (date == null) {
	      return null;
	    }
	    return new DateTime(date).toString(pattern);
	  }
	  
	  public static int getYear(Date date)
	  {
	    return new DateTime(date).getYear();
	  }
	  
	  public static int getMonth(Date date)
	  {
	    return new DateTime(date).getMonthOfYear();
	  }
	  
	  public static int getDayOfMonth(Date date)
	  {
	    return new DateTime(date).getDayOfMonth();
	  }
	  
	  public static int getHour(Date date)
	  {
	    return new DateTime(date).getHourOfDay();
	  }
	  
	  public static int getMunite(Date date)
	  {
	    return new DateTime(date).getMinuteOfHour();
	  }
	  
	  public static int getSecond(Date date)
	  {
	    return new DateTime(date).getSecondOfMinute();
	  }
	  
	  public static int getWeeks(Date begin, Date datum)
	  {
	    if (compare(begin, datum) > 0) {
	      return -1;
	    }
	    int days = getPeriod(begin, datum);
	    return days % 7 > 0 ? days / 7 + 1 : days / 7;
	  }
	  
	  public static int getAnniversary(Date beginDate, Date calculateDate)
	  {
	    DateTime start = new DateTime(beginDate);
	    DateTime end = new DateTime();
	    if (calculateDate != null) {
	      end = new DateTime(calculateDate);
	    }
	    Period p = new Period(start, end, PeriodType.years());
	    return p.getYears();
	  }
	  
	  public static int getPeriod(Date date1, Date date2)
	  {
	    if ((date1 == null) && (date2 == null)) {
	      return 0;
	    }
	    DateTime start = new DateTime(date1);
	    DateTime end = new DateTime();
	    if (date2 != null) {
	      end = new DateTime(date2);
	    }
	    Period p = new Period(start, end, PeriodType.days());
	    return p.getDays();
	  }
	  
	  public static Date getBeginOfDay(Date date)
	  {
	    Calendar c = (Calendar)calendar.get();
	    if (c == null)
	    {
	      c = Calendar.getInstance();
	      calendar.set(c);
	    }
	    if (date != null) {
	      c.setTime(date);
	    }
	    c.set(11, 0);
	    c.set(12, 0);
	    c.set(13, 0);
	    c.set(14, 0);
	    return c.getTime();
	  }
	  
	  public static Date getEndOfDay(Date date)
	  {
	    Calendar c = (Calendar)calendar.get();
	    if (c == null)
	    {
	      c = Calendar.getInstance();
	      calendar.set(c);
	    }
	    if (date != null) {
	      c.setTime(date);
	    }
	    c.set(11, 23);
	    c.set(12, 59);
	    c.set(13, 59);
	    c.set(14, 999);
	    return c.getTime();
	  }
	  
	  public static Date getBeginOfMonth(Date date)
	  {
	    Calendar c = (Calendar)calendar.get();
	    if (c == null)
	    {
	      c = Calendar.getInstance();
	      calendar.set(c);
	    }
	    if (date != null) {
	      c.setTime(date);
	    }
	    c.set(5, 1);
	    return getBeginOfDay(c.getTime());
	  }
	  
	  public static Date getEndOfMonth(Date date)
	  {
	    Calendar c = (Calendar)calendar.get();
	    if (c == null)
	    {
	      c = Calendar.getInstance();
	      calendar.set(c);
	    }
	    if (date != null) {
	      c.setTime(date);
	    }
	    c.set(5, c.getActualMaximum(5));
	    return getEndOfDay(c.getTime());
	  }
	  
	  public static final int SECOND = 24 * 60 * 60;

		public static int getNowTime() {
			return (int) (System.currentTimeMillis() / 1000);
		}

		public static int getTime(Date date) {
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			int time = (int) (cl.getTimeInMillis() / 1000);
			return time;
		}

		public static SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");

		public static String getYMDString(int time) {
			Date d = new Date(time * 1000l);
			return YMD.format(d);
		}

		public static SimpleDateFormat YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		public static Date parseyyyyMMddHHmmss(String str) {
			String year = str.substring(0, 4);
			String month = str.substring(4, 6);
			String day = str.substring(6, 8);
			String hour = str.substring(8, 10);
			String min = str.substring(10, 12);
			String second = str.substring(12);
			Date d = null;
			try {
				d = YMDHMS.parse(year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return d;
		}

		public static String dateToString(Date date) {
			return YMDHMS.format(date);
		}
}
