package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface FoodMapper {
  /*@Select("SELECT no,poster,address,name "
		 +"FROM food "
		 +"ORDER BY no ASC "
		 +"OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
  public List<FoodVO> foodListData(int start);*/
  // MySQL => Limit #{start},12
  @Select("SELECT no,poster,address,name,num "
		 +"FROM (SELECT no,poster,address,name,rownum as num "
		 +"FROM (SELECT no,poster,address,name "
		 +"FROM food ORDER BY no ASC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  // => JPA
  public List<FoodVO> foodListData(@Param("start") int start,
		       @Param("end") int end);
  
  @Select("SELECT CEIL(COUNT(*)/12.0) FROM food")
  public int foodTotalPage();
  
  @Select("SELECT no,poster,name,address,time,price,score,theme,"
		 +"content,parking "
		 +"FROM food "
		 +"WHERE no=#{no}")
  public FoodVO foodDetailData(int no);
}
