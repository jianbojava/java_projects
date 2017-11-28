package com.cocosh.framework.quarz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cocosh.car.service.ChargeService;
import com.cocosh.car.service.IllegalService;
import com.cocosh.car.service.OrderService;
import com.cocosh.nlf.service.AppointService;

/**
 * 任务调度
 * 
 * @author jerry
 */
@Component
public class Task {
	@Autowired
	private OrderService service;
	@Autowired
	private ChargeService chargeService;
	@Autowired
	private IllegalService illegalService;
	@Autowired
	private AppointService appointService;
	
	private final Logger logger=Logger.getLogger(Task.class);
	
	/**
	 * 每5分钟执行一次
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void sendReadySms() {
//		/**
//		 * 每5分钟检索订单发送备车提醒（24小时）
//		 */
//		logger.info("-----send ready msg-----");
//		service.sendReadyMsg2Disp();
//		/**
//		 * 每5分钟检索订单发送还车提醒（30分钟）
//		 */
//		logger.info("-----send return msg-----");
//		service.sendReturnMsg2Disp();
//		/**
//		 * 每5分钟检查未取车订单提醒（20分钟）
//		 */
//		logger.info("-----check expired order-----");
//		service.sendExpiredOrder();
//		/**
//		 * 每5分钟检查未支付订单取消
//		 */
//		logger.info("-----cancel nopay order-----");
//		service.cancelOrder();
	}
	
	/**
	 * 每天0点0分更新前一天违章记录
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void illegalInfo(){
//		logger.info("-----sync illegal info-----");
//		illegalService.add();
//		logger.info("-----update token-----");
//		chargeService.update_token();
//		logger.info("-----update station info-----");
//		chargeService.query_stations_info();
	}
	
//	/**
//	 * 每周一0点0分获取充电桩token（有效期7天）
//	 */
//	@Scheduled(cron="0 0 0 ? * MON")
//	public void updateToken(){
//		logger.info("-----update token-----");
//		chargeService.updateToken();
//		logger.info("-----update station info-----");
//		chargeService.updateStationsInfo();
//	}
	
	/**
	 * 每天18点0分添加提醒上课消息
	 */
	@Scheduled(cron="0 0 18 * * ?")
	public void appointRemind(){
		logger.info("-----query--remind-----");
		appointService.addRemind();
		logger.info("-----end remind-----");
	}
	
}
