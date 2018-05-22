package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.travel.bean.Comment;

public interface CommentMapper {
	@Select("select * from t_comment where groupid = #{groupid} and guideid = #{guideid}" +
			" and commentid = #{commentid} and content = #{content}")
	Comment getCommentById(Comment comment);

	@Insert("insert into t_comment (commentid, guideid, groupid, guidecomment, tourcomment, content)" +
			"values(#{commentid},#{guideid},#{groupid},#{guidecomment},#{tourcomment},#{content})")
	int addComment(Comment comment);

	@Select("select * from t_comment where commentid = #{commentid} and groupid = #{groupid}")
	Comment getCommentByUserIdAndGroupId(Comment comment);

	@Select("select * from t_comment where groupid = #{groupid}")
	List<Comment> getCommentByGroupId(Long groupid);

	@Update("update t_comment set guidecomment= #{0}, tourcomment = #{1} ,content = #{2} where id = #{3}")
	int updateComment(float guidecomment,float tourcomment,String content,long id);

	@Select("select * from t_comment where groupid = #{groupid} and commentid = #{commentid}")
	Comment getComment(Comment com);
}