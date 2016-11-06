package com.bit2016.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.repository.BoardVo;
import com.bit2016.mysite.repository.GuestBookVo;
import com.bit2016.mysite.repository.UserVo;
import com.bit2016.mysite.service.BoardService;
import com.bit2016.mysite.service.GuestBookService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String List(
		@RequestParam(value="p", required=true, defaultValue="1") Integer page,
		@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
		Model model){
		
		Map<String, Object> map = boardService.getList(page, keyword);
		model.addAttribute("map",map);
		
		return "board/list"; 
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(){
		return "board/write";
	}
	
	@RequestMapping(value="/view")
		public String view(){
		return "board/view";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	
	public String delete(
			@RequestParam(value="p", required=true)Long page,
			@RequestParam(value="no", required=true)Long no,
			Model model){
		
		boardService.delete(page,no);
		return "redirect:/board";
		
	}
	
	
	@RequestMapping( value="/write", method=RequestMethod.POST )
	public String write( HttpSession session, @ModelAttribute BoardVo vo ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		// 권한 체크
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
			
		vo.setUserNo( authUser.getNo() );
		boardService.write( vo );
		return "redirect:/board";
	}
	
}
