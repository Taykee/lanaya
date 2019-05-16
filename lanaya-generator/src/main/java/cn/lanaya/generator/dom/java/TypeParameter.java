package cn.lanaya.generator.dom.java;

import java.util.ArrayList;
import java.util.List;

import cn.lanaya.generator.dom.JavaDomUtils;

public class TypeParameter {

	private String name;

	private List<QualifiedJavaType> extendsTypes;

	public TypeParameter(String name) {
		this(name, new ArrayList<QualifiedJavaType>());
	}

	public TypeParameter(String name, List<QualifiedJavaType> extendsTypes) {
		super();
		this.name = name;
		this.extendsTypes = extendsTypes;
	}

	public String getFormattedContent(CompilationUnit compilationUnit) {
		StringBuilder sb = new StringBuilder();

		sb.append(name);
		if (!extendsTypes.isEmpty()) {
			sb.append(" extends "); //$NON-NLS-1$
			boolean addAnd = false;
			for (QualifiedJavaType type : extendsTypes) {
				if (addAnd) {
					sb.append(" & "); //$NON-NLS-1$
				} else {
					addAnd = true;
				}
				sb.append(JavaDomUtils.calculateTypeName(compilationUnit, type));
			}
		}

		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public List<QualifiedJavaType> getExtendsTypes() {
		return extendsTypes;
	}

	@Override
	public String toString() {
		return getFormattedContent(null);
	}

}
