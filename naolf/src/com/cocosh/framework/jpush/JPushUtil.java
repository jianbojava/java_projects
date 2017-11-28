package com.cocosh.framework.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送
 * http://docs.jpush.cn/pages/viewpage.action?pageId=557241
 * @author jerry
 */
public class JPushUtil {
	//客户端
	public static String CLIENT_APP_KEY="215a3e51873c7521ece03616";
	public static String CLIENT_APP_SECRET="a50717565e583436a3b75ed3";
	//商户端
	public static String MERCHANT_APP_KEY="75970f8ee72ef1163c37b087";
	public static String MERCHANT_APP_SECRET="c93bf28e10cf5bd915ec4893";
	
	/**
	 * 所有平台推送
	 * @param message
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static PushResult buildPushObject_all_all(int application,String message) {
		try {
			JPushClient jpushClient;
			if (application==0) {
				jpushClient = new JPushClient(CLIENT_APP_SECRET,CLIENT_APP_KEY, 3);
			}else {
				jpushClient = new JPushClient(MERCHANT_APP_SECRET,MERCHANT_APP_KEY, 3);
			}
			PushPayload payload= PushPayload.alertAll(message);
			PushResult result = jpushClient.sendPush(payload);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 所有平台，指定目标推送
	 * @param alias
	 * @param message
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static PushResult buildPushObject_all_alias(int application,String[] alias,String message) {
		try {
			JPushClient jpushClient;
			if (application==0) {
				jpushClient = new JPushClient(CLIENT_APP_SECRET,CLIENT_APP_KEY, 3);
			}else {
				jpushClient = new JPushClient(MERCHANT_APP_SECRET,MERCHANT_APP_KEY, 3);
			}
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias)).setNotification(Notification.alert(message)).build();
			PushResult result = jpushClient.sendPush(payload);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	 /**
	  * Android平台推送
	  * @param message
	  * @return
	  */
	 @SuppressWarnings("deprecation")
	 public static PushResult buildPushObject_android(int application,String message){
		 try {
			 JPushClient jpushClient;
			 	if (application==0) {
					jpushClient = new JPushClient(CLIENT_APP_SECRET,CLIENT_APP_KEY, 3);
				}else {
					jpushClient = new JPushClient(MERCHANT_APP_SECRET,MERCHANT_APP_KEY, 3);
				}
				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all()).setNotification(Notification.alert(message)).build();
				PushResult result = jpushClient.sendPush(payload);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	 }
	 
	 /**
	  * Android平台,指定目标推送
	  * @param message
	  * @return
	  */
	 @SuppressWarnings("deprecation")
	 public static PushResult buildPushObject_android(int application,String[] alias,String message){
		 try {
			 	JPushClient jpushClient;
			 	if (application==0) {
					jpushClient = new JPushClient(CLIENT_APP_SECRET,CLIENT_APP_KEY, 3);
				}else {
					jpushClient = new JPushClient(MERCHANT_APP_SECRET,MERCHANT_APP_KEY, 3);
				}
				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.alias(alias)).setNotification(Notification.alert(message)).build();
				PushResult result = jpushClient.sendPush(payload);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	 }
	 
	 
	 /**
	  * IOS平台推送
	  * @param message
	  * @return
	  */
	 @SuppressWarnings("deprecation")
	 public static PushResult buildPushObject_ios(int application,String message){
		 try {
			 	JPushClient jpushClient;
			 	if (application==0) {
					jpushClient = new JPushClient(CLIENT_APP_SECRET,CLIENT_APP_KEY, 3);
				}else {
					jpushClient = new JPushClient(MERCHANT_APP_SECRET,MERCHANT_APP_KEY, 3);
				}
				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.all()).setNotification(Notification.alert(message)).build();
				PushResult result = jpushClient.sendPush(payload);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	 }
	 
	 /**
	  * IOS平台推送,指定目标推送
	  * @param message
	  * @return
	  */
	 @SuppressWarnings("deprecation")
	 public static PushResult buildPushObject_ios(int application,String[] alias,String message){
		 try {
			 	JPushClient jpushClient;
			 	if (application==0) {
					jpushClient = new JPushClient(CLIENT_APP_SECRET,CLIENT_APP_KEY, 3);
				}else {
					jpushClient = new JPushClient(MERCHANT_APP_SECRET,MERCHANT_APP_KEY, 3);
				}
				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.alias(alias)).setNotification(Notification.alert(message)).build();
				PushResult result = jpushClient.sendPush(payload);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	 }

	public static void main(String[] args) {
		buildPushObject_all_all(0,"特宠队上线啦");
	}
}
