package com.kosa.project4.board.model;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board   {
		private int boardNum;
		private String title;
		private String id;
		private String content;
		private Date regdate;
		private int readcount;
		private int pnum;
		private int level = 1;
		
		//검색 필드
		private String searchTitle= "";
		
		// 페이징 
		private int pageNo = 1; 				// 현재 페이지 번호	
		private int totalCount;    			 //전체 건수  
		private int totalPageSize;			// 전체 페이지수			
		private int pageLength = 10;	// 한페이지의 길이			
		private int navSize = 10;				// 페이지 하단에 출력되는 페이지의 항목 수 
		private int navStart = 0;			// 페이지 하단에 출력되는 페이지의 시작 번호 
		private int navEnd = 0;				// 페이지 하단에 출력되는 페이지의 끝 번호
		
		// 게시물 삭제 리스트번호 
		private int[]  deleteNumList;
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Board other = (Board) obj;
			return boardNum == other.boardNum && Objects.equals(content, other.content) && Objects.equals(id, other.id)
					&& readcount == other.readcount && Objects.equals(regdate, other.regdate)
					 && Objects.equals(title, other.title);
		}

		@Override
		public int hashCode() {
			return Objects.hash(boardNum, content, id, readcount, regdate, title);
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
		
		
	public JSONObject getSJONoject() throws Exception {
		JSONObject json = new JSONObject();
		Class cls = this.getClass();
		
		  for (Field field : cls.getDeclaredFields()) {
	            json.put(field.getName(), field.get(this));
	        }
		return json;
	}
		
}
