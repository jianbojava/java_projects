package com.cocosh.sys.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.tree.TreeNode;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.Regions;

public interface DepartMapper {
	List<Regions> queryByPId(String grade);
	Integer add(Depart po);
	Integer del(Map<String, Object> map);
	Integer update(Depart po);
	List<Depart> queryPage(BaseConditionVO vo);
	Depart queryById(String id);
	Depart queryParent();
	List<Depart> queryChild(String parnet_id);
	List<TreeNode> queryTreeChild(String parent_id);
	List<Depart> queryByAll();
	List<Depart> queryByAllNew();
	List<Depart> queryByAllNew2();
	List<Depart> queryCenter();
	List<Regions> queryProvince();//查询全部部门的省
	List<Regions> queryCity(String province_id);//根据省查询市区
	List<Depart> queryByRegion(Depart depart);//根据地址查询部门
}
