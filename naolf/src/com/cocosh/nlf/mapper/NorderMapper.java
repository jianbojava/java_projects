package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Norder;
import com.cocosh.sys.model.User;

public interface NorderMapper {
	Integer add(Norder po);
	Integer del(String[] ids);
	Integer update(Norder po);
	List<Norder> queryPage(BaseConditionVO vo);
	Norder queryById(String id);
	List<Norder> queryByAll(Norder norder);
	List<Norder> queryLesson(Norder norder);
	List<Norder> queryTicket(Norder norder);
	Norder queryBySn(String sn);
}
