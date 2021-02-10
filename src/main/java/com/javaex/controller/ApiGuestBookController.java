package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// ajax - json 형식으로 데이터가 올 때
	@ResponseBody
	@RequestMapping(value="/write2", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestVo write2(@RequestBody GuestVo guestVo) {
		System.out.println("apicontroller write2: " + guestVo);
		
		// 입력된 Vo 값을 저장시키고 저장 시킨 vo를 받아서 넘김		
		return guestService.writeResultVo(guestVo);
	}
	
	
	// 방명록 삭제(ajax)
	@ResponseBody
	@RequestMapping(value="/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestVo guestVo) {
		System.out.println("apicontroller remove" + guestVo);

		int count = guestService.delete(guestVo);
		System.out.println(count);
		
		return count;
	}
	
	
}
