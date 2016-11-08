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
	
	public List<GuestBookVo> getList(int page){
		return GuestBookDao.getList(page);
	}
	
	public boolean delete(GuestBookVo vo){
		int result = GuestBookDao.delete(vo);
		return (result == 1);
	}
	
	public GuestBookVo write(GuestBookVo vo, boolean fetch){
		GuestBookVo guestbookvo = null;

		Long no = GuestBookDao.insert(guestbookvo);
		
		if(fetch){
			guestbookvo = GuestBookDao.get(no);
		}
		System.out.println(no);
		return guestbookvo;
	}
	
	public GuestBookVo write(GuestBookVo vo){
		Long no = GuestBookDao.insert(vo);
		return vo;
	}
	
	
	
}
