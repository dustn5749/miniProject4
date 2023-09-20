package com.kosa.project4.board.dao;

import java.util.List;

import com.kosa.project4.board.model.AttacheFile;

public interface AttacheFileDAO {
	
	// ÷������ �����ϱ�
	boolean add(AttacheFile file) throws Exception;

	// ÷������ ��������
	AttacheFile getAttacheFile(int fileNo) throws Exception;

	// �Խ��ǿ� ���� ÷������ ��������
	List<AttacheFile> getFiles(int boardNum) throws Exception;

	//÷������ ����(����)
	boolean delete(int boardNum) throws Exception;

	//�Խ��� ������ ���� ÷������ ����
	boolean deletes(int[] deleteNumList) throws Exception;
 
}
