package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travel.bean.Comment;
import com.travel.bean.CommentShow;
import com.travel.bean.Group;
import com.travel.service.CommentService;
import com.travel.service.GroupService;

@Controller
@RequestMapping(value = "comment", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	GroupService groupService;
	
	@RequestMapping("saveComment")
	public JSONObject saveComment(float guidecomment,float tourcomment,long commentid,
			long guideid,long groupid,String content){
		System.out.println(guidecomment);
		Comment comment = new Comment();
		comment.setGroupid(groupid);
		comment.setCommentid(commentid);
		comment.setGuideid(guideid);
		comment.setGuidecomment(guidecomment);
		comment.setTourcomment(tourcomment);
		comment.setContent(content);
		
		Comment com = new Comment();
		com.setGroupid(groupid);
		com.setCommentid(commentid);
		
		Comment c = commentService.getComment(com);
		if(c != null){
			int res = commentService.updateComment(guidecomment,tourcomment,content,c.getId());
			System.out.println(commentService.getComment(com).getGuidecomment());
			return getResult(1, "修改成功", "");
		}else{
			int res = commentService.addComment(comment);
			if(res == 1){
				return getResult(res, "评论成功", "");
			}else{
				return getResult(-2, "评论失败", "");
			}
		}
	}
	
	@RequestMapping("getMyComment")
	public JSONObject getMyComment(long commentid,long groupid){
		Comment comment = new Comment();
		comment.setGroupid(groupid);
		comment.setCommentid(commentid);
		
		Comment c = commentService.getCommentByTwoId(comment);
		if(c != null){
			return getResult(1, "查询成功", JSON.toJSONString(c));
		}else{
			return getResult(1, "查询成功", "");
		}
	}
	
	@RequestMapping("getComment")
	public JSONObject getComment(long userid){
		float guidecomment = 0;
		float tourcomment = 0;
		List<Group> groups = groupService.queryGroups(userid);
		for (Group group : groups) {
			guidecomment = 0;
			tourcomment = 0;
			List<Comment> comments = commentService.getCommentByGroupId(group.getId());
			for(Comment c : comments){
				guidecomment = guidecomment + c.getGuidecomment();
				tourcomment = tourcomment + c.getTourcomment();
			}
			if(guidecomment != 0){
				guidecomment = guidecomment / comments.size();
			}
			if(tourcomment != 0){
				tourcomment = tourcomment / comments.size();
			}
			CommentShow commentShow = new CommentShow();
			commentShow.setGuidecomment(guidecomment);
			commentShow.setTourcomment(tourcomment);
			group.setCommentShow(commentShow);
		}
		return getResult(1, "查询成功", JSON.toJSONString(groups));
	}
	
	@RequestMapping("getAllComment")
	public JSONObject getAllComment(long userid, long groupid){
		float guidecomment = 0;
		float tourcomment = 0;
		List<Comment> comments = commentService.getCommentByGroupId(groupid);
		for(Comment c : comments){
			guidecomment = guidecomment + c.getGuidecomment();
			tourcomment = tourcomment + c.getTourcomment();
		}
		if(guidecomment != 0){
			guidecomment = guidecomment / comments.size();
		}
		if(tourcomment != 0){
			tourcomment = tourcomment / comments.size();
		}
		CommentShow commentShow = new CommentShow();
		commentShow.setGuidecomment(guidecomment);
		commentShow.setTourcomment(tourcomment);
		return getResult(1, "查询成功", JSON.toJSONString(commentShow));
	}
	
	@RequestMapping("getComments")
	public JSONObject getComments(long groupid){
		List<Comment> comments = commentService.getCommentByGroupId(groupid);
		return getResult(1, "查询成功", JSON.toJSONString(comments));
	}
	
	public static JSONObject getResult(int status, String msg, String data) {
		JSONObject jsb = new JSONObject();
		jsb.put("status", status);
		jsb.put("msg", msg);
		jsb.put("data", data);
		return jsb;
	}
}
