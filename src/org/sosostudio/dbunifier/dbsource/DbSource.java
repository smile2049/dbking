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

public interface DbSource {

	public Connection getConnection();

	public Encoding getEncoding();

}
