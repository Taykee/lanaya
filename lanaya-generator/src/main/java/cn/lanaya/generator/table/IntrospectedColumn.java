package cn.lanaya.generator.table;

import java.util.Properties;

import cn.lanaya.generator.dom.java.QualifiedJavaType;
import lombok.Data;

@Data
public class IntrospectedColumn {
	
	protected String actualColumnName;

	protected int jdbcType;

	protected String jdbcTypeName;

	protected boolean nullable;

	protected int length;

	protected int scale;

	protected boolean identity;

	protected boolean isSequenceColumn;

	protected String javaProperty;

	protected QualifiedJavaType qualifiedJavaType;

	protected String tableAlias;

	protected String typeHandler;

	protected boolean isColumnNameDelimited;

	protected Properties properties;

	// any database comment associated with this column. May be null
	protected String remarks;

	protected String defaultValue;

	/**
	 * true if the JDBC driver reports that this column is auto-increment.
	 */
	protected boolean isAutoIncrement;

	/**
	 * true if the JDBC driver reports that this column is generated.
	 */
	protected boolean isGeneratedColumn;

	/**
	 * True if there is a column override that defines this column as GENERATED
	 * ALWAYS.
	 */
	protected boolean isGeneratedAlways;

}
