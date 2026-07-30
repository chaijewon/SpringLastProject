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
   width: 960px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td width=30% class="text-center" rowspan="8">
            <img src="${vo.poster }" style="width: 290px;height: 250px">
          </td>
          <td colspan="2">
            <h3>${vo.name }&nbsp;<span style="color:orange;">${vo.score }</span></h3>
          </td>
        </tr>
        <tr>
          <td width=15% style="color:gray">주소</td>
          <td width=55%>${vo.address }</td>
        </tr>
        <tr>
          <td width=15% style="color:gray">전화</td>
          <td width=55%>${vo.phone }</td>
        </tr>
        <tr>
          <td width=15% style="color:gray">음식종류</td>
          <td width=55%>${vo.type }</td>
        </tr>
        <tr>
          <td width=15% style="color:gray">가격대</td>
          <td width=55%>${vo.price }</td>
        </tr>
        <tr>
          <td width=15% style="color:gray">영업시간</td>
          <td width=55%>${vo.time }</td>
        </tr>
        <tr>
          <td width=15% style="color:gray">주차</td>
          <td width=55%>${vo.parking }</td>
        </tr>
        <tr>
          <td width=15% style="color:gray">테마</td>
          <td width=55%>${vo.theme }</td>
        </tr>
        <tr>
          <td colspan="3">${vo.content }</td>
        </tr>
        <tr>
          <td colspan="3" class="text-right">
           <a href="../main/main.do" class="btn btn-sm btn-danger">목록</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>