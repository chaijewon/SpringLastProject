package com.sist.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.service.*;
@RestController  // 데이터만 전송 : JSON / script / 일반 문자열 
@RequiredArgsConstructor
public class BoardRestController {
   private final BoardService bService; // 싱글턴 
   
   @PostMapping(value="board/delete_ok.do",produces = "text/html;charset=UTF-8")
   public String board_delete_ok(int no,String pwd)
   {
	   String result="";
	   boolean bCheck=bService.boardDelete(no, pwd);
	   if(bCheck==true)
	   {
		  result="<script>"
				+"location.href=\"../board/list.do\""
				+"</script>";
	   }
	   else
	   {
		   result="<script>"
				 +"alert(\"비밀번호가 틀립니다!!\");"
				 +"history.back();"
				 +"</script>";
	   }
	   return result;
   }
}







