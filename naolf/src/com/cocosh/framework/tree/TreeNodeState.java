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
public class TreeNodeState implements Serializable {  
      
	
   public boolean selected=false;
	
   public boolean isSelected() {
		return selected;
	}
   public TreeNodeState(){}
   
	public TreeNodeState(boolean selected) {
		super();
		this.selected = selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	private static final long serialVersionUID = 1L;  
   
      
    
}  