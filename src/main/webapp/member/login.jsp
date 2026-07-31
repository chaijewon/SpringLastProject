<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
   margin: 0px auto;
   width: 400px;
}
h3 {
  text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
       <tr>
         <th width=20% class="text-center">ID</th>
         <td width=80%>
           <input type=text name="userid" size=15 class="input-sm">
         </td>
       </tr>
       <tr>
         <th width=20% class="text-center">PW</th>
         <td width=80%>
           <input type=password name="userpwd" size=15 class="input-sm">
         </td>
       </tr>
       <tr>
         <td colspan="2">
           <input type=checkbox name="remember-me">자동로그인
         </td>
       </tr>
       <tr>
         <td colspan="2" class="text-center">
           <button type=submit class="btn-sm btn-primary">로그인</button>
           <button type=button class="btn-sm btn-primary"
            onclick="javascript:history.back()"
           >취소</button>
         </td>
       </tr>
      </table>
    </div>
  </div>
</body>
</html>