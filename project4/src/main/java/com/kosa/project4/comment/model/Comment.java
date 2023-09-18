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
		
		// ����¡ 
		private int pageNo = 1; 				// ���� ������ ��ȣ	
		private int totalCount;    				//��ü �Ǽ�  
		private int totalPageSize;				// ��ü ��������			
		private int pageLength = 10;			// ���������� ����			
		private int navSize = 10;				// ������ �ϴܿ� ��µǴ� �������� �׸� �� 
		private int navStart = 0;				// ������ �ϴܿ� ��µǴ� �������� ���� ��ȣ 
		private int navEnd = 0;					// ������ �ϴܿ� ��µǴ� �������� �� ��ȣ
		
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
		
}
