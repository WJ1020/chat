package com.example.util;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WangShiXiang on 2017/3/30.
 * 判断此时的节次
 */
public class TimeUtil {

    //返回周几
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

    public static void main(String[] args){
        System.out.println(getSection());
    }
}
