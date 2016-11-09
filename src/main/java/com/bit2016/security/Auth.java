package com.bit2016.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)	//메소드 파라미터 타입
@Retention(RetentionPolicy.RUNTIME)  //런타임때까지 
public @interface Auth {
	String role() default("user");
//	boolean required() default false;

	
	
}
