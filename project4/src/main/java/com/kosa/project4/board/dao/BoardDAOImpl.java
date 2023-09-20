package com.kosa.project4.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kosa.project4.board.model.Board;
import com.kosa.project4.member.model.Member;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private  SqlSession sqlSession;
	
	// �Խ��� �߰��ϱ�
	@Override
	public int add(Board board) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("BoardImpl = " + board);
	
		map.put("title", board.getTitle());
		map.put("id", board.getId());
		map.put("content", board.getContent());
		map.put("pnum", board.getPnum());
		sqlSession.insert("mapper.board.add", map);
		String result = (String) map.get("v_cursor");
		System.out.println("result = " + result);
		return Integer.parseInt(result);
	}
	
	// �Խ��� �����ϱ�
	@Override
	public boolean update(Board board) throws Exception {
		return 	sqlSession.update("mapper.board.update", board) != 0;
	}
	
	//�Խ��� ���� ��������
	@Override
	public Board getBoard(int boardNum) throws Exception {
		return sqlSession.selectOne("mapper.board.getBoard", boardNum);
	}

	//�Խñ� ����
	@Override
	public boolean deleteBoards(int[] deleteLists) throws Exception {
		return 	sqlSession.update("mapper.board.deleteBoards", deleteLists) != 0;
	}

	// ��ȸ�� ����
	@Override
	public void upReadCount(int boardNum) throws Exception {
		sqlSession.update("mapper.board.upReadCount", boardNum);
	}
	
	//��ü �Խ��ǰ���
	@Override
	public int getTotalCount(Board board) throws Exception {
		return sqlSession.selectOne("mapper.board.getTotalCount", board);
	}
	
	// �˻��� �Խñ� ����¡
	@Override
	public List<Board> getSearchBoardList(Board board) throws Exception {
		System.out.println("BoardDAOImpl.getSearchBoardList()");
		Map<String, Object> map = new HashMap();
		map.put("StartNo", board.getStartNo());
		map.put("EndNo", board.getEndNo());
		map.put("searchTitle", board.getSearchTitle());
		System.out.println(map);
		List<Board> BoardList = new ArrayList<Board>(); 
		sqlSession.selectList("mapper.board.getSearchBoardList", map);
		BoardList = (List<Board>) map.get("v_cursor");
		return BoardList;
		
	}
	// �� �ۼ��� ��������
	@Override
	public String getWriter(int pnum) throws Exception {
		System.out.println("BoardDAOImpl.getWriter()");
		String user =sqlSession.selectOne("mapper.board.getWriter", pnum);
		System.out.println("user = " + user);
		return user; 
	}

	// �θ�� ������ ���� �ڽı� ����
	@Override
	public boolean deleteReplyBoards(int[] deleteLists) throws Exception {
		System.out.println("BoardDAOImpl.deleteReplyBoards()");
		
		return sqlSession.delete("mapper.board.deleteReplyBoards", deleteLists) !=0;
	}


	

} 