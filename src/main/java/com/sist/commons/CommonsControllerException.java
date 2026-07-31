package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonsControllerException {
   @ExceptionHandler(Exception.class)
   public void exception(Exception ex)
   {
	   System.out.println("====== Controller 예외발생 ======");
	   ex.printStackTrace();
   }
   @ExceptionHandler(Throwable.class)
   public void throwable(Throwable ex)
   {
	   System.out.println("====== Controller 에러발생 ======");
	   ex.printStackTrace();
   }
}
