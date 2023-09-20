package com.kosa.project4;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosa.project4.board.model.Board;
import com.kosa.project4.board.service.BoardService;
import com.kosa.project4.member.model.Member;
import com.kosa.project4.member.service.MemberService;
import com.kosa.project4.notice.dao.NoticeDAO;
import com.kosa.project4.notice.service.NoticeService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	public NoticeService noticeService;
	
	@Autowired
	public BoardService boardService; 
	
	@Autowired
	public MemberService memberService;

	
	@RequestMapping(value = { "/", "/index/index.do"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("top5List", noticeService.getTop5Notice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	//관리자 페이지로  이동
	@RequestMapping(value = "/admin.do")
	public String adminPage(Member member,HttpServletRequest  request, HttpSession session) throws Exception{
		System.out.println("adminPage()");
		try {
			request.setAttribute("boardtop5List", boardService.getTop5Board());
			request.setAttribute("noticetop5List", noticeService.getTop5Notice());
			request.setAttribute("membertop5List", memberService.getTop5Member());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return  "adminMain";		
	}
	
}
 