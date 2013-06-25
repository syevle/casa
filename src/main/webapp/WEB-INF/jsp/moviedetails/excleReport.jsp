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
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>MOV_NAME</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>LANGUAGES</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>REL_DATE</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>STARCAST_NAME_1</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>STARCAST_NAME_2</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>STARCAST_NAME_3</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>STARCAST_NAME_4</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>STARCAST_NAME_5</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>STARCAST_NAME_6</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>DIRECTOR_1</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>DIRECTOR_2</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>DIRECTOR_3</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>CREATOR_1</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>CREATOR_2</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>CREATOR_3</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>GENRE_EXTRA</strong></font></td>
				<td  bgcolor="#f0edd9"><font face="Verdana, Geneva, sans-serif" size="-1"><strong>TAG_LINE</strong></font></td>
			</tr>
			<c:forEach items="${list}" var="movieDetails">
				<tr>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.movName}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.languages}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.avalStatus}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.relDate}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.starcastName1}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.starcastName2}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.starcastName3}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.starcastName4}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.starcastName5}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.starcastName6}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.director1}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.director2}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.director3}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.creator1}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.creator2}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.creator3}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.genreExtra}</font></td>
					<td><font face="Verdana, Geneva, sans-serif" size="-1">${movieDetails.tagLine}</font></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>
