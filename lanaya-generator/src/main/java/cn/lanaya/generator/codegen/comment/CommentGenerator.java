package cn.lanaya.generator.codegen.comment;

import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.java.Field;
import cn.lanaya.generator.dom.java.InnerClass;
import cn.lanaya.generator.dom.java.InnerEnum;
import cn.lanaya.generator.dom.java.JavaElement;
import cn.lanaya.generator.dom.java.Method;
import cn.lanaya.generator.dom.java.TopLevelClass;

/**
 * 注释生成器
 * @author taiqin
 *
 */
public interface CommentGenerator {

	void addFieldComment(Field field, JavaElement element);
	
	void addTopLevelClassComment(TopLevelClass topLevelClass);
	
	void addInnerClassComment(InnerClass innerClass);
	
	void addEnumComment(InnerEnum innerEnum);
	
	void addGetterComment(Method method);
	
	void addSetterComment(Method method, boolean returnThis);
	
	void addGeneralMethodComment(Method method);
	
	void addJavaFileComment(CompilationUnit compilationUnit);
	
	void addLombokAnnotation(boolean lombok);
}
