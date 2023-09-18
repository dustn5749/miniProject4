package com.kosa.project4.comment.model;

import java.sql.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
		private int boardnum;
		private String id;
		private String content;
		private Date regdate;
		private int parentnum;
		
		// 페이징 
		private int pageNo = 1; 				// 현재 페이지 번호	
		private int totalCount;    				//전체 건수  
		private int totalPageSize;				// 전체 페이지수			
		private int pageLength = 10;			// 한페이지의 길이			
		private int navSize = 10;				// 페이지 하단에 출력되는 페이지의 항목 수 
		private int navStart = 0;				// 페이지 하단에 출력되는 페이지의 시작 번호 
		private int navEnd = 0;					// 페이지 하단에 출력되는 페이지의 끝 번호
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Comment other = (Comment) obj;
			return boardnum == other.boardnum && Objects.equals(content, other.content) && Objects.equals(id, other.id)
					&& Objects.equals(regdate, other.regdate) ;
		}
		@Override
		public int hashCode() {
			return Objects.hash(boardnum, content, id, regdate);
		}
		@Override
		public String toString() {
			return "Comment [boardnum=" + boardnum + ", id=" + id  + ", content=" + content
					+ ", regdate=" + regdate + "]";
		}
		
		
		// 전체 건수 세팅
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			
			//2. 전체 페이지 건수를 계산한다 
			totalPageSize = (int) Math.ceil((double) totalCount / pageLength);
			
			//3. 페이지 네비게이터 시작 페이지를 계산한다
			navStart = ((pageNo - 1) / navSize) * navSize + 1;
			
			//4. 페이지 네비게이터 끝 페이지를 계산한다
			navEnd = ((pageNo - 1) / navSize + 1) * navSize;
			
			//5. 전체 페이지 보다 크면 전체 페이지 값을 변경한다
			if (navEnd >= totalPageSize) {
				navEnd = totalPageSize;
			}
		}
		
		public int getStartNo() {
			return (pageNo - 1) * pageLength + 1; 
		}
		
		public int getEndNo() {
			return pageNo * pageLength; 
		}
		
}
