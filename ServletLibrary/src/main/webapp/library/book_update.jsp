<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="library.vo.BookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
<style>
        table, td, th {
            border : 1px solid black;
            border-collapse: collapse;
            margin: 20px auto;
        }
        td {
            width: 150px;
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
    	function afterReset() {
    		$("#fm_regist")[0].reset();
    	}
    </script>
</head>
<body>
<form action="/ServletLibrary/libraryService" method="post" id="fm_regist">
    <table>
        <tr><th colspan="4" id="form">도서등록</th></tr>
        <tr><th>구분</th><th class="data_ui" colspan="2">데이터입력</th><th>비고</th></tr>
        <tr>
            <td>도서번호</td>
            <td colspan="2">
            	<input type="text" id="book_seq" name="book_seq" value="${info.bookSeq}" readonly>
            </td>
            <td id="message">자동생성</td></tr>
        <tr>
        	<td>ISBN</td>
        	<td colspan="2">
        		<input type="text" id="isbn" name="isbn" value="${info.bookIsbn}" readonly>
        	</td>
        	<td>
        		<input type="hidden" id="flag" value="false">
        	</td>
        </tr>
        <tr>
        	<td>도서명</td>
        	<td colspan="2">
        		<input type="text" id="book_title" name="book_title" value="${info.bookTitle}">
        	</td><td></td>
        </tr>
        <tr>
        	<td>저자/역자</td>
        	<td colspan="2">
        		<input type="text" id="author" name="author" value="${info.bookAuthor}">
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판사</td>
        	<td colspan="2">
        		<input type="text" id="publisher" size="35" name="publisher" value="${info.bookPublisher}">
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판일</td>
        	<td colspan="2">
        		<fmt:formatDate value="${info.bookPublishDate }" pattern="yyyy" var="pubYear"/>
        		<fmt:formatDate value="${info.bookPublishDate }" pattern="MM" var="pubMonth"/>
        		<fmt:formatDate value="${info.bookPublishDate }" pattern="dd" var="pubDate"/>
        		<select id="year" name="year">
        			<c:forEach begin="1920" end="2024" step="1" var="year">
        				<option value="${year }" <c:if test="${pubYear == year }">selected</c:if>>${year }</option>
        			</c:forEach>
       			</select>
       			-
       			<select id="month" name="month">
       				<c:forEach begin="1" end="13" step="1" var="month">
        				<option value="${month }" <c:if test="${pubMonth == month }">selected</c:if>>${month }</option>
	       			</c:forEach>
       			</select>
       			-
       			<select id="date" name="date">
	       			<c:forEach begin="1" end="31" step="1" var="date">
        				<option value="${date }" <c:if test="${pubDate == date }">selected</c:if>>${date }</option>
	       			</c:forEach>
       			</select>
        		<%-- <input type="text" id="publish_date" size="35" name="publish_date" value="<%=book.getBookPublishDate()%>"> --%>
        	</td>
        	<td></td>
        <tr>
        <tr>
        	<td>도서위치</td>
        	<td colspan="2">
        		<select name="book_position">
        			<option value='BS' <c:if test="${info.bookPosition == 'BS' }">selected</c:if>>--도서 위치--
        			<option value='BS-0001' <c:if test="${info.bookPosition == 'BS-0001' }">selected</c:if>>일반서가
        			<option value='BS-0002' <c:if test="${info.bookPosition == 'BS-0002' }">selected</c:if>>예약서가
        			<option value='BB-' <c:if test="${info.bookPosition == 'BB-' }">selected</c:if>>회원
        		</select>
        	</td>
        	<td>기본값삽입</td>
        <tr>
        <tr>
        	<td>도서상태</td>
        	<td colspan="2">
        		<select name="book_status">
        			<option value='BM' <c:if test="${info.bookStatus == 'BS' }">selected</c:if>>--도서 상태--
        			<option value='BM-0001' <c:if test="${info.bookStatus == 'BM-0001' }">selected</c:if>>도서대출서비스
        			<option value='BM-0002' <c:if test="${info.bookStatus == 'BM-0002' }">selected</c:if>>도서수선
        			<option value='BM-0003' <c:if test="${info.bookStatus == 'BM-0003' }">selected</c:if>>도서저장고
        		</select>
        	</td>
        	<td>기본값삽입</td>
        <tr>
        <tr>
        	<td colspan="4" id="sending">
        		<input type="submit" value="도서등록"> 
        		<input type="reset" onclick="afterReset()">
        		<a href="./libraryService"><button type="button">도서리스트</button></a>
        	</td>
        </tr>
    </table>
</form>
</body>
</html>