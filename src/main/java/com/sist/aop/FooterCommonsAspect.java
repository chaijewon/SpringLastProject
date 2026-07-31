package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;
@Aspect // 공통모듈 => 메모리 할당이 안된다 
@Component // 메모리 할당 
@RequiredArgsConstructor
/*
 *    1. 메소드 어느 위치에서 호출  : JoinPoint
 *       public String display()
 *       {
 *           ======> @Before
 *           try
 *           {
 *               ============== @Around (before)
 *                 소스 코딩 ===> 핵심 관심사 
 *               
 *               ============== @Around (after)
 *              
 *           }catch(Exception ex)
 *           {
 *              ======> @AfterThrowing
 *           }
 *           finally
 *           {
 *              ======> @After  
 *           }
 *           
 *           return "" ====> @AfterReturning
 *       } 
 *    2. 어떤 메소드 : PointCut
 *                  * 패키지.클래스.메소드(매개변수)
 *                 --- 리턴형 
 *    ------------------------------------ + Advice
 *    3. 언제 통합 : Weaving 
 *    
 *    => 전송 (브라우저) 
 *       | Model 
 *       | HttpServletRequest
 *         -------------------- DispatcherServlet <===>
 *                                                @Controller
 *                                                @RestController
 */
public class FooterCommonsAspect {
   private final FoodService fService;
   
   @After("execution(* com.sist.web.*Controller.*(..))")
   public void sendData()
   {
	   // 현재 사용중인 request를 얻어 온다 
	   HttpServletRequest request=
			   ((ServletRequestAttributes)
					   RequestContextHolder.getRequestAttributes()).getRequest();
	   List<FoodVO> fList=fService.foodHit7Data();
	   request.setAttribute("fList", fList);
   }
   @Around("execution(* com.sist.web.*Controller.*(..))")
   public Object log(ProceedingJoinPoint jp)
   throws Throwable
   {
	   // 로그 파일 
	   Object obj=null;
	   System.out.println("사용자 요청:"+jp.getSignature().getName());
	   obj=jp.proceed();
	   System.out.println("사용자 요청 완료:"+jp.getSignature().getName());
	   return obj;
   }
}
