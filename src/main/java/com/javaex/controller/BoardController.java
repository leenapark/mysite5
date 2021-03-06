package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping(value="/board")
public class BoardController {

	// 필드
	@Autowired
	private BoardService boardService;
	
	
	
	// 게시판 리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String listSearch(Model model, @RequestParam(value="str", required=false) String str) {
		System.out.println("list");		
		
		System.out.println("controller str: " + str);
		
		List<BoardVo> boardList = boardService.listSearch(str);
		model.addAttribute("boardList", boardList);
		
		return "/board/list";
	}
	
	// 게시판 리스트2 - 검색 기능 포함
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="keyword", required=false, defaultValue="") String keyword, Model model) {
		System.out.println("controller list: " + keyword);
		
		List<BoardVo> boardVo = boardService.getBoardList2(keyword);
		
		System.out.println(boardVo);
		
		model.addAttribute("boardList", boardVo);
		
		return "/board/list2";
	}
	
	// 게시판 리스트3 - 검색 기능 + 페이징 포함
		@RequestMapping(value="/list3", method= {RequestMethod.GET, RequestMethod.POST})
		public String list3(@RequestParam(value="keyword", required=false, defaultValue="") String keyword, Model model,
							@RequestParam(value="crtPage", required = false, defaultValue="1") int crtPage) {
			System.out.println("controller list: " + keyword + ", " + crtPage);
			
			
			Map<String, Object> pMap = boardService.getBoardList3(keyword, crtPage);
			
			System.out.println(pMap);
			
			model.addAttribute("pMap", pMap);
			
			return "/board/list3";
		}
	
	
	// 글 읽기
	@RequestMapping(value="/post", method= {RequestMethod.GET, RequestMethod.POST})
	/*
	public String postread(HttpSession session, @RequestParam("no") int no) {
		System.out.println("post");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		//authUser 값이 null 이면 tostring으로 확인 불가
		
		BoardVo boardVo = boardService.postread(no, authUser);
		System.out.println("controller read: " + boardVo);
		
		session.setAttribute("postVo", boardVo);
		
		return "/board/read";
		
	}
	*/
	public String postread(HttpSession session, @RequestParam("no") int no) {
		System.out.println("post");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		//authUser 값이 null 이면 tostring으로 확인 불가
		
		BoardVo boardVo = boardService.postread(no, authUser);
		System.out.println("controller read: " + boardVo);
		
		session.setAttribute("postVo", boardVo);
		
		return "/board/read";
		
	}
	
	
	// 수정 폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("modifyForm");
		
		BoardVo boardVo = boardService.modifyPost(no);
		model.addAttribute("postVo", boardVo);
		
		return "/board/modifyForm";
	}
	
	// 수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("modify");
		System.out.println("controller modify: " + boardVo.toString());
		
		int count = boardService.modify(boardVo);
		
		return "redirect:/board/post?no="+boardVo.getNo();
	}
	
	// 게시글 쓰기 폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("writeForm");
		
		return "/board/writeForm";
	}
	
	// 게시글 저장
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("write");
		System.out.println("controller write: " + boardVo.toString());
		
		int count = boardService.postInsert(boardVo);
		
		return "redirect:/board/list";
	}
	
	// 게시글 삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("delete");
		
		int count = boardService.postDelete(no);
		
		return "redirect:/board/list";
	}
	
}
