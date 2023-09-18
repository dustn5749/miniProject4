package com.kosa.project4.notice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.notice.dao.NoticeDAO;
import com.kosa.project4.notice.model.Notice;

@Service
public class NoticeService {

	@Autowired
	private  NoticeDAO noticeDAO;
	
	// 공지사항 전체 리스트 
	public  Map<String, Object> getNoticeList(Notice notice) throws Exception {
		System.out.println("NoticeService.getNoticeList(notice)");
		 notice.setTotalCount(noticeDAO.getTotalCount(notice));

		
		Map <String, Object> result = new HashMap();
		
		result.put("notice", notice);
		result.put("noticeList",noticeDAO.getSearchNoticeList(notice));
		System.out.println("result = " + result.get("notice"));
		return result;
	}

	// 조회수 증가
	public void upReadCount(int boardNum) throws Exception {
		System.out.println("NoticeService.upReadCount(notice)");
		noticeDAO.upReadCount(boardNum);
	}

	// 공지사항 상세 보기
	public Notice getNotice(int boardNum) throws Exception {
		System.out.println("NoticeService.getNotice(notice)");
		return noticeDAO.getNotice(boardNum);
	}

	// 공지사항 수정하기
	public boolean update(Notice notice) throws Exception {
		System.out.println("NoticeService.update(notice)");
		return noticeDAO.update(notice);
	}

	// 공지사항 삭제하기
	public boolean deletes(int[] deleteNumList) throws Exception {
		System.out.println("NoticeService.deletes(notice)");
		return noticeDAO.deleteNotices(deleteNumList);
	}

	// 공지사항 새글 추가하기
	public boolean add(Notice notice) throws Exception {
		System.out.println("NoticeService.add(notice)");
		return noticeDAO.add(notice);
	}
}
