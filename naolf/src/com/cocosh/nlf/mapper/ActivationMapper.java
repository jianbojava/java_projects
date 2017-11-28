package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Activation;

public interface ActivationMapper {
	Integer add(Activation po);
	Integer del(String[] ids);
	Integer update(Activation po);
	List<Activation> queryPage(BaseConditionVO vo);
	Activation queryById(String id);
	Activation checkNumber(String number);
	Activation queryByNumber(String number);
}
