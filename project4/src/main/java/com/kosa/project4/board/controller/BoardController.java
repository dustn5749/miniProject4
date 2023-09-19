package com.kosa.project4.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.project4.board.model.AttacheFile;
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
	
	
	private static final String CURR_IMAGE_REPO_PATH = "C:\\file_repo";

	
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
	
	// 게시글 추가하기
	@ResponseBody
	@RequestMapping(value="/board/boardInsert.do", method=RequestMethod.POST)
	public Map<String, Object> boardInsert(MultipartHttpServletRequest request) throws Exception{
		System.out.println("boardInsert()");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		request.setCharacterEncoding("utf-8");

		
	      String id = request.getParameter("id");
	      String title = request.getParameter("title");
	      String content = request.getParameter("content");
	      Board board = new Board();
	      board.setId(id);
	      board.setTitle(title);
	      board.setContent(content);
	      
	      
		Enumeration enu=request.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=request.getParameter(name);
			map.put(name,value);
		}
		System.out.println("map = " + map.toString());
		board.setFile(fileProcess(request));
	
		resultMap.put("result", boardService.insertBoard(board));			
		

		return resultMap;		
	}	
	
	
	// 답글 추가하기
	@ResponseBody
	@RequestMapping(value="/board/boardInsert2.do", method=RequestMethod.POST)
	public Map<String, Object> boardInsert2(MultipartHttpServletRequest request) throws Exception{
		System.out.println("boardInsert2()");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		request.setCharacterEncoding("utf-8");

		
	      String id = request.getParameter("id");
	      String title = request.getParameter("title");
	      String content = request.getParameter("content");
	      String pnum = request.getParameter("pnum");
	      Board board = new Board();
	      board.setId(id);
	      board.setTitle(title);
	      board.setContent(content);
	      board.setPnum(Integer.parseInt(pnum));
	      
	      
		Enumeration enu=request.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=request.getParameter(name);
			map.put(name,value);
		}
		System.out.println("map = " + map.toString());
		board.setFile(fileProcess(request));
	
		resultMap.put("result", boardService.insertBoard(board));			
		

		return resultMap;		
	}	
	
	// 파일 저장 프로세스
	private List<AttacheFile> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<AttacheFile> fileList = new ArrayList<>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("\\yyyy\\MM\\dd");
		
		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String fileNameOrg = mFile.getOriginalFilename();
			String realFolder = sdf.format(now.getTime());
			
			File file = new File(CURR_IMAGE_REPO_PATH + realFolder);
			if (file.exists() == false) {
				file.mkdirs();
			}

			String fileNameReal = UUID.randomUUID().toString();
			
			//파일 저장 
			mFile.transferTo(new File(file, fileNameReal)); //임시로 저장된 multipartFile을 실제 파일로 전송

			fileList.add(
					AttacheFile.builder()
					.fileNameOrg(fileNameOrg)
					.fileNameReal(realFolder + "\\" + fileNameReal)
					.length((int) mFile.getSize())
					.contentType(mFile.getContentType())
					.build()
					);
		}
		return fileList;
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
	

}
