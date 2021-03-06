package com.bit2016.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.bit2016.mysite.vo.GuestBookVo;


@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	public int delete(GuestBookVo vo) {
		System.out.println("delete dao");
		return sqlSession.delete("guestbook.delete", vo);
	}

	public Long insert(GuestBookVo vo ) {
		sqlSession.insert("guestbook.insert", vo);
		System.out.println(vo);
		return vo.getNo();
	}
	
	
	public List<GuestBookVo> getList() {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
		
		stopWatch.stop();
		System.out.println(
				"@@@@@@@@@@@@@@[executionTime][GuestbookDao.getList] : " + stopWatch.getTotalTimeMillis() + "millis");
		return list;
	}
	
	public List<GuestBookVo> getList(int page) {
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getListByPage", page);
		return list;
	}
	
	
	public GuestBookVo get(Long no){
		return sqlSession.selectOne("guestbook.getNo", no);
		
	}
	
}
