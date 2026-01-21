<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
      <tr>
         <td class="text-center"><h3>공지 목록</h3></td>
      </tr>
   </table>
   <table class="table">
   	<tr>
   		<td>
   			<a  href="/admin/notice_insert" class="btn btn-sm btn-primary" >등록</a>
   		</td>
   	</tr>
   </table>
   <table class="table">
   	<thead>
   		<tr class="success"> 
   			<th class="text-center"  width="10%">번호</th>
   			<th class="text-center"  width="45%">제목</th>
   			<th class="text-center"  width="15%">이름</th>
   			<th class="text-center"  width="20%">작성일</th>
   			<th class="text-center"  width="10%">조회수</th>
   		</tr>
   	</thead>
   	<tbody>
   		<c:forEach var="vo" items="${list }">
	   		<tr> 
	   			<td class="text-center"  width="10%">${vo.no }</td>
	   			<td width="45%"><a href="/admin/notice_detail?no=${vo.no }">[${vo.type}] &nbsp; ${vo.subject }</a></td> 
	   			<td class="text-center"  width="15%">${vo.name }</td>
	   			<td class="text-center"  width="20%">${vo.dbday }</td>
	   			<td class="text-center"  width="10%">${vo.hit }</td>
	   		</tr>
   		</c:forEach>
   		<tr>
   			<td colspan="5" class="text-center">
   				<c:if test="${curpage > 1 }">
   					<a href="/admin/notice_list?page=${curpage - 1 }" class="btn btn-sm btn-primary">이전</a>
   				</c:if>
				${curpage} page / ${totalpage } page
				<c:if test="${curpage < totalpage }">
   					<a href="/admin/notice_list?page=${curpage + 1 }" class="btn btn-sm btn-primary">다음</a>
   				</c:if>
   			</td>
   		</tr>
   	</tbody>
   </table>
</body>
</html>