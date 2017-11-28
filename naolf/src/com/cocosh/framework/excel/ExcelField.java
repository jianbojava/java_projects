package com.cocosh.framework.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	String title();// 导出字段标题

	int sort() default 0;// 导出字段字段排序（升序）

	int width() default 9;// 导出字段列宽

	boolean isSum() default false;// 导出字段是否需要统计

	String numType() default "String";// 导出字段的类型，默认为字符串，针对需要统计

	boolean isGroupSum() default false;// 导出字段是否需要分组统计

	String groupSumTitle() default "小计";// 导出分组统计字段的文字，默认为小计
}
