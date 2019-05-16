package cn.lanaya.generator.codegen.mybatis3.service;

import cn.lanaya.generator.codegen.InterfaceGenerator;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Interface;
import cn.lanaya.generator.dom.java.JavaVisibility;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.Parameter;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.dom.xml.XmlMapperKeys;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedTable;

public class ServiceGenerator extends InterfaceGenerator{

	public CompilationUnit getCompilationUnit(IntrospectedTable introspectedTable) {
		this.introspectedTable = introspectedTable;
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String serviceName = table.getFullServiceName();
		QualifiedJavaType javaType = new QualifiedJavaType(serviceName);
		Interface service = new Interface(javaType);
		service.setVisibility(JavaVisibility.PUBLIC);
		//添加import包
		addImportedType(table, service);
		
		//添加方法
		addCountByClauseMethod(service);
		addDeleteByClauseMethod(service, false);
		addDeleteByPrimaryKeyMethod(service);
		addInsertMethod(service);
		addInsertSelectiveMethod(service);
		addSelectByClauseMethod(service, false);
		addSelectByPrimaryKeyMethod(service);
		addUpdateByClauseSelectiveMethod(service, false);
		addUpdateByPrimaryKeySelectiveMethod(service, false);
		addUpdateByPrimaryKeyMethod(service, false);
		addPageByClauseMethod(service);
		addDeleteByPrimaryKeyBatchMethod(service);
		addInsertSelectiveBatchMethod(service);
		return service;
	}

	private void addImportedType(FullyQualifiedTable table, Interface service) {
		service.addImportedType(new QualifiedJavaType(table.getFullViewObjectName()));
		service.addImportedType(new QualifiedJavaType(table.getFullDomainObjectName()));
		service.addImportedType(new QualifiedJavaType("java.util.List"));
		service.addImportedType(new QualifiedJavaType("cn.lanaya.common.bean.PageQO"));
		service.addImportedType(new QualifiedJavaType("com.github.pagehelper.PageInfo"));
	}
	
	protected void addPageByClauseMethod(Interface interfaze) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setName(XmlMapperKeys.PAGE_BY_CLAUSE_SQL);
		QualifiedJavaType list = new QualifiedJavaType("com.github.pagehelper.PageInfo");
		list.addTypeArgument(new QualifiedJavaType(table.getFullViewObjectName()));
		method.setReturnType(list);
		QualifiedJavaType javaType = new QualifiedJavaType("cn.lanaya.common.bean.PageQO");
		Parameter parameter = new Parameter(javaType, "page");
		method.addParameter(parameter);
		interfaze.addMethod(method);
	}
}
