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
<h3><center>Movie List</center></h3>
	<div id="column1">
		<form:form id="frm" class="formular">
			
			<h3>Movie Details</h3>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${requestController}/add"><%-- <img src="<%=request.getContextPath()%>/media/images/create.png" alt="Add" /> --%>Add</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${requestController}/serch"><%-- <img src="<%=request.getContextPath()%>/media/images/ico_xls.gif" alt="Report" /> --%>Serch</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:openNewWindow();"><%-- <img src="<%=request.getContextPath()%>/media/images/ico_xls.gif" alt="Report" /> --%>Excle Report</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:openNewWindow();"><%-- <img src="<%=request.getContextPath()%>/media/images/ico_xls.gif" alt="Report" /> --%>Html Report</a>
			<div id="container">
				<div class="demo_jui">
					<c:if test="${!empty list}">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
							<thead>
								<tr>
									<th>Movie Code</th>
									<th>Movie Name</th>
									<th>Available Status</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="movieDetails">
									<tr class="gradeX">
										<td><a href="${requestController}/find/get/${movieDetails.id}">${movieDetails.id}</a></td>
										<td>${movieDetails.movName}</td>
										<td>${movieDetails.avalStatus}</td>
										<td><a href="${requestController}/find/delete/${movieDetails.id}"><img src="<%=request.getContextPath()%>/media/images/delete.png" alt="Delete" /></a></td>
										<td><a href="${requestController}/find/update/${movieDetails.id}"><img src="<%=request.getContextPath()%>/media/images/update.png" alt="Update" /></a></td>
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