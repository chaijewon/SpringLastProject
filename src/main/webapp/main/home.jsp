<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
   margin: 0px auto;
   width: 960px;
}
p{
   overflow: hidden;
   white-space: nowrap;
   text-overflow: ellipsis;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
     <c:forEach var="vo" items="${list }">
	      <div class="col-sm-3">
	        <div class="thumbnail">
	         <a href="../food/detail_before.do?no=${vo.no }">
	         <img src="${vo.poster }" title="${vo.address }" style="width: 250px;height: 130px;object-fit:cover">
	         <p>${vo.name }</p>
	         </a>
	        </div>
	      </div>
     </c:forEach>
    </div>
    <div class="row text-center" style="margin-top: 10px">
      <ul class="pagination">
       <c:if test="${startPage>1 }">
        <li><a href="../main/main.do?page=${startPage-1 }">&laquo;</a></li>
       </c:if>
       
       <c:forEach var="i" begin="${startPage }" end="${endPage }">
         <li ${i==curpage?"class=active":"" }>
           <a href="../main/main.do?page=${i }">${i }</a></li>
       </c:forEach>
        
       <c:if test="${endPage<totalpage }">
        <li><a href="../main/main.do?page=${endPage+1 }">&raquo;</a></li>
       </c:if>
      </ul>
    </div>
    <div class="row" style="margin-top: 10px">
      <h3>최근 방문 맛집</h3>
      <hr>
      <c:if test="${size<1}">
        <h3>방문 기록이 없습니다</h3>
      </c:if>
      <c:if test="${size>0 }">
        <c:forEach var="cvo" items="${cList }">
         <div style="width: 100px;height: 100px;margin-left:3px;display:inline-block; ">
           <img src="${cvo.poster }" style="width: 100px;height: 100px;">
         </div>
        </c:forEach>
      </c:if>
    </div>
  </div>
</body>
</html>