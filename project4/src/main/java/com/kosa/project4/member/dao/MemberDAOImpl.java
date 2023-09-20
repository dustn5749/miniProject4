package com.kosa.project4.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosa.project4.member.model.Member;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// �������Ʈ ��������
	@Override
	public List<Member> getPageMemberList(Member member) throws Exception {
		Map<String, Object> map = new HashMap();
		map.put("startNo", member.getStartNo());
		map.put("EndNo", member.getEndNo());
		System.out.println("map =  "+ map);
		List<Member> memberList = new ArrayList<Member>(); 
		sqlSession.selectList("mapper.member.getPageMemberList", map);
		memberList = (List<Member>)map.get("v_cursor");
	    
	    System.out.println("memberList = " + memberList);
	    
	    return memberList;
	}


	// ���̵� ���� ���� Ȯ��
	@Override
	public boolean existUid(String uid) throws Exception {
		int count = sqlSession.selectOne("mapper.member.existUid", uid);
		System.out.println("count = " + count);
		return count == 0 ;
	}

	// ȸ�������ϱ�
	@Override
	public int add(Member member) throws Exception {
		return sqlSession.insert("mapper.member.insertMember", member);
	}



	// ȸ�� ���� �����ϱ�
	@Override
	public int update(Member member) throws Exception {
		System.out.println("memeberDAOImpl.update()");
		System.out.println(member);
		
		return sqlSession.update("mapper.member.existUid", member);
	}

	// ȸ�� ���� ���
	@Override
	public Member getMember(String uid) throws Exception {
		return sqlSession.selectOne("mapper.member.getMember", uid);
	}

	// ���̵� ã��
	@Override
	public String searchId(Member member) throws Exception {
		return sqlSession.selectOne("mapper.member.searchId", member);
	}

	// ��й�ȣ ã��
	@Override
	public String searchPwd(Member member) throws Exception {
		String uid = sqlSession.selectOne("mapper.member.searchPwd", member);
		System.out.println(uid);
		return uid;
	}
	
	
	
	// �α��� ��� ��������
	@Override
	public Member login(Member member) throws Exception {
		return sqlSession.selectOne("mapper.member.getMember", member);
	}
	
	// ȸ�� Ż���ϱ�(�Ѹ�)
	@Override
	public boolean deleteOne(Member member) throws Exception {
		return sqlSession.delete("mapper.member.delete", member) != 0;
	}
	
	// ��üȸ���� 
	@Override
	public int getTotalCount(Member member) throws Exception {
		return sqlSession.selectOne("mapper.member.getTotalCount", member);
	}



	//ȸ�� ���� ������
	@Override
	public boolean delete(String[] deleteLists) throws Exception {
		System.out.println("deletelists  = " + deleteLists.toString());
	    return sqlSession.delete("mapper.member.deleteMembers", deleteLists) != 0;
	}

	// �̸��� ��������
	@Override
	public String getEmail(String user) throws Exception {
		System.out.println("MemberDAOImpl.getEmail()");
		String email =sqlSession.selectOne("mapper.member.getEmail", user);
		System.out.println("user.getEmail = " + user);
		System.out.println("email = " + email);
		return email;
	}

}
