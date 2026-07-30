<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
  margin: 0px auto;
  width: 800px;
}
h3 {
  text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <h3>답변하기</h3>
      <form method="post" action="../board/reply_ok.do">
      <table class="table">
        <tr>
         <th width=15% class="text-center success">이름</th>
         <td width="85%">
           <input type=text name=name size=20 class="input-sm" required>
           <input type="hidden" name=pno value="${no }">
         </td>
        </tr>
        <tr>
         <th width=15% class="text-center success">제목</th>
         <td width="85%">
           <input type=text name=subject size=60 class="input-sm" required>
         </td>
        </tr>
        <tr>
         <th width=15% class="text-center success">내용</th>
         <td width="85%">
           <textarea rows="10" cols="62" name=content required></textarea>
         </td>
        </tr>
        <tr>
         <th width=15% class="text-center success">비밀번호</th>
         <td width="85%">
           <input type=password name=pwd size=10 class="input-sm" required>
         </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <button class="btn-sm btn-danger" type=submit>답변</button>
            <button class="btn-sm btn-danger" type=button
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