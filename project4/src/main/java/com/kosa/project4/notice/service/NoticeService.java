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
	
	// �������� ��ü ����Ʈ 
	public  Map<String, Object> getNoticeList(Notice notice) throws Exception {
		System.out.println("NoticeService.getNoticeList(notice)");
		 notice.setTotalCount(noticeDAO.getTotalCount(notice));

		
		Map <String, Object> result = new HashMap();
		
		result.put("notice", notice);
		result.put("noticeList",noticeDAO.getSearchNoticeList(notice));
		System.out.println("result = " + result.get("notice"));
		return result;
	}

	// ��ȸ�� ����
	public void upReadCount(int boardNum) throws Exception {
		System.out.println("NoticeService.upReadCount(notice)");
		noticeDAO.upReadCount(boardNum);
	}

	// �������� �� ����
	public Notice getNotice(int boardNum) throws Exception {
		System.out.println("NoticeService.getNotice(notice)");
		return noticeDAO.getNotice(boardNum);
	}

	// �������� �����ϱ�
	public boolean update(Notice notice) throws Exception {
		System.out.println("NoticeService.update(notice)");
		return noticeDAO.update(notice);
	}

	// �������� �����ϱ�
	public boolean deletes(int[] deleteNumList) throws Exception {
		System.out.println("NoticeService.deletes(notice)");
		return noticeDAO.deleteNotices(deleteNumList);
	}

	// �������� ���� �߰��ϱ�
	public boolean add(Notice notice) throws Exception {
		System.out.println("NoticeService.add(notice)");
		return noticeDAO.add(notice);
	}
}
