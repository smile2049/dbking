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

package org.sosostudio.dbking.oom;

public class DeleteSql {

	private String tableName;

	private ConditionClause conditionClause;

	public String getTableName() {
		return tableName;
	}

	public DeleteSql setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public ConditionClause getConditionClause() {
		return conditionClause;
	}

	public DeleteSql setConditionClause(ConditionClause conditionClause) {
		this.conditionClause = conditionClause;
		return this;
	}

}
