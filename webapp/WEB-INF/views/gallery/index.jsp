<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%pageContext.setAttribute( "newLine", "\n" );%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h2>GALLERY</h2>
					<a href="${pageContext.request.contextPath }/gallery/form" id="upload-image">
					<img src="${pageContext.request.contextPath }/assets/images/gallery.png" width=auto/>
					</a>
				</div>
				<ul>
					<c:forEach items="${list  }" var="vo">
					<li>
						<a href="${pageContext.request.contextPath }/gallery/view?no=${vo.no }" data-lightbox="roadtrip" style="background-image:url('${pageContext.request.contextPath }${url }/${vo.saveFileName }')"> </a>
					</li>	
				</c:forEach>	
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>