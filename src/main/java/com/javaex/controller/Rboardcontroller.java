package com.javaex.controller;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/rboard")
public class Rboardcontroller {

	// 필드
	@Autowired
	private RboardService rService;
	
	// 첫화면
	@RequestMapping(value="/main", method= {RequestMethod.GET, RequestMethod.POST})
	public String rboardlist(Model model) {
		System.out.println("controller rList");
		
		List<RboardVo> rbList = rService.getList();
		System.out.println("rbList: " + rbList.toString());
		
		
		model.addAttribute("rboardList", rbList);
		
		return "/rboard/list";
	}
	
	// 게시글 쓰기
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("writeForm");
		
		
		return "/rboard/writeForm";
	}
	
	// 게시글 등록(저장)
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute RboardVo rboardVo) {
		System.out.println("controller write: " + rboardVo.toString());
		
		int count = rService.insert(rboardVo);
		
		return "redirect:/rboard/main";
	}
	
	// 게시글 읽기
	@RequestMapping(value="/post", method= {RequestMethod.GET, RequestMethod.POST})
	public String postRead(@RequestParam("no") int no, HttpSession session) {
		System.out.println("controller postRead");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		RboardVo rboardVo = rService.postRead(authUser, no);
		
		session.setAttribute("rpostVo", rboardVo);
		
		return "/rboard/read";
	}
	
	// 답글 폼
	@RequestMapping(value="/rewriteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String rewriteForm() {
		System.out.println("controller rewriteForm");
		
		
		
		return "/rboard/rewriteForm";
	}
	
	// 답글 등록(저장)
	@RequestMapping(value="/rewrite", method= {RequestMethod.GET, RequestMethod.POST})
	public String reWrite(@ModelAttribute RboardVo rboardVo) {
		System.out.println("controller rewrite: " + rboardVo.toString());
		
		rService.reWrite(rboardVo);
		
		return "";
	}
	
	
/*	public String rewrite(@RequestParam("no") int groupNo, @RequestParam("userNo") int userNo, @RequestParam("orderNo") int orderNo, @RequestParam("depth") int depth) {
		System.out.println("controller rewrite: " + groupNo + ", " + userNo + ", " + orderNo + ", " + depth);
		
		
		
		return "/rboard/rewriteForm";
	}*/
	
	
}
