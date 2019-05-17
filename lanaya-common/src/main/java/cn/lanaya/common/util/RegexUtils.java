package cn.lanaya.common.util;

/**
 * @author Taykee
 *
 */
public final class RegexUtils {
	
	/**
	 * 匹配公司邮箱的正则表达式
	 */
	public static final String MAIL_REGEX = 
			"[1-9a-zA-Z]\\w{2,20}@[0-9a-zA-Z][0-9a-zA-Z-]+(\\.[0-9a-zA-Z][0-9a-zA-Z-]+)+";

	/**
	 * 按正则表达式验证电话号码
	 */
	public static final String PHONE_REGEX = "1[34578]\\d{9}";
	
	/**
	 * 将手机号码中间4位替换为*，用法如下str.repalceAll(PHONE_REGEX_STAR, "$1****$2");
	 */
	public static final String PHONE_REGEX_STAR = "([1][34578]\\d)\\d{4}(\\d{4})";
	
	
}
