package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	//*필드
	
	@Autowired
	private UserService userService;
	
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
		
		int count = userService.join(userVo);
		
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
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser == null) {
			System.out.println("로그인 실패");
			
			return "redirect:/user/loginForm?result=fail";
			
		} else {
			System.out.println("로그인 성공");

			session.setAttribute("authUser", authUser);
			
			return "redirect:/";
		}
		
	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	// 회원정보 수정 폼
	@RequestMapping(value="/mForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String mForm(HttpSession session, Model model) {
		System.out.println("mForm");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		UserVo userVo = userService.mForm(authUser.getNo());
		
		// 정보 확인 겸 진행 확인
		System.out.println("ahthUser: " + userVo);
		
		model.addAttribute("ahthUser", userVo);
		
		return "user/modifyForm";
	}
	
	// 회원정보 수정
	@RequestMapping(value="/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("modify");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		int no = authUser.getNo();
		
		userVo.setNo(no);
		
		int count = userService.modify(userVo);
		
		authUser.setName(userVo.getName());
		
		
		return "redirect:/";
		
		
	}
	
	
}
