package cn.lanaya.generator.dom.java;

import java.util.ArrayList;
import java.util.List;

import cn.lanaya.generator.dom.JavaDomUtils;

/**
 * 方法参数
 * @author taiqin
 *
 */
public class Parameter {

	private String name;
	private QualifiedJavaType type;
	private boolean isVarargs;
	
	private List<String> annotations;

	public Parameter(QualifiedJavaType type, String name, boolean isVarargs) {
        super();
        this.name = name;
        this.type = type;
        this.isVarargs = isVarargs;
        annotations = new ArrayList<String>();
    }

    public Parameter(QualifiedJavaType type, String name) {
        this(type, name, false);
    }

    public Parameter(QualifiedJavaType type, String name, String annotation) {
        this(type, name, false);
        addAnnotation(annotation);
    }

    public Parameter(QualifiedJavaType type, String name, String annotation, boolean isVarargs) {
        this(type, name, isVarargs);
        addAnnotation(annotation);
    }
	
    public String getFormattedContent(CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        for (String annotation : annotations) {
            sb.append(annotation);
            sb.append(' ');
        }

        sb.append(JavaDomUtils.calculateTypeName(compilationUnit, type));

        sb.append(' ');
        if (isVarargs) {
            sb.append("... "); //$NON-NLS-1$
        }
        sb.append(name);

        return sb.toString();
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QualifiedJavaType getType() {
		return type;
	}

	public void setType(QualifiedJavaType type) {
		this.type = type;
	}

	public boolean isVarargs() {
		return isVarargs;
	}

	public void setVarargs(boolean isVarargs) {
		this.isVarargs = isVarargs;
	}

	public List<String> getAnnotations() {
		return annotations;
	}

	public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }
	
	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}
	
}
