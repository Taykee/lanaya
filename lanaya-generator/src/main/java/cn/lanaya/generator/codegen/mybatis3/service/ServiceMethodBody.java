package cn.lanaya.generator.codegen.mybatis3.service;

import java.util.List;

import cn.lanaya.generator.table.FullyQualifiedTable;

public interface ServiceMethodBody {

	List<String> getSelectByClause(FullyQualifiedTable table);

	List<String> getSelectByPrimaryKey(FullyQualifiedTable table);

	List<String> getDeleteByPrimaryKey(FullyQualifiedTable table);

	List<String> getDeleteByClause(FullyQualifiedTable table);

	List<String> getInsert(FullyQualifiedTable table);

	List<String> getInsertSelective(FullyQualifiedTable table);

	List<String> getCountByClause(FullyQualifiedTable table);

	List<String> getUpdateByClauseSelective(FullyQualifiedTable table);

	List<String> getUpdateByPrimaryKeySelective(FullyQualifiedTable table);

	List<String> getUpdateByPrimaryKey(FullyQualifiedTable table);
	
	List<String> getPageByClause(FullyQualifiedTable table);
	
	List<String> getDeleteByPrimaryKeyBatch(FullyQualifiedTable table);
	
	List<String> getInsertSelectBatch(FullyQualifiedTable table);

}
