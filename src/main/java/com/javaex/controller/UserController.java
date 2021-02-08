package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// 회원가입 - id check
	/*
	@RequestMapping(value="/idcheck", method = {RequestMethod.GET, RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id) {
		System.out.println("/user/idcheck: " + id);
		
		UserVo userVo = userService.idcheck(id);
		System.out.println("controller: " + userVo);
		
		String result = "";
		
		if(userVo == null) {
			result = "can";
		} else {
			result = "cant";			
		}
		
		//redirect:/user/joinForm?result="+result=can&name=황일영&password=1234&gender=male";
		return "redirect:/user/joinForm?result="+result;
	}
	*/
	@ResponseBody
	@RequestMapping(value="/idcheck", method = {RequestMethod.GET, RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id, @RequestParam("password") String pw) {
		System.out.println("/user/idcheck: " + id + ", " + pw);
		
		String result = userService.idcheck(id);
		System.out.println("controller: " + result);
	
		//return "redirect:/user/joinForm?result="+result;
		return result;	//데이터만 리턴 해줄 때 @ResponseBody 사용 --> 데이터만 response의 body 영역에 데이터만 보낸다
	}
	
}
