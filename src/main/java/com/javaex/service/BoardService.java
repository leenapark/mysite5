package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	// 필드
	@Autowired
	private BoardDao boardDao;
	
	
	// 게시판 리스트
	public List<BoardVo> listSearch(String str) {
		System.out.println("service list: " + str);
		
		List<BoardVo> boardList = boardDao.getList(str);
		
		return boardList;
	}
	
	// 게시판 읽기
	public BoardVo postread(int no, UserVo authUser) {
		System.out.println("service read: " + no + ", " + authUser);
		BoardVo boardVo = boardDao.getPost(no);
		
		
		if(authUser==null||authUser.getNo() != boardVo.getUserNo()) {
			
			
			boardDao.hitUp(no);
			int hit = boardVo.getHit()+1;
			boardVo.setHit(hit);
			
			return boardVo;
		} else {
			
			return boardVo;
		}
		
	}
	
	// 게시글 수정 폼
	public BoardVo modifyPost(int no) {
		System.out.println("service modifyPost: " + no);
		
		return boardDao.getPost(no);		
	}
	
	// 게시글 수정
	public int modify(BoardVo boardVo) {
		System.out.println("service modify: " + boardVo.toString());
		
		return boardDao.update(boardVo);
	}
	
	// 게시글 등록(저장)
	public int postInsert(BoardVo boardVo) {
		System.out.println("service postInsert: " + boardVo.toString());
		
		return boardDao.postInsert(boardVo);
		
	}
	
	// 게시글 삭제
	public int postDelete(int no) {
		System.out.println("service postDelete: " + no);
		
		return boardDao.delete(no);
	}
	
}
