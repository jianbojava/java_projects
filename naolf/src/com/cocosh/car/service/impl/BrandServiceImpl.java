package com.cocosh.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.car.mapper.BrandMapper;
import com.cocosh.car.mapper.BrandTypeMapper;
import com.cocosh.car.model.Brand;
import com.cocosh.car.model.BrandType;
import com.cocosh.car.service.BrandService;
import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;

@Transactional
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandMapper mapper;
	@Autowired
	private BrandTypeMapper typeMapper;

	@LogClass(module = "品牌管理", method = "添加")
	@Override
	public boolean add(Brand po) {
		po.setId(StringUtil.getUuid());
		if(mapper.add(po)>0){
		   //添加类型
			List<BrandType> types=po.getTypes();
			if (types!=null&&types.size()>0) {
				for(BrandType t:types){
					t.setId(StringUtil.getUuid());
					t.setBrand_id(po.getId());
					typeMapper.add(t);
				}
			}
		}
		return true;
	}
	
    @LogClass(module = "品牌管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "品牌管理", method = "修改")
	@Override
	public boolean update(Brand po) {
		//修改品牌
		mapper.update(po);
		//红包
		List<String> type_ids=typeMapper.queryIdsByBrandId(po.getId());
		List<BrandType> types=po.getTypes();
		for (int i = 0; i < type_ids.size(); i++) {
			boolean flag=true;
			for (int j = 0; j < types.size(); j++) {
				if (type_ids.get(i).equals(types.get(j).getId())) {
					types.get(j).setBrand_id(po.getId());
					typeMapper.update(types.get(j));
					types.remove(j);
					flag=false;
				}
			}
			if (flag) {
				typeMapper.del(new String[]{type_ids.get(i)});
			}
		}
		for (int i = 0; i < types.size(); i++) {
			types.get(i).setId(StringUtil.getUuid());
			types.get(i).setBrand_id(po.getId());
			typeMapper.add(types.get(i));
		}
		return true;
	}

	@Override
	public Page<Brand> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Brand> page=PaginationInterceptor.endPage();
		if (vo.getType().equals("0")) {
			List<Brand> list=page.getResult();
			for(Brand b:list){
				b.setType_names(typeMapper.queryNamesByBrandId(b.getId()));
			}
		}
		return page;
	}

	@Override
	public Brand queryById(String id) {
		Brand brand= mapper.queryById(id);
		if(brand!=null&&brand.getType()==0){
			List<BrandType> list=typeMapper.queryByBrandId(id);
			brand.setTypes(list);
		}
		return brand;
	}

	@Override
	public List<Brand> queryAllByType(String type) {
		return mapper.queryAllByType(type);
	}

	@Override
	public List<BrandType> queryByBId(String brandid) {
		return typeMapper.queryByBrandId(brandid);
	}
	
}
