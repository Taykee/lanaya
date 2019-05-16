package cn.lanaya.generator.config;

import cn.lanaya.generator.codegen.mybatis3.javamapper.MapperGenerator;
import cn.lanaya.generator.codegen.mybatis3.model.BaseRecordGenerator;
import cn.lanaya.generator.codegen.mybatis3.service.ServiceGenerator;
import cn.lanaya.generator.codegen.mybatis3.service.ServiceMethodBody;
import cn.lanaya.generator.codegen.mybatis3.service.impl.ServiceMethodBodyDefaultImpl;
import cn.lanaya.generator.codegen.mybatis3.web.ControllerDefaultImpl;
import cn.lanaya.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import cn.lanaya.generator.dom.java.CompilationUnit;
import cn.lanaya.generator.dom.xml.Document;
import cn.lanaya.generator.file.JavaFileWriter;
import cn.lanaya.generator.file.XmlMapperWriter;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.table.IntrospectedTable;
import cn.lanaya.generator.util.StringTools;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class BasicConfiguration {

	private Properties propertiesHolder;
	private List<String> tables;
	private boolean prefixRemove;
	private boolean openComments;
	private boolean openAnnotation;
	private boolean camelCase;

	public BasicConfiguration(String filename) {
		propertiesHolder = new Properties();
		try {
			propertiesHolder.load(Properties.class.getResourceAsStream("/" + filename));
			String tableNames = propertiesHolder.getProperty("tableName");
			String[] nameArray = StringUtils.split(tableNames, ",");
			tables = Arrays.asList(nameArray);
			prefixRemove = StringTools.isTrue(propertiesHolder.getProperty("prefix.remove"));
			openComments = StringTools.isTrue(propertiesHolder.getProperty("open.comments"));
			openAnnotation = StringTools.isTrue(propertiesHolder.getProperty("open.annotation"));
			camelCase = StringTools.isTrue(propertiesHolder.getProperty("camel.case"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Set<String> getTablenames(){
		String property = propertiesHolder.getProperty("tableName");
		if(StringUtils.isBlank(property)) {
			return null;
		}
		String[] split = property.split(",");
		Set<String> result = new HashSet<>();
		result.addAll(Arrays.asList(split));
		return result;
	}
	
	/**
	 * 初始化databaseMetaData
	 */
	public void generate(ExtendGenerator extend) {
		List<IntrospectedTable> introspectedTables = null;
		Connection conn = null;
		try {
			Class.forName(propertiesHolder.getProperty("jdbc.driver"));
			conn = DriverManager.getConnection(propertiesHolder.getProperty("jdbc.url"), 
					propertiesHolder.getProperty("jdbc.username"),
					propertiesHolder.getProperty("jdbc.password"));
			IntrospectedTable introspectedTable = new IntrospectedTable(propertiesHolder);
			introspectedTables = introspectedTable.calculateIntrospectedTable(conn.getMetaData(), getTablenames());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConection(conn);
		}
		if(introspectedTables == null) {
			return ;
		}
		BaseRecordGenerator baseRecordGenerator = new BaseRecordGenerator();
		MapperGenerator mapperGenerator = new MapperGenerator();
		ServiceGenerator serviceGenerator = new ServiceGenerator();
		XMLMapperGenerator xmlMapperGenerator = new XMLMapperGenerator();
		String javaRoot = propertiesHolder.getProperty("root.java.folder");
		String xmlRoot = propertiesHolder.getProperty("root.xml.folder");
		for (IntrospectedTable introspectedTable : introspectedTables) {
			FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
			CompilationUnit interfaces = mapperGenerator.getCompilationUnit(introspectedTable);
			CompilationUnit service = serviceGenerator.getCompilationUnit(introspectedTable);
			Document document = xmlMapperGenerator.getDocument(introspectedTable);
			XmlMapperWriter.writeXmlMaper(table, document, xmlRoot);
			JavaFileWriter.writer(baseRecordGenerator.getDomainCompilationUnit(introspectedTable),
					javaRoot + "/entity");
			JavaFileWriter.writer(baseRecordGenerator.getViewCompilationUnit(introspectedTable),
					javaRoot + "/vo");
			JavaFileWriter.writer(interfaces, javaRoot + "/mapper");
			JavaFileWriter.writer(service, javaRoot + "/service");
		}
		if(extend != null) {
			ServiceMethodBody serviceMethodBody = new ServiceMethodBodyDefaultImpl();
			ServiceMethodBody controller = new ControllerDefaultImpl();
			extend.generateServiceImpl(introspectedTables, serviceMethodBody, javaRoot);
			extend.generateController(introspectedTables, controller, javaRoot);
//			JsonGenerator jsonGenerator = new JsonGenerator();
//			jsonGenerator.generateJson(introspectedTables, javaRoot + "/json");
		}
	}

	private void closeConection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Properties getPropertiesHolder() {
		return propertiesHolder;
	}

	public void setPropertiesHolder(Properties propertiesHolder) {
		this.propertiesHolder = propertiesHolder;
	}

	public List<String> getTables() {
		return tables;
	}

	public void addTables(String table) {
		this.tables.add(table);
	}

	public boolean isPrefixRemove() {
		return prefixRemove;
	}

	public void setPrefixRemove(boolean prefixRemove) {
		this.prefixRemove = prefixRemove;
	}

	public boolean isOpenComments() {
		return openComments;
	}

	public void setOpenComments(boolean openComments) {
		this.openComments = openComments;
	}

	public boolean isOpenAnnotation() {
		return openAnnotation;
	}

	public void setOpenAnnotation(boolean openAnnotation) {
		this.openAnnotation = openAnnotation;
	}

	public boolean isCamelCase() {
		return camelCase;
	}

	public void setCamelCase(boolean camelCase) {
		this.camelCase = camelCase;
	}

}
