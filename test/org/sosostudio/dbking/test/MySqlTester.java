package org.sosostudio.dbking.test;

import org.junit.Test;
import org.sosostudio.dbking.DbKing;

public class MySqlTester extends BaseTester {

	public MySqlTester() {
		dbKing = new DbKing("mysql");
	}

	@Test
	public void testTypeBinary() {
		String typeName = "binary(200)";
		super.testSmallBlob(typeName);
	}

	@Test
	public void testTypeFloat() {
		String typeName = "float(3,2)";
		testSmallDecimal(typeName);
	}

	@Test
	public void testTypeTimestamp() {
		String typeName = "timestamp null";
		testTimestamp(typeName);
	}
	
}
