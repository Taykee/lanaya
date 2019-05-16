package cn.lanaya.generator.dom.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author taiqin
 *
 */
public class QualifiedJavaType implements Comparable<QualifiedJavaType> {

	private static final String JAVA_LANG = "java.lang";

	private static QualifiedJavaType intInstance = null;

	private static QualifiedJavaType stringInstance = null;

	private static QualifiedJavaType booleanPrimitiveInstance = null;

	private static QualifiedJavaType objectInstance = null;

	private static QualifiedJavaType dateInstance = null;

	/** The short name without any generic arguments. */
	private String baseShortName;

	/** The fully qualified name without any generic arguments. */
	private String baseQualifiedName;

	// 是否需要导入
	private boolean explicitlyImported;

	private boolean isArray;

	private String packageName;

	private boolean primitive;

	private PrimitiveTypeWrapper primitiveTypeWrapper;

	private List<QualifiedJavaType> typeArguments;

	// the following three values are used for dealing with wildcard types
	private boolean wildcardType;

	private boolean boundedWildcard;

	private boolean extendsBoundedWildcard;

	public QualifiedJavaType(String qualifiedJavaType) {
		typeArguments = new ArrayList<>();
		parse(qualifiedJavaType);
	}

	private void parse(String qualifiedJavaType) {
		String spec = StringUtils.trim(qualifiedJavaType);
		// ? super Class or ? extend Class
		if (spec.startsWith("?")) {
			wildcardType = true;
			spec = spec.substring(1).trim();
			if (spec.startsWith("extends ")) {
				boundedWildcard = true;
				extendsBoundedWildcard = true;
				spec = spec.substring(8); // "extends ".length()
			} else if (spec.startsWith("super ")) { //$NON-NLS-1$
				boundedWildcard = true;
				extendsBoundedWildcard = false;
				spec = spec.substring(6); // "super ".length()
			} else {
				boundedWildcard = false;
			}
			parse(spec);
		} else {
			int index = spec.indexOf('<');
			if (index == -1) {
				simpleParse(spec);
			} else {
				simpleParse(spec.substring(0, index));
				int endIndex = spec.lastIndexOf('>');
				if (endIndex == -1) {
					// throw new RuntimeException(getString(
					// "RuntimeError.22", spec)); //$NON-NLS-1$
				}
				genericParse(spec.substring(index, endIndex + 1));
			}
		}
	}

	private void genericParse(String genericSpecification) {
		int lastIndex = genericSpecification.lastIndexOf('>');
		if (lastIndex == -1) {
			throw new RuntimeException();
		}
		// java.lang.String, java.util.List<java.lang.Object>
		String argumentString = genericSpecification.substring(1, lastIndex);
		String[] split = StringUtils.split(argumentString, ',');
		for (String arg : split) {
			typeArguments.add(new QualifiedJavaType(arg));
		}

	}

	private void simpleParse(String qualifiedJavaType) {
		baseQualifiedName = qualifiedJavaType.trim();
		if (qualifiedJavaType.contains(".")) {
			baseShortName = StringUtils.substringAfterLast(qualifiedJavaType, ".");
			packageName = StringUtils.substringBeforeLast(qualifiedJavaType, ".");
			if (qualifiedJavaType.startsWith(JAVA_LANG)) {
				explicitlyImported = false;
			} else {
				explicitlyImported = true;
			}
		} else {
			// 处理原始类型
			primitiveWrapper();
		}

	}

	private void primitiveWrapper() {
		baseShortName = baseQualifiedName;
		explicitlyImported = false;
		packageName = "";
		switch (baseQualifiedName) {
		case "byte":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getByteInstance();
			break;
		case "short":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getShortInstance();
			break;
		case "int":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getIntegerInstance();
			break;
		case "long":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getLongInstance();
			break;
		case "char":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getCharacterInstance();
			break;
		case "float":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getFloatInstance();
			break;
		case "double":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getDoubleInstance();
			break;
		case "boolean":
			primitive = true;
			primitiveTypeWrapper = PrimitiveTypeWrapper.getBooleanInstance();
			break;
		default:
			primitive = false;
			primitiveTypeWrapper = null;
			break;
		}
	}

	public static QualifiedJavaType getIntInstance() {
		if (intInstance == null) {
			intInstance = new QualifiedJavaType("int");
		}
		return intInstance;
	}

	public static QualifiedJavaType getStringInstance() {
		if (stringInstance == null) {
			stringInstance = new QualifiedJavaType("java.lang.String");
		}
		return stringInstance;
	}

	public static QualifiedJavaType getBooleanPrimitiveInstance() {
		if (booleanPrimitiveInstance == null) {
			booleanPrimitiveInstance = new QualifiedJavaType("boolean");
		}
		return booleanPrimitiveInstance;
	}

	public static QualifiedJavaType getObjectInstance() {
		if (objectInstance == null) {
			objectInstance = new QualifiedJavaType("java.lang.Object");
		}
		return objectInstance;
	}

	public static QualifiedJavaType getDateInstance() {
		if (dateInstance == null) {
			dateInstance = new QualifiedJavaType("java.util.Date");
		}
		return dateInstance;
	}

	@Override
	public int hashCode() {
		return getQualifiedNameIncludeTypeArgs().hashCode();
	}

	/**
	 * Returns the fully qualified name - including any generic type parameters.
	 * @return Returns the qualifiedName
	 */
	public String getQualifiedNameIncludeTypeArgs() {
		StringBuilder sb = new StringBuilder();
		if (wildcardType) {
			sb.append('?');
			if (boundedWildcard) {
				if (extendsBoundedWildcard) {
					sb.append(" extends ");
				} else {
					sb.append(" super ");
				}
				sb.append(baseQualifiedName);
			}
		} else {
			sb.append(baseQualifiedName);
		}
		if (typeArguments.size() > 0) {
			boolean first = true;
			sb.append('<');
			for (QualifiedJavaType fqjt : typeArguments) {
				if (first) {
					first = false;
				} else {
					sb.append(", "); //$NON-NLS-1$
				}
				sb.append(fqjt.getQualifiedNameIncludeTypeArgs());
			}
			sb.append('>');
		}
		return sb.toString();
	}

	/**
	 * @return the import list
	 */
	public List<String> getImportList() {
		List<String> answer = new ArrayList<String>();
		if (isExplicitlyImported()) {
			int index = baseShortName.indexOf('.');
			if (index == -1) {
				answer.add(calculateActualImport(baseQualifiedName));
			} else {
				// an inner class is specified, only import the top
				// level class
				StringBuilder sb = new StringBuilder();
				sb.append(packageName);
				sb.append('.');
				sb.append(calculateActualImport(baseShortName.substring(0, index)));
				answer.add(sb.toString());
			}
		}
		for (QualifiedJavaType fqjt : typeArguments) {
			answer.addAll(fqjt.getImportList());
		}
		return answer;
	}

	/**
	 * @return 包含泛型类型信息的类名
	 */
	public String getShortNameIncludeTypeArgs() {
		StringBuilder sb = new StringBuilder();
		if (wildcardType) {
			sb.append('?');
			if (boundedWildcard) {
				if (extendsBoundedWildcard) {
					sb.append(" extends ");
				} else {
					sb.append(" super ");
				}
				sb.append(baseShortName);
			}
		} else {
			sb.append(baseShortName);
		}
		if (typeArguments.size() > 0) {
			boolean first = true;
			sb.append('<');
			for (QualifiedJavaType fqjt : typeArguments) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(fqjt.getShortNameIncludeTypeArgs());
			}
			sb.append('>');
		}
		return sb.toString();
	}

	private String calculateActualImport(String name) {
		String answer = name;
		if (this.isArray()) {
			int index = name.indexOf("["); //$NON-NLS-1$
			if (index != -1) {
				answer = name.substring(0, index);
			}
		}
		return answer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof QualifiedJavaType)) {
			return false;
		}
		QualifiedJavaType other = (QualifiedJavaType) obj;
		return getQualifiedNameIncludeTypeArgs().equals(other.getQualifiedNameIncludeTypeArgs());
	}

	public static final QualifiedJavaType getNewMapInstance() {
		// always return a new instance because the type may be parameterized
		return new QualifiedJavaType("java.util.Map"); //$NON-NLS-1$
	}

	public static final QualifiedJavaType getNewListInstance() {
		// always return a new instance because the type may be parameterized
		return new QualifiedJavaType("java.util.List"); //$NON-NLS-1$
	}

	public static final QualifiedJavaType getNewHashMapInstance() {
		// always return a new instance because the type may be parameterized
		return new QualifiedJavaType("java.util.HashMap"); //$NON-NLS-1$
	}

	public static final QualifiedJavaType getNewArrayListInstance() {
		// always return a new instance because the type may be parameterized
		return new QualifiedJavaType("java.util.ArrayList"); //$NON-NLS-1$
	}

	public static final QualifiedJavaType getNewIteratorInstance() {
		// always return a new instance because the type may be parameterized
		return new QualifiedJavaType("java.util.Iterator"); //$NON-NLS-1$
	}

	@Override
	public String toString() {
		return getQualifiedNameIncludeTypeArgs();
	}

	@Override
	public int compareTo(QualifiedJavaType other) {
		return getQualifiedNameIncludeTypeArgs().compareTo(other.getQualifiedNameIncludeTypeArgs());
	}

	public void addTypeArgument(QualifiedJavaType type) {
		typeArguments.add(type);
	}

	public String getBaseShortName() {
		return baseShortName;
	}

	public String getBaseQualifiedName() {
		return baseQualifiedName;
	}

	public boolean isExplicitlyImported() {
		return explicitlyImported;
	}

	public String getPackageName() {
		return packageName;
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public PrimitiveTypeWrapper getPrimitiveTypeWrapper() {
		return primitiveTypeWrapper;
	}

	public List<QualifiedJavaType> getTypeArguments() {
		return typeArguments;
	}

	public boolean isWildcardType() {
		return wildcardType;
	}

	public boolean isBoundedWildcard() {
		return boundedWildcard;
	}

	public boolean isExtendsBoundedWildcard() {
		return extendsBoundedWildcard;
	}

	public boolean isArray() {
		return isArray;
	}

}
