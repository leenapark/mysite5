package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 회원가입 --> 회원 정보 저장
	public int insert(UserVo userVo) {
		System.out.println("userDao insert: " + userVo.toString());
		
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
	}
	
	
	// 로그인 --> 회원 정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("userDao select: " + userVo.toString());
		
		UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
				
		return vo;
	}
	
}
