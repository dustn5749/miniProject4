package com.kosa.project4.board.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kosa.project4.board.model.Board;


public interface BoardDAO {
		
	
	//게시판 추가
	boolean add(Board board) throws Exception;

	
	// 게시판 수정
	boolean update(Board board) throws Exception;
	
	// 특정 게시판의 정보 가져오기
	Board getBoard( int boardNum) throws Exception;
	
	//  게시판 삭제
	boolean deleteBoards(int[] deleteLists) throws Exception;
	
	// 게시판 조회수 증가
	void upReadCount(int boardNum) throws Exception;

	
	// 검색된 전체 건수
	public int getTotalCount(Board board) throws Exception;
	
	
	// 검색된 리스트 출력
	public List<Board> getSearchBoardList(Board board) throws Exception;


	// 답글 작성하기
	boolean writeReplyForm(Board board) throws Exception;




}
