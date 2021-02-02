package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Service
public class RboardService {
	
	// 필드
	@Autowired
	private RboardDao rboardDao;
	
	//메소드
	
	// 전체 리스트 가져오기
	public List<RboardVo> getList() {
		System.out.println("service list");
		
		List<RboardVo> rbList = rboardDao.getList();
		
		return rbList;
	}
	
	// 게시글 등록
	public int insert(RboardVo rboardVo) {
		System.out.println("service insert: " + rboardVo.toString());
		
		return rboardDao.insert(rboardVo);
	}
	
	// 게시글 보기
	public RboardVo postRead(UserVo authUser, int no) {
		System.out.println("service postRead: " + authUser + ", " + no);
		
		RboardVo rboardVo = rboardDao.getPost(no);
		
		if(authUser==null||authUser.getNo() != rboardVo.getUserNo()) {
			
			rboardDao.hitUp(no);
			int hit = rboardVo.getHit()+1;
			rboardVo.setHit(hit);
			
			return rboardVo;
		} else {
			
			return rboardVo;
		}
	}
	
	public void reWrite(RboardVo rboardVo) {
		System.out.println("service reWrite: " + rboardVo.toString());
		
		rboardDao.insert(rboardVo);
		
		
	}
}
