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
      <h3>회원가입</h3>
      <form method=post action="../member/join_ok.do">
      <table class="table">
        <tr>
          <th width=25% class="text-center">ID</th>
          <td width=75%>
            <input type=text name=userid class="input-sm" size=20>
          </td>
        </tr>
        <tr>
          <th width=25% class="text-center">PW</th>
          <td width=75%>
            <input type=password name=userpwd class="input-sm" size=20>
          </td>
        </tr>
        <tr>
          <th width=25% class="text-center">이름</th>
          <td width=75%>
            <input type=text name=username class="input-sm" size=20>
          </td>
        </tr>
        <tr>
          <th width=25% class="text-center">성별</th>
          <td width=75%>
            <input type="radio" name=sex value="남자" checked>남자
            <input type="radio" name=sex value="여자">여자
          </td>
        </tr>
        <tr>
         <td colspan="2" class="text-center">
           <button type=submit class="btn-sm btn-primary">회원가입</button>
           <button type=button class="btn-sm btn-primary"
            onclick="javascript:history.back()"
           >취소</button>
         </td>
       </tr>
      </table>
      </form>
    </div>
  </div>
</body>
</html>