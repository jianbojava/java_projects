package com.cocosh.framework.freemarker;

import com.cocosh.framework.freemarker.FreemarkerUtils;

/**
 * 创建MyBatis相关文件(包含controller,Service,Mapper,Model)
 * @author Eric.lan
 *
 */
public class FoundEntity {
 	
 	public static void main(String[] args) throws Exception{

 		FreemarkerUtils.PROJECT_DIR = "E:\\MyEclipse 10\\naolf\\naolf\\";//项目路径
 	 	FreemarkerUtils.BASE_PACKAGE = "com.cocosh";//BASE包名

 	 	FreemarkerUtils.TARGET_PACKAGE = "com.cocosh.nlf";//目标包名（为model上一次）
 	 	FreemarkerUtils.MODULE= "老师评论";//日志说明（需要日志）

// 	 	FreemarkerUtils.MODULE= null;//无需日志
 	 	FreemarkerUtils.create("Tcomment", "nlf_tcomment");//PO对象名称和表名
	}
 }
 