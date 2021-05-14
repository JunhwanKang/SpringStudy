package com.cdj.sboard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdj.sboard.dao.BoardDao;
import com.cdj.sboard.dao.CommentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class BoardTest {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private CommentDao commentDao;
	
	//@Test
	public void findByBno() {
		assertEquals("æ»≥Á«œººø‰", boardDao.findByBno(1).getTitle());
	}
	
	@Test
	public void find() {
		assertEquals("GUEST123", commentDao.findAllByBno(20).get(1).getWriter());
	}
}
