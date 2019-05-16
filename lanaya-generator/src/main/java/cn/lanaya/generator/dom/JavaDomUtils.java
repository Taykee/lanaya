package cn.lanaya.generator.dom;

import org.apache.commons.lang3.StringUtils;

import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.QualifiedJavaType;

public class JavaDomUtils {
	public static String calculateTypeName(CompilationUnit compilationUnit, QualifiedJavaType fqjt) {

		if (fqjt.getTypeArguments().size() > 0) {
			return calculateParameterizedTypeName(compilationUnit, fqjt);
		}

		if (compilationUnit == null || typeDoesNotRequireImport(fqjt) || typeIsInSamePackage(compilationUnit, fqjt)
				|| typeIsAlreadyImported(compilationUnit, fqjt)) {
			return fqjt.getShortNameIncludeTypeArgs();
		} else {
			return fqjt.getQualifiedNameIncludeTypeArgs();
		}
	}

	private static String calculateParameterizedTypeName(CompilationUnit compilationUnit, QualifiedJavaType fqjt) {
		String baseTypeName = calculateTypeName(compilationUnit, new QualifiedJavaType(fqjt.getBaseQualifiedName()));

		StringBuilder sb = new StringBuilder();
		sb.append(baseTypeName);
		sb.append('<');
		boolean comma = false;
		for (QualifiedJavaType ft : fqjt.getTypeArguments()) {
			if (comma) {
				sb.append(", "); //$NON-NLS-1$
			} else {
				comma = true;
			}
			sb.append(calculateTypeName(compilationUnit, ft));
		}
		sb.append('>');

		return sb.toString();

	}

	private static boolean typeDoesNotRequireImport(QualifiedJavaType QualifiedJavaType) {
		return QualifiedJavaType.isPrimitive() || !QualifiedJavaType.isExplicitlyImported();
	}

	private static boolean typeIsInSamePackage(CompilationUnit compilationUnit, QualifiedJavaType QualifiedJavaType) {
		return QualifiedJavaType.getPackageName().equals(compilationUnit.getType().getPackageName());
	}

	private static boolean typeIsAlreadyImported(CompilationUnit compilationUnit, QualifiedJavaType QualifiedJavaType) {
		QualifiedJavaType nonGenericType = new QualifiedJavaType(QualifiedJavaType.getBaseQualifiedName());
		return compilationUnit.getImportedTypes().contains(nonGenericType);
	}
	
	public static String composeQualifiedTableName(String catalog,
            String schema, String tableName, char separator) {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isNotBlank(catalog)) {
            sb.append(catalog);
            sb.append(separator);
        }

        if (StringUtils.isNotBlank(schema)) {
            sb.append(schema);
            sb.append(separator);
        } else {
            if (sb.length() > 0) {
                sb.append(separator);
            }
        }

        sb.append(tableName);

        return sb.toString();
    }
}
