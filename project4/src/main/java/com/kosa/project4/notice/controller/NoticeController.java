package com.kosa.project4.notice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.project4.board.service.BoardService;
import com.kosa.project4.notice.model.Notice;
import com.kosa.project4.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	// �������� �Խ������� �̵�
	@RequestMapping("/notice/list.do")
	public String NoticeList(Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("list()");
		try {
			request.setAttribute("result", noticeService.getNoticeList(notice));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notice/list";
	}
	
	// �Խ��� ����¡  ������ ��������
	@ResponseBody
	@RequestMapping("/notice/getlist.do")
	public Map<String, Object> getNoticeList(@RequestBody Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("getlist()");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("result", true);
			map.put("noticeList", noticeService.getNoticeList(notice));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getList.map = " + map);
		return map;
	}
	
	//�����ڿ� �������� �Խ���
	@RequestMapping("/notice/adminList.do")
	public String adminList(Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("adminList()");
		try {
			request.setAttribute("result", noticeService.getNoticeList(notice));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notice/adminList";
	}
	
	
	// �󼼺���
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> getNotice(@RequestBody Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("getNotice()");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		
			noticeService.upReadCount(notice.getBoardNum());
			Notice seletedNotice =noticeService.getNotice(notice.getBoardNum());
			if(seletedNotice != null) {
				map.put("result", true);
				map.put("notice", seletedNotice);
			}else {
				map.put("result", false);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("result.notice =" + map.get("notice"));
		return  map;
	}
	
	// ���� �ۼ��ϱ�
	@RequestMapping(value="/notice/insertNotice.do", method=RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> insertNotice(@RequestBody Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("insertNotice()");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("result", noticeService.add(notice));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("result.notice =" + map.get("notice"));
		return  map;
	}
	
	// �����ϱ�
	@RequestMapping(value="/notice/update.do", method=RequestMethod.POST )
	@ResponseBody
	public  Map<String, Object> update(@RequestBody Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("update()");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("updateNotice = " + notice);
		try {
		map.put("result", noticeService.update(notice));
		map.put("notice", noticeService.getNotice(notice.getBoardNum()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	//�����ϱ�
	@RequestMapping(value="/notice/delete.do", method=RequestMethod.POST )
	@ResponseBody
	public  Map<String, Object> delete(@RequestBody Notice notice, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("delete()");
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("result", noticeService.deletes(notice.getDeleteNumList()));
		return map;
	}
	
	
	
}
