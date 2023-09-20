package com.kosa.project4.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kosa.project4.comment.model.Comment;

@Repository
public interface CommentDAO {
	

	// 댓글 리스트 가져오기
	List<Comment> getCommentList(int boardNum, Comment comment)throws Exception;
	
	// 댓글 추가하기
	boolean add(Comment commnet) throws Exception;
	
	//댓글 삭제하기
	boolean delete(int boardnum)  throws Exception;
	
	//댓글 수정하기
	boolean update(Comment comment)  throws Exception;
	
	
	//댓글 총 수세기
	int totalComment(int boardnum) throws Exception;

	//댓슬 여러개 삭제
	boolean deletes(int[] deletePnum) throws Exception;


}
