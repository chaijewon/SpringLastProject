<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
  margin: 0px auto;
  width: 900px;
}
h3 {
  text-align: center;
}
</style>
</head>
<body>
   <div class="container">
    <div class="row">
     <h3>묻고 답하기</h3>
     <table class="table">
       <tr>
        <td>
         <a href="../board/insert.do" class="btn btn-sm btn-primary">등록</a>
        </td>
       </tr>
     </table>
     <table class="table table-hover">
       <tr class="success">
         <th width=10% class="text-center">번호</th>
         <th width=45% class="text-center">제목</th>
         <th width=15% class="text-center">이름</th>
         <th width=20% class="text-center">작성일</th>
         <th width=10% class="text-center">조회수</th>
       </tr>
       <c:set var="count" value="${count }"/>
       <c:forEach var="vo" items="${list }">
       <tr>
         <td width=10% class="text-center">${count }</td>
         <td width=45%>
         <c:if test="${vo.group_tab>0 }">
          <c:forEach var="i" begin="1" end="${vo.group_tab }">
            &nbsp;&nbsp;
          </c:forEach>
          <img src="../board/re_icon.png">
         </c:if>
         <a href="../board/detail.do?no=${vo.no }">${vo.subject }</a>
         &nbsp; <c:if test="${vo.dbday==today }">
                 <sup><img src="../board/new.gif"></sup>
                </c:if>
         </td>
         <td width=15% class="text-center">${vo.name }</td>
         <td width=20% class="text-center">${vo.dbday }</td>
         <td width=10% class="text-center">${vo.hit }</td>
       </tr>
       <c:set var="count" value="${count-1 }"/>
       </c:forEach>
     </table>
    </div>
   </div>
</body>
</html>