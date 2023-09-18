package com.kosa.project4.board.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadFile {
	private String id;
	private String name;
	private MultipartFile[] file;
}
