package com.javaex.dao;

import java.util.List;

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
	public List<BoardVo> getList() {
		System.out.println("Dao getList");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		return boardList;
	}
	
	// 선택한 게시물 읽기
	public BoardVo getPost(int no) {
		System.out.println("dao read: " + no);
		
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard", no);
		System.out.println("dao read: " + boardVo.toString());
		
		return boardVo;
	}
	
	// 조회수 증가
	public int hitUp(int no) {
		System.out.println("dao hit");
		
		return sqlSession.update("board.hitUp", no);
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
