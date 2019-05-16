/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cn.lanaya.generator.util;

import java.util.Locale;

import cn.lanaya.generator.dom.java.Field;
import cn.lanaya.generator.dom.java.JavaVisibility;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.Parameter;
import cn.lanaya.generator.dom.java.QualifiedJavaType;
import cn.lanaya.generator.table.IntrospectedColumn;
import cn.lanaya.generator.table.IntrospectedTable;

/**
 * @author Jeff Butler
 */
public class JavaBeansUtil {

    private JavaBeansUtil() {
        super();
    }

    /**
     * Computes a getter method name.  Warning - does not check to see that the property is a valid
     * property.  Call getValidPropertyName first.
     * 
     * @param property
     *            the property
     * @param fullyQualifiedJavaType
     *            the fully qualified java type
     * @return the getter method name
     */
    public static String getGetterMethodName(String property,
            QualifiedJavaType fullyQualifiedJavaType) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        if (fullyQualifiedJavaType.equals(QualifiedJavaType
                .getBooleanPrimitiveInstance())) {
            sb.insert(0, "is"); //$NON-NLS-1$
        } else {
            sb.insert(0, "get"); //$NON-NLS-1$
        }

        return sb.toString();
    }

    /**
     * Computes a setter method name.  Warning - does not check to see that the property is a valid
     * property.  Call getValidPropertyName first.
     *
     * @param property
     *            the property
     * @return the setter method name
     */
    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }

        sb.insert(0, "set"); //$NON-NLS-1$

        return sb.toString();
    }

    public static String getCamelCaseString(String inputString,
            boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
            case '_':
            case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
                break;

            default:
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    public static String getValidPropertyName(String inputString) {
        String answer;

        if (inputString == null) {
            answer = null;
        } else if (inputString.length() < 2) {
            answer = inputString.toLowerCase(Locale.US);
        } else {
            if (Character.isUpperCase(inputString.charAt(0))
                    && !Character.isUpperCase(inputString.charAt(1))) {
                answer = inputString.substring(0, 1).toLowerCase(Locale.US)
                        + inputString.substring(1);
            } else {
                answer = inputString;
            }
        }

        return answer;
    }

    public static Method getJavaBeansGetter(IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable) {
//        QualifiedJavaType fqjt = introspectedColumn
//                .getQualifiedJavaType();
//        String property = introspectedColumn.getJavaProperty();
//
//        Method method = new Method();
//        method.setVisibility(JavaVisibility.PUBLIC);
//        method.setReturnType(fqjt);
//        method.setName(getGetterMethodName(property, fqjt));
//        context.getCommentGenerator().addGetterComment(method,
//                introspectedTable, introspectedColumn);

//        StringBuilder sb = new StringBuilder();
//        sb.append("return "); //$NON-NLS-1$
//        sb.append(property);
//        sb.append(';');
//        method.addBodyLine(sb.toString());
//
//        return method;
    	return null;
    }

    public static Field getJavaBeansField(IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable) {
//        QualifiedJavaType fqjt = introspectedColumn
//                .getQualifiedJavaType();
//        String property = introspectedColumn.getJavaProperty();
//
//        Field field = new Field();
//        field.setVisibility(JavaVisibility.PRIVATE);
//        field.setType(fqjt);
//        field.setName(property);

//        return field;
    	return null;
    }

    public static Method getJavaBeansSetter(IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable) {
//        QualifiedJavaType fqjt = introspectedColumn
//                .getQualifiedJavaType();
//        String property = introspectedColumn.getJavaProperty();
//
//        Method method = new Method();
//        method.setVisibility(JavaVisibility.PUBLIC);
//        method.setName(getSetterMethodName(property));
//        method.addParameter(new Parameter(fqjt, property));
//        context.getCommentGenerator().addSetterComment(method,
//                introspectedTable, introspectedColumn);
//
//        StringBuilder sb = new StringBuilder();
//        if (introspectedColumn.isStringColumn() && isTrimStringsEnabled(introspectedColumn)) {
//            sb.append("this."); //$NON-NLS-1$
//            sb.append(property);
//            sb.append(" = "); //$NON-NLS-1$
//            sb.append(property);
//            sb.append(" == null ? null : "); //$NON-NLS-1$
//            sb.append(property);
//            sb.append(".trim();"); //$NON-NLS-1$
//            method.addBodyLine(sb.toString());
//        } else {
//            sb.append("this."); //$NON-NLS-1$
//            sb.append(property);
//            sb.append(" = "); //$NON-NLS-1$
//            sb.append(property);
//            sb.append(';');
//            method.addBodyLine(sb.toString());
//        }

//        return method;
    	return null;
    }



}
