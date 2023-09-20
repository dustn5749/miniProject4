package com.kosa.project4.board.dao;

import java.util.List;

import com.kosa.project4.board.model.AttacheFile;

public interface AttacheFileDAO {
	
	// 첨부파일 저장하기
	boolean add(AttacheFile file) throws Exception;

	// 첨부파일 가져오기
	AttacheFile getAttacheFile(int fileNo) throws Exception;

	// 게시판에 따른 첨부파일 가져오기
	List<AttacheFile> getFiles(int boardNum) throws Exception;

	//첨부파일 삭제(전부)
	boolean delete(int boardNum) throws Exception;

	//게시판 삭제에 따른 첨부파일 삭제
	boolean deletes(int[] deleteNumList) throws Exception;
 
}
