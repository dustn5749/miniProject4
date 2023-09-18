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
		
		//�˻� �ʵ�
		private String searchTitle= "";
		
		// ����¡ 
		private int pageNo = 1; 				// ���� ������ ��ȣ	
		private int totalCount;    			 //��ü �Ǽ�  
		private int totalPageSize;			// ��ü ��������			
		private int pageLength = 10;	// ���������� ����			
		private int navSize = 10;				// ������ �ϴܿ� ��µǴ� �������� �׸� �� 
		private int navStart = 0;			// ������ �ϴܿ� ��µǴ� �������� ���� ��ȣ 
		private int navEnd = 0;				// ������ �ϴܿ� ��µǴ� �������� �� ��ȣ
		
		// �Խù� ���� ����Ʈ��ȣ 
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
		
		// ��ü �Ǽ� ����
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			
			//2. ��ü ������ �Ǽ��� ����Ѵ� 
			totalPageSize = (int) Math.ceil((double) totalCount / pageLength);
			
			//3. ������ �׺������ ���� �������� ����Ѵ�
			navStart = ((pageNo - 1) / navSize) * navSize + 1;
			
			//4. ������ �׺������ �� �������� ����Ѵ�
			navEnd = ((pageNo - 1) / navSize + 1) * navSize;
			
			//5. ��ü ������ ���� ũ�� ��ü ������ ���� �����Ѵ�
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
