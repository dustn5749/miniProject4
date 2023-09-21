package com.kosa.project4.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.project4.board.model.AttacheFile;
import com.kosa.project4.board.model.Board;
import com.kosa.project4.board.service.AttacheFileService;
import com.kosa.project4.board.service.BoardService;
import com.kosa.project4.comment.model.Comment;
import com.kosa.project4.comment.service.CommentService;
import com.kosa.project4.member.service.MemberService;
import com.kosa.project4.notice.model.Notice;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileController {

	private static final String CURR_IMAGE_REPO_PATH = "C:\\file_repo";

	@Autowired
	private AttacheFileService attacheFileService;
	
	// 첨부파일 다운로드 
	@RequestMapping("/file/download.do")
	public void download(AttacheFile file,
							HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		System.out.println("fileController.download()");
		AttacheFile attacheFile = attacheFileService.getAttacheFile(file.getFileNo());
		if(attacheFile != null) {
			String filePath = CURR_IMAGE_REPO_PATH  + attacheFile.getFileNameReal();
			System.out.println("attachFile = " + attacheFile);
			System.out.println("filePath = " + filePath);
			File images = new File(filePath);
			
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(attacheFile.getFileNameOrg(), "UTF-8") );
			response.setContentType("application/octet-stream");
			response.setContentLength(attacheFile.getLength());
			
			FileInputStream in = new FileInputStream(images);
			
			byte[] buffer = new byte[1024*8];
			while(true) {
				int count = in.read(buffer);
				if(count == -1) {
					break;
				}
				out.write(buffer, 0, count);
			}
			in.close();
		}
		out.close();
		System.out.println("download()");
	}
	
	   // 첨부파일 이미지 출력 ( 썸네일 )
	   @RequestMapping("/file/displayImage.do")
	   public void displayImage(AttacheFile file, HttpServletResponse response) throws Exception {
	       OutputStream out = response.getOutputStream();
	       AttacheFile attacheFile = attacheFileService.getAttacheFile(file.getFileNo());
	       if (attacheFile != null) {
	           String filePath = CURR_IMAGE_REPO_PATH + attacheFile.getFileNameReal();
	           File images = new File(filePath);
	           String getFileName = attacheFile.getFileNameOrg();
	           int lastIndex = getFileName.lastIndexOf(".");
			   String extension = getFileName.substring(lastIndex);
	           File thumbnail = new File(CURR_IMAGE_REPO_PATH + "\\" +"thumbnail"+ "\\" + getFileName + extension);
	           if (images.exists()) {
	        	   thumbnail.getParentFile().mkdirs();
	               Thumbnails.of(images).forceSize(500, 500).outputFormat("png").toOutputStream(out);
	           }
	       }
	       out.close();
	   }

}
