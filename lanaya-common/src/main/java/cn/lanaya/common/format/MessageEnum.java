package cn.lanaya.common.format;

public enum MessageEnum {
	SUCCESS(0, "success"),
	UNKNOW_ERROR(101, "系统错误，请联系管理员"),
	RPC_ERROR(102, "远程调用错误")
	;
	
	private final int code;
	private final String msg;
	
	private MessageEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}
