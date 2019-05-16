package cn.lanaya.generator.codegen.mybatis3.javamapper;

import cn.lanaya.generator.codegen.InterfaceGenerator;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Interface;
import cn.lanaya.generator.dom.java.JavaVisibility;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedTable;

public class MapperGenerator extends InterfaceGenerator{

	public CompilationUnit getCompilationUnit(IntrospectedTable introspectedTable) {
		this.introspectedTable = introspectedTable;
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String mapperName = table.getFullMapperName();
		QualifiedJavaType type = new QualifiedJavaType(mapperName);
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		//添加import包
		addImportedType(table, interfaze);
		//添加方法
		addMethods(interfaze);
		return interfaze;
	}

	private void addMethods(Interface interfaze) {
		addCountByClauseMethod(interfaze);
		addDeleteByClauseMethod(interfaze, true);
		addDeleteByPrimaryKeyMethod(interfaze);
		addInsertMethod(interfaze);
		addInsertSelectiveMethod(interfaze);
		addSelectByClauseMethod(interfaze, true);
		addSelectByPrimaryKeyMethod(interfaze);
		addUpdateByClauseSelectiveMethod(interfaze, true);
		addUpdateByPrimaryKeySelectiveMethod(interfaze, true);
		addUpdateByPrimaryKeyMethod(interfaze, false);
		addPageByClauseMethod(interfaze);
		addDeleteByPrimaryKeyBatchMethod(interfaze);
		addInsertSelectiveBatchMethod(interfaze);
	}

	private void addImportedType(FullyQualifiedTable table, Interface interfaze) {
		interfaze.addImportedType(new QualifiedJavaType("org.apache.ibatis.annotations.Param"));
		interfaze.addImportedType(new QualifiedJavaType(table.getFullViewObjectName()));
		interfaze.addImportedType(new QualifiedJavaType(table.getFullDomainObjectName()));
		interfaze.addImportedType(new QualifiedJavaType("java.util.List"));
		interfaze.addImportedType(new QualifiedJavaType("cn.lanaya.common.bean.PageQO"));
	}

}
