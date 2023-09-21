package com.kosa.project4.notice.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Notice {
	private int boardNum;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private int readcount;
	private String fixed_yn;
	
	//검색 필드
	private String searchTitle= "";
	
	// 페이징 
	private int pageNo = 1; 				// 현재 페이지 번호	
	private int totalCount;    			 //전체 건수  
	private int totalPageSize;			// 전체 페이지수			
	private int pageLength = 10;		// 한페이지의 길이			
	private int navSize = 10;				// 페이지 하단에 출력되는 페이지의 항목 수 
	private int navStart = 0;			// 페이지 하단에 출력되는 페이지의 시작 번호 
	private int navEnd = 0;				// 페이지 하단에 출력되는 페이지의 끝 번호
	//private int fixedNoticeCount = 0;	// 고정된 개시물의 갯수
	
	// 게시물 삭제 리스트번호 
	private int[]  deleteNumList;
	
	
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
	public int getStartNo(/* int fixedNoticeCount */) {
		return (pageNo - 1) * pageLength/*-fixedNoticeCount)*/ + 1;
	}
	
	// 현재 페이지에 출력되는 게시글의 끝 번호
	public int getEndNo(/* int fixedNoticeCount */) {
		return pageNo * (pageLength/*-fixedNoticeCount*/);
	}

	@Override
	public String toString() {
		return "Notice [boardNum=" + boardNum + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", readcount=" + readcount + ", fixed_y=" + fixed_yn + ", pageNo=" + pageNo
				+ ", totalCount=" + totalCount + ", totalPageSize=" + totalPageSize + ", pageLength=" + pageLength
				+ ", navSize=" + navSize + ", navStart=" + navStart + ", navEnd=" + navEnd + ", deleteNumList="
				+ Arrays.toString(deleteNumList) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		return boardNum == other.boardNum && Objects.equals(content, other.content)
				&& Arrays.equals(deleteNumList, other.deleteNumList) && Objects.equals(fixed_yn, other.fixed_yn)
				&& Objects.equals(id, other.id) && navEnd == other.navEnd && navSize == other.navSize
				&& navStart == other.navStart && pageLength == other.pageLength && pageNo == other.pageNo
				&& readcount == other.readcount && Objects.equals(regdate, other.regdate)
				&& Objects.equals(title, other.title) && totalCount == other.totalCount
				&& totalPageSize == other.totalPageSize;
	}


}
