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
	
	//�˻� �ʵ�
	private String searchTitle= "";
	
	// ����¡ 
	private int pageNo = 1; 				// ���� ������ ��ȣ	
	private int totalCount;    			 //��ü �Ǽ�  
	private int totalPageSize;			// ��ü ��������			
	private int pageLength = 10;		// ���������� ����			
	private int navSize = 10;				// ������ �ϴܿ� ��µǴ� �������� �׸� �� 
	private int navStart = 0;			// ������ �ϴܿ� ��µǴ� �������� ���� ��ȣ 
	private int navEnd = 0;				// ������ �ϴܿ� ��µǴ� �������� �� ��ȣ
	//private int fixedNoticeCount = 0;	// ������ ���ù��� ����
	
	// �Խù� ���� ����Ʈ��ȣ 
	private int[]  deleteNumList;
	
	
	// ��ü �Ǽ� ����
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		totalPageSize = ((int)Math.ceil((double) totalCount/pageLength));
		navStart = (pageNo / navSize) * navSize + 1;
		navEnd = (pageNo / navSize + 1) * navSize;
		
		if(navEnd >= totalPageSize) {
			navEnd = totalPageSize;
		}
	}
	
	// ���� �������� ��µǴ� �Խñ��� ���� ��ȣ
	public int getStartNo(/* int fixedNoticeCount */) {
		return (pageNo - 1) * pageLength/*-fixedNoticeCount)*/ + 1;
	}
	
	// ���� �������� ��µǴ� �Խñ��� �� ��ȣ
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
