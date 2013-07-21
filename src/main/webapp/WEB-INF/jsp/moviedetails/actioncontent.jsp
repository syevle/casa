
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
	    $( "#relDate" ).datepicker({ dateFormat: "dd-mm-yy" }).val()
	  });
  $(function() {
	    $( "#entryDt" ).datepicker({ dateFormat: "dd-mm-yy" }).val()
	  });  
  </script>	

<script language="JavaScript">
 <!-- hide
 function openNewWindow(name) {
	 if(name=='starcastnamechild'){
		 popupWin = window.open("/casa/moviedetails/starcastnamechild","Ratting","width=450,height=550,left=150,top=200,toolbar=1,status=1,")
	 }
	 if(name=='creatorchild'){
		 popupWin = window.open("/casa/moviedetails/creatorchild","Ratting","width=450,height=450,left=150,top=200,toolbar=1,status=1,")
	 }
	 if(name=='directorchild'){
		 popupWin = window.open("/casa/moviedetails/directorchild","Ratting","width=450,height=450,left=150,top=200,toolbar=1,status=1,")
	 }
	 if(name=='generchild'){
		 popupWin = window.open("/casa/moviedetails/generchild","Ratting","width=450,height=450,left=150,top=200,toolbar=1,status=1,")
	 }
 }
 // done hiding -->
 </script>
<c:set var="readonly" value="${readonly}" />
<c:set var="movId" value="${movId}" />

<div id="content">
	<div id="column1">
		<h3>${fromName} 
		<c:if test="${(not empty movId)}">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${requestController}/add?id=${movId}">Copy Similar Movie</a>
		</c:if>
		</h3>
		<form:form method="post" action="${action}" commandName="frmObject" name="frm" id="frm" class="formular">
			
			<table><tr><form:errors path="*" cssClass="error" element="div" /></tr>
			<tr>${error}</tr>
			</table>
			<table>
				<!-- <form:hidden path="id" /> -->

				<tr>
					<td><form:label path="id"><spring:message code="label.movCode" /></form:label></td>
					<td><form:input path="id" readonly="${readonly}" onblur="javascript:validateSize('id',this.value,10);" /></td>
				
					<td><form:label path="movName"><spring:message code="label.movName"  /></form:label></td>
					<td><form:input path="movName" readonly="${readonly}"  size="50" tabindex="1" onblur="javascript:validateSize('movName',this.value,50);" /></td>
				</tr>
				<tr>
					<td><form:label path="avalStatus"><spring:message code="label.avalStatus" /></form:label></td>
					<td>
					<form:select path="avalStatus" disabled="${disabled}" tabindex="1">
	                  <form:option value="Y" label="Yes" />
	                  <form:option value="N" label="No" />
	                </form:select>
					</td>
				
					<td><form:label path="mediaFormat"><spring:message code="label.mediaFormat" /></form:label></td>
					<td>
					<form:select path="mediaFormat"  tabindex="2">
	                  <form:option value="DVD" label="DVD" />
	                  <form:option value="BLR" label="BLR" />
	                  <form:option value="OTH" label="OTH" />
	                </form:select>
					</td>
				</tr>
				<tr>
					<td><form:label path="languages"><spring:message code="label.languages" /></form:label></td>
					<td><%-- <form:select path="languages" items="${languageList}" disabled="${disabled}" /> --%>
					<c:if test="${readonly == true}">
		                	<form:select path="languages" items="${languageList}" disabled="${disabled}" />
		               </c:if>
		               <c:if test="${readonly == false}">
		                	<form:select path="languages" items="${languageList}" onchange="generateId(this);" tabindex="3" />
		               </c:if>
					</td>
				
					<td><form:label path="relDate"><spring:message code="label.relDate" /></form:label></td>
					<td><form:input path="relDate" readonly="${readonly}" tabindex="4" /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName1"><spring:message code="label.starcastName1" /></form:label></td>
					<td><input type="text" name="h_starcastName1" id="h_starcastName1" value="${h_starcastName1}" onblur="javascript:validateSize('h_starcastName1',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="starcastName2"><spring:message code="label.starcastName2" /></form:label></td>
					<td><input type="text" name="h_starcastName2" id="h_starcastName2" value="${h_starcastName2}" readonly="${readonly}" onblur="javascript:validateSize('h_starcastName2',this.value,30);" tabindex="6" ><c:if test="${(command == 'add') || (command == 'update')}">&nbsp;<a href="javascript:openNewWindow('starcastnamechild');">Set StarCast Click</a></c:if></td>
				</tr>
				<tr>
					<td><form:label path="starcastName3"><spring:message code="label.starcastName3" /></form:label></td>
					<td><input type="text" name="h_starcastName3" id="h_starcastName3"  value="${h_starcastName3}" onblur="javascript:validateSize('h_starcastName3',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="starcastName4"><spring:message code="label.starcastName4" /></form:label></td>
					<td><input type="text" name="h_starcastName4" id="h_starcastName4"  value="${h_starcastName4}" onblur="javascript:validateSize('h_starcastName4',this.value,30);" tabindex="6" ></td>
				</tr>
				<tr>
					<td><form:label path="starcastName5"><spring:message code="label.starcastName5" /></form:label></td>
					<td><input type="text" name="h_starcastName5" id="h_starcastName5" value="${h_starcastName5}" onblur="javascript:validateSize('h_starcastName5',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="starcastName6"><spring:message code="label.starcastName6" /></form:label></td>
					<td><input type="text" name="h_starcastName6" id="h_starcastName6"  value="${h_starcastName6}" onblur="javascript:validateSize('h_starcastName6',this.value,30);" tabindex="6" ></td>
				</tr>
				<tr>
					<td><form:label path="director1"><spring:message code="label.director1" /></form:label></td>
					<td><input type="text" name="h_director1" id="h_director1"  value="${h_director1}" onblur="javascript:validateSize('h_director1',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="director2"><spring:message code="label.director2" /></form:label></td>
					<td><input type="text" name="h_director2" id="h_director2"  value="${h_director2}"  onblur="javascript:validateSize('h_director2',this.value,30);" tabindex="6" ><c:if test="${(command == 'add') || (command == 'update')}">&nbsp;<a href="javascript:openNewWindow('directorchild');">Set Director Click</a></c:if></td>
				</tr>
				<tr>
					<td><form:label path="director3"><spring:message code="label.director3" /></form:label></td>
					<td><input type="text" name="h_director3" id="h_director3" value="${h_director3}"  onblur="javascript:validateSize('h_director3',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="creator1"><spring:message code="label.creator1" /></form:label></td>
					<td><input type="text" name="h_creator1" id="h_creator1"  value="${h_creator1}" onblur="javascript:validateSize('h_creator1',this.value,30);" tabindex="6" ></td>
				</tr>
				
				<tr>
					<td><form:label path="creator2"><spring:message code="label.creator2" /></form:label></td>
					<td><input type="text" name="h_creator2" id="h_creator2" value="${h_creator2}" onblur="javascript:validateSize('h_creator2',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="creator3"><spring:message code="label.creator3" /></form:label></td>
					<td><input type="text" name="h_creator3" id="h_creator3" value="${h_creator3}"  onblur="javascript:validateSize('h_creator3',this.value,30);" tabindex="6" ><c:if test="${(command == 'add') || (command == 'update')}">&nbsp;<a href="javascript:openNewWindow('creatorchild');">Set Creator Click</a></c:if></td>
				</tr>
				
				<tr>
					<td><form:label path="genre1"><spring:message code="label.genre1" /></form:label></td>
					<td><input type="text" name="h_genre1" id="h_genre1" value="${h_genre1}"  onblur="javascript:validateSize('h_genre1',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="genre2"><spring:message code="label.genre2" /></form:label></td>
					<td><input type="text" name="h_genre2" id="h_genre2" value="${h_genre2}" onblur="javascript:validateSize('h_genre2',this.value,30);" tabindex="6" ><c:if test="${(command == 'add') || (command == 'update')}">&nbsp;<a href="javascript:openNewWindow('generchild');">Set Gener Click</a></c:if></td>
				</tr>
				
				<tr>
					<td><form:label path="genre3"><spring:message code="label.genre3" /></form:label></td>
					<td><input type="text" name="h_genre3" id="h_genre3" value="${h_genre3}" onblur="javascript:validateSize('h_genre3',this.value,30);" tabindex="6" ></td>
				
					<td><form:label path="genreExtra"><spring:message code="label.genreExtra" /></form:label></td>
					<td><input type="text" name="h_genreExtra" id="h_genreExtra" value="${h_genreExtra}" onblur="javascript:validateSize('h_genreExtra',this.value,30);" tabindex="6" ></td>
				</tr>
				
				<tr>
					<td><form:label path="country"><spring:message code="label.country" /></form:label></td>
					<td>
						<c:if test="${readonly == true}">
		                	<form:select path="country" items="${countryList}" disabled="${disabled}" />
		               </c:if>
		               <c:if test="${readonly == false}">
		                	<form:select path="country" items="${countryList}" tabindex="21"/>
		               </c:if>
		             </td>
				
					<td><form:label path="tagLine"><spring:message code="label.tagLine" /></form:label></td>
					<td><form:textarea path="tagLine" rows="5" cols="100" readonly="${readonly}" onblur="javascript:validateSize('tagLine',this.value,500);" tabindex="22"/></td>
				</tr>
				
				<tr>
					<td><form:label path="movPath"><spring:message code="label.movPath" /></form:label></td>
					<td><form:input path="movPath" readonly="true" size="50" onblur="javascript:validateSize('movPath',this.value,50);" tabindex="23" /></td>
				
					<td><form:label path="dvdLocation"><spring:message code="label.dvdLocation" /></form:label></td>
					<td><input type="text" name="h_dvdLocation" id="h_dvdLocation" value="${h_dvdLocation}" onblur="javascript:validateSize('h_dvdLocation',this.value,30);" tabindex="6" ></td>
				</tr>
				<c:if test="${(command == 'add') || (command == 'update')}">
				<tr>
					<td></td>
					<td></td>
				
					<td></td>
					<td><form:select path="dvdLocation" items="${dvdLocationList}"  onchange="fillTextBox('h_dvdLocation',this);" /></td>
				</tr>
				</c:if>
				<tr>
					<td><form:label path="entryDt"><spring:message code="label.entryDt" /></form:label></td>
					<td><form:input path="entryDt" readonly="${readonly}" value="${todayDate}" tabindex="25" /></td>
				
					<td><form:label path="uplodReq"><spring:message code="label.uplodReq" /></form:label></td>
					<td>					
						<form:select path="uplodReq" disabled="${disabled}">
		                  <form:option value="Y" label="Yes" />
		                  <form:option value="N" label="No" />
		                </form:select>
	                </td>
				</tr>				
			</table>
			<%@ include file="../common/commandButton.jsp"%>
		</form:form>
	</div>
</div>

