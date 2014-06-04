/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2014 YU YUE, SOSO STUDIO, wuyuetiger@gmail.com
 *
 * License: GNU Lesser General Public License (LGPL)
 * 
 * Source code availability:
 *  https://github.com/wuyuetiger/db-unifier
 *  https://code.csdn.net/tigeryu/db-unifier
 *  https://git.oschina.net/db-unifier/db-unifier
 */

package org.sosostudio.dbunifier.dbsource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sosostudio.dbunifier.util.DbUnifierException;

public class JdbcDbSource implements DbSource {

	private String databaseDriver;

	private String databaseUrl;

	private String username;

	private String password;

	public JdbcDbSource(String databaseDriver, String databaseUrl,
			String username, String password) {
		this.databaseDriver = databaseDriver;
		this.databaseUrl = databaseUrl;
		this.username = username;
		this.password = password;
	}

	public JdbcDbSource(String databaseDriver, String databaseUrl) {
		this.databaseDriver = databaseDriver;
		this.databaseUrl = databaseUrl;
	}

	@Override
	public Connection getConnection() {
		if (username == null) {
			try {
				Class.forName(databaseDriver);
				return DriverManager.getConnection(databaseUrl);
			} catch (ClassNotFoundException e) {
				throw new DbUnifierException(e);
			} catch (SQLException e) {
				throw new DbUnifierException(e);
			}
		} else {
			try {
				Class.forName(databaseDriver);
				return DriverManager.getConnection(databaseUrl, username,
						password);
			} catch (ClassNotFoundException e) {
				throw new DbUnifierException(e);
			} catch (SQLException e) {
				throw new DbUnifierException(e);
			}
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append("\ndatabaseUrl: ")
				.append(databaseUrl).append("\nusername: ").append(username)
				.append("\n").toString();
	}

}
