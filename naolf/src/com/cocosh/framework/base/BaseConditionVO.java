package com.cocosh.framework.base;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cocosh.framework.util.CommonUtil;

public class BaseConditionVO {
	private int pageNo = 1;
	private int pageSize = CommonUtil.PAGESIZE;
	private String keywords;// 关键字
	private String orderField;// 排序字段
	private String orderDirection;// 排序方式

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate2;

	// ID
	private String id;
	// 会员ID
	private String mem_id;
	// 类型
	private String type;
	// 网点
	private String dot_id;
	// 车位
	private String park_id;
	// 品牌
	private String brand_id;
	// 经度
	private Double longitude;
	// 纬度
	private Double latitude;
	// 是否查阅
	private String is_read;
	// 企业ID
	private String firm_id;
	// 调度员ID
	private String dispatch_id;
	// 状态
	private String status;
	// 审核
	private String review;
	// 状态
	private String enabled;
	// 星级
	private String star;
	// 系统用户ID
	private String user_id;
	// 验证码
	private String validCode;
	// 手机号
	private String mobile;
	// 支付密码
	private String pay_pwd;
	// 支付金额
	private String pay_amount;
	// 支付方式
	private String pay_type;
	// 订单号
	private String order_sn;
	//部门id
	private String depart_id;
	//会员类型
	private Integer user_type;
	private Integer is_locked;//是否锁定 0正常 1锁定
	//课程id或卡券id
	private String conn_id;
	private String recharge_id;//充值记录id
	private String course_id;//发布的课程的id;
	private Integer ticket_type;//0卡，1券
	private Integer used;//使用状态  0未使用，1已使用
	// API接口
	private String timeStamp;// 时间戳
	private String credential;// 证书值
	private String summary;// 签名
	
	private String operat_id ;//操作者
	
	


	public String getConn_id() {
		return conn_id;
	}
	
	

	public String getRecharge_id() {
		return recharge_id;
	}



	public void setRecharge_id(String recharge_id) {
		this.recharge_id = recharge_id;
	}



	public void setConn_id(String conn_id) {
		this.conn_id = conn_id;
	}
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}

	public Date getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getType() {
		return "".equals(type) ? null : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDot_id() {
		return "".equals(dot_id) ? null : dot_id;
	}

	public void setDot_id(String dot_id) {
		this.dot_id = dot_id;
	}

	public String getPark_id() {
		return "".equals(park_id) ? null : park_id;
	}

	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}

	public String getBrand_id() {
		return "".equals(brand_id) ? null : brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return "desc".equals(orderDirection) ? "desc" : "asc";
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getKeywords() {
		return "".equals(keywords) ? null : keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIs_read() {
		return is_read;
	}

	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}

	public String getFirm_id() {
		return "".equals(firm_id) ? null : firm_id;
	}

	public void setFirm_id(String firm_id) {
		this.firm_id = firm_id;
	}

	public String getDispatch_id() {
		return "".equals(dispatch_id) ? null : dispatch_id;
	}

	public void setDispatch_id(String dispatch_id) {
		this.dispatch_id = dispatch_id;
	}

	public String getStatus() {
		return "".equals(status) ? null : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReview() {
		return "".equals(review) ? null : review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getEnabled() {
		return "".equals(enabled) ? null : enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getStar() {
		return "".equals(star) ? null : star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getUser_id() {
		return "".equals(user_id) ? null : user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getValidCode() {
		return "".equals(validCode) ? null : validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getMobile() {
		return "".equals(mobile) ? null : mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPay_pwd() {
		return "".equals(pay_pwd) ? null : pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}

	public String getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}

	public String getPay_type() {
		return "".equals(pay_type) ? null : pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	
	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}
	
	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	 public String getCourse_id() {
			return course_id;
		}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public Integer getIs_locked() {
		return is_locked;
	}

	public void setIs_locked(Integer is_locked) {
		this.is_locked = is_locked;
	}
	
	public String getOperat_id() {
		return operat_id;
	}
	public void setOperat_id(String operat_id) {
		this.operat_id = operat_id;
	}
	public Integer getTicket_type() {
		return ticket_type;
	}
	public void setTicket_type(Integer ticket_type) {
		this.ticket_type = ticket_type;
	}
	
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}


}
