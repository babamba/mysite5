package com.bit2016.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.exception.UserDaoException;
import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	//회원가입
	@Autowired
	private UserService userService;
	

	@RequestMapping("/join")
	public String join( @ModelAttribute UserVo vo){

		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinform")
	public String joinForm(){
		return "user/joinform";
	}
	

	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	
	
	//로그인
	@RequestMapping("/login")
	public String login(
		@RequestParam(value="email", required=true, defaultValue="") String email,
		@RequestParam(value="password", required=true, defaultValue="") String password,
		HttpSession session){
		
		
		UserVo userVo = userService.login(email, password);
		if(userVo == null){
			return "redirect:/user/loginform?result=fail";
		}
		//인증 성공
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}
	
	@RequestMapping("/loginform")
	public String loginForm(){
		return "user/loginform";
	}
	
	@RequestMapping("/logout")
	public String loginout(HttpSession session){
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		
		System.out.println(authUser);
		
		vo.setNo(authUser.getNo());
		authUser.setName(vo.getName());
		
		userService.updateUser(vo);
		return "redirect:/user/modifyform?update=success";
	}
	
	@RequestMapping("/modifyform")
		public String modifyForm(HttpSession session, Model model){
		//public String modifyForm(@AuthUser UserVo vo , Model model){
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		//접근제한
		
		if(authUser==null){
			return "redirect:/user/loginform";
		}
		
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}
	
/*	@ExceptionHandler( UserDaoException.class )
	public String handleUserDaoException() {
		// 1.logging (파일에 내용저장)
		// 2. 사용자에게 안내페이지
		return "error/500";
	}*/
	
	
}
