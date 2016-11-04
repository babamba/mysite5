<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinform" method="post" action="${pageContext.request.contextPath }/user">
					<input type="hidden" name="a" value="modify"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">

					<label class="block-label" for="email">이메일</label>
					<strong>${userVo.email }</strong>	
											
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="${userVo.password }">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test="${'male' == userVo.gender }">
								<label>여</label> <input type="radio" name="gender" value="female" >
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male" >
							</c:otherwise>
						</c:choose>		
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
<c:if test="${'success' == param.update }">
<script>
	alert( "성공적으로 정보를 수정하였습니다." );
</script>
</c:if>
</html>