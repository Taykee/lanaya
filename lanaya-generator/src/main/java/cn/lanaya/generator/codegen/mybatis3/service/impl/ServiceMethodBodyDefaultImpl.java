package cn.lanaya.generator.codegen.mybatis3.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.lanaya.generator.codegen.mybatis3.service.ServiceMethodBody;
import cn.lanaya.generator.dom.xml.XmlMapperKeys;
import cn.lanaya.generator.table.FullyQualifiedTable;
import cn.lanaya.generator.util.StringTools;

public class ServiceMethodBodyDefaultImpl implements ServiceMethodBody {


	@Override
	public List<String> getSelectByClause(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "ValidatorUtils.notNull(record,\"参数不能为空\");";
		StringBuilder returnLine = new StringBuilder("return ");
		String fieldName = StringTools.lowerFirstChar(table.getMapperName());
		returnLine.append(fieldName).append(".").append(XmlMapperKeys.SELECT_BY_CLAUSE_SQL)
			.append("(record);");
		body.add(s1);
		body.add(returnLine.toString());
		return body;
	}

	@Override
	public List<String> getSelectByPrimaryKey(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		return body;
	}

	@Override
	public List<String> getDeleteByPrimaryKey(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "ValidatorUtils.notNull(saved,\"未找到记录，不能执行删除\");";
		body.add(s1);
		return body;
	}

	@Override
	public List<String> getDeleteByClause(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "ValidatorUtils.notNull(record,\"参数不能为空\");";
		String s2 = "return " + StringTools.lowerFirstChar(table.getMapperName()) +
				"." + XmlMapperKeys.DELETE_BY_CLAUSE_SQL + "(record);";
		body.add(s1);
		body.add(s2);
		return body;
	}

	@Override
	public List<String> getInsert(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "UserInfo user = getCurrentUser();";
		String s2 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行新增\");";
		String s3 = "record.setUpdator(user.getName());";
		String s4 = "record.setCreator(user.getName());";
		String s5 = "record.setId(GuidUtils.getGUID());";
		String s6 = "validate(record);";
		String s7 = "record.setCreatetime(DateUtils.getLongDate(new Date()));";
		String s8 = "return " + StringTools.lowerFirstChar(table.getMapperName()) + "." 
				+ XmlMapperKeys.INSERT_SQL + "(record);";
		body.add(s1);
		body.add(s2);
		body.add(s3);
		body.add(s4);
		body.add(s5);
		body.add(s6);
		body.add(s7);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getInsertSelective(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "UserInfo user = getCurrentUser();";
		String s2 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行新增\");";
		String s3 = "record.setUpdator(user.getName());";
		String s4 = "record.setCreator(user.getName());";
		String s5 = "record.setId(GuidUtils.getGUID());";
		String s6 = "validate(record);";
		String s7 = "record.setCreatetime(DateUtils.getLongDate(new Date()));";
		String s8 = "return " + StringTools.lowerFirstChar(table.getMapperName()) + "." 
				+ XmlMapperKeys.INSERT_SELECTIVE_SQL + "(record);";
		body.add(s1);
		body.add(s2);
		body.add(s3);
		body.add(s4);
		body.add(s5);
		body.add(s6);
		body.add(s7);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getCountByClause(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		StringBuilder returnLine = new StringBuilder("return ");
		String fieldName = StringTools.lowerFirstChar(table.getMapperName());
		returnLine.append(fieldName).append(".").append(XmlMapperKeys.COUNT_BY_CLAUSE_SQL)
			.append("(record);");
		body.add(returnLine.toString());
		return body;
	}

	@Override
	public List<String> getUpdateByClauseSelective(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "UserInfo user = getCurrentUser();";
		String s2 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行更新\");";
		String s3 = "entity.setUpdator(user.getName());";
		String s6 = "validate(entity);";
		String s8 = "return " + StringTools.lowerFirstChar(table.getMapperName()) + "." 
				+ XmlMapperKeys.UPDATE_BY_CLAUSE_SELECTIVE_SQL + "(entity, param);";
		body.add(s1);
		body.add(s2);
		body.add(s3);
		body.add(s6);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getUpdateByPrimaryKeySelective(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "UserInfo user = getCurrentUser();";
		String s2 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行更新\");";
		String s3 = "record.setUpdator(user.getName());";
		String s6 = "validate(record);";
		String s8 = "return " + StringTools.lowerFirstChar(table.getMapperName()) + "." 
				+ XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL + "(record);";
		body.add(s1);
		body.add(s2);
		body.add(s3);
		body.add(s6);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getUpdateByPrimaryKey(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "UserInfo user = getCurrentUser();";
		String s2 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行更新\");";
		String s3 = "record.setUpdator(user.getName());";
		String s6 = "validate(record);";
		String s8 = "return " + StringTools.lowerFirstChar(table.getMapperName()) + "." 
				+ XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SQL + "(record);";
		body.add(s1);
		body.add(s2);
		body.add(s3);
		body.add(s6);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getPageByClause(FullyQualifiedTable table) {
		String vo = table.getViewObjectName();
		List<String> body = new ArrayList<>();
		String s2 = "PageHelper.startPage(page.getPageNum(),page.getPageSize());";
		String s3 = "List<" + vo + "> list = " + StringTools.lowerFirstChar(table.getMapperName()) 
			+ ".pageByClause(page);";
		String s8 = "return new PageInfo<" + vo + ">(list);";
		body.add(s2);
		body.add(s3);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getDeleteByPrimaryKeyBatch(FullyQualifiedTable table) {
		String vo = table.getMapperName();
		List<String> body = new ArrayList<>();
		String s2 = "UserInfo user = getCurrentUser();";
		String s3 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行删除\");";
		String s8 = "return " + StringTools.lowerFirstChar(vo) + "." 
				+ XmlMapperKeys.DELETE_BY_PRIMARY_KEY_BATCH_SQL + "(list);";
		body.add(s2);
		body.add(s3);
		body.add(s8);
		return body;
	}

	@Override
	public List<String> getInsertSelectBatch(FullyQualifiedTable table) {
		List<String> body = new ArrayList<>();
		String s1 = "UserInfo user = getCurrentUser();";
		String s2 = "ValidatorUtils.notNull(user, \"用户未登录，不能执行新增\");";
		String ss1 = "for(" + table.getDomainObjectName() + " record : list){";
		String s3 = "record.setUpdator(user.getName());";
		String s4 = "record.setCreator(user.getName());";
		String s5 = "record.setId(GuidUtils.getGUID());";
		String s6 = "validate(record);";
		String s7 = "record.setCreatetime(DateUtils.getLongDate(new Date()));";
		String ss2 = "}";
		String s8 = "return " + StringTools.lowerFirstChar(table.getMapperName()) + "." 
				+ XmlMapperKeys.INSERT_SELECTIVE_BATCH_SQL + "(list);";
		body.add(s1);
		body.add(s2);
		body.add(ss1);
		body.add(s3);
		body.add(s4);
		body.add(s5);
		body.add(s6);
		body.add(s7);
		body.add(ss2);
		body.add(s8);
		return body;
	}


}