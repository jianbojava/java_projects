package com.cocosh.member.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Level;

public interface LevelService {
	boolean add(Level po);
	boolean del(Integer flg,String ids);
	boolean update(Level po);
	Page<Level> queryPage(BaseConditionVO vo);
	Level queryById(String id);
	Level queryMin();
}
