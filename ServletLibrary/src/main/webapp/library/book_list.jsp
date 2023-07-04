<%@page import="library.vo.BookVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<style>
        table, td, th {
            border : 1px solid black;
            border-collapse: collapse;
            margin: 20px auto;
        }
        td {
            /* width: 150px; */
            min-width: 100px;
            height: 50px;
            padding: 5px;
            font-size: 20px;
            /* text-align: center; */
        }

        input , select {
            font-size: 20px;
        }

        button {
            font-size: 15px;
            margin: 5px;
        }
        
        
        #sending {
        	text-align: center;
        }
        
        
        #form {
        	font-size: 30px;
        }

    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
<c:if test="${param.rental_cnt > 0 }">
	<script type="text/javascript">alert(${param.rental_cnt}+"권 예약되었습니다.");</script>
</c:if>
<form id="fm_list">
    <table>
        <tr><th colspan="5" id="form">도서리스트</th></tr>
        <tr id="after_list"><th>순번</th><th>타이틀</th><th>저자</th><th>출판일</th><th></th></tr>
        <c:choose>
        	<c:when test="list.isEmpty() || list == null">
        		<tr>
	        		<td colspan="5">목록이 존재하지 않습니다.</td>
	        	</tr>
        	</c:when>
        	<c:otherwise>
        		<c:forEach items="${list }" var="book">
        			<tr>
	        			<td>${book.bookSeq }</td>
	        			<td>
	        				<c:choose>
	        					<c:when test="${ROLE_CODE eq 'ADMIN' }">
	        						<a href="/ServletLibrary/libraryService?book_seq=${ book.bookSeq }">${ book.bookTitle }</a>
	        					</c:when>
	        					<c:otherwise>
	        						<a href="/ServletLibrary/libraryService?book_seq=${ book.bookSeq }&cmd=detail">${ book.bookTitle }</a>
	        					</c:otherwise>
        					</c:choose>
        				</td>
	        			<td>${ book.bookAuthor }</td>
	        			<td>
	        				<fmt:formatDate value="${ book.bookPublishDate}" pattern="yyyy-MM-dd" var="pubDate"/>${pubDate }
        				</td>
	        			<td>
	        				<c:choose>
	        					<c:when test="${ROLE_CODE eq 'ADMIN' }">
	        						<a href="/ServletLibrary/libraryService?cmd=delete&book_seq=${ book.bookSeq }&book_isbn=${ book.bookIsbn }">삭제</a>
	        					</c:when>
	        					<c:otherwise>
	        						<c:choose>
	        							<c:when test="${book.bookPosition eq 'BS-0001' }">
	        								<b style="color:green;">대출가능</b>
	        							</c:when>
	        							<c:when test="${book.bookPosition eq 'BS-0002' }">
	        								<b style="color:red;">대출불가</b>
	        							</c:when>
	        							<c:when test="${fn:contains(book.bookPosition, 'BB') }">
	        								<b style="color:blue;">예약가능</b>
	        							</c:when>
	        						</c:choose>
	        					</c:otherwise>
	        				</c:choose>
        				</td>
		        	</tr>
        		</c:forEach>
        	</c:otherwise>
        </c:choose>
        <%--<% if(list.isEmpty() || list == null) { %>
        	<tr>
        		<td colspan="5">목록이 존재하지 않습니다.</td>
        	</tr>
        <% } else { 
        	for(BookVO book : list) { %>
        		<tr>
        			<td><%= book.getBookSeq() %></td>
        			<td><a href="/ServletLibrary/libraryService?book_seq=<%= book.getBookSeq() %>"><%= book.getBookTitle() %></a></td>
        			<td><%= book.getBookAuthor() %></td>
        			<td><%= book.getBookPublishDate().getYear() + "-" + (book.getBookPublishDate().getMonth()+1) + "-" + book.getBookPublishDate().getDate() %></td>
        			<td><a href="/ServletLibrary/libraryService?cmd=delete&book_seq=<%= book.getBookSeq() %>&book_isbn=<%= book.getBookIsbn() %>">삭제</a></td>
	        	</tr>
        	<% }%>
        <% }%>--%>
        <c:if test="${ROLE_CODE eq 'ADMIN' }">
        	<tr><td colspan="5"><button type="button" onclick="window.location.href = '/ServletLibrary/libraryService?cmd=regist'">도서등록</button></td></tr>
        </c:if>
    </table>
</form>
</body>
</html>