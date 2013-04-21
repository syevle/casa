<head>
<title>Spring 3 MVC</title>
<%@ include file="../common/pageCss.jsp" %>
<%@ include file="../common/pageJs.jsp" %>
</head>
<c:set var="requestContextPath" value="<%=request.getContextPath()%>" />
<c:set var="requestController" value="${requestContextPath}${requestMapping}" />