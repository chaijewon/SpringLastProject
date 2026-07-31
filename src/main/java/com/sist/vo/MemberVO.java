package com.sist.vo;

import lombok.Data;

/*
 *      USERID   NOT NULL VARCHAR2(20) 
		USERNAME NOT NULL VARCHAR2(50) 
		USERPWD  NOT NULL VARCHAR2(10) 
		ENABLE            NUMBER(1)    
		SEX               VARCHAR2(6) 
 */
@Data
public class MemberVO {
   private String userid,username,userpwd,sex;
   private int enable;
}
