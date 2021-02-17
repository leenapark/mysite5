package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	
	// 갤러리 리스트 출력: 저장된 이미지 불러오기
	public List<GalleryVo> imageList() {
		System.out.println("GalleryService imageList");
		
		return galleryDao.galleryList();		
	}
	
	public String restore(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("GalleryService: " + file.getOriginalFilename());
		
		// db 저장할 정보 수집
		String saveDir = "C:\\javaStudy\\upload";
		
		// 오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		// 파일 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName: " + exName);
		
		
		// 서버 저장 파일 이름		숫자					+		알 수 없는 문자열
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString() + exName;
		System.out.println("saveName: " + saveName);
		
		// 서버 파일 패스 --> 저장 경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		// 파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);
		
		
		// 서버 하드 디스크 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// db 저장
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("", value);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		
		System.out.println("GalleryService: " + galleryVo);
		
		galleryDao.insert(galleryVo);
		
		return saveName;
	}
	
	// 데이터 하나 가져오기
	public GalleryVo selectOne(String savaName) {
		System.out.println("GalleryService: " + savaName);
		
		return galleryDao.selectOne(savaName);		
	}
	
	// 데이터 삭제
	public int remove(int no) {
		System.out.println("GalleryService remove");
		
		return galleryDao.delete(no);
	}
	
}
