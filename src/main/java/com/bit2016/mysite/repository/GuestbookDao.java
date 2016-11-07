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

import com.bit2016.mysite.vo.GuestBookVo;


@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" + e);
		}
		return conn;
	}
	
	public void delete(GuestBookVo vo) {
		sqlSession.delete("guestbook.delete", vo);
	}

	public void insert(GuestBookVo vo ) {
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
}
