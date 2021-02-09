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
	
	public GuestVo writeResultVo(GuestVo guestVo) {
		// 방명록 저장
		
		System.out.println("guestService 실행 전: " + guestVo);
		guestDao.insertSelectKey(guestVo);
		System.out.println("guestService 실행 후: " + guestVo);

		//no 값을 알 수 있음
		
		// 방금 저장한 글 조회하기 --> 글번호 어떻게 알 것인가?
		// --> 가장 최근 글을 가져옴 --> 다른 사람 글이 나올 수 있음 --> ???
		// 내가 등록한 글 번호를 가져올 수 있음
		int no = guestVo.getNo();
		
		// 선택한 글 1개 가져오기
		return guestDao.selectOne(no);		
	}

}
