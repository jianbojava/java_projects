package com.cocosh.sys.model;

import com.cocosh.framework.base.BaseEntity;

public class Version extends BaseEntity {
	private String version;// 版本号
	private String descript;// 版本说明
	private String url;// 下载链接
	private Integer type;// 类型 0：Android 1：ios
	private Integer must;// 更新 0：非强制 1：强制更新

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMust() {
		return must;
	}

	public void setMust(Integer must) {
		this.must = must;
	}

}