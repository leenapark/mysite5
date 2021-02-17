package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String upload(@RequestParam("file") MultipartFile file, @ModelAttribute GalleryVo galleryVo) {
		
		
		System.out.println("GalleryController upload: " + file.getOriginalFilename() + ", " + galleryVo);
		
		
		galleryService.restore(file, galleryVo);
		
		return "redirect:/gallery/list";
	}
	
	// 파일 한 개 가져오기
	@ResponseBody
	@RequestMapping(value="/post", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo post(@RequestParam("saveName") String saveName) {
		System.out.println("GalleryController post: " + saveName);
		
		GalleryVo vo = galleryService.selectOne(saveName);
		System.out.println("GalleryController post: " + vo);

		
		return vo;
	}
	
	
	// 파일 삭제
	@ResponseBody
	@RequestMapping(value="/remove", method= {RequestMethod.GET, RequestMethod.POST})
	public int remove(@RequestParam("no") int no) {
		System.out.println("GalleryController remove: " + no);
		
		int count = galleryService.remove(no);
		
		return count;
	}
	
}
