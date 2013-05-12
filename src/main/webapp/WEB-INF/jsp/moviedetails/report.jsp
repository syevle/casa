<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment; filename=\"MovieDetailReport.xls\"");
   /*  response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=\"MovieDetailReport.pdf\""); */
%>
<html>
<head>
<body>
	<c:if test="${!empty list}">
		<table width="100%" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>Movie Code</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>Movie Name</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>Available Status</strong></font></td>
			</tr>
			<c:forEach items="${list}" var="movieDetails">
				<tr>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.id}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.movName}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.avalStatus}</font></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>
