package com.bit2016.mysite.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.service.GalleryService;
import com.bit2016.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	
	@Autowired
	private GalleryService galleryservice;

	@RequestMapping( "" )
	public String index( Model model ){
		
		List<GalleryVo> list= galleryservice.getImageList();
		
		model.addAttribute( "list", list );
		model.addAttribute( "URL", GalleryService.URL );
		
		return "gallery/index";
	}
	
	@RequestMapping("/form")
	public String form(){
		return "gallery/form";
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String upload(
			@ModelAttribute GalleryVo vo,
			@RequestParam("file")MultipartFile file
			){
		
		galleryservice.restore(vo, file);
		
		return "redirect:/gallery";
	}
	
	
}