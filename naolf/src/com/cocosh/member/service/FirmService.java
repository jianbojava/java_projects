package com.cocosh.member.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Firm;

public interface FirmService {
	boolean add(Firm po);
	boolean del(Integer flg,String ids);
	boolean update(Firm po);
	boolean recharge(Firm po);
	Page<Firm> queryPage(BaseConditionVO vo);
	Firm queryById(String id);
	List<Firm> queryAll();
}
