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
 */

package org.sosostudio.dbunifier;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Values {

	private List<Object> valueList = new ArrayList<Object>();

	public List<Object> getValueList() {
		return valueList;
	}

	private Values addNullValue(ColumnType type) {
		NullValue nullValue = new NullValue(type);
		valueList.add(nullValue);
		return this;
	}

	public Values addStringValue(String stringValue) {
		if (stringValue == null) {
			return addNullValue(ColumnType.TYPE_STRING);
		} else {
			valueList.add(stringValue);
			return this;
		}
	}

	public Values addNumberValue(BigDecimal numberValue) {
		if (numberValue == null) {
			return addNullValue(ColumnType.TYPE_NUMBER);
		} else {
			valueList.add(numberValue);
			return this;
		}
	}

	public Values addTimestampValue(Timestamp timestampValue) {
		if (timestampValue == null) {
			return addNullValue(ColumnType.TYPE_TIMESTAMP);
		} else {
			valueList.add(timestampValue);
			return this;
		}
	}

	public Values addClobValue(String clobValue) {
		if (clobValue == null) {
			return addNullValue(ColumnType.TYPE_CLOB);
		} else {
			valueList.add(clobValue.toCharArray());
			return this;
		}
	}

	public Values addBlobValue(byte[] blobValue) {
		if (blobValue == null) {
			return addNullValue(ColumnType.TYPE_BLOB);
		} else {
			valueList.add(blobValue);
			return this;
		}
	}

	public Values addValues(Values values) {
		valueList.addAll(values.getValueList());
		return this;
	}

}
