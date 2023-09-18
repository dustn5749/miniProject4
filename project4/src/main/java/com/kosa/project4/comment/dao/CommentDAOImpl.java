package com.kosa.project4.comment.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosa.project4.comment.model.Comment;

@Repository("commentDao")
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 게시글에 대한 댓글 리스트 가져오기
	@Override
	public List<Comment> getCommentList(int boardNum, Comment comment) throws Exception {
		System.out.println("CommentDAO.getCommentList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentnum", boardNum);
		map.put("boardnum", comment.getBoardnum());
		sqlSession.selectList("mapper.comment.getCommentList", map);
		List<Comment> commentList = (List<Comment>) map.get("p_outmsg");
		return commentList;
	}

	// 댓글 추가하기
	@Override
	public boolean add(Comment commnet) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", commnet.getId());
		map.put("content", commnet.getContent());
		map.put("parentnum", commnet.getParentnum());
		System.out.println("comment = " + commnet);

		sqlSession.selectOne("mapper.comment.insertComment", map);
		String result = (String) map.get("p_outmsg");
		
		return result.equals("success") ;
	}

	// 댓글 삭제하기
	@Override
	public boolean delete(int boardnum) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardnum", boardnum);

		System.out.println("comment.boardnum = " + boardnum);

		sqlSession.selectOne("mapper.comment.deleteComment", map);
		String result = (String) map.get("p_outmsg");
		
		return result.equals("success") ;
	}

	// 댓글 수정하기
	@Override
	public boolean update(Comment comment) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardnum", comment.getBoardnum());
		map.put("content", comment.getContent());

		System.out.println("comment.boardnum = " + comment.getBoardnum());

		sqlSession.selectOne("mapper.comment.updateComment", map);
		String result = (String) map.get("p_outmsg");
		
		return result.equals("success") ;	
		}

	//댓글 총 갯수 
	@Override
	public int totalComment(int boardnum) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentnum", boardnum);
		System.out.println("totalComment.parentnum = " + boardnum);
		sqlSession.selectOne("mapper.comment.totalCount", map);
		String result=   (String) map.get("p_outmsg");
		System.out.println("result = " + result);
		return Integer.parseInt(result);
	}


}
