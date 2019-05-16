package cn.lanaya.generator.dom.java;

import cn.lanaya.generator.dom.JavaDomUtils;
import cn.lanaya.generator.dom.OutputUtilities;

public class Field extends JavaElement {

	private QualifiedJavaType type;
	private String name;
	// 值的字符串类型
	private String initializationString;
	private boolean isTransient;
	private boolean isVolatile;

	public Field() {
		// use a default name to avoid NPE
		this("foo", QualifiedJavaType.getIntInstance());
	}

	public Field(String name, QualifiedJavaType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Field(Field field) {
		super(field);
		this.type = field.type;
		this.name = field.name;
		this.initializationString = field.initializationString;
	}

	public String getFormattedContent(int indentLevel, CompilationUnit compilationUnit) {
		StringBuilder sb = new StringBuilder();

		addFormattedJavadoc(sb, indentLevel);
		addFormattedAnnotations(sb, indentLevel);

		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append(getVisibility().getValue());

		if (isStatic()) {
			sb.append("static "); 
		}

		if (isFinal()) {
			sb.append("final "); 
		}

		if (isTransient()) {
			sb.append("transient "); 
		}

		if (isVolatile()) {
			sb.append("volatile "); 
		}

		sb.append(type.getBaseShortName());

		sb.append(' ');
		sb.append(name);

		if (initializationString != null && initializationString.length() > 0) {
			sb.append(" = "); 
			sb.append(initializationString);
		}

		sb.append(';');

		return sb.toString();
	}

	public QualifiedJavaType getType() {
		return type;
	}

	public void setType(QualifiedJavaType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitializationString() {
		return initializationString;
	}

	public void setInitializationString(String initializationString) {
		this.initializationString = initializationString;
	}

	public boolean isTransient() {
		return isTransient;
	}

	public void setTransient(boolean isTransient) {
		this.isTransient = isTransient;
	}

	public boolean isVolatile() {
		return isVolatile;
	}

	public void setVolatile(boolean isVolatile) {
		this.isVolatile = isVolatile;
	}

}
