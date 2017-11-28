package com.cocosh.nlf.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Uprule;

public interface UpruleService {
	boolean add(Uprule po);
	boolean del(String ids);
	boolean update(Uprule po);
	Page<Uprule> queryPage(BaseConditionVO vo);
	Uprule queryById(String id);
	Uprule queryone();
    boolean validUprule(Uprule po);//判断升级后的卡券的课程是否==原始卡和券的总课程
}
