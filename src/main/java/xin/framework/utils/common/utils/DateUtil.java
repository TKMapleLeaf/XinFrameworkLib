package xin.framework.utils.common.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xin.framework.utils.android.Loger.Log;

/**
 * Created by Xin on 2015/12/15.
 * 时间工具类
 */
public class DateUtil {

    public static class Format {

        public static final String YEAR_MOUTH_DAY_HOUR_MINUTE_SECOND               = "yyyy-MM-dd HH:mm:ss";
        public static final String YEAR_MOUTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND   = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String YEAR_MOUTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND_1 = "yyyy-MM-dd HH:mm:ss SSS";
        public static final String CRASH_FILE_NAME_DATE_FORMAT                     = "yyyyMMdd-HHmmss-SSS";
        public static final String LOG_FILE_NAME_DATE_FORMAT                       = "yyyyMMdd-HH";
        public static final String LOG_MSG_DATE_FORMAT                             = "MM-dd HH:mm:ss.SSS";
        public static final String LOG_DIR_DATE_FORMAT                             = "yyyy_MM_dd";
        public static final String REGISTER_DATE_FORMAT                            = "yyyy/MM/dd";
        public static final String TRACE_LOG_FORMAT                                = "yyyyMMddHHmmss";
        public static final String CN_DEFAULT_FORMAT                               = "yyyy年MM月dd日 HH时mm分ss秒";
        public static final String CN_YEAR_FORMAT                                  = "yyyy年";
        public static final String LOG_DIR_DATE_FORMAT_2                           = "yyyy-MM-dd";
        public static final String YEAR_FORMAT                                     = "yyyy";
        public static final String YEAR_MOUTH_DAY_HOUR_MINUTE                      = "yyyy-MM-dd HH:mm";
        public static final String MOUTH_DAY                                       = "MM月dd日";
        public static final String MOUTH_DAY_SAMPLE                                = "M月d日";
        public static final String TIME                                            = "HH:mm:ss";
        public static final String HOUR_MINUTE                                     = "HH:mm";
    }

    /**
     * 获取当前时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取一定格式的时间
     *
     * @param format
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormatTime(String format,
                                       long time) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate,
                                 String format) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);

            return formatter.parse(strDate,
                                             pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static String strToStr(String strDate,
                                  String format) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date strToDate = formatter.parse(strDate,
                                             pos);

            return formatter.format(strToDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @param strDate
     *            2016-10-18

     * @return
     */
    public static String[] formatStrDate(String strDate,
                                         String splitStr) {
        return strDate.split(splitStr);
    }

    public static String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm",
                                    Locale.CHINA).format(new Date());
    }

    public static long getMills(String time) {

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(Format.YEAR_MOUTH_DAY_HOUR_MINUTE_SECOND);
            Date date;
            date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
            Log.e("",ex);
        }
        return 0;

    }

    /**
     *
     * @param time
     * @param format
     * @return
     */
    public static long getMills(String time,
                                String format) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date;
            date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
            Log.e("",ex);
        }
        return 0;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE,
              1);
        a.roll(Calendar.DATE,
               -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year,
                                         int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR,
              year);
        a.set(Calendar.MONTH,
              month - 1);
        a.set(Calendar.DATE,
              1);
        a.roll(Calendar.DATE,
               -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfWeek = "-1";
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfWeek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfWeek;
    }



    public static String getWeek(Date date){
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }




}
