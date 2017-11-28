package com.cocosh.sys.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.cocosh.framework.base.BaseEntity;

/**
 * 用户
 * 
 * @author jerry
 */
public class User extends BaseEntity {
	private String username;// 用户名
	private String password;// 密码
	private String nickname;// 昵称
	private String mobile;//联系电话
	private String head_img;// 头像
	private String login_ip;// 最后登录IP
	private Integer is_locked;// 是否锁定 0正常 1锁定
	private Integer fail_count;// 登录失败次数
	private String org_id;// 组织架构节点
	private String type;// 用户类型 0系统用户 1商户 3销售 4会员or消费者
	//nlf新增
	private String depart_id;//部门id
	private String name;//姓名
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;//出生日期
	private String card;//身份证
	private Integer age;//年龄
	private Integer gender;//性别0男1女
	private String address;//地址
	private String email;//邮箱
	private String bank_name;//银行名称
	private String bank_no;//银行卡号
	private String bank_user;//银行开户人
	private String wx_no;//微信账号
	private String ali_no;//支付宝账号
	private String refer_number;//推荐人编号
	private String number;//自己的编号; 
    private Integer user_type;//类型：0员工，1合伙人，2会员     
	private Integer partner_type;//合伙人类型；1，高级，2事业，3，钻石
	private Integer enabled;//员工的审核 0未审核(或未激活) 1审核成功(或激活成功)2,审核失败
	
	private String province_id;
 	private String province_name;
	private String city_id;
 	private String city_name;
 	private String region_id;
 	private String region_name;
	private Integer dispacth_grade ;//提成身份等级
	/** 非数据库字段 **/
	private String roles;// 角色集合(辅助属性)
	private List<String> role_names;// 角色名集合
	private int is_reset;// 重置密码
	private String departs;//查看部门ids
	private String depart_name;//部门名称
	
	public Integer getDispacth_grade() {
		return dispacth_grade;
	}

	public void setDispacth_grade(Integer dispacth_grade) {
		this.dispacth_grade = dispacth_grade;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public Integer getIs_locked() {
		return is_locked;
	}

	public void setIs_locked(Integer is_locked) {
		this.is_locked = is_locked;
	}

	public Integer getFail_count() {
		return fail_count;
	}

	public void setFail_count(Integer fail_count) {
		this.fail_count = fail_count;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<String> getRole_names() {
		return role_names;
	}

	public void setRole_names(List<String> role_names) {
		this.role_names = role_names;
	}

	public int getIs_reset() {
		return is_reset;
	}

	public void setIs_reset(int is_reset) {
		this.is_reset = is_reset;
	}


	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getBank_user() {
		return bank_user;
	}

	public void setBank_user(String bank_user) {
		this.bank_user = bank_user;
	}

	public String getWx_no() {
		return wx_no;
	}

	public void setWx_no(String wx_no) {
		this.wx_no = wx_no;
	}

	public String getAli_no() {
		return ali_no;
	}

	public void setAli_no(String ali_no) {
		this.ali_no = ali_no;
	}

	public String getRefer_number() {
		return refer_number;
	}

	public void setRefer_number(String refer_number) {
		this.refer_number = refer_number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public Integer getPartner_type() {
		return partner_type;
	}

	public void setPartner_type(Integer partner_type) {
		this.partner_type = partner_type;
	}
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	 public String getDeparts() {
			return departs;
	}

	public void setDeparts(String departs) {
		this.departs = departs;
	}
	
	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


}
