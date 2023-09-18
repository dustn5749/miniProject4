package com.kosa.project4.board.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.board.dao.BoardDAO;
import com.kosa.project4.board.model.Board;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	// 게시글 페이징 리스트 가져오기
	public Map<String, Object> getBoardPageList(Board board) throws Exception {
		System.out.println("BoareService.getBoardPageList(Board board)");
		board.setTotalCount(boardDAO.getTotalCount(board));
		
		Map<String, Object> result = new HashMap();
		
		result.put("board", board);
		result.put("boardList", boardDAO.getSearchBoardList(board));
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

	// 게시글 삭제하기
	public boolean deletes(int[] deleteLists) throws Exception {
		System.out.println("BoareService.deletes(Board board)");
		return boardDAO.deleteBoards(deleteLists);
	}

	// 답글 작성하기
	public boolean writeReplyForm(Board board) throws Exception{
		System.out.println("BoareService.writeReplyForm(Board board)");
		return boardDAO.writeReplyForm(board);
	}

}
