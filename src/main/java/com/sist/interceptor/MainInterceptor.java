package com.sist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 *                                           | => preHandle
 *                                                => 자동로그인 / ID저장
 *    사용자    ========= DispatcherServlet ======== HandlerMapping 
 *   main.do                                           |
 *                                                  @GetMapping("main.do")
 *                                                  public String main()
 *                                                  {
 *                                                     =====
 *                                                     =====
 *                                                     return  ==> ViewResolver 전송 
 *                                                  }
 *                                                  || => postHandle
 *                                                  |
 *                                                ViewResolver
 *                                                  | ===> afterCompletion
 *                                                 JSP
 */
public class MainInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("⏰ preHandle() Call...");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("⏰ postHandle() Call...");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("⏰ afterCompletion() Call...");
		super.afterCompletion(request, response, handler, ex);
	}
    
}
