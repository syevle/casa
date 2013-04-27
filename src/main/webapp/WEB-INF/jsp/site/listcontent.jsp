<script language="JavaScript">
 <!-- hide
 function openNewWindow() {
 popupWin = window.open('${requestController}/report',
 'open_window',
 'menubar, toolbar, location, directories, status, scrollbars, resizable, dependent, width=640, height=480, left=0, top=0')
 }
 // done hiding -->
 </script>
<div id="content">
<h3><center>Site Details</center></h3>
	<div id="column1">
		<form:form id="frm" class="formular">
			<%@ include file="serch.jsp"%>
		<%-- 	<%@ include file="actioncontent.jsp"%> --%>
			<h3>Site Details</h3>
			<a href="${requestController}/list">Refresh Site</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${requestController}/add">Add New Site</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:openNewWindow();">Report</a>
			<div id="container">
				<div class="demo_jui">
					<c:if test="${!empty list}">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
							<thead>
								<tr>
								<th>Site Id</th>
									<th>Site Name</th>
									<th>Site Address</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="site">
									<tr class="gradeX">
									<td>${site.id}</td>
										<td><a href="${requestController}/find/get/${site.id}">${site.name}</a></td>
										<td>${site.address}</td>
										<td width="200"><a href="${requestController}/find/delete/${site.id}">DELETE</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${requestController}/find/update/${site.id}">UPDATE</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</form:form>
	</div>
</div>