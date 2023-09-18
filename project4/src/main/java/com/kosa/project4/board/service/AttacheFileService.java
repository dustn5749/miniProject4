package com.kosa.project4.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.board.dao.AttacheFileDAO;
import com.kosa.project4.board.model.AttacheFile;

@Service
public class AttacheFileService {
	
	@Autowired
	private AttacheFileDAO attacheFileDAO;

	public Object insertFile(AttacheFile attcheFile) {
		return null;
	}
	
	
}
