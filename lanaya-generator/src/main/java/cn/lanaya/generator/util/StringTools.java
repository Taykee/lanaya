package cn.lanaya.generator.util;

import org.apache.commons.lang3.StringUtils;

public class StringTools {

	public static boolean isTrue(String str) {
		return StringUtils.equalsIgnoreCase(str, "true");
	}
	
	public static String getCamelCase(String source, boolean firstCharUpperCase) {
		StringBuffer sb = new StringBuffer();
		boolean next = false;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			switch(c) {
			case '_':
			case '-':
			case '@':
			case '$':
			case '#':
			case ' ':
			case '/':
			case '&':
				if(sb.length() > 0) {
					next = true;
				}
				break;
			default:
				if(next) {
					sb.append(Character.toUpperCase(c));
					next = false;
				}else {
					sb.append(Character.toLowerCase(c));
				}
				break;
			}
		}
		if(firstCharUpperCase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		return sb.toString();
	}
	
	public static String lowerFirstChar(String source) {
		StringBuilder sb = new StringBuilder(source.substring(0, 1).toLowerCase());
		sb.append(source.substring(1));
		return sb.toString();
	}
	
	public static String upperFirstChar(String source) {
		StringBuilder sb = new StringBuilder(source.substring(0, 1).toUpperCase());
		sb.append(source.substring(1));
		return sb.toString();
	}
}
