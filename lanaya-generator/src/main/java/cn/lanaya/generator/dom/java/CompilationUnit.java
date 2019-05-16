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
package cn.lanaya.generator.dom.java;

import java.util.List;
import java.util.Set;

/**
 * This interface describes methods common to all Java compilation units (Java
 * classes, interfaces, and enums).
 * 
 * @author Jeff Butler
 */
public interface CompilationUnit {

    String getFormattedContent();

    Set<QualifiedJavaType> getImportedTypes();

    Set<String> getStaticImports();

    QualifiedJavaType getSuperClass();

    boolean isJavaInterface();

    boolean isJavaEnumeration();

    Set<QualifiedJavaType> getSuperInterfaceTypes();

    QualifiedJavaType getType();

    void addImportedType(QualifiedJavaType importedType);

    void addImportedTypes(Set<QualifiedJavaType> importedTypes);

    void addStaticImport(String staticImport);

    void addStaticImports(Set<String> staticImports);

    void addFileCommentLine(String commentLine);

    List<String> getFileCommentLines();
}
