package com.kosa.project4.member.dao;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kosa.project4.member.model.Member;

@Repository
public interface MemberDAO {
		List<Member> getPageMemberList(Member memer) throws Exception;
		boolean existUid(String uid)  throws Exception;
		int add(Member member)  throws Exception;
		boolean delete(String[] strings)  throws Exception;
		int update(Member member)  throws Exception;
		Member getMember(String uid)  throws Exception;
		String searchId(Member member)  throws Exception;
		String searchPwd(Member member)  throws Exception;
		Member login(Member member) throws Exception;
		boolean deleteOne(Member member) throws Exception;
		int getTotalCount(Member member) throws Exception;
	
}
