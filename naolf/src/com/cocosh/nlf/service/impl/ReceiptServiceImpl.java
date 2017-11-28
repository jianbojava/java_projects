package com.cocosh.nlf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.ReceiptMapper;
import com.cocosh.nlf.model.Receipt;
import com.cocosh.nlf.service.ReceiptService;

@Transactional
@Service
public class ReceiptServiceImpl implements ReceiptService {
	@Autowired
	private ReceiptMapper mapper;

	@LogClass(module = "发票管理", method = "添加")
	@Override
	public boolean add(Receipt po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "发票管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "发票管理", method = "修改")
	@Override
	public boolean update(Receipt po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Receipt> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Receipt> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Receipt queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Receipt> queryByOrderId(String norder_id) {
		return mapper.queryByOrderId(norder_id);
	}
	
}
