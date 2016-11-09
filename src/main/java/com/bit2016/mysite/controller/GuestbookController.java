package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.service.GuestBookService;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.security.Auth;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("")
	public String list(Model model){
		List<GuestBookVo> list = guestBookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@Auth
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") Long no, Model model){
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo){
		
		guestBookService.delete(vo);
		
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestBookVo vo){
		guestBookService.write(vo);
		return "redirect:/guestbook";
		
	}
	
	@RequestMapping("/ajax")
	public String ajax(){
		return "guestbook/list-ajax";
	}
	
}
