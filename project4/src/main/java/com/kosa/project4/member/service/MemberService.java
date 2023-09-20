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

	// 멤버 리스트 출력
	public Map<String, Object> getMemberlist(Member member) throws Exception {
		System.out.println("MemberService.getMemberlist()");
		member.setTotalCount(memberDAO.getTotalCount(member));
		
		Map<String, Object> result = new HashMap();
		result.put("member", member);
		result.put("memberList", memberDAO.getPageMemberList(member));

		return result;
	}
	
	// 회원가입 (회원 추가하기)
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

	// 아이디 중복 검사
	public boolean existUid(String uid) throws Exception {
		System.out.println("MemberService.existUid()");
		return memberDAO.existUid(uid);
	}

	// 로그인하기
	public Member login(Member member) throws Exception {
		System.out.println("MemberService.login()");
		boolean result = false;
		JSONObject jsonObject = new JSONObject();
		Member loginMember = memberDAO.login(member);
		
		return loginMember;
	}

	// 아이디찾기
	public String searchId(Member member) throws Exception {
		System.out.println("MemberService.searchId()");
		String uid = memberDAO.searchId(member);
		System.out.println(uid);
		return uid ;
	}

	// 아이디찾기
	public String searchPwd(Member member) throws Exception {
		System.out.println("MemberService.searchPwd()");
		return memberDAO.searchPwd(member);
	}

	// 회원정보 수정하기
	public int updateMember(Member member) throws Exception {
		System.out.println("MemberService.updateMember()");

		return memberDAO.update(member);	
	}

	// 회원정보 가져오기	
	public Member getMember(Member member) throws Exception {
		System.out.println("MemberService.getMember()");

		return memberDAO.getMember(member.getUid());
	}

	//회원 탈퇴하기(여러명)
	public boolean deleteMember(String[] strings)throws Exception {
		System.out.println("MemberService.deleteMember()");

		return memberDAO.delete(strings) ;
	}

	//회원 탈퇴하기 (한명)
	public boolean delete(Member member) throws Exception {
		System.out.println("MemberService.delete()");
		return memberDAO.deleteOne(member);
	}

	// 회원의 이메일 가져오기
	public String getEmail(String user) throws Exception{
		System.out.println("MemberService.getEmail()");
		return memberDAO.getEmail(user);
	}



}
