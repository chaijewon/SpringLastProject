package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
/*             gi   gt  gs  depth
 *    AAAA      1   0   0
 *     => DDDD  1   1   1
 *     => BBBB  1   2   2
 *      => CCCC 1   3   3
 *    FFFF      2   0   0
 *     
 *     
 *     
 *     
 */
public interface BoardMapper {
  @Select("SELECT no,subject,name,"
  		+ "TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit,group_tab "
		+ "FROM springReplyBoard "
  		+ "ORDER BY group_id DESC , group_step ASC "
		+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
  public List<BoardVO> boardListData(int start);
  
  @Select("SELECT COUNT(*) FROM springReplyBoard")
  public int boardRowCount();
  
  @Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id) "
		 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
		 +"#{content},#{pwd},"
		 +"(SELECT NVL(MAX(group_id)+1,1) FROM springReplyBoard))")
  public void boardInsert(BoardVO vo);
  
  // 상세보기 
  @Update("UPDATE springReplyBoard SET "
		 +"hit=hit+1 "
		 +"WHERE no=#{no}")
  public void boardHitIncrement(int no);
  
  @Select("SELECT no,name,subject,content,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit "
		 +"FROM springReplyBoard "
		 +"WHERE no=#{no}")
  public BoardVO boardDetailData(int no);
  // 답변하기 ===> Transaction
  // 1. 상위 데이터를 읽기 
  @Select("SELECT group_id,group_step,group_tab "
		 +"FROM springReplyBoard "
		 +"WHERE no=#{no}")
  public BoardVO boardParentInfoData(int no);
  // 2. UPDATE 
  @Update("UPDATE springReplyBoard SET "
		 +"group_step=group_step+1 "
		 +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
  public void boardStepIncrement(@Param("group_id") int group_id,
		  @Param("group_step") int group_step);
  // Map , VO
  // 3. INSERT 
  @Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root,depth) "
			 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
			 +"#{content},#{pwd},"
			 +"#{group_id},#{group_step},#{group_tab},#{root},#{depth})")
  public void boardReplyInsert(BoardVO vo);
  // 4. UPDATE 
  
  @Update("UPDATE springReplyBoard SET "
		 +"depth=depth+1 "
		 +"WHERE no=#{no}")
  public void boardDepthIncrement(int no);
  /*
   *   group_id , step , tab  => 답변 형식 
   *   root depth => 삭제 
   *   
   *   group_id , => 답변 모음 
   *   step , 답변안에 출력 순서 
   *   tab 간격 조절 
   *   
   *   root : 어느 게시물의 답변인지 
   *   depth : 답변이 몇개인지 확인 
   */
  // 수정 
  // 삭제    ===> Transaction
  // 1. 정보 읽기 
  @Select("SELECT root,depth FROM springReplyBoard "
		 +"WHERE no=#{no}")
  public BoardVO boardInfoData(int no);
  
  // 2. 비밀번호 체크 
  @Select("SELECT pwd FROM springReplyBoard "
		 +"WHERE no=#{no}")
  public String boardGetPassword(int no);
  
  // 3. 결과 ==> 답변이 있는 경우 
  @Update("UPDATE springReplyBoard SET "
		 +"subject=#{subject},content=#{content} "
		 +"WHERE no=#{no}")
  public void boardMsgUpdate(BoardVO vo);
  // ==> 답변이 없는 경우 
  @Delete("DELETE FROM springReplyBoard "
		 +"WHERE no=#{no}")
  public void boardDelete(int no);
  
  // 4. 상위 게시물 depth 감소 
  @Update("UPDATE springReplyBoard SET "
		 +"depth=depth-1 "
		 +"WHERE no=#{no}")
  public void boardDepthDecrement(int no);
}
