package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/api/guestbook")
public class ApiGuestBookController {

	@Autowired
	private GuestService guestService;

	// ajax 방명록 리스트
	@ResponseBody
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestVo> list() {
		System.out.println("apicontroller list");
		
		return guestService.getList();
	}
	
	
	// 방명록 작성
	@ResponseBody
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestVo write(@ModelAttribute GuestVo guestVo) {
		System.out.println("apicontroller write: " + guestVo);
		
		// 입력된 Vo 값을 저장시키고 저장 시킨 vo를 받아서 넘김		
		return guestService.writeResultVo(guestVo);
	}
	
	
}
