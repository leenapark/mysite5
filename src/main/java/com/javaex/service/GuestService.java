package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;
	
	
	// 방명록 리스트
	public List<GuestVo> getList(){
		List<GuestVo> addList = guestDao.getList();
		
		return addList;
	}
	
	// 방명록 생성
	public int insert(GuestVo guestVo) {
		System.out.println("service: " + guestVo.toString());
		
		int count = guestDao.insert(guestVo);
		
		return count;
	}
	
	// 방명록 삭제
	public int delete(GuestVo guestVo) {
		System.out.println("service: " + guestVo.toString());
		
		int count = guestDao.delete(guestVo);
		
		return count;
	}

}
