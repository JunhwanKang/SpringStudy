package com.cdj.sboard.dao;

import java.util.List;
import java.util.Map;

import com.cdj.sboard.entity.Board;

public interface BoardDao {
	public int insert(Board board);
	
	public List<Board> findAll(Map<String, Object> map);
	
	public Board findByBno(int bno);

	public int update(Board board);
	
	public int count();
	
	public int delete(int bno);
}
