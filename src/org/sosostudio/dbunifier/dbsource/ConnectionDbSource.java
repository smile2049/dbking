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

import org.sosostudio.dbunifier.Encoding;

public class ConnectionDbSource implements DbSource {

	private Connection con;

	private Encoding encoding;

	public ConnectionDbSource(Connection con, Encoding encoding) {
		this.con = con;
		this.encoding = encoding;
	}

	public ConnectionDbSource(Connection con) {
		this.con = con;
	}

	@Override
	public Connection getConnection() {
		return con;
	}

	@Override
	public Encoding getEncoding() {
		return encoding;
	}

}
