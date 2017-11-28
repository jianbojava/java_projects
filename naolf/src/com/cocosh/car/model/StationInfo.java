package com.cocosh.car.model;

import java.io.Serializable;

public class StationInfo implements Serializable {
	private String stationID;// 充电站ID
	private String operatorID;// 运营商ID
	private String equipmentOwnerID;// 设备所属方ID
	private String stationName;// 充电站名称
	private String countryCode;// 充电站国家代码
	private String areaCode;// 充电站省市辖区编码
	private String address;// 详细地址
	private String stationTel;// 站点电话
	private String serviceTel;// 服务电话
	private Integer stationType;// 站点类型 1：公共 50：个人 100：公交（专用） 101：环卫（专用）
								// 102：物流（专用） 103：出租车（专用） 255：其他
	private Integer stationStatus;// 站点状态 0：未知 1：建设中 5：关闭下线 6：维护中 50：正常使用
	private Integer parkNums;// 车位数量 可停放进行充电的车位总数，默认：0 未知
	private Double stationLng;// 经度
	private Double stationLat;// 纬度
	private String siteGuide;// 站点引导
	private Integer construction;// 建设场所 1：居民区 2：公共机构 3：企事业单位 4：写字楼 5：工业园区
									// 6：交通枢纽 7：大型文体设施 8：城市绿地 9：大型建筑配建停车场
									// 10：路边停车位 11：城际高速服务区 255：其他
	private String pictures;// 站点照片
	private String matchCars;// 描述该站点接受的车大小以及类型，如大巴、物流车、私家乘用车、出租车等
	private String parkInfo;// 车位楼层及数量描述
	private String busineHours;// 营业时间
	private Double elePrice;// 电费
	private Double serPrice;// 服务费
	private String electricityFee;// 充电费描述
	private String serviceFee;// 服务费率描述
	private String parkFee;// 停车费率描述
	private String payment;// 支付方式:刷卡、线上、现金 其中电子钱包类卡为刷卡，身份鉴权卡、微信/支付宝、APP为线上
	private Integer supportOrder;// 充电设备是否需要提前预约后才能使用。0为不支持预约、1为支持预约。不填默认为0
	private String remark;// 其他备注信息
	private Integer enabled;// 0 正常 1：禁用
	private Integer type;// 类型 0:智行家桩 1：特来电

	/***** 非数据库字段 ******/
	private Double distances = 0.00;// 距离
	private Integer totalConnNum;// 空闲接口数
	private String operatorName;// 运营商名称
	private Integer quickConnNum = 0;// 0 慢充 1快充
	private Double comment_star;// 评论星级

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getEquipmentOwnerID() {
		return equipmentOwnerID;
	}

	public void setEquipmentOwnerID(String equipmentOwnerID) {
		this.equipmentOwnerID = equipmentOwnerID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStationTel() {
		return stationTel;
	}

	public void setStationTel(String stationTel) {
		this.stationTel = stationTel;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public Integer getStationType() {
		return stationType;
	}

	public void setStationType(Integer stationType) {
		this.stationType = stationType;
	}

	public Integer getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(Integer stationStatus) {
		this.stationStatus = stationStatus;
	}

	public Double getElePrice() {
		return elePrice;
	}

	public void setElePrice(Double elePrice) {
		this.elePrice = elePrice;
	}

	public Integer getParkNums() {
		return parkNums;
	}

	public void setParkNums(Integer parkNums) {
		this.parkNums = parkNums;
	}

	public Double getStationLng() {
		return stationLng;
	}

	public void setStationLng(Double stationLng) {
		this.stationLng = stationLng;
	}

	public Double getStationLat() {
		return stationLat;
	}

	public void setStationLat(Double stationLat) {
		this.stationLat = stationLat;
	}

	public String getSiteGuide() {
		return siteGuide;
	}

	public void setSiteGuide(String siteGuide) {
		this.siteGuide = siteGuide;
	}

	public Integer getConstruction() {
		return construction;
	}

	public void setConstruction(Integer construction) {
		this.construction = construction;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getMatchCars() {
		return matchCars;
	}

	public void setMatchCars(String matchCars) {
		this.matchCars = matchCars;
	}

	public String getParkInfo() {
		return parkInfo;
	}

	public void setParkInfo(String parkInfo) {
		this.parkInfo = parkInfo;
	}

	public String getBusineHours() {
		return busineHours;
	}

	public void setBusineHours(String busineHours) {
		this.busineHours = busineHours;
	}

	public Double getSerPrice() {
		return serPrice;
	}

	public void setSerPrice(Double serPrice) {
		this.serPrice = serPrice;
	}

	public String getElectricityFee() {
		return electricityFee;
	}

	public void setElectricityFee(String electricityFee) {
		this.electricityFee = electricityFee;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getParkFee() {
		return parkFee;
	}

	public void setParkFee(String parkFee) {
		this.parkFee = parkFee;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getSupportOrder() {
		return supportOrder;
	}

	public void setSupportOrder(Integer supportOrder) {
		this.supportOrder = supportOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getDistances() {
		return distances;
	}

	public void setDistances(Double distances) {
		this.distances = distances;
	}

	public Integer getTotalConnNum() {
		return totalConnNum;
	}

	public void setTotalConnNum(Integer totalConnNum) {
		this.totalConnNum = totalConnNum;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getQuickConnNum() {
		return quickConnNum;
	}

	public void setQuickConnNum(Integer quickConnNum) {
		this.quickConnNum = quickConnNum;
	}

	public Double getComment_star() {
		return comment_star;
	}

	public void setComment_star(Double comment_star) {
		this.comment_star = comment_star;
	}

}