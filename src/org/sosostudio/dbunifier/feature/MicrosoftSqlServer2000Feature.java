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

package org.sosostudio.dbunifier.feature;

import java.sql.Connection;

import org.sosostudio.dbunifier.ColumnType;
import org.sosostudio.dbunifier.Encoding;

public class MicrosoftSqlServer2000Feature extends DbFeature {

	@Override
	public String defaultCaps(String name) {
		return name.toUpperCase();
	}

	@Override
	public String getNStringDbType(int size) {
		size = Math.max(0, Math.min(size, ColumnType.MAX_STRING_SIZE));
		return "nvarchar(" + size + ")";
	}

	@Override
	public String getTimestampDbType() {
		return "datetime";
	}

	@Override
	public String getClobDbType() {
		return "text";
	}

	@Override
	public String getBlobDbType() {
		return "image";
	}

	@Override
	public Encoding getEncoding(Connection con) {
		return Encoding.GBK;
	}

}
