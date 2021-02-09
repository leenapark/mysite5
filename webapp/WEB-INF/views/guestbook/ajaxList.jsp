<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header">
			<h1>
				<a href="">MySite</a>
			</h1>

			<ul>
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</ul>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li>ajax방명록</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<!-- <form action="" method=""> -->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass"type="password" name="pass"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnButton" type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					<input type="hidden" name="action" value="add">
					
				<!-- </form>	 -->
				
				<div id="guestbookListArea">
					<!-- 방명록 글 리스트 출력 영역 -->
				</div>
				
<%-- 				<table class="guestRead">
					<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
					</colgroup>
					<tr>
						<td>1234555</td>
						<td>이정재</td>
						<td>2020-03-03 12:12:12</td>
						<td><a href="">[삭제]</a></td>
					</tr>
					<tr>
						<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
					</tr>
				</table>	
				<!-- //guestRead --> --%>
				
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	// DOM이 생성되면
	$("document").ready(function(){
		console.log("ready")
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/list",		
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(guestList){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);
				
				for(var i=0; i<guestList.length; i++){
					render(guestList[i]);
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
</script>


<script type="text/javascript">

	// 방명록 등록 버튼 클릭 시
	$("#btnButton").on("click", function(){
		console.log("방명록 등록 버튼 눌렀을 때");
		
		// 방명록 데이터 수집
		var name = $("[name='name']").val();
		var password = $("[name='pass']").val();
		var content = $("[name='content']").val();
		
		console.log(name);
		console.log(password);
		console.log(content);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/write",		
			type : "post",
			//contentType : "application/json",
			data : {name: name, password: password, content: content},

			dataType : "json",
			success : function(guestVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestVo);
				console.log(guestVo.no);
				console.log(guestVo.name);
				render(guestVo);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});

	// 방명록 글 정보 + html 조합하여 화면에 출력
	function render(guestVo){
		var str="";
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+ guestVo.no +'</td>';
		str += '		<td>'+ guestVo.name +'</td>';
		str += '		<td>'+ guestVo.regDate +'</td>';
		str += '		<td><a href="">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+ guestVo.content +'</td>';
		str += '	</tr>';
		str += '</table>';
		
		$("#guestbookListArea").prepend(str);
		
	}
	
	
</script>

</html>