<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">



<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

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
	<div class="modal fade" id="delModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">방명록 삭제</h4>
	      </div>
	      <div class="modal-body">
	       <label>비밀번호</label>
	       <input id="modalPassword" type="text" name="" value="">
	       
	       <!-- 해당 방명록 번호를 히든으로 감춰서 넣어둠 -->
	       <input id="modalNo" type="text" name="no" value="">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	        <button id="modalDel" type="button" class="btn btn-primary">삭제</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</body>

<script type="text/javascript">
	// DOM이 생성되면
	$("document").ready(function(){
		console.log("ready")
		
		// 리스트 출력
		fetchList();
	});
</script>



<script type="text/javascript">

	// 삭제 버튼 클릭할 때
	$("#guestbookListArea").on("click", "a", function(){
		event.preventDefault();
		
		console.log("삭제 버튼 클릭 모달 창 호출");
		
		$("#modalPassword").val("");
		
		//var $ = $(this)
		var no = $(this).data("no");
		$("#modalNo").val(no);
		
		console.log(no);
		
		$("#delModal").modal();
		
	});

	// modal 에 있는 삭제 버튼을 눌렀을 때
	$("#modalDel").on("click", function(){
		console.log("모달창 삭제 버튼")
		
		var no = $("#modalNo").val();
		
		// password, no 수집
		var guestVo = {
			password: $("#modalPassword").val(),
			no: no
		};
		
		/* var password = $("#modalPassword").val();
		var no = $("#modalNo").val();
		
		console.log(no);
		console.log(password); */
		
		console.log(guestVo);
		
		
		// ajax 삭제 요청
		$.ajax({
		
			url : "${pageContext.request.contextPath }/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			data : guestVo,
	
			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				// result 가 1이면 리스트에서 삭제 작업을 해야한다
				// 1. 모달창 닫기
				// 2. 새로고침 안되도 테이블 삭제 처리
				
				if(count == 1) {
					// 비밀 번호 일치
					// 1. 모달창 닫기
					// 2. 새로고침 안되도 테이블 삭제 처리
					$("#delModal").modal("hide");
					
					$("#t-"+no).remove();
					
					
				} else {
					// 비밀 번호 미일치
					// 1. 모달창 닫기
					// 2. 테이블 변동 없음
					alert("비밀번호가 틀렸습니다");
					$("#modalPassword").val("");
					
					//$("#delModal").modal("hide");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	
	});
	
	
	// 방명록 등록 버튼 클릭 시
	$("#btnButton").on("click", function(){
		console.log("방명록 등록 버튼 눌렀을 때");
		
		
		// 방명록 데이터 수집
		// 1번 방법		
		/* var name = $("[name='name']").val();
		var password = $("[name='pass']").val();
		var content = $("[name='content']").val();
		
		console.log(name);
		console.log(password);
		console.log(content); */
		
		// 2번 방법
		var guestVo = {
				name: $("[name='name']").val(),
				password: $("[name='pass']").val(),
				content: $("[name='content']").val()
		};
				
		console.log(guestVo);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/write2",		
			type : "post",
			contentType : "application/json",	// json 형식으로 보내겠다는 뜻
			//data : {name: name, password: password, content: content},	// 1번 방법
			data : JSON.stringify(guestVo),		// 2번 방법 --> json으로 변환함

			dataType : "json",
			success : function(guestVo){
				/*성공시 처리해야될 코드 작성*/
				/* console.log(guestVo);
				console.log(guestVo.no);
				console.log(guestVo.name); */
				render(guestVo, "up");
				
				// 입력폼 비우기
				$("[name='name']").val("");
				$("[name='pass']").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});

	// 방명록 글 정보 + html 조합하여 화면에 출력
	function render(guestVo, updown){
		var str="";
		str += '<table id="t-'+ guestVo.no +'" class="guestRead">';
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
		str += '		<td><a href=""data-no="' + guestVo.no +'">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+ guestVo.content +'</td>';
		str += '	</tr>';
		str += '</table>';
				
		if(updown == "down"){
			$("#guestbookListArea").append(str);
		} else if(updown == "up"){
			$("#guestbookListArea").prepend(str);
		} else {
			console.log("방향 미지정")
		}
		
	}

	// 전체 리스트 출력
	// 함수 정의로 먼저 실행되게 함
	function fetchList() {
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
						render(guestList[i], "down");
					}
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
	}
	
</script>

</html>