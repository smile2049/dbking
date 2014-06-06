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

package org.sosostudio.dbking.config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.sosostudio.dbking.dbsource.DbSource;
import org.sosostudio.dbking.dbsource.JdbcDbSource;
import org.sosostudio.dbking.dbsource.JndiDbSource;
import org.sosostudio.dbking.exception.DbKingException;
import org.sosostudio.dbking.util.IoUtil;

public class XmlConfig {

	private static boolean showSql = true;

	private static Map<String, DbSource> dbSourceMap = new ConcurrentHashMap<String, DbSource>();

	static {
		SAXReader saxReader = new SAXReader();
		InputStream is = null;
		try {
			is = XmlConfig.class.getResourceAsStream("/dbking.config.xml");
			Document doc = saxReader.read(is);
			Element rootElement = doc.getRootElement();
			showSql = Boolean.valueOf(rootElement.elementText("show_sql"));
			@SuppressWarnings("unchecked")
			List<Element> dbSourceElementList = rootElement
					.elements("db_source");
			for (Element dbSourceElement : dbSourceElementList) {
				String dbSourceName = dbSourceElement.attributeValue("name");
				if (dbSourceName == null) {
					dbSourceName = "";
				}
				String databaseDriver = dbSourceElement
						.elementText("database_driver");
				String databaseUrl = dbSourceElement
						.elementText("database_url");
				String username = dbSourceElement.elementText("username");
				String password = dbSourceElement.elementText("password");
				String jndi = dbSourceElement.elementText("jndi");
				if (databaseDriver != null && databaseUrl != null
						&& username != null && password != null) {
					DbSource dbSource = new JdbcDbSource(databaseDriver,
							databaseUrl, username, password);
					dbSourceMap.put(dbSourceName, dbSource);
				} else if (databaseDriver != null && databaseUrl != null) {
					DbSource dbSource = new JdbcDbSource(databaseDriver,
							databaseUrl);
					dbSourceMap.put(dbSourceName, dbSource);
				} else if (jndi != null && username != null && password != null) {
					DbSource dbSource = new JndiDbSource(jndi, username,
							password);
					dbSourceMap.put(dbSourceName, dbSource);
				} else if (jndi != null) {
					DbSource dbSource = new JndiDbSource(jndi);
					dbSourceMap.put(dbSourceName, dbSource);
				} else {
					throw new DbKingException(
							"initial dbking config failed");
				}

			}
		} catch (DocumentException e) {
			throw new DbKingException(e);
		} finally {
			IoUtil.closeInputStream(is);
		}
	}

	public static boolean needsShowSql() {
		return showSql;
	}

	public static DbSource getDbSource(String name) {
		return dbSourceMap.get(name);
	}

}
