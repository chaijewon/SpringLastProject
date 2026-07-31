package com.sist.web;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.service.*;
@Controller
@RequiredArgsConstructor
public class BoardController {
   private final BoardService bService;
   
   @GetMapping("board/list.do")
   public String board_list(String page, Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   final int ROWSIZE=10;
	   int start=(ROWSIZE*curpage)-ROWSIZE;
	   List<BoardVO> list=bService.boardListData(start);
	   int count=bService.boardRowCount();
	   int totalpage=(int)(Math.ceil(count/10.0));// 총페이지 
	   count=count-((curpage*ROWSIZE)-ROWSIZE);
	   /*
	    *  count  == 20   => 20 19...
	    *            10   => 10 9...
	    *  
	    */
	   
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("count", count);
	   model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	   
	   model.addAttribute("main_jsp", "../board/list.jsp");
	   return "main/main";
   }
   @GetMapping("board/insert.do")
   public String board_insert(Model model) 
   {
	   model.addAttribute("main_jsp", "../board/insert.jsp");
	   return "main/main";
   }
   @PostMapping("board/insert_ok.do")
   public String board_insert_ok(BoardVO vo)
   {
	 bService.boardInsert(vo);
	 return "redirect:../board/list.do";   
   }
   @GetMapping("board/detail.do")
   public String board_detail(int no,Model model)
   {
	   BoardVO vo=bService.boardDetailData(no);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../board/detail.jsp");
	   return "main/main";
   }
   @GetMapping("board/reply.do")
   public String board_reply(int no,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("main_jsp", "../board/reply.jsp");
	   return "main/main";
   }
   @PostMapping("board/reply_ok.do")
   public String board_reply_ok(int pno,BoardVO vo)
   {
	   bService.boardReplyInsert(pno, vo);
	   return "redirect:../board/list.do";
   }
   
   @GetMapping("board/delete.do")
   public String board_delete(int no,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("main_jsp", "../board/delete.jsp");
	   return "main/main";
   }
   /*
    *   @Controller ======> Router
    *   1. 화면 출력 => 데이터가 필요 (request) => request유지 
    *      => return "main/main";
    *         => forward
    *   2. 기존 화면 이동 => 데이터가 필요 없다 (request를 초기화)
    *      => return "redirect:../board/list.do";
    *         => sendRedirect
    *         
    *            |=> 요청값
    *   1) 서버 ====== 브라우저 
    *            |=> 응답데이터 (결과)
    *   2) 전송시에 메소드 방식 (GET/POST) 
    *   3) request초기화 / 유지 
    *   4) JSP(.do) => Mapper => Service => Controller => JSP
    *                    |         |
    *                    -----------
    *                      | 없는 경우도 있다
    *                      
    */
   
}
