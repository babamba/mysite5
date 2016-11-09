package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2016.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object handler /* Handler Method */) 
			throws Exception {
		
		//1. handler 종류
		if( handler instanceof HandlerMethod == false){
			return true;
		}
		
		//2. @Auth 가 붙어 있는지 없는지.
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		if(auth == null){ //null이면 접근 제어를 안했다는 것  null이면 접근제어가 필요없는 핸들러 
			return true;
		}
		
		//3. 접근제어
		HttpSession session = request.getSession();
		if(session == null){ //세션없을때 로그인폼으로 보내고
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		//4. 인증된 사용자 (4번까지 오면 인증이 확인된것)
		
		
		return true;
	}

}	
