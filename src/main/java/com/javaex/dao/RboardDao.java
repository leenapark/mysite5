package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;
	
	
	
	// 게시글 전체 가져오기
	public List<RboardVo> getList(String str) {
		System.out.println("Dao getList");
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("str", str);
		
		System.out.println("Dao getList: " + boardMap);
		
		List<RboardVo> rbList = sqlSession.selectList("rboard.selectList", boardMap);
		
		return rbList;
	}
	
	// 게시글 등록(저장)
	public int insert(RboardVo rboardVo) {
		System.out.println("Dao insert: " + rboardVo.toString());
		
		return sqlSession.insert("rboard.insert", rboardVo);
	}
	
	// 게시글 읽기
	public RboardVo getPost(int no) {
		System.out.println("Dao getpost: " + no);
		
		RboardVo rboardVo = sqlSession.selectOne("rboard.selectPost", no);
		return rboardVo;
	}
	
	// 조회수 증가
	public int hitUp(int no) {
		System.out.println("Dao hitUp: " + no);
		
		return sqlSession.update("rboard.hitUp", no);
	}
	
	// 답글 등록(저장)
	public int reInsert(RboardVo rboardVo) {
		System.out.println("Dao reInsert: " + rboardVo.toString());
		
		// update로 먼저 등록되어 있는 글의 계층 필드 값을 수정해준다
		sqlSession.update("rboard.reUpdate", rboardVo);
		
		return sqlSession.insert("rboard.reinsert", rboardVo);
	}
	
	// 게시글 삭제
	public int delete(int no) {
		System.out.println("Dao delete");
		
		return sqlSession.delete("rboard.delete", no);
	}
	
	// 게시글 수정
	public int update(RboardVo rboardVo) {
		System.out.println("Dao update: " + rboardVo);
		
		return sqlSession.update("rboard.update", rboardVo);
	}
	
	
}
