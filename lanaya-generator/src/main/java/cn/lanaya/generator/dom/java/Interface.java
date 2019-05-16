package cn.lanaya.generator.dom.java;

import static cn.lanaya.generator.dom.OutputUtilities.calculateImports;
import static cn.lanaya.generator.dom.OutputUtilities.newLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Interface extends InnerInterface implements CompilationUnit {

	private Set<QualifiedJavaType> importedTypes;

	private Set<String> staticImports;

	private List<String> fileCommentLines;

	public Interface(QualifiedJavaType type) {
		super(type);
		importedTypes = new TreeSet<QualifiedJavaType>();
		fileCommentLines = new ArrayList<String>();
		staticImports = new TreeSet<String>();
	}

	public Interface(String type) {
		this(new QualifiedJavaType(type));
	}

	@Override
	public Set<QualifiedJavaType> getImportedTypes() {
		return importedTypes;
	}

	@Override
	public void addImportedType(QualifiedJavaType importedType) {
		if (importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName())) {
			importedTypes.add(importedType);
		}
	}

	@Override
	public String getFormattedContent(int indentLevel, CompilationUnit compilationUnit) {
		StringBuilder sb = new StringBuilder();

		for (String commentLine : fileCommentLines) {
			sb.append(commentLine);
			newLine(sb);
		}

		if (StringUtils.isNotBlank(getType().getPackageName())) {
			sb.append("package "); //$NON-NLS-1$
			sb.append(getType().getPackageName());
			sb.append(';');
			newLine(sb);
			newLine(sb);
		}

		for (String staticImport : staticImports) {
			sb.append("import static "); //$NON-NLS-1$
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
	public String getFormattedContent() {

		return getFormattedContent(0, this);
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
