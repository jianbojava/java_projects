package com.cocosh.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.car.mapper.CarMapper;
import com.cocosh.car.model.Car;
import com.cocosh.car.service.CarService;

@Transactional
@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarMapper mapper;

	@LogClass(module = "车辆管理", method = "添加")
	@Override
	public boolean add(Car po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "车辆管理", method = "审核")
	@Override
	public boolean review(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("del_field", "review");
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    @LogClass(module = "车辆管理", method = "操作")
	@Override
	public boolean enabled(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("del_field", "enabled");
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    @LogClass(module = "车辆管理", method = "删除")
	@Override
	public boolean del(String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("del_field", "del_flg");
		map.put("del_flg", "1");
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "车辆管理", method = "修改")
	@Override
	public boolean update(Car po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Car> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Car> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Car queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Car> queryPageApp(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<Car> page=PaginationInterceptor.endPage();
		return page.getResult();
	}

	@Override
	public Page<Car> queryDyncPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryDyncPage(vo);
		Page<Car> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Car queryByIdAPP(BaseConditionVO vo) {
		return mapper.queryByIdAPP(vo);
	}

	@Override
	public List<Car> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public List<Car> queryAllNoPage(BaseConditionVO vo) {
		return mapper.queryAllNoPage(vo);
	}
	
}
