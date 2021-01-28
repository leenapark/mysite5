package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 방명록 리스트
	public List<GuestVo> getList(){
		System.out.println("Dao getList");
		
		List<GuestVo> addList = sqlSession.selectList("guest.selectList");
		
		return addList;
		
	}
	
	// 방명록 생성
	public int insert(GuestVo guestVo) {
		System.out.println("Dao insert: " + guestVo.toString());
		
		int count = sqlSession.insert("guest.insert", guestVo);
		
		return count;
	}
	
	// 방명록 삭제
	public int delete(GuestVo guestVo) {
		System.out.println("Dao delete: " + guestVo.toString());
		
		int count = sqlSession.delete("guest.delete", guestVo);
		
		return count;
	}
	
}
