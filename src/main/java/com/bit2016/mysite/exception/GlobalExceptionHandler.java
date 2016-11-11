package com.bit2016.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.bit2016.mysite.controller.GuestbookController;


@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOG = LogFactory.getLog(GuestbookController.class );
/*	@ExceptionHandler(Exception.class)
	public String handlerException(
			HttpServletRequest request, 
			Exception e){
// 1. 로깅
		System.out.println("exception : " + e);
		
//2.ajax 요청여부 판단
/*		if("application/json".equals(request.getContentType())){
			
		
		
		return "error/exception";
	}*/
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(
			HttpServletRequest request, 
			Exception e){
		
		
		// 1. 로깅
				StringWriter errors = new StringWriter();
				e.printStackTrace( new PrintWriter( errors ) );
				LOG.error( errors.toString());	
		
		
		
		
		//2.ajax 요청여부 판단
/*		if("application/json".equals(request.getContentType())){
			
		}*/
		
		ModelAndView mav = new ModelAndView();
//		mav.addObject("exceptionMessage", e.getMessage());
		mav.setViewName("error/exception");
		return mav;
	}
	
	
	
}
