package com.javaex.controller;

import java.security.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guest")
public class GuestController {

	//필드
	@Autowired
	private GuestService guestService;
	
	//생성자
	//g/s
	
	// 리스트
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		List<GuestVo> addList = guestService.getList();
		
		model.addAttribute("addList", addList);
		
		return "guestbook/addList";
	}
	
	// 방명록 생성
	@RequestMapping(value="/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestVo guestVo) {
		System.out.println("add");
		System.out.println("guestController guestVo: " + guestVo.toString());
		
		int count = guestService.insert(guestVo);
				
		return "redirect:/guest/list";
	}
	
	// 삭제 폼
	@RequestMapping(value="/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("daleteForm");
		
		
		return "guestbook/deleteForm";
	}

	// 삭제
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("delete");
		System.out.println("guestController deleteForm: " + guestVo.toString());
				
		int count = guestService.delete(guestVo);
		
		if(count==1) {
			System.out.println("삭제 성공");
			
			return "redirect:/guest/list";
		} else {
			System.out.println("삭제 실패");
			
			return "redirect:/guest/deleteForm?no="+guestVo.getNo();
		}
	}
}
