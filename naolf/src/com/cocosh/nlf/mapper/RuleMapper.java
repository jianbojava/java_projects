package com.cocosh.nlf.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Rule;

public interface RuleMapper {
	Integer add(Rule po);
	Integer del(Map<String, Object> map);
	Integer update(Rule po);
	List<Rule> queryPage(BaseConditionVO vo);
	Rule queryById(String id);
	List<Rule> queryAll();
	Rule queryRule(Rule rule);
}
