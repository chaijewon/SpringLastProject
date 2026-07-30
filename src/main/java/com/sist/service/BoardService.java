package com.sist.service;

import java.util.List;
import com.sist.vo.BoardVO;

public interface BoardService {
	  public List<BoardVO> boardListData(int start);
	  public int boardRowCount();
	  public void boardInsert(BoardVO vo);
	  // 상세보기 
	  public BoardVO boardDetailData(int no);
	  // 답변하기 ===> Transaction
	  public void boardReplyInsert(int pno,BoardVO vo);
	 
}
