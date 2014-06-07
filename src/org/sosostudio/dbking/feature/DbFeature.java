/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2014 YU YUE, SOSO STUDIO, wuyuetiger@gmail.com
 *
 * License: GNU Lesser General Public License (LGPL)
 * 
 * Source code availability:
 *  https://github.com/wuyuetiger/dbking
 *  https://code.csdn.net/tigeryu/dbking
 *  https://git.oschina.net/db-unifier/dbking
 */

package org.sosostudio.dbking.feature;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sosostudio.dbking.ColumnType;
import org.sosostudio.dbking.Values;
import org.sosostudio.dbking.exception.DbKingException;
import org.sosostudio.dbking.util.DbUtil;

public class DbFeature {

	public final static String ORACLE = "Oracle";

	public final static String MSSQLSERVER = "Microsoft SQL Server";

	public final static String MYSQL = "MySQL";

	public final static String DB2 = "DB2";

	public final static String SYBASE = "Adaptive Server Enterprise";

	public final static String POSTGRESQL = "PostgreSQL";

	public final static String DERBY = "Apache Derby";

	public final static String KINGBASE = "KingbaseES";

	public final static String DM = "DM DBMS";

	public static DbFeature getInstance(DatabaseMetaData databaseMetaData)
			throws SQLException {
		String name = databaseMetaData.getDatabaseProductName();
		String version = databaseMetaData.getDatabaseProductVersion();
		int majorVersion = databaseMetaData.getDatabaseMajorVersion();
		int minorVersion = databaseMetaData.getDatabaseMinorVersion();
		if (ORACLE.equals(name)) {
			return new OracleFeature();
		} else if (MSSQLSERVER.equals(name)) {
			if (majorVersion >= 9) {
				return new MicrosoftSqlServerFeatureAbove2005();
			} else {
				return new MicrosoftSqlServerFeatureBelow2005();
			}
		} else if (MYSQL.equals(name)) {
			if (version.indexOf("MariaDB") >= 0) {
				return new MariaDbFeature();
			} else {
				return new MySqlFeature();
			}
		} else if (name.startsWith(DB2)) {
			float ver = new Float(majorVersion + "." + minorVersion);
			if (ver > 9.7) {
				return new Db2FeatureAbove972();
			} else {
				if ("SQL9072".equals(version)) {
					return new Db2FeatureAbove972();
				} else {
					return new Db2FeatureBelow972();
				}
			}
		} else if (SYBASE.equals(name)) {
			return new SybaseFeature();
		} else if (POSTGRESQL.equals(name)) {
			return new PostgreSqlFeature();
		} else if (DERBY.equals(name)) {
			return new DerbyFeature();
		} else if (KINGBASE.equals(name)) {
			return new KingbaseFeature();
		} else if (DM.equals(name)) {
			return new DmFeature();
		} else {
			System.out
					.println("dbking will use default db feature without test");
			return new DbFeature();
		}
	}

	public String getStringDbType(int size) {
		size = Math.max(0, Math.min(size, ColumnType.MAX_STRING_SIZE));
		return "nvarchar(" + size + ")";
	}

	public String getNumberDbType(int precision, int scale) {
		return "numeric(" + Math.max(0, precision) + "," + Math.max(0, scale)
				+ ")";
	}

	public String getTimestampDbType() {
		return "timestamp";
	}

	public String getClobDbType() {
		return "clob";
	}

	public String getBlobDbType() {
		return "blob";
	}

	public String getDatabaseSchema(DatabaseMetaData dmd) {
		return null;
	}

	public String defaultCaps(String name) {
		return name.toUpperCase();
	}

	public boolean allowNullByDefault() {
		return true;
	}

	public String getPaginationSql(String mainSubSql, String orderBySubSql,
			int startPos, int endPos) {
		return null;
	}

	public int getTotalRowCount(Connection con, String mainSubSql, Values values) {
		String countSql = "select count(*) from (" + mainSubSql + ") t";
		DbUtil.printSql(countSql, values);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(countSql);
			DbUtil.setColumnValue(ps, 1, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getBigDecimal(1).intValue();
			} else {
				throw new DbKingException("no resultset");
			}
		} catch (SQLException e) {
			throw new DbKingException(e);
		} finally {
			DbUtil.closeResultSet(rs);
			DbUtil.closeStatement(ps);
		}
	}

}
