<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
	var isEnd = false;
	var page = 0;
	var render = function(vo, mode) {
		
		// 현업에서는 이부분을 template library ex) ejs
		
		var htmls = 
				"<li id='gb-'" + vo.no + "><table width=510 border=1>"
				+ "<tr><td><span>"
				+ vo.name
				+ "&nbsp</span>&nbsp&nbsp"
				+ vo.no
				+ "&nbsp&nbsp"
				+ vo.date
				+ "&nbsp</td>"
				+ "<tr><td colspan=4>"
				+ vo.content
				+ "</td></tr>"
				+ "<tr><td align=right><a class=\"delete\" href=\"/mysite5/guestbook?a=deleteform&no=" + vo.no + "\">삭제</a>"
				+ "</td></tr></table> <br></li>";
				
		//js template libarary -> ejs
		if(mode = true){ //true prepend false:append
			$("#list-guestbook").prepend(htmls);						
		}else{
			$("#list-guestbook").append(htmls);			
		}
	}
	var fetchList = function() {
		if (isEnd == true) {
			return;
		}
		++page
		$.ajax({
					url : "${pageContext.request.contextPath}/api/guestbook?a=ajax-list&p=" + page,
					type : "get",
					dataType : "json",
					//data:"a=ajax=add&name=asdfasdf&password=asdfasdf&content=asdfasdfa"  //포스트방식으로
					
					success : function(response) {   			// response.result = "success" or "fail"
																// response.data = [{}, {}, {}...]
						if (response.result != "success") {
							console.error(response.message);
							isEnd = true;
							return;
						}

						//렌더링
						$(response.data).each(function(index, vo) {
							render(vo);
						});

						if (response.data.length < 5) {
							isEnd = true;
							$("#btn-fetch").prop("disabled", true);
						}
					},
					error : function(jqXHR, status, e) {
						console.error(status + ":" + e);
					}
				});
		}

	$(function() {
		// 삭제버튼 click event
		$( document ).on( "click", "#list-guestbook li a", function( event ){
		event.preventDefault();
		
		$("#hidden-no").val( $(this).attr( "data-no" ) );
		dialogDelete.dialog( "open" );
		});
		
		$("#add-form").submit(function(){
			event.preventDefault();
			var name = $("#inputname").val(); 
			var password = $("#inputpass").val();
			var content = $("#inputcontent").val();
			
			if(name == ""){
				$("#inputname").focus();
				alert("이름은 필수입력사항")
				return;
			}

			if(password == ""){
				alert("비밀번호는 필수입력사항")
				return;
			}

			if(content == ""){
				alert("작성글이 없습니다.")
				return;
			}
			
		$.ajax({
				url : "${pageContext.request.contextPath }/api/guestbook",
				type : "post",
				dataType : "json",
				data : "a=ajax-add" + 
						"&name=" + name + 
						"&password=" + password + 
						"&content=" + content,
				
				success : function(response) { 
					if (response.result != "success") {
						console.error(response.message);
						return;
					}

					//렌더링
				
					render(response.data, true);
					$("#add-form")[0].reset();
					
					console.log("submit")
				},
				error : function(jqXHR, status, e) {
					console.error(status + ":" + e);
				}
			});
		});
		
		$(window).scroll( function(){
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $( document ).height();
			
			// 스크롤 바가 바닥까지 왔을 때( 10px 덜 왔을 때 )
			if( scrollTop + windowHeight + 10 > documentHeight ) {
				//console.log( "call fetchList" );
				fetchList();
			}
		});
		
/* 		$("#btn-fetch").click(function() {
			fetchList();
		}); */

		// 1번째 리스트 가져오기
		fetchList();
	});
</script>
</head>
<body>


	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div class="float_sidebar" id="content">
			<div id="guestbook">
				
				<tr>
				<hr>
					<h1>방명록</h1>
				<hr>
				</tr>
				<form id="add-form" action="" method="post">
					<input type="hidden" name="a" value="ajax-add">
						<table>
							<tr>
								<td><input type="text" name="inputname" placeholder="이름"></td>
							</tr>
							<tr>
								<td><input type="password" name="inputpass" placeholder="비밀번호"></td>
							</tr>
							<tr>
								<td><textarea name="inputcontent" id="content"
										placeholder="글을 작성해 주세요"></textarea></td>
							</tr>
							<tr class="submit">
								<td><input type="submit" VALUE="submit"></td>
							</tr>
						</table>
						</form>
					</div>
						<ul id="list-guestbook">
						
						</ul>
				<form>
				    <input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
				</form>
		</div>
			<div id="dialog" title="비밀번호를 입력하세요">
				<p></p>
			</div>
			<div id="navigation">
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
			</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>