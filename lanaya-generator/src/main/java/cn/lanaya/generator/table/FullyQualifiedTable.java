package cn.lanaya.generator.table;

import lombok.Data;

@Data
public class FullyQualifiedTable {
	private String introspectedCatalog;
	private String introspectedSchema;
	private String introspectedTableName;
	private String runtimeCatalog;
	private String runtimeSchema;
	private String runtimeTableName;
	// 实体bean
	private String domainObjectName;
	// bean 包路径
	private String domainObjectSubPackage;
	// VO
	private String viewObjectName;
	// VO路径
	private String viewObjectSubPackage;
	private String mapperName;
	private String mapperSubPackage;
	private String serviceName;
	private String serviceSubPackage;
	private String serviceImplName;
	private String serviceImplSubPackage;
	private String controllerName;
	private String controllerSubPackage;
	// 表别名
	private String alias;
	private boolean ignoreQualifiersAtRuntime;
	private String beginningDelimiter;
	private String endingDelimiter;

	public String getFullServiceName() {
		return serviceSubPackage + "." + serviceName;
	}

	public String getFullControllerName() {
		return controllerSubPackage + "." + controllerName;
	}

	public String getFullDomainObjectName() {
		return domainObjectSubPackage + "." + domainObjectName;
	}

	public String getFullViewObjectName() {
		return viewObjectSubPackage + "." + viewObjectName;
	}

	public String getFullMapperName() {
		return mapperSubPackage + "." + mapperName;
	}
	
	public String getFullServiceImplName() {
		return serviceImplSubPackage + "." + serviceImplName;
	}
}
