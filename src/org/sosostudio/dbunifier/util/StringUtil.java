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

package org.sosostudio.dbunifier.util;

import org.sosostudio.dbunifier.ColumnType;

public class StringUtil {

	private static String getName(String name, boolean first) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (ch == '_') {
				first = true;
				continue;
			}
			if (first) {
				sb.append(Character.toUpperCase(ch));
				first = false;
			} else {
				sb.append(Character.toLowerCase(ch));
			}
		}
		return sb.toString();
	}

	public static String getDefinationName(String name) {
		return getName(name, true);
	}

	public static String getVariableName(String name) {
		return getName(name, false);
	}

	public static String truncate(String s, int maxSize) {
		if (s == null) {
			return null;
		}
		if (s.length() <= maxSize) {
			return s;
		} else {
			if (maxSize >= ColumnType.MAX_STRING_SIZE) {
				return s.substring(0, maxSize);
			} else {
				throw new DbUnifierException("string is too long");
			}
		}
	}

}
