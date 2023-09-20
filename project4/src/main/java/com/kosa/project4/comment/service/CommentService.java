package com.kosa.project4.comment.service;

import java.util.List;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.project4.comment.dao.CommentDAO;
import com.kosa.project4.comment.model.Comment;


@Service
public class CommentService {
	
	@Autowired
	private CommentDAO commentDao;

	// ��� ����Ʈ ��������
	public  List<Comment> getCommentList(int boardNum, Comment comment) throws Exception {
		System.out.println("commentService.getCommentList(boardNum)");

		return commentDao.getCommentList(boardNum, comment);
	}

	// ��� �߰��ϱ�-
	public  boolean add(Comment commnet)  throws Exception{
		boolean result = commentDao.add(commnet);
		System.out.println("result.service = " + result);
		return result; 
	}
	
	//��� �����ϱ�
	public  boolean delete(int boardnum) throws Exception{
		return commentDao.delete(boardnum);
	}
	
	// ��� ������ ����
	public boolean deletes(int[] deletePnum) throws Exception{
		return commentDao.deletes(deletePnum);
	}

	//��� �����ϱ�
	public  boolean update(Comment comment)  throws Exception{
		 return commentDao.update(comment);
	}
	
	//��� �� ���� 
	public int totalComment(int boardnum) throws Exception{
		return commentDao.totalComment(boardnum);
	}
}
