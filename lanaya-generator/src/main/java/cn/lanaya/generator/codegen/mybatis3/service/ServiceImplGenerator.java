package cn.lanaya.generator.codegen.mybatis3.service;

import java.util.ArrayList;
import java.util.List;

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

public class ServiceImplGenerator {
	
	protected IntrospectedTable introspectedTable;
	
	protected ServiceMethodBody serviceMethodBody;
	
	public CompilationUnit getCompilationUnit(IntrospectedTable introspectedTable,
			ServiceMethodBody serviceMethodBody) {
		this.introspectedTable = introspectedTable;
		this.serviceMethodBody = serviceMethodBody;
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		//实现类
		String serviceName = table.getFullServiceImplName();
		QualifiedJavaType javaType = new QualifiedJavaType(serviceName);
		TopLevelClass service = new TopLevelClass(javaType);
		service.setVisibility(JavaVisibility.PUBLIC);
		service.addAnnotation("@Service");
//		service.addAnnotation("@Slf4j");
		//设置父类
		service.setSuperClass("AbstractService");
		//添加接口
		addSuperInterfaces(table, service);
		//添加字段
		addFields(table, service);
		//添加import包路径
		addImportedType(table, service);
		//添加方法
		addMethods(service);
		return service;
	}

	private void addSuperInterfaces(FullyQualifiedTable table, TopLevelClass service) {
		String serviceInterfaceName = table.getFullServiceName();
		QualifiedJavaType serviceInterfaceType = new QualifiedJavaType(serviceInterfaceName);
		service.addSuperInterface(serviceInterfaceType);
		service.addImportedType(serviceInterfaceType);
	}

	private void addFields(FullyQualifiedTable table, TopLevelClass service) {
		Field field = new Field(StringTools.lowerFirstChar(table.getMapperName()), 
				new QualifiedJavaType(table.getFullMapperName()));
		field.setVisibility(JavaVisibility.PRIVATE);
		field.addAnnotation("@Autowired");
		service.addField(field);
		service.addImportedType(field.getType());
		Field field2 = new Field("log", new QualifiedJavaType("org.slf4j.Logger"));
		field2.setStatic(true);
		field2.setInitializationString("LoggerFactory.getLogger(" + table.getFullServiceImplName() + ".class);");
		field2.setVisibility(JavaVisibility.PRIVATE);
		service.addImportedType(field2.getType());
		service.addField(field2);
		service.addImportedType(new QualifiedJavaType("org.slf4j.LoggerFactory"));
	}

	private void addMethods(TopLevelClass service) {
		addCountByClauseMethod(service);
		addDeleteByClauseMethod(service);
		addDeleteByPrimaryKeyMethod(service);
		addInsertMethod(service);
		addInsertSelectiveMethod(service);
		addSelectByClauseMethod(service);
		addSelectByPrimaryKeyMethod(service);
		addUpdateByClauseSelectiveMethod(service);
		addUpdateByPrimaryKeySelectiveMethod(service);
		addUpdateByPrimaryKeyMethod(service);
		addPrivateMethod(service);
		addPageByClauseMethod(service);
		addDeleteByPrimaryKeyBatchMethod(service);
		addInsertSelectBatchMethod(service);
	}

	private void addImportedType(FullyQualifiedTable table, TopLevelClass service) {
		service.addImportedType("org.springframework.stereotype.Service");
		service.addImportedType("com.xhvms.login.service.AbstractService");
		service.addImportedType(new QualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		service.addImportedType(new QualifiedJavaType("cn.lanaya.common.validate.ValidatorUtils"));
		service.addImportedType(new QualifiedJavaType(table.getFullViewObjectName()));
		service.addImportedType(new QualifiedJavaType(table.getFullDomainObjectName()));
		service.addImportedType(new QualifiedJavaType(table.getFullMapperName()));
		service.addImportedType(new QualifiedJavaType("java.util.Date"));
		service.addImportedType(new QualifiedJavaType("java.util.List"));
		service.addImportedType(new QualifiedJavaType("com.xhvms.login.vo.UserInfo"));
		service.addImportedType(new QualifiedJavaType("com.xhvms.common.guid.GuidUtils"));
		service.addImportedType(new QualifiedJavaType("com.xhvms.common.date.DateUtils"));
		service.addImportedType(new QualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
		service.addImportedType(new QualifiedJavaType("lombok.extern.slf4j.Slf4j"));
		service.addImportedType(new QualifiedJavaType("cn.lanaya.common.bean.PageQO"));
		service.addImportedType(new QualifiedJavaType("com.github.pagehelper.PageHelper"));
		service.addImportedType(new QualifiedJavaType("com.github.pagehelper.PageInfo"));
	}

	protected void addInsertSelectBatchMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getDomainObjectName()));
		Parameter parameter = new Parameter(list, "list");
		Method method = getMethod(XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getInsertSelectBatch(table));
		method.addAnnotation("@Transactional");
		service.addMethod(method);
	}

	protected void addDeleteByPrimaryKeyBatchMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getFullViewObjectName()));
		Parameter parameter = new Parameter(list, "list");
		Method method = getMethod(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getDeleteByPrimaryKeyBatch(table));
		method.addAnnotation("@Transactional");
		service.addMethod(method);
	}

	protected void addPageByClauseMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		QualifiedJavaType javaType = new QualifiedJavaType("cn.lanaya.common.bean.PageQO");
		Parameter parameter = new Parameter(javaType, "page");
		Method method = getMethod(XmlMapperKeys.PAGE_BY_CLAUSE_SQL, parameter);
		QualifiedJavaType list = new QualifiedJavaType("com.github.pagehelper.PageInfo");
		list.addTypeArgument(new QualifiedJavaType(table.getFullViewObjectName()));
		method.setReturnType(list);
		method.addBodyLines(serviceMethodBody.getPageByClause(table));
		service.addMethod(method);
	}

	protected void addPrivateMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PRIVATE);
		method.setName("validate");
		QualifiedJavaType javaType = new QualifiedJavaType(table.getFullDomainObjectName());
		Parameter parameter = new Parameter(javaType, "record");
		method.addParameter(parameter);
		for (IntrospectedColumn c : introspectedTable.getBaseColumns()) {
			if(!c.isNullable()) {
				if(c.getQualifiedJavaType().getBaseShortName().equalsIgnoreCase("string")) {
					method.addBodyLine("ValidatorUtils.isBlank(record.get" 
							+ StringTools.upperFirstChar(c.getJavaProperty()) + "(), \" "+ c.getRemarks() + "不能为空\");");
				}else{
					method.addBodyLine("ValidatorUtils.notNull(record.get" 
							+ StringTools.upperFirstChar(c.getJavaProperty()) + "(), \" "+ c.getRemarks() + "不能为空\");");
				}
			}
		}
		service.addMethod(method);
	}

	/**
	 * @param service
	 * @param hasAnnotation
	 */
	protected void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getUpdateByPrimaryKeySelective(table));
		method.addAnnotation("@Transactional");
		service.addMethod(method);
	}
	
	protected void addUpdateByPrimaryKeyMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getUpdateByPrimaryKey(table));
		method.addAnnotation("@Transactional");
		service.addMethod(method);
	}
	
	/**
	 * @param service
	 * @param hasAnnotation
	 */
	protected void addUpdateByClauseSelectiveMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		QualifiedJavaType vo = new QualifiedJavaType(table.getFullViewObjectName());
		Parameter entity = new Parameter(javaType, "entity");
		Parameter param = new Parameter(vo, "param");
		Method method = getMethod(XmlMapperKeys.UPDATE_BY_CLAUSE_SELECTIVE_SQL, entity, param);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getUpdateByClauseSelective(table));
		method.addAnnotation("@Transactional");
		service.addMethod(method);
	}
	
	/**
	 * @param service
	 */
	protected void addSelectByPrimaryKeyMethod(TopLevelClass service) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		if (columns == null || columns.isEmpty()) {
			return;
		}
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		StringBuilder returnLine = new StringBuilder("return ");
		returnLine.append(service.getFields().get(0).getName()).append(".")
		.append(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL).append("(");
		List<Parameter> parameters = new ArrayList<>();
		for (IntrospectedColumn column : columns) {
			String javaProperty = column.getJavaProperty();
			QualifiedJavaType type = column.getQualifiedJavaType();
			Parameter parameter = new Parameter(type, javaProperty);
			parameters.add(parameter);
			returnLine.append(javaProperty).append(", ");
		}
		Method method = getMethod(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL, parameters.toArray(new Parameter[] {}));
		QualifiedJavaType qualifiedJavaType = new QualifiedJavaType(table.getFullDomainObjectName());
		method.setReturnType(qualifiedJavaType);
		returnLine.delete(returnLine.length() - 2, returnLine.length());
		returnLine.append(");");
		method.addBodyLines(serviceMethodBody.getSelectByPrimaryKey(table));
		method.addBodyLine(returnLine.toString());
		
		service.addMethod(method);
	}

	/**
	 * @param service
	 * @param hasAnnotation
	 */
	protected void addSelectByClauseMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.SELECT_BY_CLAUSE_SQL, parameter);
		QualifiedJavaType list = new QualifiedJavaType("java.util.List");
		list.addTypeArgument(new QualifiedJavaType(table.getFullDomainObjectName()));
		method.setReturnType(list);
		method.addBodyLines(serviceMethodBody.getSelectByClause(table));
		service.addMethod(method);
	}

	/**
	 * @param service
	 */
	protected void addInsertSelectiveMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.INSERT_SELECTIVE_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getInsertSelective(table));
		method.addAnnotation("@Transactional");
		service.addMethod(method);
	}

	/**
	 * @param service
	 */
	protected void addInsertMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullDomainObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.INSERT_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getInsert(table));
		method.addAnnotation("@Transactional");
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
		StringBuilder returnLine = new StringBuilder("return ");
		returnLine.append(service.getFields().get(0).getName()).append(".")
		.append(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL).append("(");
		
		String mapper = StringTools.lowerFirstChar(table.getMapperName());
		StringBuilder sb1 = new StringBuilder(table.getDomainObjectName());
		sb1.append(" saved = ").append(mapper).append(".").append(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL)
		.append("(");
		List<Parameter> parameters = new ArrayList<>();
		for (IntrospectedColumn column : columns) {
			String javaProperty = column.getJavaProperty();
			QualifiedJavaType type = column.getQualifiedJavaType();
			Parameter parameter = new Parameter(type, javaProperty);
			parameters.add(parameter);
			returnLine.append(javaProperty).append(", ");
			sb1.append(javaProperty).append(", ");
		}
		Method method = getMethod(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL, parameters.toArray(new Parameter[] {}));
		sb1.delete(sb1.length() - 2, sb1.length());
		sb1.append(");");
		method.addBodyLine(sb1.toString());
		returnLine.delete(returnLine.length() - 2, returnLine.length());
		returnLine.append(");");
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addAnnotation("@Transactional");
		method.addBodyLines(serviceMethodBody.getDeleteByPrimaryKey(table));
		method.addBodyLine(returnLine.toString());
		service.addMethod(method);
	}

	/**
	 * @param service
	 * @param hasAnnotation
	 */
	protected void addDeleteByClauseMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullViewObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.DELETE_BY_CLAUSE_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addAnnotation("@Transactional");
		method.addBodyLines(serviceMethodBody.getDeleteByClause(table));
		service.addMethod(method);
	}
	
	private Method getMethod(String name, Parameter... parameters) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addAnnotation("@Override");
		method.setName(name);
		StringBuilder sb = new StringBuilder("log.info(\"");
		sb.append(name).append(" - ");
		for (Parameter parameter : parameters) {
			method.addParameter(parameter);
			sb.append("{}, ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("\", ");
		for (Parameter p : parameters) {
			sb.append(p.getName()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(");");
		method.addBodyLine(sb.toString());
		return method;
	}
	
	/**
	 * 添加
	 * @param service
	 * @param field 
	 */
	protected void addCountByClauseMethod(TopLevelClass service) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String name = table.getFullViewObjectName();
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		Parameter parameter = new Parameter(javaType, "record");
		Method method = getMethod(XmlMapperKeys.COUNT_BY_CLAUSE_SQL, parameter);
		method.setReturnType(QualifiedJavaType.getIntInstance());
		method.addBodyLines(serviceMethodBody.getCountByClause(table));
		service.addMethod(method);
	}

}
