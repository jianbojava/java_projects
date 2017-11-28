package com.cocosh.framework.json;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class ComplexPropertyPreFilter implements PropertyFilter {
	String[] excludes = null;

	@Override
	public boolean apply(Object object, String name, Object value) {
		for (String p : excludes) {
			if (p.equals(name)) {
				return false;
			}
		}
		return true;
	}

	public ComplexPropertyPreFilter(String[] excludes) {
		this.excludes = excludes;
	}

}
