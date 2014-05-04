package org.sosostudio.dbunifier;

public class Column {

	public final static String TYPE_STRING = "string";

	public final static String TYPE_NUMBER = "number";

	public final static String TYPE_TIMESTAMP = "timestamp";

	public final static String TYPE_CLOB = "clob";

	public final static String TYPE_BLOB = "blob";

	public final static String TYPE_UNKNOWN = "unknown";

	private String name;

	private String type;

	private int size = 50;

	private int precision = 10;

	private int scale = 2;

	private boolean nullable;

	private boolean isPrimaryKey;

	public Column() {
	}

	public Column(String name, String type, boolean nullable,
			boolean isPrimaryKey) {
		this.name = name;
		this.type = type;
		this.nullable = nullable;
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getName() {
		return name;
	}

	public Column setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public Column setType(String type) {
		this.type = type;
		return this;
	}

	public int getSize() {
		return size;
	}

	public Column setSize(int size) {
		this.size = size;
		return this;
	}

	public int getPrecision() {
		return precision;
	}

	public Column setPrecision(int precision) {
		this.precision = precision;
		return this;
	}

	public int getScale() {
		return scale;
	}

	public Column setScale(int scale) {
		this.scale = scale;
		return this;
	}

	public boolean getNullable() {
		return nullable;
	}

	public Column setNullable(boolean nullable) {
		this.nullable = nullable;
		return this;
	}

	public boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public Column setIsPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
		return this;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("\t[name = ").append(name)
				.append("]").append("[type = ").append(type).append("]")
				.append("[size = ").append(size).append("]")
				.append("[precision = ").append(precision).append("]")
				.append("[scale = ").append(scale).append("]")
				.append("[nullable = ").append(nullable).append("]")
				.append("[isPrimaryKey = ").append(isPrimaryKey).append("]\n")
				.toString();
	}
}