package com.kosa.project4.board.dao;

import com.kosa.project4.board.model.AttacheFile;

public interface AttacheFileDAO {
	
	// 첨부파일 저장하기
	boolean add(AttacheFile file) throws Exception;

}
