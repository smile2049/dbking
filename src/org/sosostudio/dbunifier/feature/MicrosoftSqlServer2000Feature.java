package org.sosostudio.dbunifier.feature;

public class MicrosoftSqlServer2000Feature extends DbFeature {

	@Override
	public String getNumberDbType() {
		return "numeric";
	}

	@Override
	public String getTimestampDbType() {
		return "timestamp";
	}

	@Override
	public String getClobDbType() {
		return "text";
	}

	@Override
	public String getBlobDbType() {
		return "image";
	}
	
}
