package com.example.util;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WangShiXiang on 2017/3/30.
 * 判断此时的节次
 */
public class TimeUtil {

    //返回一周中的第几天
    public static int getSection(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        int count=calendar.get(Calendar.DAY_OF_WEEK);
        int[] day=new int[]{7,1,2,3,4,5,6};
        int section=(day[count-1]-1)*4+getDaySection();
        return section;
    }

    private static int getDaySection(){
        LocalTime localTime=LocalTime.now();
        //每节课的起始时间，都会提前十分钟
        return getSection(localTime);
    }
    //第几节
    private static int getSection(LocalTime localTime){
        //每节课的起始时间，都会提前十分钟
        LocalTime oneSection=LocalTime.of(8,00);
        LocalTime twoSection=LocalTime.of(9,50);
        LocalTime threeSection=LocalTime.of(13,20);
        LocalTime fourSection=LocalTime.of(15,10);

        if(localTime.isAfter(fourSection)){
            return 4;
        }else if (localTime.isAfter(threeSection)){
            return 3;
        }else if(localTime.isAfter(twoSection)){
            return 2;
        }else if (localTime.isAfter(oneSection)){
            return 1;
        }else {
            return 0;
        }
    }

    public static int getSpecifyTimeSection(LocalDateTime localDateTime){
        DayOfWeek dayOfWeek=localDateTime.getDayOfWeek();
        int count=(dayOfWeek.getValue()-1)*4+getSection(localDateTime.toLocalTime());
        return count;
    }

    public static   Date switchDate(String str){
        String[] date=str.split("-");
        LocalDateTime localDateTime=LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(date[3]),Integer.parseInt(date[4]));
        ZoneId zoneId=ZoneId.systemDefault();
        Instant instant=localDateTime.atZone(zoneId).toInstant();
        Date date1=Date.from(instant);
        return date1;

    }

    public static void main(String[] args){

        String dat="2017-04-09-13-35";
        String[] date=dat.split("-");
        LocalDateTime localDateTime=LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(date[3]),Integer.parseInt(date[4]));
       System.out.println(getSpecifyTimeSection(localDateTime));
       System.out.println(getSection());
    }


}
