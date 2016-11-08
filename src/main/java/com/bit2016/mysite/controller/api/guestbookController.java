package com.bit2016.mysite.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.service.GuestBookService;
import com.bit2016.mysite.vo.GuestBookVo;

@Controller("/guestbookAPIController")
@RequestMapping("/guestbook/api")
public class guestbookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(
			@RequestParam(value="p", required=true, defaultValue="1") Integer page){
		
			List<GuestBookVo> list = guestbookService.getList(page);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", "success");
			map.put("data", list);

			return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Map<String, Object> add(@ModelAttribute GuestBookVo vo){
		GuestBookVo guestBookVo = guestbookService.write(vo, true);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", guestBookVo);
		System.out.println(map);
		return map;
		
	}
	
/*	public Object delete(
			@ModelAttribute GuestBookVo vo){
	
		boolean result = guestbookService.delete(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "succuess");
		map.put("data",	"result -1");

		return map;
	}*/
	
	
	

}
