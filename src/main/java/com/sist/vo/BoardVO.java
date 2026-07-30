package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
 *   NO         NOT NULL NUMBER         
NAME       NOT NULL VARCHAR2(51)   
SUBJECT    NOT NULL VARCHAR2(2000) 
CONTENT    NOT NULL CLOB           
PWD        NOT NULL VARCHAR2(10)   
REGDATE             DATE           
HIT                 NUMBER         
GROUP_ID            NUMBER         
GROUP_STEP          NUMBER         
GROUP_TAB           NUMBER         
ROOT                NUMBER         
DEPTH               NUMBER  
                         DESC  ASC
                    no    gi   gs  gt  root  depth
         AAAAA      1     1    0    0   0     2
          => BBBBB  2     1    1    1   1     2
          => DDDD   5     1    1    1   1     0
           => DDDD  4     1    2    2   2     0
           => CCCC  3     1    3    2   2     0
         일괄 처리 
         ------- Transaction 
         
          insert  => commit(X)
          insert  => commit(X)
          insert  => commit(X)
          commit
          
          => rollback
          => 금융권 => SI (65%) / 공기업 (20%) / 대기업
           
          
 */
@Data
public class BoardVO {
   private int no,hit,group_id,group_step,group_tab,root,depth;
   private String name,subject,content,pwd,dbday;
   private Date regdate;
}
