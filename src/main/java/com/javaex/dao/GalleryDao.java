package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;

	// 전체 리스트
	public List<GalleryVo> galleryList() {
		System.out.println("GalleryDao galleryList");
		
		List<GalleryVo> vo = sqlSession.selectList("gallery.selectList");
		System.out.println("GalleryDao: " + vo);
		
		return vo;
	}
	
	// 갤러리 등록(db 저장)
	public int insert(GalleryVo galleryVo) {
		System.out.println("GalleryDao insert: " + galleryVo);
		
		return sqlSession.insert("gallery.insert", galleryVo);
		
	}
	
	// 한개 선택하기
	public GalleryVo selectOne(String saveName) {
		System.out.println("GalleryDao selectOne: " + saveName);
		
		return sqlSession.selectOne("gallery.selectOne", saveName);
	}
	
	// 데이터 삭제
	public int delete(int no) {
		System.out.println("GalleryDao delete");
		
		return sqlSession.delete("gallery.delete", no);		
	}
	
}
