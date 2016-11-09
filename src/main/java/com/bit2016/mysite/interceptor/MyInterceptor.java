package com.bit2016.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	@Override //이게제일중요 해당되는 경로에 호출하기전에 먼저 불려지는 메소드
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("myInterCeptor.preHandle called");
		return true;
	}
	
	@Override  //응답을 준다음에 뷰랜더링까지 다 끝나고 응답아주끝전에 
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("myInterCeptor.afterCompletion called");

	}

	@Override //
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("myInterCeptor.postHandle called");

	}

	

}
