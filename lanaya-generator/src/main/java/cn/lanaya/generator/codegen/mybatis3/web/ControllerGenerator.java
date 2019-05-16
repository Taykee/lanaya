package cn.lanaya.generator.codegen.mybatis3.web;

import java.util.List;

import cn.lanaya.generator.codegen.mybatis3.service.ServiceMethodBody;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Field;
import cn.lanaya.generator.dom.java.JavaVisibility;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.Parameter;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.dom.java.TopLevelClass;
import cn.lanaya.generator.dom.xml.XmlMapperKeys;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedColumn;
import cn.lanaya.generator.table.IntrospectedTable;
import cn.lanaya.generator.util.StringTools;

public class ControllerGenerator {
	protected IntrospectedTable introspectedTable;
	
	protected ServiceMethodBody controllerMethodBody;
	
	public CompilationUnit getCompilationUnit(IntrospectedTable introspectedTable,
			ServiceMethodBody serviceMethodBody) {
		this.introspectedTable = introspectedTable;
		this.controllerMethodBody = serviceMethodBody;
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		//实现类
		String controllerName = table.getFullControllerName();
		QualifiedJavaType javaType = new QualifiedJavaType(controllerName);
		TopLevelClass controller = new TopLevelClass(javaType);
		controller.setVisibility(JavaVisibility.PUBLIC);
		controller.addAnnotation("@RestController");
		controller.addAnnotation("@RequestMapping(value = \"" +
				StringTools.lowerFirstChar(table.getDomainObjectName()) + "\")");
		controller.addAnnotation("@Slf4j");
		//设置父类
		controller.setSuperClass("AbstractController");
		//添加字段
		addFields(table, controller);
		//添加import包路径
		addImportedType(table, controller);
		
		//添加方法
		addMethods(controller);
		return controller;
	}

	private void addFields(FullyQualifiedTable table, TopLevelClass controller) {
		Field field = new Field(StringTools.lowerFirstChar(table.getServiceName()), 
				new QualifiedJavaType(table.getFullServiceName()));
		field.setVisibility(JavaVisibility.PRIVATE);
		field.addAnnotation("@Autowired");
		controller.addField(field);
		controller.addImportedType(field.getType());
	}

	private void addMethods(TopLevelClass controller) {
		addCountByClauseMethod(controller);
		addDeleteByClauseMethod(controller);
		addDeleteByPrimaryKeyMethod(controller);
		addInsertMethod(controller);
		addInsertSelectiveMethod(controller);
		addSelectByClauseMethod(controller);
		addSelectByPrimaryKeyMethod(controller);
		//不需要暴露此方法
//		addUpdateByClauseSelectiveMethod(controller);
		addUpdateByPrimaryKeySelectiveMethod(controller);
		addPageByClauseMethod(controller);
		addDeleteByPrimaryKeyBatchMethod(controller);
		addInsertSelectBatchMethod(controller);
	}

	private void addImportedType(FullyQualifiedTable table, TopLevelClass controller) {
		controller.addImportedType("com.xhvms.common.web.AbstractController");
		controller.addImportedType("org.springframework.web.bind.annotation.RestController");
		controller.addImportedType(new QualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		controller.addImportedType(new QualifiedJavaType(table.getFullViewObjectName()));
		controller.addImportedType(new QualifiedJavaType(table.getFullDomainObjectName()));
		controller.addImportedType(new QualifiedJavaType("java.util.List"));
		controller.addImportedType(new QualifiedJavaType("com.xhvms.common.format.Result"));
		controller.addImportedType(new QualifiedJavaType("com.alibaba.fastjson.JSONObject"));
		controller.addImportedType(new QualifiedJavaType("com.xhvms.common.format.MessageEnum"));
		controller.addImportedType(new QualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
		controller.addImportedType(new QualifiedJavaType("org.springframework.web.bind.annotation.PostMapping"));
		controller.addImportedType(new QualifiedJavaType("org.springframework.web.bind.annotation.RequestBody"));
		controller.addImportedType(new QualifiedJavaType("lombok.extern.slf4j.Slf4j"));
		controller.addImportedType(new QualifiedJavaType("com.github.pagehelper.PageInfo"));
		controller.addImportedType(new QualifiedJavaType("cn.lanaya.common.bean.PageQO"));
		controller.addImportedType(new QualifiedJavaType("com.github.pagehelper.PageInfo"));
	}
	
	private void addInsertSelectBatchMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getInsertSelectBatch(table));
		controller.addMethod(method);
	}

	private void addDeleteByPrimaryKeyBatchMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getDeleteByPrimaryKeyBatch(table));
		controller.addMethod(method);
	}

	private void addPageByClauseMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.PAGE_BY_CLAUSE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.PAGE_BY_CLAUSE_SQL);
		QualifiedJavaType result = new QualifiedJavaType("com.xhvms.common.format.Result");
		QualifiedJavaType pageInfo = new QualifiedJavaType("com.github.pagehelper.PageInfo");
		pageInfo.addTypeArgument(new QualifiedJavaType(table.getFullViewObjectName()));
		result.addTypeArgument(pageInfo);
		method.setReturnType(result);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getPageByClause(table));
		controller.addMethod(method);
	}

	/**
	 * 添加
	 * @param controller
	 * @param field 
	 */
	protected void addCountByClauseMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.COUNT_BY_CLAUSE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.COUNT_BY_CLAUSE_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getCountByClause(table));
		controller.addMethod(method);
	}

	/**
	 * @param service
	 * @param hasAnnotation
	 */
	protected void addDeleteByClauseMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.DELETE_BY_CLAUSE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.DELETE_BY_CLAUSE_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getDeleteByClause(table));
		service.addMethod(method);
	}
	
	/**
	 * @param service
	 */
	protected void addDeleteByPrimaryKeyMethod(TopLevelClass service) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		if (columns == null || columns.isEmpty()) {
			return;
		}
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getDeleteByPrimaryKey(table));
		String serviceName = StringTools.lowerFirstChar(table.getServiceName());
		StringBuilder sb = new StringBuilder("return ");
		sb.append(serviceName).append(".").append(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL)
			.append("(");
		for (IntrospectedColumn c : columns) {
			sb.append("record.get").append(StringTools.upperFirstChar(c.getJavaProperty()))
				.append("(), ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(");");
		method.addBodyLine(sb.toString());
		method.addBodyLine("});");
		service.addMethod(method);
	}
	
	/**
	 * @param service
	 */
	protected void addInsertMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.INSERT_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.INSERT_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getInsert(table));
		service.addMethod(method);
	}

	/**
	 * @param service
	 */
	protected void addInsertSelectiveMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.INSERT_SELECTIVE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.INSERT_SELECTIVE_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getInsertSelective(table));
		service.addMethod(method);
	}

	/**
	 * @param controller
	 */
	protected void addSelectByClauseMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.SELECT_BY_CLAUSE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.SELECT_BY_CLAUSE_SQL);
		addDomainListReturn(method, table);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getSelectByClause(table));
		controller.addMethod(method);
	}

	/**
	 * @param controller
	 */
	protected void addSelectByPrimaryKeyMethod(TopLevelClass controller) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		if (columns == null || columns.isEmpty()) {
			return;
		}
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL);
		addDomainReturn(method, table);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getSelectByPrimaryKey(table));
		String serviceName = StringTools.lowerFirstChar(table.getServiceName());
		StringBuilder sb = new StringBuilder("return success(MessageEnum.SUCCESS, ");
		sb.append(serviceName).append(".")
			.append(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL).append("(");
		for (IntrospectedColumn c : columns) {
			String javaProperty = StringTools.upperFirstChar(c.getJavaProperty());
			sb.append("record.get").append(javaProperty).append("(), ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("));");
		method.addBodyLine(sb.toString());
		
		controller.addMethod(method);
	}
	
	/**
	 * @param controller
	 */
	protected void addUpdateByClauseSelectiveMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.UPDATE_BY_CLAUSE_SELECTIVE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.UPDATE_BY_CLAUSE_SELECTIVE_SQL);
		addBasicReturn(method);
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		QualifiedJavaType vo = new QualifiedJavaType(table.getFullViewObjectName());
		addParameters(method, javaType, "entity");
		addParameters(method, vo, "param");
		method.addBodyLines(controllerMethodBody.getUpdateByClauseSelective(table));
		controller.addMethod(method);
	}
	
	/**
	 * @param controller
	 */
	protected void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass controller) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = getMethod(XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL);
		addPostMappingAnnotation(method, XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL);
		addBasicReturn(method);
		addParameter(method);
		method.addBodyLines(controllerMethodBody.getUpdateByPrimaryKeySelective(table));
		
		controller.addMethod(method);
	}
	
	private void addParameters(Method method, QualifiedJavaType type, String name) {
		Parameter parameter = new Parameter(type, name);
		parameter.addAnnotation("@RequestBody(value = \" " + name + "\")");
		method.addParameter(parameter);
	}

	private Method getMethod(String name) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName(name);
		return method;
	}
	
	private void addBasicReturn(Method method) {
		QualifiedJavaType result = new QualifiedJavaType("com.xhvms.common.format.Result");
		result.addTypeArgument(new QualifiedJavaType("java.lang.Object"));
		method.setReturnType(result);
	}
	
	private void addDomainListReturn(Method method, FullyQualifiedTable table) {
		QualifiedJavaType result = new QualifiedJavaType("com.xhvms.common.format.Result");
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getFullDomainObjectName()));
		result.addTypeArgument(list);
		method.setReturnType(result);
	}
	
	private void addDomainReturn(Method method, FullyQualifiedTable table) {
		QualifiedJavaType result = new QualifiedJavaType("com.xhvms.common.format.Result");
		result.addTypeArgument(new QualifiedJavaType(table.getFullDomainObjectName()));
		method.setReturnType(result);
	}
	
	private void addPostMappingAnnotation(Method method, String url) {
		method.addAnnotation("@PostMapping(value = \"" + url + "\")");
	}
	
	private void addParameter(Method method) {
		Parameter parameter = new Parameter(QualifiedJavaType.getStringInstance(), "json");
		parameter.addAnnotation("@RequestBody");
		method.addParameter(parameter);
	}
}
