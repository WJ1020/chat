package com.example.service;

import com.example.entity.CLICKMessage;
import com.example.entity.FollowMessage;
import com.example.entity.LocationEventMessage;
import com.example.entity.ScanMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 对事件的分类
 */
@Service
public class EventService {
    @Resource
    private RespEventService respEventService;

    public Object event(Map<String,String> map){
        if (map.get("Event").equalsIgnoreCase("subscribe")||map.get("Event").equalsIgnoreCase("unsubscribee")){
            FollowMessage followMessage=new FollowMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("Event"));
            return respEventService.event(followMessage);
        }else if (map.get("Event").equalsIgnoreCase("SCAN")){
            ScanMessage scanMessage=new ScanMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("Event"),new Integer(map.get("EventKey")),map.get("Ticket"));
            return respEventService.event(scanMessage);
        }else if(map.get("Event").equalsIgnoreCase("LOCATION")){
            LocationEventMessage locationEventMessage=new LocationEventMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("Event"),new Double(map.get("Latitude")),new Double(map.get("Longitude")),new Double(map.get("Precision")));
            return respEventService.event(locationEventMessage);
        }else if(map.get("Event").equalsIgnoreCase("CLICK")){
            CLICKMessage clickMessage=new CLICKMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("Event"),map.get("EventKey"));
            return respEventService.event(clickMessage);
        }
        return null;
    }
}
