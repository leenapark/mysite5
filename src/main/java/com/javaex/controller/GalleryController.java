package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	
	@Autowired
	private GalleryService galleryService;
	
	// 갤러리 메인 화면
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController list");
		
		List<GalleryVo> vo = galleryService.imageList();
		model.addAttribute("galleryList", vo);
		
		return "/gallery/list";
	}

	// 파일 업로드
	@RequestMapping(value="/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileUploadController upload");
		
		galleryService.restore(file);
		
		return "";
	}
	
}
