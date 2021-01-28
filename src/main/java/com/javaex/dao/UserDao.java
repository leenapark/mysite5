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
	
	// 수정할 정보 보내기
	public UserVo selectUser(int no) {
		System.out.println("userDao select: " + no);
		
		UserVo vo = sqlSession.selectOne("user.getUser", no);
		
		return vo;
	}
	
	// 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("userDao update: " + userVo);
		
		return sqlSession.update("user.update", userVo);
	}
	
}
