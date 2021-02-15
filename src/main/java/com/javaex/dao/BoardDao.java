package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;
	
	
	// 게시판 리스트
	public List<BoardVo> getList(String str) {
		System.out.println("Dao getList: " + str);
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("str", str);
		
		System.out.println("Dao getList: " + boardMap);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList", boardMap);
		
		
		return boardList;
	}
	
	// 게시판 리스트 (검색 기능 포함)
	public List<BoardVo> selectList2(String keyword) {
		System.out.println("Dao selectList2: " + keyword);
		
		List<BoardVo> boardVo = sqlSession.selectList("board.selectList2", keyword);
		
		return boardVo;
	}
	
	// 게시판 리스트 (검색 기능 + 페이징 포함)
	public List<BoardVo> selectList3(String keyword, int startRnum, int endRnum) {
		System.out.println("Dao selectList3");
		
		// key word, startRnum, endRnum
		// 1회성이기 때문에 Map으로 묶어준다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println("map: " + map);
		
		List<BoardVo> boardVo = sqlSession.selectList("board.selectList3", map);
		
		return boardVo;
	}
	
	// 페이징 전체 페이지 갯수 구하기
	public int selectTotalcnt(String keyword) {
		System.out.println("Dao selectTotalcnt");
		
		return sqlSession.selectOne("board.selectTotalcnt", keyword);
	}
	
	
	// 조회수 증가
	public int hitUp(int no) {
		System.out.println("dao hit");
		
		
		return sqlSession.update("board.hitUp", no);
	}
	
	// 선택한 게시물 읽기
	public BoardVo getPost(int no) {
		System.out.println("dao read: " + no);
		
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard", no);
		System.out.println("dao read: " + boardVo.toString());
		
		return boardVo;
	}
	
	
	// 게시글 수정
	public int update(BoardVo boardVo) {
		System.out.println("dao update: " + boardVo.toString());
		
		return sqlSession.update("board.update", boardVo);
	}
	
	// 게시글 등록(저장)
	public int postInsert(BoardVo boardVo) {
		System.out.println("dao boardInsert: " + boardVo.toString());
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	// 게시글 삭제
	public int delete(int no) {
		System.out.println("dao boardInsert: ");
		
		return sqlSession.delete("board.delete", no);
	}
	
	
}
