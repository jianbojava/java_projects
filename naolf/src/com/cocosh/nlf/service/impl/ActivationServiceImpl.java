package com.cocosh.nlf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.ActivationMapper;
import com.cocosh.nlf.model.Activation;
import com.cocosh.nlf.service.ActivationService;

@Transactional
@Service
public class ActivationServiceImpl implements ActivationService {
	@Autowired
	private ActivationMapper mapper;

	@LogClass(module = "激活码管理", method = "添加")
	@Override
	public boolean add(Activation po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "激活码管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "激活码管理", method = "修改")
	@Override
	public boolean update(Activation po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Activation> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Activation> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Activation queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public Activation checkNumber(String  number) {
		return mapper.checkNumber(number);
	}
	
	@LogClass(module = "激活码管理", method = "添加")
	@Override
	public boolean add(Integer num) {
		for(int i=0;i<num;i++){
			String number=StringUtil.getRandom(8);
			foradd(number);
		}
		return true;
	}
	
	public void   foradd(String number){
		if(mapper.queryByNumber(number)!=null){
			foradd(StringUtil.getNumber(8));
		}else{
			mapper.add(new Activation(StringUtil.getUuid(), number, null, 0));
		}
	}
	
}
