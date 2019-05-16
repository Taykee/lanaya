package cn.lanaya.generator.codegen.mybatis3.web;

import java.util.ArrayList;
import java.util.List;

import cn.lanaya.generator.codegen.mybatis3.service.ServiceMethodBody;
import cn.lanaya.generator.dom.xml.XmlMapperKeys;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.util.StringTools;

public class ControllerDefaultImpl implements ServiceMethodBody{

	@Override
	public List<String> getSelectByClause(FullyQualifiedTable table) {
		List<String> body = getDomainBody(table, XmlMapperKeys.SELECT_BY_CLAUSE_SQL);
		StringBuilder returnLine = new StringBuilder("return success(MessageEnum.SUCCESS, ");
		String fieldName = StringTools.lowerFirstChar(table.getServiceName());
		returnLine.append(fieldName).append(".").append(XmlMapperKeys.SELECT_BY_CLAUSE_SQL)
			.append("(record));");
		body.add(returnLine.toString());
		return body;
	}

	@Override
	public List<String> getSelectByPrimaryKey(FullyQualifiedTable table) {
		List<String> body = getBody(table, XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL);
		return body;
	}

	@Override
	public List<String> getDeleteByPrimaryKey(FullyQualifiedTable table) {
		List<String> body = getBody(table, XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL);
		String s3 = "return doProcess(json, () -> {";
		body.add(s3);
		return body;
	}

	@Override
	public List<String> getDeleteByClause(FullyQualifiedTable table) {
		List<String> body = getBody(table, XmlMapperKeys.DELETE_BY_CLAUSE_SQL);
		String serviceName= StringTools.lowerFirstChar(table.getServiceName());
		
		String s3 = "return doProcess(json, () -> {";
		String s4 = "return " + serviceName + "." + XmlMapperKeys.DELETE_BY_CLAUSE_SQL + "(record);";
		String s5 = "});";
		body.add(s3);
		body.add(s4);
		body.add(s5);
		return body;
	}

	@Override
	public List<String> getInsert(FullyQualifiedTable table) {
		List<String> body = getDomainBody(table, XmlMapperKeys.INSERT_SQL);
		String serviceName= StringTools.lowerFirstChar(table.getServiceName());
		String s3 = "return doProcess(json, () -> {";
		String s4 = "return " + serviceName + "." + XmlMapperKeys.INSERT_SQL + "(record);";
		String s5 = "});";
		body.add(s3);
		body.add(s4);
		body.add(s5);
		return body;
	}

	@Override
	public List<String> getInsertSelective(FullyQualifiedTable table) {
		List<String> body = getDomainBody(table, XmlMapperKeys.INSERT_SELECTIVE_SQL);
		String serviceName= StringTools.lowerFirstChar(table.getServiceName());
		String s3 = "return doProcess(json, () -> {";
		String s4 = "return " + serviceName + "." + XmlMapperKeys.INSERT_SELECTIVE_SQL + "(record);";
		String s5 = "});";
		body.add(s3);
		body.add(s4);
		body.add(s5);
		return body;
	}

	@Override
	public List<String> getCountByClause(FullyQualifiedTable table) {
		List<String> body = getBody(table, XmlMapperKeys.COUNT_BY_CLAUSE_SQL);
		StringBuilder returnLine = new StringBuilder("return success(MessageEnum.SUCCESS, ");
		String fieldName = StringTools.lowerFirstChar(table.getServiceName());
		returnLine.append(fieldName).append(".").append(XmlMapperKeys.COUNT_BY_CLAUSE_SQL)
			.append("(record));");
		body.add(returnLine.toString());
		return body;
	}

	private List<String> getBody(FullyQualifiedTable table, String name){
		List<String> body = new ArrayList<>();
		String voName = table.getViewObjectName();
		String s1 = "log.info(\"" + name + "\", json);";
		String s2 = voName + " record = JSONObject.parseObject(json, " + voName + ".class);";
		body.add(s1);
		body.add(s2);
		return body;
	}
	
	private List<String> getDomainBody(FullyQualifiedTable table, String name){
		List<String> body = new ArrayList<>();
		String voName = table.getDomainObjectName();
		String s1 = "log.info(\"" + name + "\", json);";
		String s2 = voName + " record = JSONObject.parseObject(json, " + voName + ".class);";
		body.add(s1);
		body.add(s2);
		return body;
	}
	
	@Override
	public List<String> getUpdateByClauseSelective(FullyQualifiedTable table) {
		List<String> body = getBody(table, XmlMapperKeys.UPDATE_BY_CLAUSE_SQL);
		
		return body;
	}

	@Override
	public List<String> getUpdateByPrimaryKeySelective(FullyQualifiedTable table) {
		List<String> body = getDomainBody(table, XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL);
		String serviceName= StringTools.lowerFirstChar(table.getServiceName());
		String s3 = "return doProcess(json, () -> {";
		String s4 = "return " + serviceName + "." + XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL + "(record);";
		String s5 = "});";
		body.add(s3);
		body.add(s4);
		body.add(s5);
		return body;
	}

	@Override
	public List<String> getUpdateByPrimaryKey(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		return body;
	}

	@Override
	public List<String> getPageByClause(FullyQualifiedTable table) {
		List<String> body = getBody(table, XmlMapperKeys.PAGE_BY_CLAUSE_SQL);
		String s1 = "PageQo page = JSONObject.parseObject(json, PageQo.class);";
		String s2 = "page.getParams().put(\"entity\", record);";
		String s3 = "return success(MessageEnum.SUCCESS, " + StringTools.lowerFirstChar(table.getServiceName()) + ".pageByClause(page));";
		body.add(s1);
		body.add(s2);
		body.add(s3);
		return body;
	}

	@Override
	public List<String> getDeleteByPrimaryKeyBatch(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String voName = table.getViewObjectName();
		String s1 = "log.info(\"" + XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL + "\", json);";
		String s2 = "List<" + voName + "> list = JSONObject.parseArray(json, " + voName + ".class);";
		body.add(s1);
		body.add(s2);
		String serviceName= StringTools.lowerFirstChar(table.getServiceName());
		String s3 = "return doProcess(json, () -> {";
		String s4 = "return " + serviceName + "." + XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL + "(list);";
		String s5 = "});";
		body.add(s3);
		body.add(s4);
		body.add(s5);
		return body;
	}

	@Override
	public List<String> getInsertSelectBatch(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String voName = table.getDomainObjectName();
		String s1 = "log.info(\"" + XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL + "\", json);";
		String s2 = "List<" + voName + "> list = JSONObject.parseArray(json, " + voName + ".class);";
		body.add(s1);
		body.add(s2);
		String serviceName= StringTools.lowerFirstChar(table.getServiceName());
		String s3 = "return doProcess(json, () -> {";
		String s4 = "return " + serviceName + "." + XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL + "(list);";
		String s5 = "});";
		body.add(s3);
		body.add(s4);
		body.add(s5);
		return body;
	}

}
