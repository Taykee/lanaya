package cn.lanaya.generator.codegen.mybatis3.xmlmapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.lanaya.generator.codegen.XmlConstants;
import cn.lanaya.generator.dom.OutputUtilities;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.dom.xml.Attribute;
import cn.lanaya.generator.dom.xml.Document;
import cn.lanaya.generator.dom.xml.TextElement;
import cn.lanaya.generator.dom.xml.XmlElement;
import cn.lanaya.generator.dom.xml.XmlMapperKeys;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedColumn;
import cn.lanaya.generator.table.IntrospectedTable;

public class XMLMapperGenerator {

	private IntrospectedTable introspectedTable;
	
	public XmlElement getSqlMapElement(IntrospectedTable introspectedTable) {
		this.introspectedTable = introspectedTable;
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		XmlElement answer = new XmlElement("mapper");
		String mapperName = table.getFullMapperName();
		answer.addAttribute(new Attribute("namespace", mapperName));

		addResultMapElement(answer);
		addBaseColumnListElement(answer);
		addSelectByClauseElement(answer);
		addSelectByPrimaryKeyElement(answer);
		addDeleteByPrimaryKeyElement(answer);
		addDeleteByClauseElement(answer);
		addInsertElement(answer);
		addInsertSelectiveElement(answer);
		addCountByClauseElement(answer);
		addUpdateByClauseSelectiveElement(answer);
		addUpdateByPrimaryKeySelectiveElement(answer);
		addUpdateByPrimaryKeyElement(answer);
		addPageByClauseElement(answer);//分页sql需要用户自己写
		addDeleteByPrimaryKeyBatchElement(answer);
		addInsertSelectiveBatchElement(answer);
		addWhereClauseElement(answer);
		addPageWhereClauseElement(answer);
		addSetSelectiveSql(answer);

		return answer;
	}

	private void addPageWhereClauseElement(XmlElement parentElement) {
		XmlElement sql = new XmlElement("sql");
		sql.addAttribute(new Attribute("id", XmlMapperKeys.PAGE_WHERE_CLAUSE_ID));
		XmlElement where = new XmlElement("where");
		List<IntrospectedColumn> baseColumns = introspectedTable.getBaseColumns();
		for (IntrospectedColumn c : baseColumns) {
			String javaProperty = c.getJavaProperty();
			String actualColumnName = c.getActualColumnName();
			String jdbcTypeName = c.getJdbcTypeName();
			if(c.getQualifiedJavaType().equals(QualifiedJavaType.getDateInstance())) {
				XmlElement ifEl = new XmlElement("if");
				String value = "params.entity." + javaProperty + "Start != null";
				if(c.getQualifiedJavaType().equals(QualifiedJavaType.getStringInstance())){
					value = value + " and params.entity." + javaProperty + "Start != ''";
				}
				ifEl.addAttribute(new Attribute("test", value));
				String content = "<![CDATA[and " + actualColumnName + " >= " + "#{params.entity." + javaProperty
						+ "Start, jdbcType = " + jdbcTypeName + "}]]>";
				TextElement text = new TextElement(content);
				ifEl.addElement(text);
				XmlElement if2 = new XmlElement("if");
				String value2 = "params.entity." + javaProperty + "End != null";
				if2.addAttribute(new Attribute("test", value2));
				content = "<![CDATA[and " + actualColumnName + " <= " + "#{params.entity." + javaProperty
						+ "End, jdbcType = " + jdbcTypeName + "}]]>";
				TextElement text2 = new TextElement(content);
				if2.addElement(text2);
				where.addElement(ifEl);
				where.addElement(if2);
			}else {
				XmlElement ifEl = new XmlElement("if");
				String value = "params.entity." + javaProperty + " != null";
				if(c.getQualifiedJavaType().equals(QualifiedJavaType.getStringInstance())){
					value = value + " and params.entity." + javaProperty + " != ''";
				}
				ifEl.addAttribute(new Attribute("test", value));
				String content = "and " + actualColumnName + " = " + "#{params.entity." + javaProperty
						+ ", jdbcType = " + jdbcTypeName + "}";
				TextElement text = new TextElement(content);
				ifEl.addElement(text);
				where.addElement(ifEl);
			}
		}
		sql.addElement(where);
		parentElement.addElement(sql);		
	}

	private void addInsertSelectiveBatchElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		XmlElement insert = new XmlElement("insert");
		insert.addAttribute(new Attribute("id", XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL));
		insert.addAttribute(new Attribute("parameterType", "java.util.List"));
		insert.addElement(new TextElement("insert into " + table.getIntrospectedTableName()));
		
		XmlElement foreach = new XmlElement("foreach");
		foreach.addAttribute(new Attribute("collection", "list"));
		foreach.addAttribute(new Attribute("item", "param"));
		foreach.addAttribute(new Attribute("separator", ","));
		StringBuilder sb = new StringBuilder("(");
		StringBuilder sb2 = new StringBuilder("(");
		for (IntrospectedColumn c : introspectedTable.getAllColumns()) {
			sb.append(c.getActualColumnName()).append(", ");
			sb2.append("#{param.").append(c.getJavaProperty()).append(", jdbcType = ")
				.append(c.getJdbcTypeName()).append("},");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(")");
		sb2.delete(sb2.length() - 1, sb2.length());
		sb2.append(")");
		foreach.addElement(new TextElement(sb2.toString()));
		insert.addElement(new TextElement(sb.toString()));
		insert.addElement(new TextElement("values"));
		insert.addElement(foreach);
		parentElement.addElement(insert);
		
	}

	private void addDeleteByPrimaryKeyBatchElement(XmlElement parentElement) {
		XmlElement delete = new XmlElement("delete");
		delete.addAttribute(new Attribute("id", XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL));
		delete.addAttribute(new Attribute("parameterType", "java.util.List"));
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		delete.addElement(new TextElement("delete from " + table.getIntrospectedTableName()));
		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
		if(primaryKeyColumns.size() == 1) {
			delete.addElement(new TextElement("where"));
			XmlElement foreach = new XmlElement("foreach");
			foreach.addAttribute(new Attribute("collection", "list"));
			foreach.addAttribute(new Attribute("item", "param"));
			foreach.addAttribute(new Attribute("separator", ","));
			foreach.addAttribute(new Attribute("open", "("));
			foreach.addAttribute(new Attribute("close", ")"));
			StringBuilder sb = new StringBuilder();
			StringBuilder key = new StringBuilder("#{param.");
			for (IntrospectedColumn c : primaryKeyColumns) {
				sb.append(c.getActualColumnName());
				key.append(c.getJavaProperty()).append(", jdbcType = ").append(c.getJdbcTypeName()).append("}");
			}
			sb.append(" in ");
			delete.addElement(new TextElement(sb.toString()));
			foreach.addElement(new TextElement(key.toString()));
			delete.addElement(foreach);
		}
		parentElement.addElement(delete);
	}

	private void addPageByClauseElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voName = table.getFullDomainObjectName();
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", XmlMapperKeys.PAGE_BY_CLAUSE_SQL));
		select.addAttribute(new Attribute("resultMap", XmlMapperKeys.BASE_RESULT_MAP_ID));
		select.addAttribute(new Attribute("parameterType", voName));
		select.addElement(new TextElement("select"));
		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", XmlMapperKeys.BASE_COLUMN_LIST_ID));
		select.addElement(include);
		select.addElement(new TextElement("from " + table.getIntrospectedTableName()));
		XmlElement include2 = new XmlElement("include");
		include2.addAttribute(new Attribute("refid", XmlMapperKeys.PAGE_WHERE_CLAUSE_ID));
		select.addElement(include2);
		parentElement.addElement(select);
	}

	protected void addSetSelectiveSql(XmlElement parentElement) {
		XmlElement sql = new XmlElement("sql");
		sql.addAttribute(new Attribute("id", XmlMapperKeys.UPDATE_SET_SQL));
		XmlElement set = new XmlElement("set");
		for (IntrospectedColumn c : introspectedTable.getBaseColumns()) {
			XmlElement ifEl = new XmlElement("if");
			ifEl.addAttribute(new Attribute("test", "entity." + c.getJavaProperty() + " != null"));
			ifEl.addElement(new TextElement(c.getActualColumnName() + " = #{entity." +
					c.getJavaProperty() + ", jdbcType = " + c.getJdbcTypeName() + "},"));
			set.addElement(ifEl);
		}
		sql.addElement(set);
		parentElement.addElement(sql);
	}

	protected void addResultMapElement(XmlElement parentElement) {
		XmlElement result = new XmlElement("resultMap");
		result.addAttribute(new Attribute("id", XmlMapperKeys.BASE_RESULT_MAP_ID));
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		result.addAttribute(new Attribute("type", table.getFullDomainObjectName()));

		addResultMapElements(result);
		parentElement.addElement(result);
	}

	private void addResultMapElements(XmlElement answer) {
		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
		for (IntrospectedColumn column : primaryKeyColumns) {
			XmlElement primaryKey = new XmlElement("id");
			primaryKey.addAttribute(new Attribute("column", column.getActualColumnName()));
			primaryKey.addAttribute(new Attribute("property", column.getJavaProperty()));
			primaryKey.addAttribute(new Attribute("jdbcType", column.getJdbcTypeName()));
			answer.addElement(primaryKey);
		}

		List<IntrospectedColumn> baseColumns = introspectedTable.getBaseColumns();
		for (IntrospectedColumn column : baseColumns) {
			XmlElement field = new XmlElement("result");
			field.addAttribute(new Attribute("column", column.getActualColumnName()));
			field.addAttribute(new Attribute("property", column.getJavaProperty()));
			field.addAttribute(new Attribute("jdbcType", column.getJdbcTypeName()));
			answer.addElement(field);
		}

	}

	protected void addWhereClauseElement(XmlElement parentElement) {
		XmlElement sql = new XmlElement("sql");
		sql.addAttribute(new Attribute("id", XmlMapperKeys.WHERE_CLAUSE_ID));
		XmlElement where = new XmlElement("where");
		List<IntrospectedColumn> baseColumns = introspectedTable.getBaseColumns();
		for (IntrospectedColumn c : baseColumns) {
			String javaProperty = c.getJavaProperty();
			String actualColumnName = c.getActualColumnName();
			String jdbcTypeName = c.getJdbcTypeName();
			if(c.getQualifiedJavaType().equals(QualifiedJavaType.getDateInstance())) {
				XmlElement ifEl = new XmlElement("if");
				String value = "param." + javaProperty + "Start != null";
				if(c.getQualifiedJavaType().equals(QualifiedJavaType.getStringInstance())){
					value = value + " and params." + javaProperty + "Start != ''";
				}
				ifEl.addAttribute(new Attribute("test", value));
				String content = "<![CDATA[and " + actualColumnName + " >= " + "#{param." + javaProperty
						+ "Start, jdbcType = " + jdbcTypeName + "}]]>";
				TextElement text = new TextElement(content);
				ifEl.addElement(text);
				XmlElement if2 = new XmlElement("if");
				String value2 = "param." + javaProperty + "End != null";
				if2.addAttribute(new Attribute("test", value2));
				content = "<![CDATA[and " + actualColumnName + " <= " + "#{param." + javaProperty
						+ "End, jdbcType = " + jdbcTypeName + "}]]>";
				TextElement text2 = new TextElement(content);
				if2.addElement(text2);
				where.addElement(ifEl);
				where.addElement(if2);
			}else {
				XmlElement ifEl = new XmlElement("if");
				String value = "param." + javaProperty + " != null";
				if(c.getQualifiedJavaType().equals(QualifiedJavaType.getStringInstance())){
					value = value + " and param." + javaProperty + " != ''";
				}
				ifEl.addAttribute(new Attribute("test", value));
				String content = "and " + actualColumnName + " = " + "#{param." + javaProperty
						+ ", jdbcType = " + jdbcTypeName + "}";
				TextElement text = new TextElement(content);
				ifEl.addElement(text);
				where.addElement(ifEl);
			}
		}
		
		sql.addElement(where);
		parentElement.addElement(sql);
	}
	
	protected void addBaseColumnListElement(XmlElement parentElement) {
		XmlElement sql = new XmlElement("sql");
		sql.addAttribute(new Attribute("id", XmlMapperKeys.BASE_COLUMN_LIST_ID));
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		StringBuilder sb = new StringBuilder();
		for (IntrospectedColumn c : columns) {
			sb.append(c.getActualColumnName()).append(", ");
		}
//		sb.deleteCharAt(sb.length() - 1);
		sb.delete(sb.length() - 2, sb.length());
		sql.addElement(new TextElement(sb.toString()));
		parentElement.addElement(sql);
	}

	protected void addSelectByClauseElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voName = table.getFullDomainObjectName();
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", XmlMapperKeys.SELECT_BY_CLAUSE_SQL));
		select.addAttribute(new Attribute("resultMap", XmlMapperKeys.BASE_RESULT_MAP_ID));
		select.addAttribute(new Attribute("parameterType", voName));
		select.addElement(new TextElement("select"));
		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", XmlMapperKeys.BASE_COLUMN_LIST_ID));
		select.addElement(include);
		select.addElement(new TextElement("from " + table.getIntrospectedTableName()));
		XmlElement include2 = new XmlElement("include");
		include2.addAttribute(new Attribute("refid", XmlMapperKeys.WHERE_CLAUSE_ID));
		select.addElement(include2);
		parentElement.addElement(select);
	}

	protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL));
		select.addAttribute(new Attribute("resultMap", XmlMapperKeys.BASE_RESULT_MAP_ID));
		StringBuilder sb = new StringBuilder("where ");
		Set<String> set = new HashSet<>();
		for (IntrospectedColumn c : primaryKeyColumns) {
			sb.append(c.getActualColumnName()).append(" = #{").append(c.getJavaProperty())
			.append(", jdbcType = ").append(c.getJdbcTypeName()).append("} and");
		}
		if(set.size() == 1) {
			select.addAttribute(new Attribute("parameterType", set.iterator().next()));
		}
		sb.delete(sb.length() - 4, sb.length());
		select.addElement(new TextElement("select"));
		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", XmlMapperKeys.BASE_COLUMN_LIST_ID));
		select.addElement(include);
		select.addElement(new TextElement("from " + table.getIntrospectedTableName()));
		select.addElement(new TextElement(sb.toString()));
		parentElement.addElement(select);
	}

	protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
		XmlElement delete = new XmlElement("delete");
		delete.addAttribute(new Attribute("id", XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL));
		StringBuilder sb = new StringBuilder("where ");
		Set<String> set = new HashSet<>();
		for (IntrospectedColumn c : primaryKeyColumns) {
			set.add(c.getQualifiedJavaType().getBaseQualifiedName());
			sb.append(c.getActualColumnName()).append(" = #{").append(c.getJavaProperty())
			.append(", jdbcType = ").append(c.getJdbcTypeName()).append("} and");
		}
		if(set.size() == 1) {
			delete.addAttribute(new Attribute("parameterType", set.iterator().next()));
		}
		sb.delete(sb.length() - 4, sb.length());
		delete.addElement(new TextElement("delete from " + table.getIntrospectedTableName()));
		delete.addElement(new TextElement(sb.toString()));
		parentElement.addElement(delete);
	}

	protected void addDeleteByClauseElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voName = table.getFullViewObjectName();
		XmlElement delete = new XmlElement("delete");
		delete.addAttribute(new Attribute("id", XmlMapperKeys.DELETE_BY_CLAUSE_SQL));
		delete.addAttribute(new Attribute("parameterType", voName));
		delete.addElement(new TextElement("delete from " + table.getIntrospectedTableName()));
		XmlElement include2 = new XmlElement("include");
		include2.addAttribute(new Attribute("refid", XmlMapperKeys.WHERE_CLAUSE_ID));
		delete.addElement(include2);
		parentElement.addElement(delete);
	}

	protected void addInsertElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		XmlElement insert = new XmlElement("insert");
		insert.addAttribute(new Attribute("id", XmlMapperKeys.INSERT_SQL));
		insert.addAttribute(new Attribute("parameterType", name));
		insert.addElement(new TextElement("insert into " + table.getIntrospectedTableName()));
		StringBuilder properties = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (IntrospectedColumn c : introspectedTable.getAllColumns()) {
			properties.append(c.getActualColumnName()).append(", ");
			values.append("#{").append(c.getJavaProperty()).append(", jdbcType = ")
			.append(c.getJdbcTypeName()).append("}, ");
		}
		properties.delete(properties.length() - 2, properties.length());
		properties.append(")");
		values.delete(values.length() - 2, values.length());
		values.append(")");
		insert.addElement(new TextElement(properties.toString()));
		insert.addElement(new TextElement("values"));
		insert.addElement(new TextElement(values.toString()));
		parentElement.addElement(insert);
	}

	protected void addInsertSelectiveElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		XmlElement insert = new XmlElement("insert");
		insert.addAttribute(new Attribute("id", XmlMapperKeys.INSERT_SELECTIVE_SQL));
		insert.addAttribute(new Attribute("parameterType", name));
		insert.addElement(new TextElement("insert into " + table.getIntrospectedTableName()));
		XmlElement trim = new XmlElement("trim");
		trim.addAttribute(new Attribute("prefix", "("));
		trim.addAttribute(new Attribute("suffix", ")"));
		trim.addAttribute(new Attribute("suffixOverrides", ","));
		XmlElement values = new XmlElement("trim");
		values.addAttribute(new Attribute("prefix", "values ("));
		values.addAttribute(new Attribute("suffix", ")"));
		values.addAttribute(new Attribute("suffixOverrides", ","));
		for (IntrospectedColumn c : introspectedTable.getAllColumns()) {
			XmlElement ifEl = new XmlElement("if");
			ifEl.addAttribute(new Attribute("test", c.getJavaProperty() + " != null"));
			ifEl.addElement(new TextElement(c.getActualColumnName() + ","));
			trim.addElement(ifEl);
			XmlElement ifEl2 = new XmlElement("if");
			ifEl2.addAttribute(new Attribute("test", c.getJavaProperty() + " != null"));
			ifEl2.addElement(new TextElement("#{" +c.getJavaProperty() + ", jdbcType = " + c.getJdbcTypeName() + "},"));
			values.addElement(ifEl2);
		}
		insert.addElement(trim);
		insert.addElement(values);
		parentElement.addElement(insert);
	}

	protected void addCountByClauseElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voName = table.getFullViewObjectName();
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", XmlMapperKeys.COUNT_BY_CLAUSE_SQL));
		select.addAttribute(new Attribute("parameterType", voName));
		select.addAttribute(new Attribute("resultType", "java.lang.Integer"));
		select.addElement(new TextElement("select count(*) from " + table.getIntrospectedTableName()));
		parentElement.addElement(select);
	}

	protected void addUpdateByClauseSelectiveElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
//		String voName = table.getFullViewObjectName();
		XmlElement update = new XmlElement("update");
		update.addAttribute(new Attribute("id", XmlMapperKeys.UPDATE_BY_CLAUSE_SELECTIVE_SQL));
//		update.addAttribute(new Attribute("parameterType", voName));
		update.addElement(new TextElement("update " + table.getIntrospectedTableName()));
		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", XmlMapperKeys.UPDATE_SET_SQL));
		update.addElement(include);
		XmlElement include2 = new XmlElement("include");
		include2.addAttribute(new Attribute("refid", XmlMapperKeys.WHERE_CLAUSE_ID));
		update.addElement(include2);
		
		parentElement.addElement(update);
	}

	protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		XmlElement update = new XmlElement("update");
		update.addAttribute(new Attribute("id", XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL));
		update.addAttribute(new Attribute("parameterType", name));
		update.addElement(new TextElement("update " + table.getIntrospectedTableName()));
		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", XmlMapperKeys.UPDATE_SET_SQL));
		update.addElement(include);
		StringBuilder sb = new StringBuilder("where ");
		for (IntrospectedColumn c : introspectedTable.getPrimaryKeyColumns()) {
			sb.append(c.getActualColumnName()).append(" = #{entity.").append(c.getJavaProperty())
				.append(", jdbcType = ").append(c.getJdbcTypeName()).append("} and ");
		}
		sb.delete(sb.length() - 5, sb.length());
		update.addElement(new TextElement(sb.toString()));
		parentElement.addElement(update);
	}

	protected void addUpdateByPrimaryKeyElement(XmlElement parentElement) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		XmlElement update = new XmlElement("update");
		update.addAttribute(new Attribute("id", XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SQL));
		update.addAttribute(new Attribute("parameterType", name));
		update.addElement(new TextElement("update " + table.getIntrospectedTableName()));
		StringBuilder set = new StringBuilder("set ");
		for (IntrospectedColumn c : introspectedTable.getBaseColumns()) {
			OutputUtilities.newLine(set);
			OutputUtilities.javaIndent(set, 1);
			set.append(c.getActualColumnName()).append(" = #{").append(c.getJavaProperty())
				.append(", jdbcType = ").append(c.getJdbcTypeName()).append("},");
		}
		set.delete(set.length() - 1, set.length());
		update.addElement(new TextElement(set.toString()));
		StringBuilder sb = new StringBuilder("where ");
		for (IntrospectedColumn c : introspectedTable.getPrimaryKeyColumns()) {
			sb.append(c.getActualColumnName()).append(" = #{").append(c.getJavaProperty())
				.append(", jdbcType = ").append(c.getJdbcTypeName()).append("} and ");
		}
		sb.delete(sb.length() - 5, sb.length());
		update.addElement(new TextElement(sb.toString()));
		parentElement.addElement(update);
	}

	public Document getDocument(IntrospectedTable introspectedTable) {
		Document doc = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
		doc.setRootElement(getSqlMapElement(introspectedTable));
		return doc;
	}

}
