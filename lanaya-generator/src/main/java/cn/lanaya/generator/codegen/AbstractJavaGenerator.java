package cn.lanaya.generator.codegen;

import cn.lanaya.generator.dom.OutputUtilities;
import cn.lanaya.generator.dom.java.Field;
import cn.lanaya.generator.dom.java.JavaVisibility;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.Parameter;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.table.IntrospectedColumn;
import cn.lanaya.generator.util.JavaBeansUtil;

public abstract class AbstractJavaGenerator extends AbstractGenerator {

	protected Method getJavaBeansSetter(IntrospectedColumn column, QualifiedJavaType beanType) {
		Method method = new Method();
		QualifiedJavaType type = column.getQualifiedJavaType();
		String property = column.getJavaProperty();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName(JavaBeansUtil.getSetterMethodName(property));
		method.addParameter(new Parameter(type, property));
		StringBuilder sb = new StringBuilder();
		sb.append("this.").append(property);
		sb.append(" = ").append(property);
		sb.append(" == null ? null : ");
        sb.append(property);
        if(column.getQualifiedJavaType().getBaseShortName().equals("String")) {
        	sb.append(".trim()");
        }
        sb.append(";");
        if(beanType != null) {
        	method.setReturnType(beanType);
        	OutputUtilities.newLine(sb);
        	OutputUtilities.javaIndent(sb, 2);
        	sb.append("return this;");
        }
		method.addBodyLine(sb.toString());
		return method;
	}
	
	protected Field getJavaBeansField(IntrospectedColumn column) {
		QualifiedJavaType type = column.getQualifiedJavaType();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(type);
		field.setName(column.getJavaProperty());
		field.addJavaDocLine(column.getRemarks());
		StringBuilder sb = new StringBuilder("@FieldProperty(comment = \"");
		sb.append(column.getRemarks()).append("\"");
		if(!column.isNullable() || column.isSequenceColumn()) {
			sb.append(", required = true");
		}
		sb.append(")");
		field.addAnnotation(sb.toString());
		return field;
	}
	
	protected Method getJavaBeansGetter(IntrospectedColumn column) {
		Method method = new Method();
		QualifiedJavaType type = column.getQualifiedJavaType();
		String property = column.getJavaProperty();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(type);
		method.setName(JavaBeansUtil.getGetterMethodName(property, type));
		StringBuffer sb = new StringBuffer();
		sb.append("return ").append(property).append(';');
		method.addBodyLine(sb.toString());
		return method;
	}
}
