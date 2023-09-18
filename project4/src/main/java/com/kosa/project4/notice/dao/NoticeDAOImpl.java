package com.kosa.project4.notice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosa.project4.board.model.Board;
import com.kosa.project4.notice.model.Notice;

@Repository("noticeDAO")
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	public SqlSession sqlSession;
	
	// �������� �߰��ϱ�
	@Override
	public boolean add(Notice notice) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", notice.getId());
		map.put("title", notice.getTitle());		
		map.put("content", notice.getContent());
		map.put("fixed_yn", notice.getFixed_yn());
		System.out.println("map = " + map);
		sqlSession.insert("mapper.notice.insert",map);
		String result = (String) map.get("v_cursor");
		return result.equals("success");
	}

	// �������� �����ϱ�
	@Override
	public boolean update(Notice notice) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", notice.getTitle());
		map.put("content", notice.getContent());
		map.put("fixed_yn", notice.getFixed_yn());
		map.put("boardnum", notice.getBoardNum());
		System.out.println("map = " + map);
		sqlSession.insert("mapper.notice.update",map);
		String result = (String) map.get("v_cursor");
		return result.equals("success");
	}
	
	// �������� �󼼺���
	@Override
	public Notice getNotice(int boardNum) throws Exception {
		System.out.println("boardNum = " + boardNum);
		return (Notice) sqlSession.selectOne("mapper.notice.getNotice",boardNum);
	}

	// �������� �����ϱ�(������)
	@Override
	public boolean deleteNotices(int[] deleteLists) throws Exception {
		System.out.println("deletelists  = " + deleteLists.toString());
	    return sqlSession.delete("mapper.notice.deletes", deleteLists) != 0;
	}

	// ��ȸ�� �����ϱ�
	@Override
	public void upReadCount(int boardNum) throws Exception {
		System.out.println("boardNum = " + boardNum);
		 sqlSession.update("mapper.notice.upReadCount",boardNum);
	}

	// TOP5 ����ϱ�
	@Override
	public List<Notice> getTop5Notice() throws Exception {
		return sqlSession.selectList("mapper.notice.getTop5Notice");
	}

	// ��ü�� �� 
	@Override
	public int getTotalCount(Notice notice) throws Exception {
		return sqlSession.selectOne("mapper.notice.getTotalCount", notice);
	}

	//������
	@Override
	public List<Notice> getSearchNoticeList(Notice notice) throws Exception {
		System.out.println("NoticeDAOImpl.getSearchNoticeList()");
		Map<String, Object> map = new HashMap();
		map.put("StartNo", notice.getStartNo());
		map.put("EndNo", notice.getEndNo());
		map.put("searchTitle", notice.getSearchTitle());
		List<Notice> noticeList = new ArrayList<Notice>(); 
		sqlSession.selectList("mapper.notice.getSearchNoticeList", map);
		noticeList = (List<Notice>) map.get("v_cursor");
		return noticeList;
	}

}
