package com.sist.service;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;
/*
 *   Mapper : 데이터베이스 연결 
 *     => 메뉴 
 *     
 *   Service : 여기의 SQL문장 조합해서 결과물 => BI(기능 통합)
 *     => 주방 
 *     
 *   Controller : Service에서 제공하는 결과값을 => 브라우저로 전송 
 *     => 서빙  
 *     
 *   JSP => 손님 
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper mapper; // 스프링에서 주소값 제공 

	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.boardListData(start);
	}
	
	@Override
	public int boardRowCount() {
		// TODO Auto-generated method stub
		return mapper.boardRowCount();
	}
	
	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
	    mapper.boardInsert(vo);	
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	@Override
	@Transactional   // => AOP적용 
	/*
	 *     외부(메소드 접근전)  내부(메소드 안)
	 *      | 인터셉트        | AOP => Transaction , Log 
	 *      | 자동로그인 / 알림 
	 *     public void boardReplyInsert(int pno,BoardVO vo) 
	 *     {
	 *        @Before => session.openSession()
	 *        try
	 *        {
	 *             conn.setAutoCommit(false) => @Around
	 *             BoardVO pvo=mapper.boardParentInfoData(pno);
	 *             mapper.boardStepIncrement(pvo.getGroup_id(), pvo.getGroup_step());	
	 *             mapper.boardReplyInsert(vo);
	 *             mapper.boardDepthIncrement(pno);
	 *             conn.commit()
	 *        }catch(Exception e)
	 *        {
	 *             conn.rollback() => @AfterThrowing
	 *        }
	 *        finally
	 *        {
	 *            conn.setAutoCommit(true) => @After
	 *        }  
	 *     }
	 */
	public void boardReplyInsert(int pno,BoardVO vo) {
		// TODO Auto-generated method stub
		/*
		 *   @Select("SELECT group_id,group_step,group_tab "
		              +"FROM springReplyBoard "
		              +"WHERE no=#{no}")
		 */
		BoardVO pvo=mapper.boardParentInfoData(pno);
        /*
         *   @Update("UPDATE springReplyBoard SET "
		      +"group_step=group_step+1 "
		      +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
         */
		mapper.boardStepIncrement(pvo.getGroup_id(), pvo.getGroup_step());	
		/*
		 *  @Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root,depth) "
			 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
			 +"#{content},#{pwd},"
			 +"(SELECT NVL(MAX(group_id)+1,1) FROM springReplyBoard),#{group_step},#{group_tab},#{root},#{depth})")
		 * 
		 */
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		vo.setDepth(0);
		mapper.boardReplyInsert(vo);
		/*
		 *  @Update("UPDATE springReplyBoard SET "
		 +"depth=depth+1 "
		 +"WHERE no=#{no}")
		 */
		mapper.boardDepthIncrement(pno);
	}
    /*
     *                         no  gi  gs  gt root depth
     *      AAAAAA             1    1  0    0  0     1
     *        = BBBBB          2    1  1    1  1     0
     *        = CCCCC          3    1  2    1  1     1
     *           = DDDDDD      4    1  3    2  3     0
     *      KKKKK              5    2  0    0  0     0
     */
	@Override
	@Transactional
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		boolean bCheck=false;
		BoardVO vo=mapper.boardInfoData(no);
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			bCheck=true;
			if(vo.getDepth()==0)
			{
				mapper.boardDelete(no);
			}
			else
			{
			    BoardVO bvo=new BoardVO();
			    bvo.setContent("관리자 삭제한 게시물입니다");
			    bvo.setSubject("관리자 삭제한 게시물입니다");
			    bvo.setNo(no);
			    
			    mapper.boardMsgUpdate(bvo);
			}
			
			mapper.boardDepthDecrement(vo.getRoot());
		}
		return bCheck;
		
	}	   
   
}




