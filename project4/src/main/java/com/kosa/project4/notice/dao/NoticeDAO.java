package com.kosa.project4.notice.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.kosa.project4.notice.model.Notice;

public interface NoticeDAO {

	// 공지사항 추가
	boolean add(Notice notice) throws Exception;
	
	// 게시판 수정
	boolean update(Notice notice) throws Exception;
	
	// 특정 게시판의 정보 가져오기
	Notice getNotice( int boardNum) throws Exception;
	
	//  게시판 삭제
	boolean deleteNotices(int[] deleteLists) throws Exception;
	
	// 게시판 조회수 증가
	void upReadCount(int boardNum) throws Exception;
	
	// 게시판 5개만 출력
	List<Notice> getTop5Notice() throws Exception;
	
	// 전체 건수 출력
	public int getTotalCount(Notice notice) throws Exception;
	
	// 더보기 눌렀을시 
	public List<Notice> getSearchNoticeList(Notice notice) throws Exception;
}
