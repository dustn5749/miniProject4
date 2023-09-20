package com.kosa.project4.board.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kosa.project4.board.model.Board;


public interface BoardDAO {
		
	
	//�Խ��� �߰�
	int add(Board board) throws Exception;

	
	// �Խ��� ����
	boolean update(Board board) throws Exception;
	
	// Ư�� �Խ����� ���� ��������
	Board getBoard( int boardNum) throws Exception;
	
	//  �Խ��� ����
	boolean deleteBoards(int[] deleteLists) throws Exception;
	
	// �Խ��� ��ȸ�� ����
	void upReadCount(int boardNum) throws Exception;

	
	// �˻��� ��ü �Ǽ�
	public int getTotalCount(Board board) throws Exception;
	
	
	// �˻��� ����Ʈ ���
	public List<Board> getSearchBoardList(Board board) throws Exception;

	// �� �ۼ��� ��������
	String getWriter(int pnum) throws Exception;

	// �θ�� ������ ���� �ڽı� ����
	boolean deleteReplyBoards(int[] deleteLists) throws Exception;

	// TOP5 ����Ʈ ���
	List<Board> getTop5List() throws Exception;



}
