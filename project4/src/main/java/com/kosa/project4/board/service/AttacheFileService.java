package com.kosa.project4.board.service;

import java.util.List;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.board.dao.AttacheFileDAO;
import com.kosa.project4.board.model.AttacheFile;

@Service
public class AttacheFileService {
	
	@Autowired
	private AttacheFileDAO attacheFileDAO;


	// 첨부파일추가하기
	public boolean add(AttacheFile file) throws Exception {
		System.out.println("AttacheFileService.add()");
		return attacheFileDAO.add(file);
	}

	// 게시판에 따른 첨부파일 가져오기
	public List<AttacheFile> getFiles(int boardNum) throws Exception{
		System.out.println("AttacheFileService.getFiles()");
		return attacheFileDAO.getFiles(boardNum);
	}
	
	// 첨부파일 가져오기
	public AttacheFile getAttacheFile(int fileNo) throws Exception {
		System.out.println("AttacheFileService.getAttacheFile()");
		return attacheFileDAO.getAttacheFile(fileNo);
	}

	// 첨부파일 삭제 ( 전부다 )
	public boolean delete(int boardNum) throws Exception{
		System.out.println("AttacheFileService.delete()");
		return attacheFileDAO.delete(boardNum);
	}

	// 게시판 삭제(체크리스트)에 따른 첨부파일 삭제
	public boolean deletes(int[] deleteNumList) throws Exception {
		System.out.println("AttacheFileService.deletes()");
		return attacheFileDAO.deletes(deleteNumList);
	}

	
	
}
