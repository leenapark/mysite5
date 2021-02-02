package com.javaex.dao;

import java.util.List;

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
	public List<RboardVo> getList() {
		System.out.println("Dao getList");
		
		List<RboardVo> rbList = sqlSession.selectList("rboard.selectList");
		
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
	
	// 답글 추가 필드 수정
	public void reUpdate(int no) {
		System.out.println("Dao hitUp: " + no);
		sqlSession.update("rboard.reUpdate", no);
		
	}
	
}
