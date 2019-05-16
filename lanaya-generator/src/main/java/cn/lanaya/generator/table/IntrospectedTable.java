package cn.lanaya.generator.table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import cn.lanaya.generator.codegen.JavaTypeResolver;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.util.StringTools;

public class IntrospectedTable {

	private Properties propertiesHolder;
	protected FullyQualifiedTable fullyQualifiedTable;
	protected List<IntrospectedColumn> primaryKeyColumns;
	protected List<IntrospectedColumn> baseColumns;
	protected List<IntrospectedColumn> blobColumns;
	protected String remarks;
	protected String tableType;
	private JavaTypeResolver javaTypeResolver = new JavaTypeResolver();

	public IntrospectedTable(Properties propertiesHolder) {
		this.propertiesHolder = propertiesHolder;
		primaryKeyColumns = new ArrayList<>();
		this.baseColumns = new ArrayList<>();
		this.blobColumns = new ArrayList<>();
	}
	public IntrospectedTable() {
		primaryKeyColumns = new ArrayList<>();
		this.baseColumns = new ArrayList<>();
		this.blobColumns = new ArrayList<>();
	}

	/**
	 * @param databaseMetaData
	 * @return
	 */
	public List<IntrospectedTable> calculateIntrospectedTable(DatabaseMetaData databaseMetaData, Set<String> target) {
		Map<ActualTableName, List<IntrospectedColumn>> columns = introspectedTable(databaseMetaData, target);
		calculateIntrospectedColumn(columns);
		List<IntrospectedTable> answer = new ArrayList<>();
		for (Entry<ActualTableName, List<IntrospectedColumn>> entry : columns.entrySet()) {
			IntrospectedTable table = new IntrospectedTable();
			ActualTableName key = entry.getKey();
			List<IntrospectedColumn> value = entry.getValue();
			table.addBaseColumns(value);
			FullyQualifiedTable qualifiedTable = getFullyQualifiedTable(key.getTableName());
			qualifiedTable.setMapperName(javaTypeResolver.calculateMapperName(qualifiedTable));
			qualifiedTable.setServiceName(javaTypeResolver.calculateServiceName(qualifiedTable));
			table.setFullyQualifiedTable(qualifiedTable);
			calculatePrimaryColumn(qualifiedTable, table, databaseMetaData);
			
			answer.add(table);
		}
		return answer;
	}
	
	private void calculatePrimaryColumn(FullyQualifiedTable value, IntrospectedTable table, DatabaseMetaData databaseMetaData) {
		ResultSet rs = null;
		try {
			rs = databaseMetaData.getPrimaryKeys(null, null, value.getIntrospectedTableName());
			TreeMap<Short, String> pk = new TreeMap<>();
			while(rs.next()) {
				String name = rs.getString("COLUMN_NAME");
				short index = rs.getShort("KEY_SEQ");
				pk.put(index, name);
			}
			for (String column : pk.values()) {
				table.addPrimaryKeyColumns(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
	}
	
	/**
	 * @param tableName
	 * @return
	 */
	private FullyQualifiedTable getFullyQualifiedTable(String tableName) {
		FullyQualifiedTable table = new FullyQualifiedTable();
		String rootPackage = propertiesHolder.getProperty("module.package");
		table.setDomainObjectSubPackage(rootPackage + ".entity");
		table.setViewObjectSubPackage(rootPackage + ".vo");
		table.setMapperSubPackage(rootPackage + ".dao");
		table.setServiceSubPackage(rootPackage + ".service");
		table.setServiceImplSubPackage(rootPackage + ".service.impl");
		table.setControllerSubPackage(rootPackage + ".web");
		table.setIntrospectedTableName(tableName);
		if("true".equals(propertiesHolder.getProperty("prefix.remove"))){
			tableName = tableName.substring(tableName.indexOf('_'));
		}
		String domainObjectName = StringTools.getCamelCase(tableName, true);
		table.setDomainObjectName(domainObjectName);
		table.setServiceName(domainObjectName + "Service");
		table.setServiceImplName(table.getServiceName() + "Impl");
		table.setControllerName(domainObjectName + "Controller");
		table.setViewObjectName(domainObjectName + propertiesHolder.getProperty("vo.name"));
		return table;
	}
	
	private void calculateIntrospectedColumn(Map<ActualTableName, List<IntrospectedColumn>> columns) {
		for (Entry<ActualTableName, List<IntrospectedColumn>> e : columns.entrySet()) {
			List<IntrospectedColumn> value = e.getValue();
			for (IntrospectedColumn introspectedColumn : value) {
				QualifiedJavaType qualifiedJavaType = javaTypeResolver.calculateJavaType(introspectedColumn);
				introspectedColumn.setQualifiedJavaType(qualifiedJavaType);
				String jdbcTypeName = javaTypeResolver.calculateJavaTypeName(introspectedColumn);
				introspectedColumn.setJdbcTypeName(jdbcTypeName);
				String javaProperty = StringTools.getCamelCase(introspectedColumn.getActualColumnName(), false);
				introspectedColumn.setJavaProperty(javaProperty);
			}
		}
	}

	/**
	 * 
	 * @param databaseMetaData
	 * @return
	 */
	public Map<ActualTableName, List<IntrospectedColumn>> introspectedTable(DatabaseMetaData databaseMetaData, Set<String> target) {
		List<String> tableNames = getTableNames(databaseMetaData);
		return getColumns(tableNames, databaseMetaData, target);
	}

	private Map<ActualTableName, List<IntrospectedColumn>> getColumns(List<String> tableNames,
			DatabaseMetaData metaData, Set<String> target) {
		Map<ActualTableName, List<IntrospectedColumn>> res = new HashMap<>();
		for (String tableName : tableNames) {
			if(target == null || target.contains(tableName)) {
				Map<ActualTableName, List<IntrospectedColumn>> columns = getColumns(tableName, metaData);
				res.putAll(columns);
			}
		}
		return res;
	}

	private Map<ActualTableName, List<IntrospectedColumn>> getColumns(String tableName, DatabaseMetaData metaData) {
		ResultSet rs = null;
		try {
			rs = metaData.getColumns(null, null, tableName, "%");
			return getColumns(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
		}
		return null;
	}

	private Map<ActualTableName, List<IntrospectedColumn>> getColumns(ResultSet rs) throws SQLException {
		Map<ActualTableName, List<IntrospectedColumn>> answer = new HashMap<ActualTableName, List<IntrospectedColumn>>();
		boolean supportsIsAutoIncrement = false;
		boolean supportsIsGeneratedColumn = false;
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) {
				supportsIsAutoIncrement = true;
			}
			if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) {
				supportsIsGeneratedColumn = true;
			}
		}
		while (rs.next()) {
			IntrospectedColumn introspectedColumn = new IntrospectedColumn();
			introspectedColumn.setTableAlias("");
			introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE"));
			introspectedColumn.setLength(rs.getInt("COLUMN_SIZE"));
			introspectedColumn.setActualColumnName(rs.getString("COLUMN_NAME"));
			introspectedColumn.setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
			introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS"));
			introspectedColumn.setRemarks(rs.getString("REMARKS"));
			introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF"));
			if (supportsIsAutoIncrement) {
				introspectedColumn.setAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
			}

			if (supportsIsGeneratedColumn) {
				introspectedColumn.setGeneratedColumn("YES".equals(rs.getString("IS_GENERATEDCOLUMN")));
			}
			ActualTableName atn = new ActualTableName(rs.getString("TABLE_CAT"), rs.getString("TABLE_SCHEM"),
					rs.getString("TABLE_NAME"));

			List<IntrospectedColumn> columns = answer.get(atn);
			if (columns == null) {
				columns = new ArrayList<IntrospectedColumn>();
				answer.put(atn, columns);
			}
			columns.add(introspectedColumn);
		}
		return answer;
	}

	private List<String> getTableNames(DatabaseMetaData databaseMetaData) {
		List<String> res = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = databaseMetaData.getTables(null, null, null, null);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				res.add(tableName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
		}
		return res;
	}

	private void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<IntrospectedColumn> getAllColumns() {
		List<IntrospectedColumn> res = new ArrayList<>();
		res.addAll(primaryKeyColumns);
		res.addAll(baseColumns);
		res.addAll(blobColumns);
		return res;
	}

	public void addPrimaryKeyColumns(String column) {
		Iterator<IntrospectedColumn> it = baseColumns.iterator();
		while (it.hasNext()) {
			IntrospectedColumn next = it.next();
			if (next.getActualColumnName().equals(column)) {
				primaryKeyColumns.add(next);
				it.remove();
			}
		}
	}

	public void addBaseColumns(List<IntrospectedColumn> column) {
		baseColumns.addAll(column);
	}

	public void addBlobColumns(IntrospectedColumn column) {
		blobColumns.add(column);
	}

	public FullyQualifiedTable getFullyQualifiedTable() {
		return fullyQualifiedTable;
	}

	public List<IntrospectedColumn> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}

	public List<IntrospectedColumn> getBaseColumns() {
		return baseColumns;
	}

	public List<IntrospectedColumn> getBlobColumns() {
		return blobColumns;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getTableType() {
		return tableType;
	}

	public void setFullyQualifiedTable(FullyQualifiedTable qualifiedTable) {
		this.fullyQualifiedTable = qualifiedTable;
	}

}
