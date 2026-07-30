package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MainController {
   private final FoodService fService;
	
   @GetMapping("main/main.do")
   public String main_main(String page,Model model,HttpServletRequest request)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   final int ROWSIZE=12;
	   int start=(ROWSIZE*curpage)-(ROWSIZE-1);
	   int end=ROWSIZE*curpage;
	   // OFFSET  /  rownum 
	   //  | 0       | 1
	   List<FoodVO> list=fService.foodListData(start, end);
	   int totalpage=fService.foodTotalPage();
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   //              ------- 10
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   
	   model.addAttribute("main_jsp", "../main/home.jsp");
	   
	   List<FoodVO> cList=new ArrayList<FoodVO>();
	   Cookie[] cookies=request.getCookies();
	   if(cookies!=null)
	   {
		   
		   for(int i=cookies.length-1;i>=0;i--)
		   {
			   if(cookies[i].getName().startsWith("food_"))
			   {
				   if(cookies[i].getName().equals("food_null"))
					   continue;
				   
				   String no=cookies[i].getValue();
				   
				   FoodVO vo=fService.foodDetailData(Integer.parseInt(no));
				   
				   cList.add(vo);
			   }
		   }
	   }
	   model.addAttribute("cList", cList);
	   model.addAttribute("size", cList.size());
	   
	   return "main/main";
   }
}
