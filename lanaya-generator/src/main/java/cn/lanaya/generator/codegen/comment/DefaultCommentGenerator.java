package cn.lanaya.generator.codegen.comment;

import cn.lanaya.generator.dom.OutputUtilities;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Field;
import cn.lanaya.generator.dom.java.InnerClass;
import cn.lanaya.generator.dom.java.InnerEnum;
import cn.lanaya.generator.dom.java.JavaElement;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.TopLevelClass;

public class DefaultCommentGenerator implements CommentGenerator{

	@Override
	public void addFieldComment(Field field, JavaElement element) {
//		StringBuilder sb = new StringBuilder();
//		OutputUtilities.javaIndent(sb, 2);
//		sb.append("/**");
//		for (String s : field.getJavaDocLines()) {
//			OutputUtilities.newLine(sb);
//			OutputUtilities.javaIndent(sb, 2);
//			sb.append("* " + s);
//		}
//		OutputUtilities.newLine(sb);
//		OutputUtilities.javaIndent(sb, 2);
//		sb.append("*/");
//		field.addJavaDocLine(sb.toString());
		
	}

	@Override
	public void addTopLevelClassComment(TopLevelClass topLevelClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInnerClassComment(InnerClass innerClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGetterComment(Method method) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSetterComment(Method method, boolean returnThis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGeneralMethodComment(Method method) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLombokAnnotation(boolean lombok) {
		// TODO Auto-generated method stub
		
	}

}
