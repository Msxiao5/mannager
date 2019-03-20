package com.xiao5.mannager.core.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间获取及格式转换工具类
 * @author Wu Tianbing
 */
public class DateUtils {
    private static ThreadLocal<DateFormat> jtThreadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public static DateUtils jtDateFormat = new DateUtils(jtThreadLocal);

    private ThreadLocal<DateFormat> threadLocal;

    private DateUtils(ThreadLocal<DateFormat> threadLocal){
        this.threadLocal = threadLocal;
    }

    public Date parse(String value) {
        try {
            if(StringUtils.isEmpty(value)){
                return null;
            }
            return this.threadLocal.get().parse(value);
        } catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
    }

    public String format(Date value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        return this.threadLocal.get().format(value);
    }
    
    /**
     * 获取YYYYMMDD格式的日期字符串.
     * 
     * @param date
     *            时间
     * @return 时间格式YYYYMMDD
     * @author Wu Tianbing
     * 
     */
    public static String getYYYYMMDD(Date date) {        
        final StringBuffer backstr = new StringBuffer();
        
        if (date == null) {
            date = new Date();
        }
        final SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
        final String dateStr = time.format(date);
        backstr.append(dateStr);
        return backstr.toString();
    }
    public static Date getCurrentDate() {
        return new Date();
    }
}