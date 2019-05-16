package cn.lanaya.common.bean;

import java.util.HashMap;
import java.util.Map;

public class PageQO {
	private Integer num;
	
	private Integer size;
	
	private Map<String, Object> params = new HashMap<>();

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Map<String, Object> getParams(){
		return params;
	}

	public void addParam(String key, Object obj){
		params.put(key, obj);
	}
}
