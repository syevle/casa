<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<%@ include file="../common/pageHead.jsp"%>
<%@ include file="movieDetailsJs.jsp"%>

<script>

	function validateForm() {
		/* var fromDate = document.getElementById("h_id");
		var toDate = document.getElementById("h_id");
		if (Date.parse(fromDate) > Date.parse(toDate)) {
			alert("Invalid Date Range!\nStart Date cannot be after End Date!")
			return false;
			}
		 */
		var textBox = document.getElementById("h_id");
		var textLength = textBox.value.length;
		if(textLength < 3 && textBox > 0)
		{
			alert("Movie code should be 3 Character");
		    return false;
		}
		return true;
	}
</script>



<%-- below code used for autocompleter --%>
<style type="text/css" title="currentStyle">
@import "<%=request.getContextPath()%>/media/css/jquery-ui.css";
</style>

<%-- <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/media/js/jquery-1.9.1.js"></script> --%>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/media/js/jquery-ui.js"></script>
 
  <script>
   
  
  
	function fillTextBox(name, data) {
		//document.getElementById(name).value = data.value;
	   var e = data;
	   var str = e.options[e.selectedIndex].text;
	   document.getElementById(name).value = str;
	}
  $(function() {
	    $( "#fromDate" ).datepicker({ dateFormat: "dd-mm-yy" }).val()
	  });
  $(function() {
	    $( "#toDate" ).datepicker({ dateFormat: "dd-mm-yy" }).val()
	  });  
  </script>	

<body onload="setFoucs('movName');">
	<div id="outer">
		<div id="outer2">
			<%@ include file="../common/header.jsp"%>
			<%@ include file="../common/menu.jsp"%>
			
			
			
			
			
			
<div id="content">
	<div id="column1">
		<h3>${fromName}</h3>
		
		<h3>Movie Details</h3>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${requestController}/add"><%-- <img src="<%=request.getContextPath()%>/media/images/create.png" alt="Add" /> --%>Add</a>
		<form:form method="POST" action="${requestController}/list"
						commandName="frmObject" name="frm" id="frm" class="formular">
						<table style="margin: auto;">
							<tr>
								<td>
									<table>
										<tr>
											<td><form:label path="id">
													<spring:message code="label.movCode" />
												</form:label>
											</td>
											<td><input type="text" name="h_id" id="h_id">
											</td>
											<td><form:select path="id" items="${movieCodeList}"
													onchange="fillTextBox('h_id',this);" />
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td><form:label path="movName">
													<spring:message code="label.movName" />
												</form:label>
											</td>
											<td><input type="text" name="h_movName" id="h_movName">
											</td>
											<td><form:select path="movName" items="${movieNameList}"
													onchange="fillTextBox('h_movName',this);" />
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td><form:label path="starcastName1">
													<spring:message code="label.starcastName" />
												</form:label>
											</td>
											<td><input type="text" name="h_starcastName"
												id="h_starcastName">
											</td>
											<td><form:select path="starcastName1"
													items="${starcastList}"
													onchange="fillTextBox('h_starcastName',this);" />
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td><form:label path="genre1">
													<spring:message code="label.genre" />
												</form:label>
											</td>
											<td><input type="text" name="h_genre" id="h_genre">
											</td>
											<td><form:select path="genre1" items="${genreList}"
													onchange="fillTextBox('h_genre',this);" />
											</td>
										</tr>
									</table></td>

							</tr>
							
							<tr>
								<td>
									<table>
										<tr>
											<td><form:label path="creator1">
													<spring:message code="label.creator" />
												</form:label>
											</td>
											<td><input type="text" name="h_creator" id="h_creator">
											</td>
											<td><form:select path="creator1" items="${creatorList}"
													onchange="fillTextBox('h_creator',this);" />
											</td>
										</tr>
									</table></td>

							</tr>
							
							<tr>
								<td>
									<table>
										<tr>
											<td><form:label path="languages">
													<spring:message code="label.languages" />
												</form:label>
											</td>
											<td><input type="text" name="h_languages" id="h_languages">
											</td>
											<td><form:select path="languages" items="${languageList}"
													onchange="fillTextBox('h_languages',this);" />
											</td>
										</tr>
									</table></td>

							</tr>
							
<!-- 							<tr>
								<td>
									<table>
										<tr>
											<td><label for="fromReleaseDate">From Release Date >=</label>
											</td>
											<td><input type="text" name="fromDate" id="fromDate"></td>
											<td><label for="toReleaseDate">To Release Date <=</label>
											</td>
											<td><input type="text" name="toDate" id="toDate"></td>
										</tr>
									</table></td>

							</tr> -->
</table>
<table>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Serch" class="styled-button-1"	onclick="return validateForm();" />
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${requestController}/serch"><input type="button" class="styled-button-1" value="Clear" /> </a>
								</td>
							</tr>
						</table>
					</form:form>

	</div>
</div>

			
			
			
			
			
			
			
			

		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>

</body>
</html>
