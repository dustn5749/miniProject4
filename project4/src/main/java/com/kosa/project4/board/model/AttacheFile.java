package com.kosa.project4.board.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // �۾���
public class AttacheFile {

	// �ʵ�
	private int fileNo; 			// ÷������ ��ȣ
	private int boardNum; 			// �Խñ۹�ȣ
	private String fileNameOrg;		// ����ڰ� �ø� ���� ���ϸ� 
	private String fileNameReal;	// ������ ����� ���ϸ� 
	private int    length;			// ������ ����
	private String contentType;		// ������ Ÿ��
	private Date   reg_date;		// ����Ͻ�
	
	
}
