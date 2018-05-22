package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Comment;
import com.travel.dao.CommentMapper;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;

	public Comment getCommentById(Comment comment) {
		return commentMapper.getCommentById(comment);
	}

	public int addComment(Comment comment) {
		return commentMapper.addComment(comment);
	}

	public Comment getCommentByUserIdAndGroupId(Comment comment) {
		return commentMapper.getCommentByUserIdAndGroupId(comment);
	}

	public List<Comment> getCommentByGroupId(Long groupid) {
		List<Comment> res = commentMapper.getCommentByGroupId(groupid);
		return res;
	}

	public Comment getCommentByTwoId(Comment comment) {
		return commentMapper.getCommentByUserIdAndGroupId(comment);
	}

	public int updateComment(float guidecomment,float tourcomment,String content,long id) {
		return commentMapper.updateComment(guidecomment,tourcomment,content,id);
	}

	public Comment getComment(Comment com) {
		return commentMapper.getComment(com);
	}
}
