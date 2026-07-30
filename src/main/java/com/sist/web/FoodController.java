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
 
  @GetMapping("food/detail_before.do")
  public String food_detail_before(int no,
		  HttpServletResponse response,
		  RedirectAttributes ra)
  {
	  
	  Cookie cookie=new Cookie("food_"+no, String.valueOf(no));
	  //                       String , String => 
	  cookie.setPath("/");
	  cookie.setMaxAge(60*60*24);
	  response.addCookie(cookie);
	  ra.addAttribute("no", no); // ?no=1
	  return "redirect:../food/detail.do";
	  // 
  }
  @GetMapping("food/detail.do")
  /*
   *   <form> => get / post
   * 
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








