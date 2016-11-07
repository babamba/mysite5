package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.vo.GuestBookVo;


@Service
public class GuestBookService {

	@Autowired
	private GuestbookDao GuestBookDao;
	
	public List<GuestBookVo> getList(){
		return GuestBookDao.getList();
	}
	
	public void delete(GuestBookVo vo){
		GuestBookDao.delete(vo);
	}
	
	public void insert(GuestBookVo vo){
		GuestBookDao.insert(vo);
	}
	
}
