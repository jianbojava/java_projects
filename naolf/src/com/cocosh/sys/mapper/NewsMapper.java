package com.cocosh.sys.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.sys.model.News;

public interface NewsMapper {
	Integer add(News po);
	Integer del(Map<String,Object> map);
	Integer update(News po);
	List<News> queryPage(BaseConditionVO vo);
	News queryById(String id);
}
