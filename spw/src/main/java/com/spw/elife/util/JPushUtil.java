package com.spw.elife.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {
	
	 public static PushPayload buildPushObject_android_tag_alertWithTitle(Map<String, String> map,List<String> list) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.tag(list))
	                .setNotification(Notification.android("民盛消息", map.get("title"), map))
	                .build();
	 }
	 /**
	  * 发送推送
	  */
	 public static void sendMessage(Map<String, String> map,List<String> list) {
		JPushClient jPushClient = new JPushClient("e6fe7802d75b36f6e8d82c4f","694f24eb6896836252805cbe");
		PushPayload payload = buildPushObject_android_tag_alertWithTitle(map,list);
		try {
            PushResult result = jPushClient.sendPush(payload);
            System.out.println("推送结果-——>" + result);
        } catch (APIConnectionException e) {
        	 System.out.println("推送异常-——>" + "连接超时！");
        } catch (APIRequestException e) {
        	 System.out.println("推送异常-——>" + "请求异常，可能所推送的人没在使用！");
        }
	}
	 
	 public static void main(String[] args) {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("title", "名声");
		 map.put("content", "xiaoxi");
		 List<String> list =new ArrayList<>();
		 list.add("28");
		 sendMessage(map, list);
	}
//	 public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
//    return PushPayload.newBuilder()
//            .setPlatform(Platform.android())
//            .setAudience(Audience.tag_and("tag1", "tag_all"))
//            .setNotification(Notification.newBuilder()
//                    .addPlatformNotification(IosNotification.newBuilder()
//                            .setAlert("")
//                            .setBadge(5)
//                            .setSound("happy.caf")
//                            .addExtra("from", "JPush")
//                            .build())
//                    .build())
//             .setMessage(Message.content("12312"))
//             .setOptions(Options.newBuilder()
//                     .setApnsProduction(true)
//                     .build())
//             .build();
//}
}
