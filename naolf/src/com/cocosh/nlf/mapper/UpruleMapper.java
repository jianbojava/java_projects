package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Uprule;

public interface UpruleMapper {
	Integer add(Uprule po);
	Integer del(String[] ids);
	Integer update(Uprule po);
	List<Uprule> queryPage(BaseConditionVO vo);
	Uprule queryById(String id);
	Uprule queryone();
	String queryticket_id2();
}
