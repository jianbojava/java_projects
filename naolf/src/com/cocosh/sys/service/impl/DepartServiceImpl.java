package com.cocosh.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.tree.TreeNode;
import com.cocosh.framework.tree.TreeNodeState;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.DepartMapper;
import com.cocosh.sys.mapper.UserDepartMapper;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.Regions;
import com.cocosh.sys.model.User;
import com.cocosh.sys.model.UserDepart;
import com.cocosh.sys.service.DepartService;

@Transactional
@Service
public class DepartServiceImpl implements DepartService {
	@Autowired
	private DepartMapper mapper;
	@Autowired
	private UserDepartMapper userDepartMapper;

	@LogClass(module = "部门管理", method = "添加")
	@Override
	public boolean add(Depart po) {
		po.setId(StringUtil.getUuid());
		Integer result= mapper.add(po);
		if(result>0){//20170407bybobo;后期添加，如果添加了一个新的部门，那么新部门的父亲被选中，对于用户来说，这个新部门也要被选中
			if(!StringUtil.isEmpty(po.getParent_id())){
				UserDepart udepart=new UserDepart();
				udepart.setDepart_id(po.getParent_id());
				List<UserDepart> list=userDepartMapper.query(udepart);//查询新增加的部门的父亲被选中的用户
				udepart.setDepart_id(po.getId());
				for(UserDepart ud:list){
					udepart.setUser_id(ud.getUser_id());
					userDepartMapper.add(udepart);
				}
			}
		}
		return result>0;		
	}
    @LogClass(module = "部门管理", method = "删除")
   	@Override
   	public boolean del(Integer flg,String ids) {
       	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("del_flg", flg);
   		map.put("del_ids", ids.split(","));
   		return mapper.del(map) > 0;
   	}

	@LogClass(module = "部门管理", method = "修改")
	@Override
	public boolean update(Depart po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Depart> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Depart> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Depart queryById(String id) {
		return mapper.queryById(id);
	}
	@Override
	public Depart queryParent() {
		Depart depart= mapper.queryParent();
		forchild(depart);
		return depart;
	}
	
	public void forchild(Depart depart){
		if(depart!=null){
			List<Depart> list=mapper.queryChild(depart.getId());
			if(list.size()>0){
				depart.setChild(list);
				for(Depart d:list){
					forchild(d);
				}
			}
		}
	}
	@Override
	public TreeNode departToTree() {
		Depart depart= mapper.queryParent();
		TreeNode tree=new TreeNode();
		tree.setId(depart.getId());
		tree.setText(depart.getName());
		forchild(tree);
		return tree;
	}
	
	public void forchild(TreeNode tree){
		if(tree!=null){
			List<TreeNode> list=mapper.queryTreeChild(tree.getId());
			if(list.size()>0){
				tree.setChildren(list);
				for(TreeNode d:list){
					forchild(d);
				}
			}
		}
	}
	@Override
	public TreeNode userDepartTree(String user_id) {
		String departs=userDepartMapper.queryDepartsByUser(user_id);
		if(departs==null) departs="";
		Depart depart= mapper.queryParent();
		TreeNode tree=new TreeNode();
		tree.setId(depart.getId());
		tree.setText(depart.getName());
		if(departs.contains(depart.getId())) tree.setState(new TreeNodeState(true));
		forchild(tree,departs);
		return tree;
	}
	
	public void forchild(TreeNode tree,String departs){
		if(tree!=null){
			if(departs.contains(tree.getId())) tree.setState(new TreeNodeState(true));
			List<TreeNode> list=mapper.queryTreeChild(tree.getId());
			if(list.size()>0){
				tree.setChildren(list);
				for(TreeNode d:list){
					forchild(d,departs);
				}
			}
		}
	}
	@Override
	public Object queryByAll() {
		// TODO Auto-generated method stub 
		return mapper.queryByAll();
	}
	@Override
	public Object queryByAllNew() {
		// TODO Auto-generated method stub 
		return mapper.queryByAllNew();
	}
	@Override
	public Object queryByAllNew2() {
		// TODO Auto-generated method stub 
		return mapper.queryByAllNew2();
	}
	@Override
	public List<Depart> queryByDotId(String id) {
		// TODO Auto-generated method stub
		return mapper.queryChild(id);
	}
	@Override
	public List<Regions> queryByPId(String grade) {
		// TODO Auto-generated method stub
		return mapper.queryByPId(grade);
	}
	@Override
	public List<Depart> queryCenter() {
		return mapper.queryCenter();
	}
	@Override
	public Depart queryJustParent() {
		return mapper.queryParent();
	}
	@Override
	public List<Depart> queryChild(String parent_id) {
		return mapper.queryChild(parent_id);
	}
	@Override
	public List<Regions> queryRegions(String id) {
		if(id.equals("0")) return mapper.queryProvince();
		return mapper.queryCity(id);
	}
	@Override
	public List<Depart> queryByRegions(Depart depart) {
		return mapper.queryByRegion(depart);
	}
	@Override
	public Depart querySelfAndChild() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user"); 
	    Depart depart = mapper.queryById(user.getDepart_id());
		forchild(depart);
		return depart;
	}
	
	
	
}
