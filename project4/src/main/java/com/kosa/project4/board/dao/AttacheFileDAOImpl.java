package com.kosa.project4.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosa.project4.board.model.AttacheFile;

@Repository("attacheFileDAO")
public class AttacheFileDAOImpl implements AttacheFileDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// ÷������ �����ϱ�
	@Override
	public boolean add(AttacheFile file) throws Exception {
		System.out.println("attacheFileDAOImpl.add()");
		return sqlSession.insert("mapper.attacheFile.insert", file) != 0;
	}

	// ÷������ ��������
	@Override
	public AttacheFile getAttacheFile(int fileNo) throws Exception {
		System.out.println("attacheFileDAOImpl.getAttacheFile()");
		System.out.println("fileNo = " + fileNo);
		return sqlSession.selectOne("mapper.attacheFile.getAttacheFile", fileNo);
	}

	// �Խ��ǿ� ���� ÷������ ��������
	@Override
	public List<AttacheFile> getFiles(int boardNum) throws Exception {
		System.out.println("attacheFileDAOImpl.getFiles()");
		System.out.println("boardNum = " + boardNum);
		return sqlSession.selectList("mapper.attacheFile.getList", boardNum);
	}

	//�Խ��ǿ� ���� ÷������ ����(����)
	@Override
	public boolean delete(int boardNum) throws Exception {
		System.out.println("attacheFileDAOImpl.delete()");
		return sqlSession.delete("mapper.attacheFile.delete", boardNum) != 0;
	}

	// �Խ��� ������ ���� ÷������ ����
	@Override
	public boolean deletes(int[] deleteNumList) throws Exception {
		System.out.println("attacheFileDAOImpl.deletes()");
		return sqlSession.delete("mapper.attacheFile.deletesFile", deleteNumList) != 0;
	}


}
