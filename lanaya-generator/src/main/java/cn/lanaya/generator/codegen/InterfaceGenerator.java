package cn.lanaya.generator.codegen;

import java.util.List;

import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Interface;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.Parameter;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.dom.xml.XmlMapperKeys;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedColumn;
import cn.lanaya.generator.table.IntrospectedTable;

public abstract class InterfaceGenerator{
	
	protected IntrospectedTable introspectedTable;
	
	public abstract CompilationUnit getCompilationUnit(IntrospectedTable introspectedTable);
	
	/**
	 * @param interfaze
	 * @param hasAnnotation
	 */
	protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze, boolean hasAnnotation) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setName(XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		if(hasAnnotation) {
			parameter.addAnnotation("@Param(\"entity\")");
		}
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}
	/**
	 * @param interfaze
	 * @param hasAnnotation
	 */
	protected void addUpdateByPrimaryKeyMethod(Interface interfaze, boolean hasAnnotation) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getIntMethod(XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SQL);
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		if(hasAnnotation) {
			parameter.addAnnotation("@Param(\"entity\")");
		}
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}

	/**
	 * @param interfaze
	 * @param hasAnnotation
	 */
	protected void addUpdateByClauseSelectiveMethod(Interface interfaze, boolean hasAnnotation) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getIntMethod(XmlMapperKeys.UPDATE_BY_CLAUSE_SELECTIVE_SQL);
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		QualifiedJavaType vo = new QualifiedJavaType(table.getFullViewObjectName());
		Parameter entity = new Parameter(javaType, "entity");
		Parameter param = new Parameter(vo, "param");
		if(hasAnnotation) {
			entity.addAnnotation("@Param(\"entity\")");
			param.addAnnotation("@Param(\"param\")");
		}
		method.addParameter(entity);
		method.addParameter(param);
		interfaze.addMethod(method);
	}

	protected Method getIntMethod(String name) {
		Method method = new Method();
		method.setName(name);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		return method;
	}

	/**
	 * @param interfaze
	 */
	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		if (columns == null || columns.isEmpty()) {
			return;
		}
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setName(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL);
		QualifiedJavaType qualifiedJavaType = new QualifiedJavaType(table.getFullDomainObjectName());
		method.setReturnType(qualifiedJavaType);
		for (IntrospectedColumn column : columns) {
			QualifiedJavaType type = column.getQualifiedJavaType();
			Parameter parameter = new Parameter(type, column.getJavaProperty());
			method.addParameter(parameter);
		}
		interfaze.addMethod(method);
	}

	/**
	 * @param interfaze
	 * @param hasAnnotation
	 */
	protected void addSelectByClauseMethod(Interface interfaze, boolean hasAnnotation) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setName(XmlMapperKeys.SELECT_BY_CLAUSE_SQL);
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getFullDomainObjectName()));
		method.setReturnType(list);
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		if(hasAnnotation) {
			parameter.addAnnotation("@Param(\"param\")");
		}
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}

	/**
	 * @param interfaze
	 */
	protected void addInsertSelectiveMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Method method = getIntMethod(XmlMapperKeys.INSERT_SELECTIVE_SQL);
		Parameter parameter = new Parameter(javaType, "record");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}

	/**
	 * @param interfaze
	 */
	protected void addInsertMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Method method = getIntMethod(XmlMapperKeys.INSERT_SQL);
		Parameter parameter = new Parameter(javaType, "record");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}

	/**
	 * @param interfaze
	 */
	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		if (columns == null || columns.isEmpty()) {
			return;
		}
		Method method = getIntMethod(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL);
		for (IntrospectedColumn column : columns) {
			QualifiedJavaType type = column.getQualifiedJavaType();
			Parameter parameter = new Parameter(type, column.getJavaProperty());
			method.addParameter(parameter);
		}
		interfaze.addMethod(method);
	}

	/**
	 * @param interfaze
	 * @param hasAnnotation
	 */
	protected void addDeleteByClauseMethod(Interface interfaze, boolean hasAnnotation) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullViewObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Method method = getIntMethod(XmlMapperKeys.DELETE_BY_CLAUSE_SQL);
		Parameter parameter = new Parameter(javaType, "record");
		if(hasAnnotation) {
			parameter.addAnnotation("@Param(\"param\")");
		}
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}

	/**
	 * 添加
	 * @param interfaze
	 */
	protected void addCountByClauseMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullViewObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Method method = getIntMethod(XmlMapperKeys.COUNT_BY_CLAUSE_SQL);
		Parameter parameter = new Parameter(javaType, "record");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}

	protected void addPageByClauseMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setName(XmlMapperKeys.PAGE_BY_CLAUSE_SQL);
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getFullViewObjectName()));
		method.setReturnType(list);
		QualifiedJavaType javaType = new QualifiedJavaType("cn.lanaya.common.bean.PageQO");
		Parameter parameter = new Parameter(javaType, "page");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}
	
	protected void addDeleteByPrimaryKeyBatchMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getIntMethod(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL);
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getFullViewObjectName()));
		Parameter parameter = new Parameter(list, "list");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}
	
	protected void addInsertSelectiveBatchMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getIntMethod(XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL);
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getDomainObjectName()));
		Parameter parameter = new Parameter(list, "list");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}
}
