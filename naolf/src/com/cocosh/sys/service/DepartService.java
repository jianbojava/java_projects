package com.cocosh.sys.service;
 
import java.util.List;

import com.cocosh.car.model.Parking;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.tree.TreeNode;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.Regions;

public interface DepartService {
	boolean add(Depart po);
	boolean del(Integer flg,String ids);
	boolean update(Depart po);
	Page<Depart> queryPage(BaseConditionVO vo);
	Depart queryById(String id);
	Depart queryParent();//查询全部
	Depart querySelfAndChild();//查询自己及孩子，用在部门管理里面
	Depart queryJustParent();//只查询总裁
	TreeNode departToTree();
	TreeNode userDepartTree(String user_id);
	Object queryByAll();
	Object queryByAllNew();
	Object queryByAllNew2();
	List<Depart> queryByDotId(String id);
	List<Regions> queryByPId(String grade);
	List<Depart> queryCenter();//查询全部分中心
	List<Depart> queryChild(String parent_id);
	List<Regions> queryRegions(String id);
	List<Depart> queryByRegions(Depart depart);
}
