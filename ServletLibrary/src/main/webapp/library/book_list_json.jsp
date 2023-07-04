<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		$(function() {
			getList();
		})
		
		function getList() {
			$(".tr_book").remove();
			$.ajax({
				url: "/ServletLibrary/libraryService",
				method: "GET",
				success: function(response) {
					let bookList = JSON.parse(response).list;
					if(bookList.length > 0) {
						$(bookList).each(function(index, book) {
							let tagStr = '<tr class="tr_book">'
								+ '<td>' + book.bookSeq + '</td>'
								+ '<td><a href="/ServletLibrary/libraryService?book_seq=' + book.bookSeq + '">' + book.bookTitle + '</a></td>'
								+ '<td>' + book.bookAuthor + '</td>'
								+ '<td>' + book.bookPublishDate + '</td>'
								+ '<td><button type="button" onclick="removeBook(' + book.bookSeq + ', \"' + book.bookIsbn + '\")">삭제</button></td></tr>';
							$("#after_list").after(tagStr);
						})
					} else {
						$("#after_list").after('<tr class="tr_book"><td colspan="5">조회된 목록이 없습니다.</td></tr>');
					}
				},
				error: function(error) {
					alert("에러가 발생했습니다.");
					console.log(error);
				}
			})
		}
		
		function removeBook(bookSeq) {
			if(!confirm(bookSeq + "번 도서를 삭제하시겠습니까?")) {
				return false;
			}
			
			$.ajax({
				url: "/ServletLibrary/libraryService?book_seq="+bookSeq,
				method: "DELETE",
				/* data: {name: "book_seq", value: bookSeq}, */
				success: function(response) {
					let responseJson = JSON.parse(response);
					if(responseJson.result > 0) {
						alert(responseJson.result + "권 삭제되었습니다.");
					} else {
						alert("삭제된 도서가 없습니다.");
					}
				},
				error: function(error) {
					alert("에러가 발생했습니다.");
					console.log(error);
				},
				complete: function() {
					getList();
				}
			})
		}
		
		function bookDetail(bookSeq) {
			let str = "/ServletLibrary/libraryService?book_seq=" + bookSeq;
			console.log(str);
			$("#fm_list").attr("action", str);
			$("#fm_list").attr("method", "get");
			$("#fm_list").submit();
			
			/* $.ajax({
				url: "/ServletLibrary/libraryService?book_seq="+bookSeq,
				method: "get",
				/* data: {name: "book_seq", value: bookSeq}
				success: function(response) {
					
				}
			}) */
		}
	</script>
</head>
<body>
<%-- <% String cmd = request.getParameter("cmd"); %>
<%=cmd.equals("success")?"<script>alert('hello');</script>":""%> --%>
<form id="fm_list">
    <table>
        <tr><th colspan="5" id="form">도서리스트</th></tr>
        <tr id="after_list"><th>순번</th><th>타이틀</th><th>저자</th><th>출판일</th><th></th></tr>
        <tr>
        </tr>
        <tr><td colspan="5"><button type="button" onclick="window.location.href = 'book_regist.jsp'">도서등록</button></td></tr>
    </table>
</form>
</body>
</html>