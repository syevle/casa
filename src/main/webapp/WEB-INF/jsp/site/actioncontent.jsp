<c:set var="readonly" value="${readonly}" />

<div id="content">
	<div id="column1">
		<h3>Site 111</h3>
		<form:form method="post" action="${action}" commandName="frmObject" name="frm" id="frm" class="formular">
			
			<table><tr><form:errors path="*" cssClass="error" element="div" /></tr></table>
			<table>
				<form:hidden path="id" />

				<tr>
					<td><form:label path="name"><spring:message code="label.siteName" /></form:label></td>
					<td><form:input path="name" readonly="${readonly}" /></td>
				</tr>
				<tr>
					<td><form:label path="address"><spring:message code="label.siteAddress" /></form:label></td>
					<td><form:input path="address" readonly="${readonly}"  /></td>
				</tr>
				<%@ include file="../common/commandButton.jsp"%>
			</table>
		</form:form>
	</div>
</div>
