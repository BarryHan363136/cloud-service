package com.barry.cloud.platform.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
  /**
   * Number of milliseconds in a standard second.
   */
  public static final long MILLIS_PER_SECOND = 1000;
  /**
   * Number of milliseconds in a standard minute.
   */
  public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
  /**
   * Number of milliseconds in a standard hour.
   */
  public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
  /**
   * Number of milliseconds in a standard day.
   */
  public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

  /**
   * ISO8601 formatter for date without time zone. The format used is <tt>yyyy-MM-dd</tt>.
   */
  public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");

  /**
   * ISO8601 formatter for date-time without time zone. The format used is
   * <tt>yyyy-MM-dd HH:mm:ss</tt>.
   */
  public static final FastDateFormat DATETIME_FORMAT = FastDateFormat
      .getInstance("yyyy-MM-dd HH:mm:ss");

  public static final FastDateFormat DATETIME_FORMAT_SS = FastDateFormat
      .getInstance("yyyy-MM-dd HH:mm:ss SS");

  /**
   * Compact ISO8601 date format yyyyMMdd
   */
  public static final String COMPACT_DATE_FORMAT_PATTERN = "yyyyMMdd";

  /**
   * ISO8601 date format: yyyy-MM-dd
   */
  public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

  /**
   * ISO8601 date-time format: yyyy-MM-dd HH:mm:ss
   */
  public static String DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * DateUtils instances should NOT be constructed in standard programming.
   * <p>
   * This constructor is public to permit tools that require a JavaBean instance to operate.
   */
  public DateUtils() {}

  public static Date parse(String str) {
    return parse(str, DATE_FORMAT_PATTERN);
  }

  public static Date parseYMD(String str) {
    return parse(str, DATETIME_FORMAT_PATTERN);
  }

  public static Date parse(String str, String pattern) {
    if (StringUtils.isBlank(str)) {
      return null;
    }
    DateFormat parser = new SimpleDateFormat(pattern);
    try {
      return parser.parse(str);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Can't parse " + str + " using " + pattern);
    }
  }

  public static String format(Date date, String pattern) {
    if (date == null) {
      return null;
    }
    FastDateFormat df = FastDateFormat.getInstance(pattern);
    return df.format(date);
  }

  /**
   * return date format is <code>yyyy-MM-dd</code>
   */
  public static String format(Date date) {
    return date == null ? null : DATE_FORMAT.format(date);
  }

  /**
   * return date format is <code>yyyy-MM-dd</code>
   */
  public static String getCurrentDateAsString() {
    return DATE_FORMAT.format(new Date());
  }

  public static String getCurrentDateAsString(String pattern) {
    FastDateFormat formatter = FastDateFormat.getInstance(pattern);
    return formatter.format(new Date());
  }

  /**
   * return date format is <code>yyyy-MM-dd HH:mm:ss</code>
   */
  public static String getCurrMillisecondAsString() {
    return DATETIME_FORMAT_SS.format(new Date());
  }

  /**
   * return date format is <code>yyyy-MM-dd HH:mm:ss SS</code>
   */
  public static String getCurrentDateTimeAsString() {
    return DATETIME_FORMAT.format(new Date());
  }

  public static String getCurrentDateTimeAsString(String pattern) {
    FastDateFormat formatter = FastDateFormat.getInstance(pattern);
    return formatter.format(new Date());
  }

  public static Date getStartDateTimeOfCurrentMonth() {
    return getStartDateTimeOfMonth(new Date());
  }

  /**
   * The value of
   * <ul>
   * <li>Calendar.HOUR_OF_DAY
   * <li>Calendar.MINUTE
   * <li>Calendar.MINUTE
   * </ul>
   * will be set 0.
   * 
   * @param date
   * @return
   */
  public static Date getStartDateTimeOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  public static Date getEndDateTimeOfCurrentMonth() {
    return getEndDateTimeOfMonth(new Date());
  }

  public static Date getEndDateTimeOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  public static Date getStartTimeOfCurrentDate() {
    return getStartTimeOfDate(new Date());
  }

  public static Date getStartTimeOfDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  /**
   * <tt>2005-12-27 17:58:56</tt> will be returned as <tt>2005-12-27 23:59:59</tt>
   * 
   * @param
   * @return
   */
  public static Date getEndTimeOfCurrentDate() {
    return getEndTimeOfDate(new Date());
  }

  public static Date getEndTimeOfDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  public static Date addHours(Date date, int hours) {
    return add(date, Calendar.HOUR_OF_DAY, hours);
  }

  public static Date addMinutes(Date date, int minutes) {
    return add(date, Calendar.MINUTE, minutes);
  }

  public static Date addSeconds(Date date, int seconds) {
    return add(date, Calendar.SECOND, seconds);
  }

  public static Date addDays(Date date, int days) {
    return add(date, Calendar.DATE, days);
  }

  /**
   * Returns a new datetime adds the specified (signed) number of months.
   * 
   * @param date the date subtract on
   * @param months the amount of months to subtract, may be negative
   * @return the new Date minus the increased months
   */
  public static Date addMonths(Date date, int months) {
    return add(date, Calendar.MONTH, months);
  }

  public static Date addYears(Date date, int years) {
    return add(date, Calendar.YEAR, years);
  }

  private static Date add(Date date, int field, int amount) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(field, amount);
    return cal.getTime();
  }

  /*
   * 获取当前时间三个小时后的时间字符串
   * 
   * 传入值:秒数[即多少秒后的时间]
   */
  public static String getAfterTimeString(String currTime, int afterSeconds) {
    String afterTime = "";
    Date date = DateUtils.parse(currTime, DATETIME_FORMAT_PATTERN);
    Date date2 = DateUtils.add(date, Calendar.SECOND, afterSeconds);
    afterTime = DateUtils.format(date2, DATETIME_FORMAT_PATTERN);
    return afterTime;
  }

  /**
   * 两个时间间隔多少天或多少分
   * @param type:1-天,2-分
   * */
  public static Integer daysBetween(Date early, Date late, Integer type) {
    Calendar ecal = Calendar.getInstance();
    Calendar lcal = Calendar.getInstance();
    ecal.setTime(early);
    lcal.setTime(late);
    long etime = ecal.getTimeInMillis();
    long ltime = lcal.getTimeInMillis();
    if (type==1){
      return (int) ((ltime - etime) / MILLIS_PER_DAY);
    }
    if (type==2){
      return (int) ((ltime - etime) / MILLIS_PER_MINUTE);
    }
    return null;
  }

//  /** 两个时间间隔多少分 */
//  public static int minutesBetween(Date early, Date late) {
//    Calendar ecal = Calendar.getInstance();
//    Calendar lcal = Calendar.getInstance();
//    ecal.setTime(early);
//    lcal.setTime(late);
//    long etime = ecal.getTimeInMillis();
//    long ltime = lcal.getTimeInMillis();
//    return (int) ((ltime - etime) / MILLIS_PER_MINUTE);
//  }

  /** 两个时间间隔多少分 */
  public static final int secondsBetween(String beforeTime, String currTime) {
    int differSeconds = 0;
    try {
      Date early = DATETIME_FORMAT.parse(beforeTime);
      Date late = DATETIME_FORMAT.parse(currTime);
      Calendar ecal = Calendar.getInstance();
      Calendar lcal = Calendar.getInstance();
      ecal.setTime(early);
      lcal.setTime(late);
      long etime = ecal.getTimeInMillis();
      long ltime = lcal.getTimeInMillis();
      differSeconds = (int) ((ltime - etime) / MILLIS_PER_SECOND);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return differSeconds;
  }

  public static String getNow() {
    String sCurTime = "";
    try {
      SimpleDateFormat sdf =
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.CHINA);
      Calendar sNow = Calendar.getInstance();
      sCurTime = sdf.format(sNow.getTime());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return sCurTime;
  }

  /**
   * 验证字符串是否是合法的日期
   *
   * @param str_date
   * @return
   */
  @SuppressWarnings("unused")
  public static boolean is_valid_date_formate(String str_date) {
    if (str_date.length() > 10) {
      str_date = str_date.substring(1);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date d = sdf.parse(str_date);
      return true;
    } catch (ParseException pe) {
      return false;
    }
  }

  /**
   * 验证字符串是否是合法的日期
   *
   * @param str_date
   * @return
   */
  public static boolean is_valid_str_date_formate(String str_date) {
    boolean flag = false;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    try {
      if (str_date.equals(date.format(date.parse(str_date)))) {
        flag = true;
      }
    } catch (ParseException e) {
    }
    return flag;
  }

  /**
   * 验证字符串是否是合法的日期
   *
   * @param str_date
   * @return
   */
  public static boolean is_valid_str_dateTime_formate(String str_date) {
    boolean flag = false;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      if (str_date.equals(date.format(date.parse(str_date)))) {
        flag = true;
      }
    } catch (ParseException e) {
    }
    return flag;
  }

  /**
   * 获取月序列 201004
   *
   * @return
   */
  public static String getCurrentYearMonth() {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    if (month < 10) {
      return year + "0" + month;
    } else {
      return year + "" + month;
    }
  }

  /**
   * 比较两个日期
   *
   * @param date1
   * @param date2
   * @return boolean
   */
  public static boolean compareTwoDate(String date1, String date2) {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date ndate1 = null, ndate2 = null;
    try {
      ndate1 = (Date) format.parseObject(date1);
      ndate2 = (Date) format.parseObject(date2);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    if (ndate1.after(ndate2)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 比较两个日期間隔天數
   *
   * @param date1
   * @param date2
   * @return boolean
   */
  public static Long getBetweenDays(String date1, String date2) {
    long days = new Long(0);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date ndate1 = null, ndate2 = null;
    try {
      ndate1 = (Date) format.parseObject(date1);
      ndate2 = (Date) format.parseObject(date2);
      Calendar beginCalendar = Calendar.getInstance();
      Calendar endCalendar = Calendar.getInstance();
      beginCalendar.setTime(ndate1);
      endCalendar.setTime(ndate2);
      // 计算天数
      while (beginCalendar.before(endCalendar)) {
        days++;
        beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Long(days);
  }

  /**
   * 格式化时间,默人返回yyyy-MM-dd HH:mm:ss格式
   *
   * @param date
   * @return
   */
  public static String formatDateToString(Date date) {
    return DATETIME_FORMAT.format(date);
  }

  public static String getCurrTime() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strTime = "";
    try {
      strTime = format.format(new Date());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return strTime;
  }

  public static String formatStringToDate2(String date2) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(Long.valueOf(date2));
    String currtime = sdf.format(date);
    return currtime;
  }

  public static long formatStringToLong(String timestr) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    long time = 0;
    try {
      date = sdf.parse(timestr);
      time = date.getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }

  /*
   * 计算N天后的时间 传入参数为:天数
   */
  public static String DayAfterTime(Integer days) {
    Date now = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);
    cal.add(Calendar.DAY_OF_MONTH, days);
    Date afterTime = cal.getTime();
    String ttl = DateUtils.formatDateToString(afterTime);
    return ttl;
  }

}
