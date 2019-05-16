/**
 *    Copyright 2006-2016 the original author or authors.
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
package cn.lanaya.generator.dom.xml;

import cn.lanaya.generator.codegen.AbstractGenerator;
import cn.lanaya.generator.config.GeneratedKey;
import cn.lanaya.generator.table.IntrospectedColumn;

/**
 * 
 * @author Jeff Butler
 * 
 */
public abstract class AbstractXmlElementGenerator extends AbstractGenerator {
	public abstract void addElements(XmlElement parentElement);

	public AbstractXmlElementGenerator() {
		super();
	}

	/**
	 * This method should return an XmlElement for the select key used to
	 * automatically generate keys.
	 * 
	 * @param introspectedColumn the column related to the select key statement
	 * @param generatedKey       the generated key for the current table
	 * @return the selectKey element
	 */
	protected XmlElement getSelectKey(IntrospectedColumn introspectedColumn, GeneratedKey generatedKey) {
		String identityColumnType = introspectedColumn.getQualifiedJavaType().getBaseQualifiedName();

		XmlElement answer = new XmlElement("selectKey");
		answer.addAttribute(new Attribute("resultType", identityColumnType));
		answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
		answer.addAttribute(new Attribute("order", generatedKey.getMyBatis3Order()));

		answer.addElement(new TextElement(generatedKey.getRuntimeSqlStatement()));

		return answer;
	}

	protected XmlElement getBaseColumnListElement() {
		XmlElement answer = new XmlElement("include");
		answer.addAttribute(new Attribute("refid", XmlMapperKeys.BASE_COLUMN_LIST_ID));
		return answer;
	}

	protected XmlElement getBlobColumnListElement() {
		XmlElement answer = new XmlElement("include");
//		answer.addAttribute(new Attribute("refid", introspectedTable.getBlobColumnListId()));
		return answer;
	}

	protected XmlElement getClauseIncludeElement() {
		XmlElement ifElement = new XmlElement("if");
		ifElement.addAttribute(new Attribute("test", "_parameter != null"));

		XmlElement includeElement = new XmlElement("include");
		includeElement.addAttribute(new Attribute("refid", XmlMapperKeys.WHERE_CLAUSE_ID));
		ifElement.addElement(includeElement);

		return ifElement;
	}

	protected XmlElement getUpdateByClauseIncludeElement() {
		XmlElement ifElement = new XmlElement("if");
		ifElement.addAttribute(new Attribute("test", "_parameter != null"));

		XmlElement includeElement = new XmlElement("include");
		includeElement
				.addAttribute(new Attribute("refid", XmlMapperKeys.UPDATE_BY_CLAUSE_SQL));
		ifElement.addElement(includeElement);

		return ifElement;
	}
}
