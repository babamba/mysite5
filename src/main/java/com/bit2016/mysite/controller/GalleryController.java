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
		System.out.println("@@@@@@@@@@@@@@@@list :" + list);
		return "gallery/index";
	}
	
	@RequestMapping("/form")
	public String form(){
		return "gallery/form";
	}
	
	@RequestMapping("/view")
	public String view(
			@RequestParam(value="no", required=true, defaultValue="0")Long no
			){
		System.out.println("1");
		
		return"";
		
	}
	
	
	@RequestMapping( value="/upload", method=RequestMethod.POST )
	public String upload(
		@ModelAttribute GalleryVo galleryVo,
		@RequestParam( "file" ) MultipartFile multipartFile ){
		
		galleryservice.restore( galleryVo, multipartFile );
		
		return "redirect:/gallery";
	}
	
	
}