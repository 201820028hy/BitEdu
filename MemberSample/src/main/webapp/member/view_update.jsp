<%@page import="java.util.Date"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bitedu.bipa.member.vo.TestVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true" %><!-- jsp에서 자바를 사용하겠다 | el 무시할래? 예 --> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정화면</title>
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
        .data_ui {
            width: 450px; 
            height: 50px;
        }

        button {
            font-size: 15px;
            margin: 5px;
        }
        
        
        #sending {
        	text-align: center;
        }
        
        input.poster :disabled {
        	background: gray;
        }
        
        #form {
        	font-size: 30px;
        }
    </style>
    
    <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
  		//우편번호 검색 및 주소 입력
		function checkPostCode() {
			new daum.Postcode({
		        oncomplete: function(data) {
					$("#post_code").val(data.zonecode);
					$("#addr1").val(data.address + (data.buildingName ? " (" + data.buildingName + ")" : ""));
					$("#addr2").focus();
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		        }
		    }).open();
		}
    </script>
</head>
<body>
<h1>수정화면</h1>

<% //<!-- scriptlet이다. jsp문법을 사용 -->
	/* String id = request.getParameter("user_id");request : 객체명이다. 정해져있다. 연관1 */
	//out.print("<h1>"+id+"</h1>");//이게 머냐 비난과 힐난.. 그레서 표기법이 나옴 연관1
	TestVo test = (TestVo) request.getAttribute("user");
	String id = test.getId();
	String pass = test.getPass();
	String pass_check = test.getPass_check();
	String user_name = test.getUser_name();
	String post_code = test.getPost_code();
	String addr1 = test.getAddr1();
	String addr2 = test.getAddr2();
	String birth_year = test.getBirth_year();
	String birth_month = test.getBirth_month();
	String birth_date = test.getBirth_date();
	String gender = test.getGender();
	String[] favr = test.getFavr();
	String intro = test.getIntro();
	List list = Arrays.asList(favr);
%>
<br>
<h1><%= id %> 요 표기법이 출력의 표준(expression) 연관1</h1>
<h1><%= id %>, <%= pass %></h1>
	<table>
        <tr><th colspan="5" id="form">회원가입양식</th></tr>
        <tr><th>구분</th><th class="data_ui" colspan="2">데이터입력</th><th>유효성검사</th><th>비고</th></tr>
        <tr>
            <td>아이디</td>
            <td colspan="2"><input type="text" id="user_id" name="user_id" value="<%= id%>"><button id="btn_check_id" onclick="checkId()" type="button">아이디확인</button></td>
            <td>8자리 , 첫글자 영문소문자</td><td></td></tr>
        <tr><td>비밀번호</td><td colspan="2"><input type="password" id="pass" name="pass" onkeyup="checkPass()" value="<%= pass%>"/></td><td rowspan="2">비밀번호 일치</td><td></td></tr>
        <tr><td>비번확인</td><td colspan="2"><input type="password" id="pass_check" name="pass_check" onkeyup="checkPass()" value="<%= pass_check%>"/></td><td id="pass_isCheck" style="color:red;"></td id="pass_isCheck" style="color:red;"></tr>
        <tr><td>이름</td><td colspan="2"><input type="text" id="user_name" name="user_name" value="<%= user_name%>"/></td><td>필수입력</td><td></td></tr>
        <tr><td>우편번호</td><td colspan="2"><input type="text" id="post_code" name="post_code" readonly="readonly" value="<%= post_code%>"/><button id="btn_check_id" onclick="checkPostCode()" type="button">우편번호찾기</button></td><td rowspan="3">필수입력</td><td></td></tr>
        <tr><td>주소1</td><td colspan="2"><input type="text" id="addr1" name="addr1" readonly="readonly" value="<%= addr1%>" style="width:90%;"/></td><td></td></tr>
        <tr><td>주소2</td><td colspan="2"><input type="text" id="addr2" name="addr2" value="<%= addr2%>" style="width:90%;"/></td><td></td><tr>
        <tr>
            <td>생년월일</td>
            <td colspan="2">
				<select id="birth_year" name="birth_year">
					<option>년도</option>
					<%
						String year = "";
						for(int i = 1900; i <= 2050; i++) {
							year += "<option value='"+i+"' " + (i == Integer.parseInt(birth_year) ? "selected='selected'" : "") + ">" + i + "</option>";
							//"<option value=\"" + i + "\" " + (i == Integer.parseInt(birth_year) ? " selected='selected'" : '') + "'>" + i + "</option>";
						}
					%>
					<%= year %>
				</select> -
				<select id="birth_month" name="birth_month" onchange="setBirth(true)">
					<option>월</option>
					<%
						String month = "";
						for(int i = 1; i <= 12; i++) {
							month += "<option value='"+i+"' " + (i == Integer.parseInt(birth_month) ? "selected='selected'" : "") + ">" + i + "</option>";
							//"<option value=\"" + i + "\" " + (i == Integer.parseInt(birth_year) ? " selected='selected'" : '') + "'>" + i + "</option>";
						}
					%>
					<%= month %>
				</select> -
				<select id="birth_date" name="birth_date">
					<option>일</option>
					<%
						String date = "";
						Date day = new Date(Integer.parseInt(birth_year), Integer.parseInt(birth_month), 0);
						for(int i = 1; i <= day.getDate(); i++) {
							date += "<option value='"+i+"' " + (i == Integer.parseInt(birth_date) ? "selected='selected'" : "") + ">" + i + "</option>";
							//"<option value=\"" + i + "\" " + (i == Integer.parseInt(birth_year) ? " selected='selected'" : '') + "'>" + i + "</option>";
						}
					%>
					<%= date %>
				</select>
            </td><td>년도는 1900~2050, 월은 1~12, 일은 1~31</td><td>2개중 하나 선택</td></tr>
        <tr>
            <td>성별</td>
            <td colspan="2">
				<label><input type="radio" id="gender_m" name="gender" value="m" <%= gender.equals("m") ? "checked='true'" : "" %>/> 남</label>
				<label><input type="radio" id="gender_f" name="gender" value="f" <%= gender.equals("f") ? "checked='true'" : "" %>/> 여</label>
            </td><td>필수 선택</td><td></td></tr>
        <tr>
            <td>관심분야</td>
            <td colspan="2">
				<label><input type="checkbox" id="favr_001" name="favr" value="001" <%= list.indexOf("001") > -1 ? "checked='true'" : "" %>/> 문학</label>
				<label><input type="checkbox" id="favr_002" name="favr" value="002" <%= list.indexOf("002") > -1 ? "checked='true'" : "" %>/> 어학</label>
				<label><input type="checkbox" id="favr_003" name="favr" value="003" <%= list.indexOf("003") > -1 ? "checked='true'" : "" %>/> 정보IT</label><br/>
				<label><input type="checkbox" id="favr_004" name="favr" value="004" <%= list.indexOf("004") > -1 ? "checked='true'" : "" %>/> 과학</label>
				<label><input type="checkbox" id="favr_005" name="favr" value="005" <%= list.indexOf("005") > -1 ? "checked='true'" : "" %>/> 수학</label>
				<label><input type="checkbox" id="favr_006" name="favr" value="006" <%= list.indexOf("006") > -1 ? "checked='true'" : "" %>/> 예술</label>
            </td><td>2~3개 사이 선택</td><td></td>
        </tr>
        <tr><td>자기소개</td><td colspan="2"><textarea id="intro" name="intro" cols="50" rows="10"><%=intro %></textarea></td><td></td><td></td></tr>    
        <tr><td colspan="5" id="sending"><input type="submit" value="가입" id="save"> <input type="reset" onclick="setReset()"></td></tr>
    </table>
</body>
</html>