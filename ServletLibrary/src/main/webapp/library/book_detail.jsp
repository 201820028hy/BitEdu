<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="library.vo.BookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<form action="/ServletLibrary/libraryService" method="get" id="fm_regist">
	<input type="hidden" name="book_seq" value="${info.bookSeq}">
	<input type="hidden" name="user_id" value="user2">
    <table>
        <tr><th colspan="4" id="form">도서등록</th></tr>
        <tr><th>구분</th><th class="data_ui" colspan="2">데이터입력</th><th>비고</th></tr>
        <tr>
        	<td>도서명</td>
        	<td colspan="2">
        		${info.bookTitle}
        	</td><td></td>
        </tr>
        <tr>
        	<td>저자/역자</td>
        	<td colspan="2">
        		${info.bookAuthor}
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판사</td>
        	<td colspan="2">
        		${info.bookPublisher}
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판일</td>
        	<td colspan="2">
        		<fmt:formatDate value="${info.bookPublishDate }" pattern="yyyy-MM-dd" var="pubDate"/>
        		${pubDate }
        	</td>
        	<td></td>
        </tr>
        <tr>
        	<td colspan="4" id="sending">
        		<c:choose>
        			<c:when test="${info.bookPosition eq 'BS-0001' }">
        				<button type="submit">대출하기</button>
        			</c:when>
        			<c:when test="${fn:contains(info.bookPosition, 'BB') }">
        				<button type="submit">예약하기</button>
        			</c:when>
        		</c:choose>
        		<a href="./libraryService"><button type="button">도서리스트</button></a>
        	</td>
        </tr>
    </table>
</form>
</body>
</html>