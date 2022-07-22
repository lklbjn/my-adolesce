package com.adolesce.server.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转Cron
 *
 * @author wangbinzhe
 * @DATE 2022/7/12 15:13
 */
public class TimeToCron {
    /***
     *  功能描述：日期转换cron表达式
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
    /***
     * convert Date to cron ,eg.  "0 07 10 15 1 ? 2016"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(java.util.Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    public static void main(String[] args) {
        Long unixtimestamp = 1234L;

        System.out.println(new Date(unixtimestamp*1000));



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date(unixtimestamp * 1000));
        System.out.println(format1);//1970-01-01 08:20:34
    }
}
