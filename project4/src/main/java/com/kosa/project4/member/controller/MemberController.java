package com.kosa.project4.member.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.project4.board.model.Board;
import com.kosa.project4.board.service.BoardService;
import com.kosa.project4.member.model.Member;
import com.kosa.project4.member.service.MemberService;
import com.kosa.project4.notice.dao.NoticeDAO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;
	// 아이디 중복 검사
	@RequestMapping(value="/member/existUid.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> existUid(@RequestBody Member member, HttpServletRequest  request, HttpServletResponse response) throws Exception{
		System.out.println("existUid()");
		Map<String, Object> map = new HashMap<String, Object>();
		if(memberService.existUid(member.getUid())) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}	
		System.out.println("중복 아이디 검사 = " + map);
		return map;
	}
	
	// 로그인 확인
	@ResponseBody
	@RequestMapping("/member/loginCheck.do")
	public Map<String, Object> loginCheck( Board board, HttpSession session) throws Exception{
		System.out.println("loginCheck()");
		Map<String, Object> map = new HashMap<String, Object>();
		

		boolean result = false;
		if(session.getAttribute("loginMember") != null) {
			result = true;
		}
		map.put("result", result);
		System.out.println("result = " +result);
		return map;	
	}
	
	// 회원가입하기
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> addMember(@RequestBody Member member, HttpServletRequest  request, HttpServletResponse response) throws Exception{
		System.out.println("addMember()");
		Map<String, Object> map = memberService.add(member);
		
		return map;
	}
	
	// 로그인하기
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody Member member, HttpSession session) throws Exception{
		System.out.println("login()");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("member = " + member);
		Member loginMember = memberService.login(member);
		boolean result = false;

		 if (loginMember != null && loginMember.getPwd().equals(member.getPwd())) {
		        result = true;
		        session.setAttribute("loginMember", loginMember);
		    }
		 map.put("result", result);
		
		return map;
	}
	
	// 로그아웃하기
	@RequestMapping(value="/member/logout.do", method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> adminlogout( HttpServletRequest  request, HttpServletResponse response) throws Exception {
		System.out.println("logout");

		Map<String, Object> map = new HashMap<String, Object>();
	    HttpSession session = request.getSession();

	    // 세션을 무효화하여 로그아웃 처리
	    session.removeAttribute("loginMember");
	    
	    map.put("result", true);	    	

	    return map;
	}
	
	
	
	// 아이디찾기
	@RequestMapping(value="/member/searchId.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchId(@RequestBody Member member, HttpServletRequest  request, HttpServletResponse response) throws Exception {
		System.out.println("searchId");
		System.out.println(member.getName());
		Map<String, Object> map = new HashMap<String, Object>();
	    String uid = memberService.searchId(member);
	    if(uid== null) {
	    	map.put("result", false);	
	   
	    } else {
	    	map.put("result", true);
	    	map.put("uid", uid);	    	
	    }
	    return map;
	}
	
	
	// 비밀번호 찾기
	@RequestMapping(value="/member/searchPwd.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchPwd(@RequestBody Member member, HttpServletRequest  request, HttpServletResponse response) throws Exception {
	    System.out.println("searchPwd");

	    Map<String, Object> map = new HashMap<String, Object>();		   String pwd = memberService.searchPwd(member);
		    if(pwd== null) {
		    	map.put("result", false);	
		   
		    } else {
		    	map.put("result", true);
		    	map.put("pwd", pwd);	    	
		    }	
	    return map;
	}

	// 회원정보 수정하기
	@RequestMapping(value="/member/updateMember.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateMember(@RequestBody Member member, HttpSession session) throws Exception {
	    System.out.println("updateMember");
	    Map<String, Object> map = new HashMap<String, Object>();		   
	    Member updateMember = new Member();
	    
	    Member loginMember = (Member) session.getAttribute("loginMember");
	    
	    updateMember.setUid(member.getUid());
	    if(member.getPwd()!= "") {
	    	updateMember.setPwd(member.getPwd());
	    } else {
	    	updateMember.setPwd(loginMember.getPwd());
	    }
	    if(member.getName()!="") {
	    	updateMember.setName(member.getName());
	    }else {
	    	updateMember.setName(loginMember.getName());
	    }
	    
	    if(member.getPhone() != "") {
	    	updateMember.setPhone(member.getPhone());
	    }else {
	    	updateMember.setPhone(loginMember.getPhone());
	    }
	    
	    if(member.getEmail() !="") {
	    	updateMember.setEmail(member.getEmail());
	    } else {
	    	updateMember.setEmail(loginMember.getEmail());
	    }
	    
	    
		if(memberService.updateMember(updateMember) != 0) {
			map.put("result", true);
			map.put("member", memberService.getMember(member));
		}else {
			map.put("result", false);
		}
		
	    return map;
	}
	
	//회원탈퇴하기(한명)
	@RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Member member, HttpSession session) throws Exception {
	    System.out.println("delete");
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("result", memberService.delete(member));
	   
	    session.invalidate();
	    return map;
	}
	
	/* 관리자 */
	
	// 멤버리스트페이지로 이동
	@RequestMapping("/admin/memberlist.do")
	public String memberList( Member member, HttpServletRequest  request, HttpServletResponse response) throws Exception{
		System.out.println("memberlist()");
		System.out.println("member = " + member.getPageNo() + ", " + member.getPageLength());
		try {
			request.setAttribute("result", memberService.getMemberlist(member));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  "admin/memberlist";
	}
	
	// 멤버리스트페이지로 이동
	@ResponseBody
	@RequestMapping("/admin/memberlist2.do")
	public Map<String, Object> memberList2(@RequestBody Member member, HttpServletRequest  request, HttpServletResponse response) throws Exception{
		System.out.println("memberlist2()");
		Map<String, Object> result = memberService.getMemberlist(member);
		return result;
	}
	
	// 관리자 로그인하기
	@ResponseBody
	@RequestMapping("/admin/login.do")
	public Map<String, Object> adminLogin(@RequestBody Member member, HttpSession session) throws Exception{
		System.out.println("adminLogin()");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("member = " + member);
		Member admin = memberService.login(member);
		System.out.println("admin = " + admin);
		boolean result = false;

		 if (admin != null && admin.getUid().equals("admin")&&admin.getPwd().equals(member.getPwd())) {
		        result = true;
		        session.setAttribute("admin", admin);
		    }
		 map.put("result", result);
		
		return map;
	}
	
	
	// 관리자 로그아웃하기
	@RequestMapping(value="/member/adminlogout.do", method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> logout(HttpServletRequest  request, HttpServletResponse response) throws Exception {
		System.out.println("logout");

		Map<String, Object> map = new HashMap<String, Object>();
	    HttpSession session = request.getSession();

	    // 세션을 무효화하여 로그아웃 처리
	    session.removeAttribute("admin");
	    
	    map.put("result", true);	    	

	    return map;
	}
	

	// 회원탈퇴하기(여러명)
	@RequestMapping(value="/admin/deleteMembers.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMember(@RequestBody Member member,HttpSession session) throws Exception {
	    System.out.println("deleteMembers");
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("result", memberService.deleteMember(member.getUids()));
	    return map;
	}

	
	
}
