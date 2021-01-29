package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	// 비지니스 로직
	// 회원가입
	public int join(UserVo userVo) {
		System.out.println("userService join");
				
		return userDao.insert(userVo);	
	}
	
	// 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService login");
		
		return userDao.selectUser(userVo);
	}
	
	// 정보 수정 폼
	public UserVo mForm(int no) {
		System.out.println("userService mForm");
		
		return userDao.selectUser(no);
		
	}
	
	// 정보 수정
	public int modify(UserVo userVo) {
		System.out.println("userService modify");
		
		return userDao.userUpdate(userVo);
	}


}
