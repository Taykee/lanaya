package cn.lanaya.generator.config;

public class GeneratedKey {

    private String column;

    private String configuredSqlStatement;

    private String runtimeSqlStatement;

    private boolean isIdentity;

    private String type;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getConfiguredSqlStatement() {
		return configuredSqlStatement;
	}

	public void setConfiguredSqlStatement(String configuredSqlStatement) {
		this.configuredSqlStatement = configuredSqlStatement;
	}

	public String getRuntimeSqlStatement() {
		return runtimeSqlStatement;
	}

	public void setRuntimeSqlStatement(String runtimeSqlStatement) {
		this.runtimeSqlStatement = runtimeSqlStatement;
	}

	public boolean isIdentity() {
		return isIdentity;
	}

	public void setIdentity(boolean isIdentity) {
		this.isIdentity = isIdentity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMyBatis3Order() {
		// TODO Auto-generated method stub
		return null;
	}

    
}
