package cn.lanaya.generator.dom.java;

import static cn.lanaya.generator.dom.OutputUtilities.javaIndent;
import static cn.lanaya.generator.dom.OutputUtilities.newLine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.lanaya.generator.dom.JavaDomUtils;
import cn.lanaya.generator.dom.OutputUtilities;

public class InnerInterface extends JavaElement{

	private List<Field> fields;
	
	private QualifiedJavaType type;
	
	private List<InnerInterface> innerInterfaces;
	
	private Set<QualifiedJavaType> superInterfaceTypes;
	
	private List<Method> methods;
	
	public InnerInterface(QualifiedJavaType type) {
        super();
        this.type = type;
        innerInterfaces = new ArrayList<InnerInterface>();
        superInterfaceTypes = new LinkedHashSet<QualifiedJavaType>();
        methods = new ArrayList<Method>();
        fields = new ArrayList<Field>();
    }
	
	 /**
     * Gets the formatted content.
     *
     * @param indentLevel
     *            the indent level
     * @param compilationUnit the compilation unit
     * @return the formatted content
     */
    public String getFormattedContent(int indentLevel, CompilationUnit compilationUnit) {
        StringBuilder sb = new StringBuilder();

        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);

        javaIndent(sb, indentLevel);
        sb.append(getVisibility().getValue());

        if (isStatic()) {
            sb.append("static "); //$NON-NLS-1$
        }

        if (isFinal()) {
            sb.append("final "); //$NON-NLS-1$
        }

        sb.append("interface "); //$NON-NLS-1$
        sb.append(getType().getShortNameIncludeTypeArgs());

        if (getSuperInterfaceTypes().size() > 0) {
            sb.append(" extends "); //$NON-NLS-1$

            boolean comma = false;
            for (QualifiedJavaType fqjt : getSuperInterfaceTypes()) {
                if (comma) {
                    sb.append(", "); //$NON-NLS-1$
                } else {
                    comma = true;
                }

                sb.append(JavaDomUtils.calculateTypeName(compilationUnit, fqjt));
            }
        }

        sb.append(" {"); //$NON-NLS-1$
        indentLevel++;

        Iterator<Field> fldIter = fields.iterator();
        while (fldIter.hasNext()) {
            OutputUtilities.newLine(sb);
            Field field = fldIter.next();
            sb.append(field.getFormattedContent(indentLevel, compilationUnit));
        }

        if (fields.size() > 0 && methods.size() > 0) {
            OutputUtilities.newLine(sb);
        }

        Iterator<Method> mtdIter = getMethods().iterator();
        while (mtdIter.hasNext()) {
            newLine(sb);
            Method method = mtdIter.next();
            sb.append(method.getFormattedContent(indentLevel, true, compilationUnit));
            if (mtdIter.hasNext()) {
                newLine(sb);
            }
        }

        if (innerInterfaces.size() > 0) {
            newLine(sb);
        }
        Iterator<InnerInterface> iiIter = innerInterfaces.iterator();
        while (iiIter.hasNext()) {
            newLine(sb);
            InnerInterface innerInterface = iiIter.next();
            sb.append(innerInterface.getFormattedContent(indentLevel, compilationUnit));
            if (iiIter.hasNext()) {
                newLine(sb);
            }
        }

        indentLevel--;
        newLine(sb);
        javaIndent(sb, indentLevel);
        sb.append('}');

        return sb.toString();
    }
	
	public InnerInterface(String type) {
        this(new QualifiedJavaType(type));
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }
    /**
     * Adds the super interface.
     *
     * @param superInterface
     *            the super interface
     */
    public void addSuperInterface(QualifiedJavaType superInterface) {
        superInterfaceTypes.add(superInterface);
    }

    /**
     * Gets the methods.
     *
     * @return Returns the methods.
     */
    public List<Method> getMethods() {
        return methods;
    }

    /**
     * Adds the method.
     *
     * @param method
     *            the method
     */
    public void addMethod(Method method) {
        methods.add(method);
    }

    /**
     * Gets the type.
     *
     * @return Returns the type.
     */
    public QualifiedJavaType getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getSuperClass()
     */
    public QualifiedJavaType getSuperClass() {
        // interfaces do not have superclasses
        return null;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getSuperInterfaceTypes()
     */
    public Set<QualifiedJavaType> getSuperInterfaceTypes() {
        return superInterfaceTypes;
    }

    /**
     * Gets the inner interface.
     *
     * @return Returns the innerInterfaces.
     */
    public List<InnerInterface> getInnerInterfaces() {
        return innerInterfaces;
    }

    /**
     * Adds the inner interface.
     *
     * @param innerInterface
     *            the inner interface
     */
    public void addInnerInterfaces(InnerInterface innerInterface) {
        innerInterfaces.add(innerInterface);
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#isJavaInterface()
     */
    public boolean isJavaInterface() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#isJavaEnumeration()
     */
    public boolean isJavaEnumeration() {
        return false;
    }
}
