package cn.lanaya.common.annotation;

public @interface FieldProperty {

	String comment() default "";
	
	boolean required() default false;
	
}
