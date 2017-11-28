package com.cocosh.car.model;

import java.io.Serializable;
import java.util.Date;

public class ConnectorInfo implements Serializable {
	private String connectorID;// 充电设备接口编码
	private String connectorName;// 充电设备接口名称
	private Integer connectorType;// 充电设备接口类型 1：家用插座（模式2） 2：交流接口插座（模式3，连接方式B ）
									// 3：交流接口插头（带枪线，模式3，连接方式C） 4：直流接口枪头（带枪线，模式4）
	private Integer voltageUpperLimits;// 额定电压上限
	private Integer voltageLowerLimits;// 额定电压下限
	private Integer current;// 额定电流
	private Double power;// 额定功率
	private String parkNo;// 车位号
	private String stationID;// 充电站ID
	private String equipmentID;// 设备ID
	private Date create_date;
	private Date modify_date;
	private Integer status;// 状态 0：离网 1：空闲 2：占用（未充电） 3：占用（充电中） 4：占用（预约锁定） 255：故障
	private Integer enabled;// 0 正常 1禁用

	// 非数据字段
	private String stationName;// 充电站名称
	private Integer freeQuickNum;// 空闲快充数
	private Integer totalQuickNum;// 全部快充数
	private Integer freeSlowNum;// 空闲慢充数
	private Integer totalSlowNum;// 全部慢充数

	public String getConnectorID() {
		return connectorID;
	}

	public void setConnectorID(String connectorID) {
		this.connectorID = connectorID;
	}

	public String getConnectorName() {
		return connectorName;
	}

	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName;
	}

	public Integer getConnectorType() {
		return connectorType;
	}

	public void setConnectorType(Integer connectorType) {
		this.connectorType = connectorType;
	}

	public Integer getVoltageUpperLimits() {
		return voltageUpperLimits;
	}

	public void setVoltageUpperLimits(Integer voltageUpperLimits) {
		this.voltageUpperLimits = voltageUpperLimits;
	}

	public Integer getVoltageLowerLimits() {
		return voltageLowerLimits;
	}

	public void setVoltageLowerLimits(Integer voltageLowerLimits) {
		this.voltageLowerLimits = voltageLowerLimits;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getParkNo() {
		return parkNo;
	}

	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getFreeQuickNum() {
		return freeQuickNum;
	}

	public void setFreeQuickNum(Integer freeQuickNum) {
		this.freeQuickNum = freeQuickNum;
	}

	public Integer getTotalQuickNum() {
		return totalQuickNum;
	}

	public void setTotalQuickNum(Integer totalQuickNum) {
		this.totalQuickNum = totalQuickNum;
	}

	public Integer getFreeSlowNum() {
		return freeSlowNum;
	}

	public void setFreeSlowNum(Integer freeSlowNum) {
		this.freeSlowNum = freeSlowNum;
	}

	public Integer getTotalSlowNum() {
		return totalSlowNum;
	}

	public void setTotalSlowNum(Integer totalSlowNum) {
		this.totalSlowNum = totalSlowNum;
	}

}