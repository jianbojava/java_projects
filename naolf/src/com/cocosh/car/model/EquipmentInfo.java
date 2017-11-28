package com.cocosh.car.model;

import java.io.Serializable;
import java.util.Date;

public class EquipmentInfo implements Serializable {
	private String equipmentID;// 设备编码
	private String manufacturerID;// 设备生产商组织机构代码
	private String equipmentModel;// 设备型号
	private String productionDate;// 设备生产日期
	private Integer equipmentType;// 设备类型 1：直流设备 2：交流设备 3：交直流一体设备
	private Double equipmentLng;// 充电设备经度
	private Double equipmentLat;// 充电设备纬度
	private String stationID;// 充电站ID
	private Date create_date;
	private Date modify_date;
	private Integer enabled;// 0 正常 1禁用

	// 非数据库字段
	private String stationName;// 充电站名称

	public String getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}

	public String getManufacturerID() {
		return manufacturerID;
	}

	public void setManufacturerID(String manufacturerID) {
		this.manufacturerID = manufacturerID;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public Integer getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Double getEquipmentLng() {
		return equipmentLng;
	}

	public void setEquipmentLng(Double equipmentLng) {
		this.equipmentLng = equipmentLng;
	}

	public Double getEquipmentLat() {
		return equipmentLat;
	}

	public void setEquipmentLat(Double equipmentLat) {
		this.equipmentLat = equipmentLat;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

}