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
      <h3>내용보기</h3>
      <table class="table">
        <tr>
          <th width=20% class="text-center success">번호</th>
          <td width=30% class="text-center">${vo.no }</td>
          <th width=20% class="text-center success">작성일</th>
          <td width=30% class="text-center">${vo.dbday }</td>
        </tr>
        <tr>
          <th width=20% class="text-center success">이름</th>
          <td width=30% class="text-center">${vo.name }</td>
          <th width=20% class="text-center success">조회수</th>
          <td width=30% class="text-center">${vo.hit }</td>
        </tr>
        <tr>
          <th width=20% class="text-center success">제목</th>
          <td colspan="3">${vo.subject }</td>
        </tr>
        <tr>
          <td colspan="4" class="text-left" valign="top" height="200">
            <pre style="white-space: pre-wrap;border: none;background: white;">${vo.content }</pre>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="text-right">
            <a href="../board/reply.do?no=${vo.no }" class="btn btn-xs btn-warning">답변</a>
            <a href="#" class="btn btn-xs btn-info">수정</a>
            <a href="#" class="btn btn-xs btn-success">삭제</a>
            <a href="../board/list.do" class="btn btn-xs btn-danger">목록</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>





