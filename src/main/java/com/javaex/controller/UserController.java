package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	//*필드
	@Autowired
	private UserDao userDao;
	
	//*생성자
	//*메소드 g/s
	
	
	
	// 회원가입 폼
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	// 회원가입
	@RequestMapping(value="/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		// 어트리뷰트 확인
		System.out.println("controller userVo: " + userVo.toString());
		
		userDao.insert(userVo);
		
		return "user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("login");
		// 어트리뷰트 확인
		System.out.println("controller userVo: " + userVo.toString());
		
		UserVo authUser = userDao.selectUser(userVo);
		if(authUser == null) {
			System.out.println("로그인 실패");
			
			return "redirect:/user/loginForm?result=fail";
			
		} else {
			System.out.println("로그인 성공");

			session.setAttribute("authUser", authUser);
			
			return "redirect:/";
		}
		
	}

}
