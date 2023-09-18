package com.kosa.project4.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.project4.board.model.Board;
import com.kosa.project4.board.service.BoardService;
import com.kosa.project4.comment.model.Comment;
import com.kosa.project4.comment.service.CommentService;
import com.kosa.project4.member.service.MemberService;
import com.kosa.project4.notice.model.Notice;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommentService commentService;
	
	
	// 게시판으로 이동
	@RequestMapping("/board/list.do")
	public String boardList(Board board, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("list()");
		try {
			request.setAttribute("result", boardService.getBoardPageList( board));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/list";
	}
	
	// 게시판 페이징  데이터 가져오기
	@ResponseBody
	@RequestMapping("/board/getlist.do")
	public Map<String, Object> getBoardList(@RequestBody Board board, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("getlist()");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("result", true);
			map.put("boardList", boardService.getBoardPageList( board));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getList.map = " + map);
		return map;
	}
	
	// 관리자 게시판으로 이동
	@RequestMapping("/board/adminList.do")
	public String boardList2(Board board, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("list()");
		try {
			request.setAttribute("result", boardService.getBoardPageList( board));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/adminList";
	}

	

	// 게시글 상세보기
	@ResponseBody
	@RequestMapping("/board/boardInfo.do")
	public Map<String, Object> boardInfo(@RequestBody Board board, Comment comment, HttpSession session) throws Exception{
		System.out.println("boardInfo()");
		Map<String, Object> map = new HashMap<String, Object>();
		boardService.upReadCount(board.getBoardNum());
	    Board seletedBoard = boardService.getBoard(board.getBoardNum());
	    System.out.println("selecteBOARD = " + seletedBoard);
	    if(seletedBoard != null) {
	    	map.put("result", true);
	    	
	    	map.put("board", seletedBoard); 
	    	map.put("commentList", commentService.getCommentList(board.getBoardNum(), comment)); 
	    	map.put("commentLength", commentService.totalComment(board.getBoardNum()));
	    	
	    } else {
	    	map.put("result", false);
	    }

	    System.out.println(map);
		return map;		
	}	
	
	
	// 게시글 삭제하기
	@ResponseBody
	@RequestMapping("/board/deleteBoards.do")
	public Map<String, Object> deleteBoards(@RequestBody Board board, HttpSession session) throws Exception{
		System.out.println("deleteBoards()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", boardService.deletes(board.getDeleteNumList()));
		return map;		
	}	
	
	
	//게시글 수정하기
	
	// 답글 작성하기
	@ResponseBody
	@RequestMapping("/board/writeReplyForm.do")
	public Map<String, Object> writeReplyForm(@RequestBody Board board, HttpSession session) throws Exception{
		System.out.println("writeReplyForm()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", boardService.writeReplyForm(board));
		return map;		
	}	

}
