package com.sist.web;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;
@Controller 
@RequiredArgsConstructor
public class FoodController {
  private final FoodService fService;
  /*@Autowired
  public FoodController(FoodService fService)
  {
	  this.fService=fService;
  }*/
  /*
   *   1. 전송 => ?변수
   *   2. 커맨드 객체 => VO (회원가입 , 회원수정 , 글쓰기)
   *   3. 내장 객체 
   *      1) HttpSession 
   *      2) Cookie => 저장 : response 
   *                   읽기 : request 
   */
  @GetMapping("food/detail_before.do")
  public String food_detail_before(int no,
		  HttpServletResponse response,
		  RedirectAttributes ra)
  {
	  // 쿠키 생성 
	  Cookie cookie=new Cookie("food_"+no, String.valueOf(no));
	  //                       String , String => 문자열만 저장이 가능 
	  cookie.setPath("/");
	  cookie.setMaxAge(60*60*24);
	  response.addCookie(cookie);
	  ra.addAttribute("no", no); // ?no=1
	  return "redirect:../food/detail.do";
	  // => 조회수 증가 / 쿠키 저장된 값 출력 (back()(X)) 
  }
  @GetMapping("food/detail.do")
  /*
   *   <form> => get / post
   *   나머지 태그는 get
   *   location.href => get 
   *   redirect: => get
   *   
   *   ajax : get / post 
   *   axios : axios.get() axios.post()
   *    
   */
  public String food_detail(int no,Model model)
  {
	  FoodVO vo=fService.foodDetailData(no);
	  model.addAttribute("vo", vo);
	  model.addAttribute("main_jsp", "../food/detail.jsp");
	  return "main/main";
  }
  
}








