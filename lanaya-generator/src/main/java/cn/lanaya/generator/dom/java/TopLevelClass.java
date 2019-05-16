package cn.lanaya.generator.dom.java;

import static cn.lanaya.generator.dom.OutputUtilities.calculateImports;
import static cn.lanaya.generator.dom.OutputUtilities.newLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class TopLevelClass extends InnerClass implements CompilationUnit {
	private Set<QualifiedJavaType> importedTypes;

	private Set<String> staticImports;

	private List<String> fileCommentLines;

	public TopLevelClass(QualifiedJavaType type) {
		super(type);
		importedTypes = new TreeSet<QualifiedJavaType>();
		fileCommentLines = new ArrayList<String>();
		staticImports = new TreeSet<String>();
	}

	public TopLevelClass(String typeName) {
		this(new QualifiedJavaType(typeName));
	}

	@Override
	public String getFormattedContent() {
		StringBuilder sb = new StringBuilder();

		for (String fileCommentLine : fileCommentLines) {
			sb.append(fileCommentLine);
			newLine(sb);
		}

		if (StringUtils.isNotBlank(getType().getPackageName())) {
			sb.append("package "); 
			sb.append(getType().getPackageName());
			sb.append(';');
			newLine(sb);
			newLine(sb);
		}

		for (String staticImport : staticImports) {
			sb.append("import static "); 
			sb.append(staticImport);
			sb.append(';');
			newLine(sb);
		}

		if (staticImports.size() > 0) {
			newLine(sb);
		}

		Set<String> importStrings = calculateImports(importedTypes);
		for (String importString : importStrings) {
			sb.append(importString);
			newLine(sb);
		}

		if (importStrings.size() > 0) {
			newLine(sb);
		}

		sb.append(super.getFormattedContent(0, this));

		return sb.toString();
	}

	@Override
	public Set<QualifiedJavaType> getImportedTypes() {
		return importedTypes;
	}

	public void addImportedType(String importedType) {
		addImportedType(new QualifiedJavaType(importedType));
	}

	@Override
	public void addImportedType(QualifiedJavaType importedType) {
		if (importedType != null && importedType.isExplicitlyImported()
				&& !importedType.getPackageName().equals(getType().getPackageName())
				&& !importedType.getShortNameIncludeTypeArgs().equals(getType().getShortNameIncludeTypeArgs())) {
			importedTypes.add(importedType);
		}
	}

	@Override
	public boolean isJavaInterface() {
		return false;
	}

	@Override
	public boolean isJavaEnumeration() {
		return false;
	}

	@Override
	public void addFileCommentLine(String commentLine) {
		fileCommentLines.add(commentLine);
	}

	@Override
	public List<String> getFileCommentLines() {
		return fileCommentLines;
	}

	@Override
	public void addImportedTypes(Set<QualifiedJavaType> importedTypes) {
		this.importedTypes.addAll(importedTypes);
	}

	@Override
	public Set<String> getStaticImports() {
		return staticImports;
	}

	@Override
	public void addStaticImport(String staticImport) {
		staticImports.add(staticImport);
	}

	@Override
	public void addStaticImports(Set<String> staticImports) {
		this.staticImports.addAll(staticImports);
	}
}
