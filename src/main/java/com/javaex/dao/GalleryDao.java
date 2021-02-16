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
	
}
