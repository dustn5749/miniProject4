package com.kosa.project4.board.dao;

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
		return sqlSession.insert("mapper.attacheFile.insert", file) != 0;
	}


}
