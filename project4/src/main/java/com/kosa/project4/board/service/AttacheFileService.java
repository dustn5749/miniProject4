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


	// ÷�������߰��ϱ�
	public boolean add(AttacheFile file) throws Exception {
		System.out.println("AttacheFileService.add()");
		return attacheFileDAO.add(file);
	}

	// �Խ��ǿ� ���� ÷������ ��������
	public List<AttacheFile> getFiles(int boardNum) throws Exception{
		System.out.println("AttacheFileService.getFiles()");
		return attacheFileDAO.getFiles(boardNum);
	}
	
	// ÷������ ��������
	public AttacheFile getAttacheFile(int fileNo) throws Exception {
		System.out.println("AttacheFileService.getAttacheFile()");
		return attacheFileDAO.getAttacheFile(fileNo);
	}

	// ÷������ ���� ( ���δ� )
	public boolean delete(int boardNum) throws Exception{
		System.out.println("AttacheFileService.delete()");
		return attacheFileDAO.delete(boardNum);
	}

	// �Խ��� ����(üũ����Ʈ)�� ���� ÷������ ����
	public boolean deletes(int[] deleteNumList) throws Exception {
		System.out.println("AttacheFileService.deletes()");
		return attacheFileDAO.deletes(deleteNumList);
	}

	
	
}
