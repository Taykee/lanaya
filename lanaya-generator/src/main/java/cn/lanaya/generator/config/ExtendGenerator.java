package cn.lanaya.generator.config;

import cn.lanaya.generator.codegen.mybatis3.service.ServiceImplGenerator;
import cn.lanaya.generator.codegen.mybatis3.service.ServiceMethodBody;
import cn.lanaya.generator.codegen.mybatis3.web.ControllerGenerator;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.file.JavaFileWriter;
import cn.lanaya.generator.table.IntrospectedTable;

import java.util.List;

public class ExtendGenerator {
	
	public void generateServiceImpl(List<IntrospectedTable> introspectedTables,
			ServiceMethodBody serviceMethodBody, String javaRoot) {
		ServiceImplGenerator serviceImplGenerator = new ServiceImplGenerator();
		for (IntrospectedTable introspectedTable : introspectedTables) {
			CompilationUnit service = serviceImplGenerator.
					getCompilationUnit(introspectedTable, serviceMethodBody);
			JavaFileWriter.writer(service, javaRoot + "/service/impl");
		}
	}

	public void generateController(List<IntrospectedTable> introspectedTables, 
			ServiceMethodBody controller,
			String javaRoot) {
		ControllerGenerator controllerGenerator = new ControllerGenerator();
		for (IntrospectedTable introspectedTable : introspectedTables) {
			CompilationUnit controllerUnit = controllerGenerator.getCompilationUnit(introspectedTable, controller);
			JavaFileWriter.writer(controllerUnit, javaRoot + "/web");
		}		
	}
	
	public void generatePostmanJson(List<IntrospectedTable> introspectedTables,String javaRoot) {
//		JsonGenerator jsonGenerator = new JsonGenerator();
//		jsonGenerator.generateJson(introspectedTables, javaRoot);
//
	}
	
}
