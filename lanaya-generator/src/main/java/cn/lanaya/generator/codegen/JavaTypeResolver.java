package cn.lanaya.generator.codegen;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.lanaya.generator.codegen.JavaTypeResolver.JdbcTypeInformation;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedColumn;

/**
 * Java类型解构器
 * @author 
 *
 */
public class JavaTypeResolver {

	protected boolean forceBigDecimals;
	
	protected Map<Integer, JdbcTypeInformation> typeMap;
	
	public JavaTypeResolver() {
		typeMap = new HashMap<>();
		typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT",
				new QualifiedJavaType(Long.class.getName())));
		typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY",
				new QualifiedJavaType("byte[]")));
		typeMap.put(Types.BIT, new JdbcTypeInformation("BIT",
				new QualifiedJavaType(Boolean.class.getName())));
		typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB",
				new QualifiedJavaType("byte[]")));
		typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN",
				new QualifiedJavaType(Boolean.class.getName())));
		typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR",
				new QualifiedJavaType(String.class.getName())));
		typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB",
				new QualifiedJavaType(String.class.getName())));
		typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.DATE, new JdbcTypeInformation("DATE",
				new QualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE",
				new QualifiedJavaType(Double.class.getName())));
		typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT",
				new QualifiedJavaType(Double.class.getName())));
		typeMap.put(Types.DECIMAL, new JdbcTypeInformation("DECIMAL",
				new QualifiedJavaType(BigDecimal.class.getName())));
		typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER",
				new QualifiedJavaType(Integer.class.getName())));
		typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR",
				new QualifiedJavaType(String.class.getName())));
		typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR",
				new QualifiedJavaType(String.class.getName())));
		typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY",
				new QualifiedJavaType("byte[]")));
		typeMap.put(Types.NULL, new JdbcTypeInformation("NULL",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.REAL, new JdbcTypeInformation("REAL",
				new QualifiedJavaType(Float.class.getName())));
		typeMap.put(Types.REF, new JdbcTypeInformation("REF",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT",
				new QualifiedJavaType(Short.class.getName())));
		typeMap.put(Types.STRUCT, new JdbcTypeInformation("SMALLINT",
				new QualifiedJavaType(Object.class.getName())));
		typeMap.put(Types.TIME, new JdbcTypeInformation("TIME",
				new QualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP",
				new QualifiedJavaType(Date.class.getName())));
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
				new QualifiedJavaType(Byte.class.getName())));
		typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY",
				new QualifiedJavaType("byte[]")));
		typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR",
				new QualifiedJavaType(String.class.getName())));
	}
	
	public QualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
		QualifiedJavaType res = null;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(introspectedColumn.getJdbcType());
		if(jdbcTypeInformation != null) {
			res = jdbcTypeInformation.getQualifiedJavaType();
		}else {
			int length = introspectedColumn.getLength();
			switch(introspectedColumn.getJdbcType()) {
			case Types.NUMERIC:
				if(introspectedColumn.getScale() > 0 || length > 18) {
					res = new QualifiedJavaType(BigDecimal.class.getName());
				}else if(length > 9) {
					res = new QualifiedJavaType(Long.class.getName());
				}else  {
					res = new QualifiedJavaType(Integer.class.getName());
//				}else {
//					res = new QualifiedJavaType(Short.class.getName());
				}
			}
		}
		return res;
	}
	
	public String calculateJavaTypeName(IntrospectedColumn introspectedColumn) {
		String res = null;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(introspectedColumn.getJdbcType());
		if(jdbcTypeInformation != null) {
			res = jdbcTypeInformation.getJdbcTypeName();
		}else {
			switch(introspectedColumn.getJdbcType()) {
			case Types.NUMERIC:
				res = "NUMERIC";
			}
		}
		return res;
	}

	public static class JdbcTypeInformation{
		private String jdbcTypeName;
		
		private QualifiedJavaType qualifiedJavaType;

		public JdbcTypeInformation(String jdbcTypeName, QualifiedJavaType qualifiedJavaType) {
			super();
			this.jdbcTypeName = jdbcTypeName;
			this.qualifiedJavaType = qualifiedJavaType;
		}

		public String getJdbcTypeName() {
			return jdbcTypeName;
		}

		public QualifiedJavaType getQualifiedJavaType() {
			return qualifiedJavaType;
		}
		
	}

	public String calculateMapperName(FullyQualifiedTable qualifiedTable) {
		return qualifiedTable.getDomainObjectName() + "Mapper";
	}

	public String calculateServiceName(FullyQualifiedTable qualifiedTable) {
		return qualifiedTable.getDomainObjectName() + "Service";
	}
}
