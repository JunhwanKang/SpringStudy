package com.cdj.sboard.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdj.sboard.dao.BoardDao;
import com.cdj.sboard.dto.Page;

@Component
public class PaginationUtil {
	public Map<String, Object> getRowNum(int pageNo, int count) {
		// pageNo	startRowNum			endRowNum
		// 1			1					10
		// 2			11					20
		// 3			21					30
		int startRowNum = (pageNo-1) * BoardConstant.BOARD_PER_PAGE + 1; 
		int endRowNum = startRowNum + BoardConstant.BOARD_PER_PAGE - 1;
		if(endRowNum>count)
			endRowNum = count;
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return map;
	}
	
	public Page getPage(int pageNo, int count) {
		// 페이지 당 블록 : 5
		
		
		
		// 블록번호		이전		시작페이지			마지막페이지		다음
		//	0			 0			1					5			 6
		//	1			 5			6					10			11
		//	2			 10			11					15			16
		//				*5		  이전+1				   이전+5		마지막+1
		
		// 만약 페이지 개수가 13이라면 
		//  2			 10			11					13			 0
		
		// 만약 페이지 개수가 15이라면
		//  2			 10			11					15			 0
		
		// 글의 개수가 127->13, 128->13, 129->13, 130->13
		int countOfPage = count/BoardConstant.BOARD_PER_PAGE + 1;
		if(countOfPage%BoardConstant.BOARD_PER_PAGE==0)
			countOfPage--;
		if(pageNo>countOfPage)
			pageNo=countOfPage;
		
		// pageNo를 가지고 블록번호를 계산할 수 있다 
		// pageNo				blockNo
		// 1 2 3 4 5				0
		// 6 7 8 9 10				1
		// 11 12 13 14 15			2
		int blockNo = (pageNo-1)/BoardConstant.PAGE_PER_BLOCK;
		
		int prev = blockNo*BoardConstant.PAGE_PER_BLOCK;
		int start = prev + 1;
		int end = prev + BoardConstant.PAGE_PER_BLOCK;
		int next = end + 1;
		
		if(end>=countOfPage) {
			end = countOfPage;
			next = 0;
		}
		
		return Page.builder().pageNo(pageNo).prev(prev).start(start).end(end).next(next).build();
	}
}
