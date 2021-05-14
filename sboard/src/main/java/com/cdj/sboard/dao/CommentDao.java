package com.cdj.sboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.cdj.sboard.entity.Comment;

public interface CommentDao {
	@Insert("insert into comments values(comment_seq.nextval, #{bno}, #{writer}, #{content}, sysdate, #{profile})")
	public int insert(Comment comment);
	
	@Select("select /*+ index_desc(comments comment_pk_cno) */ * from comments where bno=#{bno}")
	public List<Comment> findAllByBno(int bno);

	@Delete("delete from comments where cno=#{cno}")
	public int deleteByCno(Integer cno);
	
	@Delete("delete from comments where bno=#{bno}")
	public void deleByBno(Integer bno);
}
