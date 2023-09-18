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
	
	// �Խñ� ����¡ ����Ʈ ��������
	public Map<String, Object> getBoardPageList(Board board) throws Exception {
		System.out.println("BoareService.getBoardPageList(Board board)");
		board.setTotalCount(boardDAO.getTotalCount(board));
		
		Map<String, Object> result = new HashMap();
		
		result.put("board", board);
		result.put("boardList", boardDAO.getSearchBoardList(board));
		return result;
	}

	// ��ȸ�� ����
	public void upReadCount(int boardNum) throws Exception {
		System.out.println("BoareService.upReadCount(Board board)");
		boardDAO.upReadCount(boardNum);
		
	}

	// �� �Խ��� ��������
	public Board getBoard(int boardNum) throws Exception {
		System.out.println("BoareService.getBoard(Board board)");
		return boardDAO.getBoard(boardNum);
	}

	// �Խñ� �����ϱ�
	public boolean deletes(int[] deleteLists) throws Exception {
		System.out.println("BoareService.deletes(Board board)");
		return boardDAO.deleteBoards(deleteLists);
	}

	// ��� �ۼ��ϱ�
	public boolean writeReplyForm(Board board) throws Exception{
		System.out.println("BoareService.writeReplyForm(Board board)");
		return boardDAO.writeReplyForm(board);
	}

}
