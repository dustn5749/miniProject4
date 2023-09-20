package com.kosa.project4.member.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.board.dao.BoardDAO;
import com.kosa.project4.member.dao.MemberDAO;
import com.kosa.project4.member.model.Member;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	// ��� ����Ʈ ���
	public Map<String, Object> getMemberlist(Member member) throws Exception {
		System.out.println("MemberService.getMemberlist()");
		member.setTotalCount(memberDAO.getTotalCount(member));
		
		Map<String, Object> result = new HashMap();
		result.put("member", member);
		result.put("memberList", memberDAO.getPageMemberList(member));

		return result;
	}
	
	// ȸ������ (ȸ�� �߰��ϱ�)
	public Map<String, Object> add(Member member) throws Exception {
		System.out.println("MemberService.add()");
		Map<String, Object> map = new HashMap<String, Object>();

		if(memberDAO.add(member) ==1) {
			map.put("result", true);				
		} else {
			map.put("result", false);				
		}
		System.out.println("resultRs = " + map);
		return map;	
		
	}

	// ���̵� �ߺ� �˻�
	public boolean existUid(String uid) throws Exception {
		System.out.println("MemberService.existUid()");
		return memberDAO.existUid(uid);
	}

	// �α����ϱ�
	public Member login(Member member) throws Exception {
		System.out.println("MemberService.login()");
		boolean result = false;
		JSONObject jsonObject = new JSONObject();
		Member loginMember = memberDAO.login(member);
		
		return loginMember;
	}

	// ���̵�ã��
	public String searchId(Member member) throws Exception {
		System.out.println("MemberService.searchId()");
		String uid = memberDAO.searchId(member);
		System.out.println(uid);
		return uid ;
	}

	// ���̵�ã��
	public String searchPwd(Member member) throws Exception {
		System.out.println("MemberService.searchPwd()");
		return memberDAO.searchPwd(member);
	}

	// ȸ������ �����ϱ�
	public int updateMember(Member member) throws Exception {
		System.out.println("MemberService.updateMember()");

		return memberDAO.update(member);	
	}

	// ȸ������ ��������	
	public Member getMember(Member member) throws Exception {
		System.out.println("MemberService.getMember()");

		return memberDAO.getMember(member.getUid());
	}

	//ȸ�� Ż���ϱ�(������)
	public boolean deleteMember(String[] strings)throws Exception {
		System.out.println("MemberService.deleteMember()");

		return memberDAO.delete(strings) ;
	}

	//ȸ�� Ż���ϱ� (�Ѹ�)
	public boolean delete(Member member) throws Exception {
		System.out.println("MemberService.delete()");
		return memberDAO.deleteOne(member);
	}

	// ȸ���� �̸��� ��������
	public String getEmail(String user) throws Exception{
		System.out.println("MemberService.getEmail()");
		return memberDAO.getEmail(user);
	}



}
