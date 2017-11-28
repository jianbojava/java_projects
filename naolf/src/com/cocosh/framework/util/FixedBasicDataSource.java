package com.cocosh.framework.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 解决DBCP连接池没有自动的回收空闲连接
 * @author jerry
 */
public class FixedBasicDataSource extends BasicDataSource{

	@Override
	public synchronized void close() throws SQLException {
		DriverManager.deregisterDriver(DriverManager.getDriver(url));
		super.close();
	}

}
