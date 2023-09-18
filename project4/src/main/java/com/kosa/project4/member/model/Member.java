package com.kosa.project4.member.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member implements Serializable {
	private static final long serialVersionUID = -1036524153261734687L;
	
	private String uid;
	private String pwd;
	private String name;
	private String phone;
	
	// 삭제할 멤버 리스트
	private String[]  uids;
	
	
	
	// 페이징 
	private int pageNo = 1; 				// 현재 페이지 번호	
	private int totalCount;    			 //전체 건수  
	private int totalPageSize;			// 전체 페이지수			
	private int pageLength = 10;	// 한페이지의 길이			
	private int navSize = 10;				// 페이지 하단에 출력되는 페이지의 항목 수 
	private int navStart = 0;			// 페이지 하단에 출력되는 페이지의 시작 번호 
	private int navEnd = 0;				// 페이지 하단에 출력되는 페이지의 끝 번호
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(uid, other.uid);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uid);
	}
	
	public boolean isEqualPwd(Member member) {
		return pwd.equals(pwd);
	}
	
	// 전체 건수 세팅
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		totalPageSize = ((int)Math.ceil((double) totalCount/pageLength));
		navStart = (pageNo / navSize) * navSize + 1;
		navEnd = (pageNo / navSize + 1) * navSize;
		
		if(navEnd >= totalPageSize) {
			navEnd = totalPageSize;
		}
	}
	
	// 현재 페이지에 출력되는 게시글의 시작 번호
	public int getStartNo() {
			return (pageNo -1) * pageLength + 1;
	}
	
	// 현재 페이지에 출력되는 게시글의 끝 번호
	public int getEndNo() {
		return pageNo * pageLength;
	}
	
	
}
