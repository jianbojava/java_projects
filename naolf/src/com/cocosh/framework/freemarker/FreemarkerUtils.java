package com.cocosh.framework.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtils {
	public static String PROJECT_DIR = "E:\\Workspaces\\MyEclipse10.6\\naolf\\";
	public static String BASE_PACKAGE = "com.cocosh";
 	public static String TARGET_PACKAGE = "com";
 	public static String MODEL = "model";
 	public static String TEMPLATE_NAME="model.ftl";
 	public static Configuration cfg;
 	public static List<Attribute> attributes = new ArrayList<Attribute>();
 	public static String MODULE= "会员";
 	private static Map<String, String> dbColumn = new HashMap<String, String>();
 	private static String IMPORT = "";//需要导入的类

 	/*
 	 * 将模版进行指定文件的输出
 	 */
 	public static void write(Map<String, Object> root,String model,String className) throws IOException, TemplateException{
 		Template t = cfg.getTemplate(TEMPLATE_NAME);
 		String filePath = PROJECT_DIR+"src\\"+TARGET_PACKAGE.replace(".", "\\")+"\\"+model;
 		File dirname = new File(filePath);
 		if (!dirname.isDirectory())
 		{ //目录不存在
 		     dirname.mkdirs(); //创建目录
 		}  
 		OutputStream out = new FileOutputStream(
 				new File(filePath+"\\"+className));
 		t.process(root, new OutputStreamWriter(out));
 	}
 	
 	/*
 	 * 执行入口
 	 */
 	public static void create(String className,String tableName) throws Exception{
 		
 		//初始化属性
 		FreemarkerUtils.init();
 		loadAttr(tableName);
 		
 		TEMPLATE_NAME = "model.ftl";
 		MODEL = "model";
 		//1.load信息
 		Class clazz = FreemarkerUtils.loadClass(className, new String[]{}, TARGET_PACKAGE);
 		//2.向root中放入模版中所需信息
 		Map<String, Object> root = new HashMap<String, Object>();
 		root.put("clazz",clazz);
 		root.put("attributes",attributes);
 		root.put("import", IMPORT);
 		root.put("basePackage", BASE_PACKAGE);
 		//3.将模版进行指定文件的输出
 		FreemarkerUtils.write(root, MODEL, className+".java");
 		
 		TEMPLATE_NAME = "mapper.ftl";
 		MODEL = "mapper";
 		//1.load信息
// 		clazz = FreemarkerUtils.loadClass(className, new String[]{}, TARGET_PACKAGE);
// 		root.put("clazz",clazz);
 		FreemarkerUtils.write(root, MODEL, className+"Mapper"+".java");
 		
 		TEMPLATE_NAME = "service.ftl";
 		MODEL = "service";
 		//1.load信息
// 		clazz = FreemarkerUtils.loadClass(className, new String[]{}, TARGET_PACKAGE);
// 		root.put("clazz",clazz);
 		FreemarkerUtils.write(root, MODEL, className+"Service"+".java");
 		
 		TEMPLATE_NAME = "serviceimpl.ftl";
 		MODEL = "service\\impl";
 		//1.load信息
// 		clazz = FreemarkerUtils.loadClass(className, new String[]{}, TARGET_PACKAGE);
// 		root.put("clazz",clazz);
 		root.put("module", MODULE);
 		FreemarkerUtils.write(root, MODEL, className+"ServiceImpl"+".java");
 		
 		TEMPLATE_NAME = "controller.ftl";
 		MODEL = "controller";
 		//1.load信息
// 		clazz = FreemarkerUtils.loadClass(className, new String[]{}, TARGET_PACKAGE);
// 		root.put("clazz",clazz);
 		root.put("module", className.toLowerCase());
 		
 		FreemarkerUtils.write(root, MODEL, className+"Controller"+".java");
 		
 		TEMPLATE_NAME = "mapperxml.ftl";
 		MODEL = "mapper";
 		//1.load信息
// 		clazz = FreemarkerUtils.loadClass(className, new String[]{}, TARGET_PACKAGE);
// 		root.put("clazz",clazz);
 		root.put("tableName", tableName);
 		createMapperXml(root);
 		
 		FreemarkerUtils.write(root, MODEL, className+"Mapper"+".xml");
 	}

	private static void createMapperXml(Map<String, Object> root) {

		StringBuffer insertField = new StringBuffer();
		StringBuffer insertValue = new StringBuffer();
		StringBuffer updateField = new StringBuffer();
// 		insertField.append("id");
//		insertValue.append("#{id}");

		int i=0;
		for (Attribute attr : attributes) {
			insertField.append(","+attr.getName());
			if (attr.getName().equals("create_date")||attr.getName().equals("modify_date")) {
				insertValue.append(",now()");
			}else {
				insertValue.append(",#{"+attr.getName()+"}");
			}
			if (attr.getName().equals("id")||attr.getName().equals("create_date")||attr.getName().equals("modify_date")) {
				continue;
			}
			updateField.append("\n\t\t\t<if test=\"@com.cocosh.framework.util.OgnlUtils@isNotNull("+attr.getName()+")\">"+attr.getName()+"=#{"+attr.getName()+"},</if>");
			i++;
		}
		root.put("insertField", insertField.substring(1));
		root.put("insertValue", insertValue.substring(1));
		root.put("delWhere", "#{item}");
		root.put("updateField", updateField);
		root.put("byId","#{id}");
		root.put("keywords", "'%${keywords}%'");
		root.put("orderField","${orderField}");
		root.put("orderDirection", "${orderDirection}");
	}
 	/**
 	 * 初始化工作
 	 */
 	public static void init() throws Exception {
 		// 负责管理的实例创建+设置模板文件所在的目录
 		cfg = new Configuration();
 		cfg.setDirectoryForTemplateLoading(new File(PROJECT_DIR+"template"));
        
        dbColumn.put("java.lang.String", "String");
        dbColumn.put("java.lang.Boolean", "Integer");
        dbColumn.put("java.lang.Integer", "Integer");
        dbColumn.put("java.lang.Long", "Integer");
        dbColumn.put("java.sql.Timestamp", "Date");
        dbColumn.put("java.lang.Double", "Double");
        dbColumn.put("java.math.BigDecimal", "Double");
 	}
 	

 	
 	/*
 	 * 初始化属性
 	 */
 	public static List<Attribute> loadAttr(String tableName) throws Exception{//获取数据库中的列名生成数据
 		attributes = new ArrayList<Attribute>();
		Properties property = new Properties();
		property.load(FreemarkerUtils.class.getResourceAsStream("/dbconfig.properties"));
		String url = property.getProperty("url");
        String user = property.getProperty("username");
        String pwd = property.getProperty("password");
        String sql = "select * from "+tableName;
        
        Map<String, String> discharge = new HashMap<String, String>();//祛除共有的字段
//        discharge.put("id", "id");
//        discharge.put("create_date", "create_date");
//        discharge.put("modify_date", "modify_date");
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            java.lang.Class.forName(property.getProperty("driver"));
            conn = DriverManager.getConnection(url,user,pwd);
            st = conn.createStatement();
            //执行查询语句，另外也可以用execute()，代表执行任何SQL语句
            rs = st.executeQuery(sql);
            ResultSetMetaData data=rs.getMetaData(); 
            for (int i = 1; i <= data.getColumnCount(); i++) {
            	String columnName = data.getColumnName(i);
            	String columnClassName = data.getColumnClassName(i);
            	if(!discharge.containsKey(columnName)){
            		attributes.add(new Attribute(columnName,dbColumn.get(columnClassName)));
            		if("Date".equals(dbColumn.get(columnClassName))&&!IMPORT.contains("import java.util.Date")){
            			IMPORT += "\nimport java.util.Date;";
            		}
            	}
			}
        //分别捕获异常
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //判断资源是否存在
                if(rs != null) {
                    rs.close();
                    //显示的设置为空，提示gc回收
                    rs = null;
                }
                if(st != null) {
                    st.close();
                    st = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }   
        }
        return attributes;
	}
 	
 	/*
 	 * 加载类信息：包名，类名，import
 	 */
 	public static Class loadClass(String className,String[] imports, String packageName) {
 		Class clazz = new Class(className, imports, packageName);
 		return clazz;
 	}
 }
 