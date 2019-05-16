package cn.lanaya.generator.codegen.mybatis3.model;

import java.util.ArrayList;
import java.util.List;

import cn.lanaya.generator.codegen.AbstractJavaGenerator;
import cn.lanaya.generator.codegen.comment.DefaultCommentGenerator;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Field;
import cn.lanaya.generator.dom.java.JavaVisibility;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.dom.java.TopLevelClass;
import cn.lanaya.generator.table.IntrospectedColumn;
import cn.lanaya.generator.table.IntrospectedTable;
import cn.lanaya.generator.util.JavaBeansUtil;

/**
 * 根据一张表生成一个实体类
 * @author taiqin
 *
 */
public class BaseRecordGenerator extends AbstractJavaGenerator{
	
	protected DefaultCommentGenerator comment = new DefaultCommentGenerator();
	
	public List<CompilationUnit> getCompilationUnit(IntrospectedTable introspectedTable) {
		List<CompilationUnit> res = new ArrayList<>();
		res.add(getDomainCompilationUnit(introspectedTable));
		res.add(getViewCompilationUnit(introspectedTable));
		return res;
	}
	
	public CompilationUnit getDomainCompilationUnit(IntrospectedTable introspectedTable) {
		String name = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
		QualifiedJavaType type = new QualifiedJavaType(introspectedTable.getFullyQualifiedTable().getFullDomainObjectName());
		TopLevelClass topLevelClass = getTopLevelClass(introspectedTable, name, type);
		return topLevelClass;
	}
	public CompilationUnit getViewCompilationUnit(IntrospectedTable introspectedTable) {
		String name = introspectedTable.getFullyQualifiedTable().getViewObjectName();
		QualifiedJavaType type = new QualifiedJavaType(introspectedTable.getFullyQualifiedTable().getFullViewObjectName());
		TopLevelClass topLevelClass = getTopLevelClass(introspectedTable, name, type);
		return topLevelClass;
	}

	private TopLevelClass getTopLevelClass(IntrospectedTable introspectedTable, String name, QualifiedJavaType type) {
		QualifiedJavaType javaType = new QualifiedJavaType(name);
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
		boolean flag = false;//时间字段是否添加start/end的额外字段的标记
		if(name.endsWith("VO")) {
			flag = true;
		}
		for (IntrospectedColumn c : allColumns) {
			Field field = getJavaBeansField(c);
			topLevelClass.addField(field);
//			comment.addFieldComment(field, topLevelClass);
			Method getter = getJavaBeansGetter(c);
			topLevelClass.addMethod(getter);
			Method setter = getJavaBeansSetter(c, javaType);
			topLevelClass.addMethod(setter);
			String baseQualifiedName = field.getType().getBaseQualifiedName();
			if(!baseQualifiedName.startsWith("java.lang")) {
				topLevelClass.addImportedType(field.getType());
			}
			if(flag) {
				if(c.getQualifiedJavaType().equals(QualifiedJavaType.getDateInstance())) {
					String javaProperty = c.getJavaProperty();
					addDateField(javaProperty + "Start", c, topLevelClass, javaType);
					addDateField(javaProperty + "End", c, topLevelClass, javaType);
				}
			}
		}
		//添加注解
		topLevelClass.addImportedType("cn.lanaya.common.annotation.FieldProperty");
		return topLevelClass;
	}
	
	private void addDateField(String propertyName, IntrospectedColumn c, TopLevelClass topLevelClass, QualifiedJavaType javaType) {
		c.setJavaProperty(propertyName);
		Field f = getJavaBeansField(c);
		topLevelClass.addField(f);
		Method g = getJavaBeansGetter(c);
		topLevelClass.addMethod(g);
		Method s = getJavaBeansSetter(c, javaType);
		topLevelClass.addMethod(s);
	}
	
	public static Method getGetter(Field field) {
        Method method = new Method();
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field
                .getType()));
        method.setReturnType(field.getType());
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append("return "); 
        sb.append(field.getName());
        sb.append(';');
        method.addBodyLine(sb.toString());
        return method;
    }
}
