package com.bit2016.mysite.controller.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.dto.JSONResult;
import com.bit2016.dto.SingletonClass;
import com.bit2016.mysite.service.UserService;


@Controller("userAPIController")
@RequestMapping( "/user/api" )
public class UserController {
   
   @Autowired
   private UserService userService;
   
   @ResponseBody
   @RequestMapping( "/checkemail" )
   public Object checkEmail(@RequestParam( value="email", required=true, defaultValue="" ) String email ) {
	   
	  
	  Calendar c = Calendar.getInstance();
	  
	  SingletonClass sc = SingletonClass.getInstance();
	  
	  boolean result = userService.emailExists(email);
	  JSONResult jsonResult = null;
      return JSONResult.success(result ? "exist" : "not exist");
    
   }
}