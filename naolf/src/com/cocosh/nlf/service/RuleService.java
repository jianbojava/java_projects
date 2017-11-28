package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Rule;

public interface RuleService {
	boolean add(Rule po);
	boolean del(Integer flg, String ids);
	boolean update(Rule po);
	Page<Rule> queryPage(BaseConditionVO vo);
	Rule queryById(String id);
	Rule queryRule(Rule rule);
	List<Rule> queryAll();
}
