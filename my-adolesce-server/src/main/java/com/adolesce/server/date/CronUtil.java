package com.adolesce.server.date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * Cron表达式工具类
 *
 * @author wangbinzhe
 * @DATE 2022/7/12 15:35
 */
public class CronUtil {

    public static String createCronExpression(TaskScheduleModel taskScheduleModel) {
        StringBuffer cronExp = new StringBuffer("");

        if (taskScheduleModel.second.equals(0)) {
            //秒
            cronExp.append("* ");
        } else if (taskScheduleModel.minute.equals(0) && taskScheduleModel.hour.equals(0)) {
            cronExp.append("*/").append(taskScheduleModel.getSecond()).append(" ");
        } else {
            cronExp.append(taskScheduleModel.getSecond()).append(" ");
        }

        if (taskScheduleModel.minute.equals(0)) {
            //分
            cronExp.append("* ");
        } else if (taskScheduleModel.hour.equals(0)) {
            cronExp.append("*/").append(taskScheduleModel.getMinute()).append(" ");
        } else {
            cronExp.append(taskScheduleModel.getMinute()).append(" ");
        }

        if (taskScheduleModel.hour.equals(0)) {
            //小时
            cronExp.append("* ");
        } else {
            cronExp.append("*/").append(taskScheduleModel.getHour()).append(" ");
        }

        //每天
        cronExp.append("* ");//日
        cronExp.append("* ");//月
        cronExp.append("?");//周

        return cronExp.toString();
    }

    public static String createDescription(TaskScheduleModel taskScheduleModel) {
        StringBuffer description = new StringBuffer("");

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            //按每天
            description.append("每过");
            description.append(taskScheduleModel.getHour()).append("时");
            description.append(taskScheduleModel.getMinute()).append("分");
            description.append(taskScheduleModel.getSecond()).append("秒");
            description.append("执行");

        }
        return description.toString();
    }

    public static TaskScheduleModel getTaskScheduleModel(Date d1, Date d2) {
        String date = DateUtil.formatBetween(d1, d2);

        Integer hour = 0;
        Integer minute = 0;
        Integer second = 0;

        if (date.contains("小时")) {
            hour = Integer.valueOf(date.substring(0, date.indexOf("小时")));
        }
        if (date.contains("分")) {
            minute = Integer.valueOf(date.substring(date.indexOf("时") + 1, date.indexOf("分")));
        }
        if (date.contains("秒")) {
            second = Integer.valueOf(date.substring(date.indexOf("分") + 1, date.indexOf("秒")));
        }
        return new TaskScheduleModel(second, minute, hour);
    }

    public static TaskScheduleModel getTaskScheduleModel(Long time) {
        String date = DateUtil.formatBetween(time);

        Integer hour = 0;
        Integer minute = 0;
        Integer second = 0;

        if (date.contains("天")) {
            throw new RuntimeException("当前为: " + date + ",超过24h了");
        }
        if (date.contains("小时")) {
            hour = Integer.valueOf(date.substring(0, date.indexOf("小时")));
        }
        if (date.contains("分")) {
            minute = Integer.valueOf(date.substring(date.indexOf("时") + 1, date.indexOf("分")));
        }
        if (date.contains("秒")) {
            second = Integer.valueOf(date.substring(date.indexOf("分") + 1, date.indexOf("秒")));
        }
        return new TaskScheduleModel(second, minute, hour);
    }

    public static void main(String[] args) throws InterruptedException {
        Long date1 = 1657607139642L;
        Long date2 = 1657710261642L;

        TaskScheduleModel taskScheduleModel = CronUtil.getTaskScheduleModel(date2 - date1);

        System.out.println(taskScheduleModel);
        System.out.println(CronUtil.createCronExpression(taskScheduleModel));
        System.out.println(CronUtil.createDescription(taskScheduleModel));

    }

}