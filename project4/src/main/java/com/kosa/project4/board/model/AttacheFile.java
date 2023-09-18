package com.kosa.project4.board.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // 글쓰기
public class AttacheFile {

	// 필드
	private int fileNo; 			// 첨부파일 아이디
	private int boardNum; 			// 게시글번호
	private String fileNameOrg;		// 사용자가 올린 원본 파일명 
	private String fileNameReal;	// 서버에 저장된 파일명 
	private int    length;			// 파일의 길이
	private String contentType;		// 컨텐츠 타입
	private Date   reg_date;		// 등록일시
	
	
}
