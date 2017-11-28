package com.cocosh.member.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Firm;

public interface FirmMapper {
	Integer add(Firm po);
	Integer del(Map<String,Object> map);
	Integer update(Firm po);
	List<Firm> queryPage(BaseConditionVO vo);
	Firm queryById(String id);
	List<Firm> queryAll();
	Integer updateAccount(Firm po);
	List<Firm> queryFirmByExcel(List<String> names);
}
