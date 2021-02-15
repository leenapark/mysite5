package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 게시판 리스트 (검색 기능 포함)
	public List<BoardVo> getBoardList2(String keyword) {
		System.out.println("service list: " + keyword);
		
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		
		
		return boardList;
	}
	
	// 게시판 리스트 (검색 기능 + 페이징 포함)
	public Map<String, Object> getBoardList3(String keyword, int crtPage) {
		System.out.println("service list: " + keyword);
		
		//crtPage --> 시작 번호, 끝번호 1 --> 1, 10 2--> 11, 20 	3 --> 21, 30
		
		/********************************/
		/**********리스트 구하기*************/
		/********************************/
		
		//페이지당 글갯수
		int listCnt = 10;
		
		//현재 페이지
		//crtPage;
		
		//시작글 번호 startRNum
		/*
		1 --> crtPage
		2 --> 11
		3 --> 21
		
		start 번호 구한 식
		(crtPage-1) * listCnt
		0*10 --> 0 + 1
		1*10 --> 10 + 1
		2*10 --> 20 + 1
		3*10 --> 30 + 1		
		*/
		// 잘못된 페이지 넘버가 들어올 경우 1페이지로 뜨는 것
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		
		int startRnum = (crtPage-1) * listCnt + 1;
		
		// 끝글 번호 endRNum
		int endRnum = (startRnum + listCnt) - 1;
		
		System.out.println("keyword : " + keyword);
		System.out.println("startRnum : " + startRnum);
		System.out.println("endRnum : " + endRnum);
		
		List<BoardVo> boardList = boardDao.selectList3(keyword, startRnum, endRnum);
		
		
		/********************************/
		/***********페이징 계산*************/
		/*******************************/
		// 페이지당 버튼 개수
		int pageBtnCount = 5;
		
		// 전체 글 갯수 구하기
		int totalCount = boardDao.selectTotalcnt(keyword);
		
		/*
		ex) pageNum : 1 --> 1 ~ 5
			pageNum : 2 --> 1 ~ 5
			pageNum : 6 --> 6 ~ 10
			pageNum : 8 --> 6 ~ 10
		*/
		// 끝 버튼 번호
		/*
		int endPageBtnNo = 1*5 --> 5
		int endPageBtnNo = 2*5 --> 10
		*/
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;	// --> 1/5 = 0.2
		
		
		// 시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		
		// 다음 버튼 boolean
		boolean next;
		
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		} else {
			next = false;
			endPageBtnNo = (int)Math.ceil( totalCount / (double)listCnt );
		}
		// 이전 버튼 boolean
		boolean prev;
		if(startPageBtnNo != 1) {
			prev = true;
		} else {
			prev = false;
		}
		
		// boardList, prev, startPageBtnNo, endPageBtnNo, next --> jsp map으로 전달
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
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
		
		/*
		 * for(int i=1; i<=1234; i++) { boardVo.setTitle(i + "번째 글 제목 입니다");
		 * boardVo.setContent(i + "번째 글 내용 입니다"); }
		 */
		
		return boardDao.postInsert(boardVo);
		
	}
	
	// 게시글 삭제
	public int postDelete(int no) {
		System.out.println("service postDelete: " + no);
		
		return boardDao.delete(no);
	}
	
}
