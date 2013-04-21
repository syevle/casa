<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<%@ include file="../common/pageHead.jsp"%>
<%@ include file="siteJs.jsp"%>
<body>
	<div id="outer">
		<div id="outer2">
			<%@ include file="../common/header.jsp"%>
			<%@ include file="../common/menu.jsp"%>
			<%@ include file="actioncontent.jsp"%>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>