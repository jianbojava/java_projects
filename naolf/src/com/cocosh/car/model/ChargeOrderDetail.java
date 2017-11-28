package com.cocosh.car.model;

import java.io.Serializable;
import java.util.Date;

public class ChargeOrderDetail implements Serializable {
	private Date detailStartTime;
	private Date detailEndTime;
	private Double elecPrice;
	private Double sevicePrice;
	private Double detailPower;
	private Double detailElecMoney;
	private Double detailSeviceMoney;
	private String orderSn;

	public Date getDetailStartTime() {
		return detailStartTime;
	}

	public void setDetailStartTime(Date detailStartTime) {
		this.detailStartTime = detailStartTime;
	}

	public Date getDetailEndTime() {
		return detailEndTime;
	}

	public void setDetailEndTime(Date detailEndTime) {
		this.detailEndTime = detailEndTime;
	}

	public Double getElecPrice() {
		return elecPrice;
	}

	public void setElecPrice(Double elecPrice) {
		this.elecPrice = elecPrice;
	}

	public Double getSevicePrice() {
		return sevicePrice;
	}

	public void setSevicePrice(Double sevicePrice) {
		this.sevicePrice = sevicePrice;
	}

	public Double getDetailPower() {
		return detailPower;
	}

	public void setDetailPower(Double detailPower) {
		this.detailPower = detailPower;
	}

	public Double getDetailElecMoney() {
		return detailElecMoney;
	}

	public void setDetailElecMoney(Double detailElecMoney) {
		this.detailElecMoney = detailElecMoney;
	}

	public Double getDetailSeviceMoney() {
		return detailSeviceMoney;
	}

	public void setDetailSeviceMoney(Double detailSeviceMoney) {
		this.detailSeviceMoney = detailSeviceMoney;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
}
