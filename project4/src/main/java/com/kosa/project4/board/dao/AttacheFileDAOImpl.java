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
	
	// 첨부파일 저장하기
	@Override
	public boolean add(AttacheFile file) throws Exception {
		System.out.println("attacheFileDAOImpl.add()");
		return sqlSession.insert("mapper.attacheFile.insert", file) != 0;
	}

	// 첨부파일 가져오기
	@Override
	public AttacheFile getAttacheFile(int fileNo) throws Exception {
		System.out.println("attacheFileDAOImpl.getAttacheFile()");
		System.out.println("fileNo = " + fileNo);
		return sqlSession.selectOne("mapper.attacheFile.getAttacheFile", fileNo);
	}

	// 게시판에 따른 첨부파일 가져오기
	@Override
	public List<AttacheFile> getFiles(int boardNum) throws Exception {
		System.out.println("attacheFileDAOImpl.getFiles()");
		System.out.println("boardNum = " + boardNum);
		return sqlSession.selectList("mapper.attacheFile.getList", boardNum);
	}

	//게시판에 따른 첨부파일 삭제(전부)
	@Override
	public boolean delete(int boardNum) throws Exception {
		System.out.println("attacheFileDAOImpl.delete()");
		return sqlSession.delete("mapper.attacheFile.delete", boardNum) != 0;
	}

	// 게시판 삭제에 따른 첨부파일 삭제
	@Override
	public boolean deletes(int[] deleteNumList) throws Exception {
		System.out.println("attacheFileDAOImpl.deletes()");
		return sqlSession.delete("mapper.attacheFile.deletesFile", deleteNumList) != 0;
	}


}
