package com.kosa.project4.comment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.project4.comment.model.Comment;
import com.kosa.project4.comment.service.CommentService;


@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	// 댓글 추가하기
	@ResponseBody
	@RequestMapping("/comment/insert.do")
	public Map<String, Object> insetComment(@RequestBody Comment comment, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("insetComment()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", commentService.add(comment));
		map.put("commentList", commentService.getCommentList(comment.getParentnum(), comment));
    	map.put("commentLength", commentService.totalComment(comment.getParentnum()));

		return map;
	}
	
	// 댓글 수정하기
	@ResponseBody
	@RequestMapping("/comment/updateComment.do")
	public Map<String, Object> updateComment(@RequestBody Comment comment,HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("updateComment()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", commentService.update(comment));
		map.put("commentList", commentService.getCommentList(comment.getParentnum(), comment));
    	map.put("commentLength", commentService.totalComment(comment.getParentnum()));

		System.out.println("댓글 수정 정보 = " + commentService.update(comment));
		return map;
	}
	
	// 댓글 삭제하기
	@ResponseBody
	@RequestMapping("/comment/deleteComment.do")
	public Map<String, Object>  deleteComment(@RequestBody Comment comment,HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("deleteComment()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", commentService.delete(comment.getBoardnum()));
		map.put("commentList", commentService.getCommentList(comment.getParentnum(), comment));
    	map.put("commentLength", commentService.totalComment(comment.getParentnum()));

		System.out.println(map);
		return map;
	}
	
	// 댓글 가져오기 ( 더보기 )
	@ResponseBody
	@RequestMapping("/comment/plusComment.do")
	public Map<String, Object>  plusComment(@RequestBody Comment comment,HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("plusComment()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentList", commentService.getCommentList(comment.getParentnum(), comment));
    	map.put("commentLength", commentService.totalComment(comment.getParentnum()));

		System.out.println(map);
		return map;
	}
	
	}


