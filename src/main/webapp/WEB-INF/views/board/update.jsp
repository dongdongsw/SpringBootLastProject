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
	 <!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>수정하기</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row text-center"  style="width: 800px; margin: 0px auto;">
            	<form action="/board/update_ok" method="post">
            	<table class="table">
            		<tbody>
            			<tr>
            				<th width="15%" class="danger">이름</th>
            				<td width="85%" class="text-left">
            					<input type="text" name="name" size="20" class="input-sm" required="required" value=${vo.name }>
            					<input type="hidden" name="no" value="${vo.no }">	
            				</td>
            				
            			</tr>
            			<tr>
            				<th width="15%" class="danger">제목</th>
            				<td width="85%" class="text-left">
            					<input type="text" name="subject" size="56" class="input-sm" required="required"  value=${vo.subject }>
            				</td>
            			</tr>
            			<tr>
            				<th width="15%" class="danger">내용</th>
            				<td width="85%" class="text-left">
            					<textarea rows="10" cols="60" name="content" required="required"  value=${vo.content }></textarea>
            				</td>
            			</tr>
            			<tr>
            				<th width="15%" class="danger">비밀번호</th>
            				<td width="85%" class="text-left">
            					<input type="password" name="pwd" size="10" class="input-sm" required="required">
            				</td>
            			</tr>
            			<tr>
            				<td class="text-center" colspan="2">
            					<button class="btn-sm btn-primary" type="submit">수정</button>
            					<button class="btn-sm btn-primary" onclick="javascript:history.back()" type="button">취소</button>            					
            				</td>
            			</tr>
            		</tbody>
            	</table>
            	</form>
           	</div>
        </div>
    </section>    

</body>
</html>