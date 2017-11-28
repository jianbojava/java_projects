package com.cocosh.framework.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/** 
 * 树形结构构建
 */  
public class TreeNode implements Serializable {  
	
    private static final long serialVersionUID = 1L;  
    /** 
     * 节点ID 
     */  
    public String id;  
	/** 
     * 节点内容 
     */  
    public String text; 
    
//    public String icon="none";
//    
//    
//    public String getIcon() {
//		return icon;
//	}
//	public void setIcon(String icon) {
//		this.icon = icon;
//	}
	public TreeNodeState state=new TreeNodeState();
    /** 
     * 孩子节点列表 
     */  
    public List<TreeNode> children = new ArrayList<TreeNode>();
    public TreeNode(){}
    public TreeNode(TreeNodeState state) {
		super();
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TreeNodeState getState() {
		return state;
	}
	public void setState(TreeNodeState state) {
		this.state = state;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}  
      
    
}  