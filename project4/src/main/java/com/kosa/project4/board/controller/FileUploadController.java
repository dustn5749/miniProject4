package com.kosa.project4.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.project4.board.model.AttacheFile;
import com.kosa.project4.board.service.AttacheFileService;

@Controller
public class FileUploadController {
	
	@Autowired
	private AttacheFileService attacheFileService;
	
	// 첨부파일 추가하기
	@ResponseBody
	@RequestMapping(value="/uploadFile/insertFile.do", method=RequestMethod.POST)
	public Map<String, Object> insertFile( AttacheFile attcheFile, MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws UnsupportedEncodingException{
		System.out.println("FileController.insertFile()");
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",attacheFileService.insertFile(attcheFile));
//		map.put("attacheFile");
		return map;
	}
		
}
