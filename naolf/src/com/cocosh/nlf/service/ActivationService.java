package com.cocosh.nlf.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Activation;

public interface ActivationService {
	boolean add(Activation po);
	boolean del(String ids);
	boolean update(Activation po);
	Page<Activation> queryPage(BaseConditionVO vo);
	Activation queryById(String id);
	Activation checkNumber(String number);
	boolean add(Integer num);//添加多少个激活码
}
