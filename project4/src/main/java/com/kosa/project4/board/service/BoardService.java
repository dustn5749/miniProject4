package com.kosa.project4.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.board.dao.AttacheFileDAO;
import com.kosa.project4.board.dao.BoardDAO;
import com.kosa.project4.board.model.AttacheFile;
import com.kosa.project4.board.model.Board;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private AttacheFileService attacheFileService;
	
	// 게시글 페이징 리스트 가져오기
	public Map<String, Object> getBoardPageList(Board board) throws Exception {
		System.out.println("BoareService.getBoardPageList(Board board)");
		board.setTotalCount(boardDAO.getTotalCount(board));
		
		Map<String, Object> result = new HashMap();
		
		result.put("board", board);
		result.put("boardList", boardDAO.getSearchBoardList(board));
		System.out.println("result = " + result);
		return result;
	}

	// 조회수 증가
	public void upReadCount(int boardNum) throws Exception {
		System.out.println("BoareService.upReadCount(Board board)");
		boardDAO.upReadCount(boardNum);
		
	}

	
	// 상세 게시판 가져오기
	public Board getBoard(int boardNum) throws Exception {
		System.out.println("BoareService.getBoard(Board board)");
		return boardDAO.getBoard(boardNum);
	}

	// 게시글 작성하기
	public boolean insertBoard(Board board) throws Exception {
		System.out.println("BoareService.insertBoard(Board board)");
		boolean result = false;
		int boardNum = boardDAO.add(board);
		for(AttacheFile file : board.getFile()) {
			file.setBoardNum(boardNum);
			attacheFileService.add(file);
			
		}
		if(boardNum != 0) {
			result = true;			
		}
		return result;
	}
	
	// 게시글 삭제하기
	public boolean deletes(int[] deleteLists) throws Exception {
		System.out.println("BoareService.deletes(Board board)");
		 boolean rs1 = boardDAO.deleteBoards(deleteLists);
		 boolean rs2 = boardDAO.deleteReplyBoards(deleteLists);
		 System.out.println("rs1 = " + rs1 + ", rs2 = " + rs2);
//		 boolean result = false;
//		 if(rs1) {
//			 if(rs2) {
//				result = true;
//			 }
//		 }
		 return rs1;
	}

	// 작성자가져오기
	public String getWriter(int pnum) throws Exception{
		System.out.println("BoareService.getWriter(Board board)");
		return boardDAO.getWriter(pnum);
	}
	
	// 게시판 수정하기
	public boolean update(Board board) throws Exception {
		System.out.println("BoareService.update(Board board)");
		
		attacheFileService.delete(board.getBoardNum());
		for(AttacheFile file : board.getFile()) {
			
			file.setBoardNum(board.getBoardNum());
			attacheFileService.add(file);

		}
		
		return boardDAO.update(board);
	}




	

}
