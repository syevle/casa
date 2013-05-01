
<%-- below code used for autocompleter --%>
<style type="text/css" title="currentStyle">
@import "<%=request.getContextPath()%>/media/css/jquery-ui.css";
</style>

<%-- <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/media/js/jquery-1.9.1.js"></script> --%>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/media/js/jquery-ui.js"></script>
 
  <script>
  $(function() {
    var availableTags = [
      ${creatorList}
    ];
    $( "#creator1" ).autocomplete({
      source: availableTags
    });
    $( "#creator2" ).autocomplete({
        source: availableTags
      });
    $( "#creator3" ).autocomplete({
        source: availableTags
      });
  });
  
  $(function() {
	    var availableTags = [
	      ${directorList}
	    ];
	    $( "#director1" ).autocomplete({
	      source: availableTags
	    });
	    $( "#director2" ).autocomplete({
	        source: availableTags
	      });
	    $( "#director3" ).autocomplete({
	        source: availableTags
	      });
	  });
  
  $(function() {
	    var availableTags = [
	      ${starcastList}
	    ];
	    $( "#starcastName1" ).autocomplete({
	      source: availableTags
	    });
	    $( "#starcastName2" ).autocomplete({
	        source: availableTags
	      });
	    $( "#starcastName3" ).autocomplete({
	        source: availableTags
	      });
	    $( "#starcastName4" ).autocomplete({
		      source: availableTags
		});
		$( "#starcastName5" ).autocomplete({
		     source: availableTags
		});
		$( "#starcastName6" ).autocomplete({
		    source: availableTags
		});	    
	  });
  
  $(function() {
	    $( "#relDate" ).datepicker({ dateFormat: "dd-mm-yy" }).val()
	  });
  $(function() {
	    $( "#entryDt" ).datepicker({ dateFormat: "dd-mm-yy" }).val()
	  });  
  </script>	


<c:set var="readonly" value="${readonly}" />

<div id="content">
	<div id="column1">
		<h3>${fromName}</h3>
		<form:form method="post" action="${action}" commandName="frmObject" name="frm" id="frm" class="formular">
			
			<table><tr><form:errors path="*" cssClass="error" element="div" /></tr></table>
			<table>
				<!-- <form:hidden path="id" /> -->

				<tr>
					<td><form:label path="id"><spring:message code="label.movCode" /></form:label></td>
					<td><form:input path="id" readonly="${readonly}" /></td>
				
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
					<td><form:input path="starcastName1" readonly="${readonly}" tabindex="5" onblur="javascript:validateSize('starcastName1',this.value,30);"  /></td>
				
					<td><form:label path="starcastName2"><spring:message code="label.starcastName2" /></form:label></td>
					<td><form:input path="starcastName2" readonly="${readonly}" onblur="javascript:validateSize('starcastName2',this.value,30);" tabindex="6" /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName3"><spring:message code="label.starcastName3" /></form:label></td>
					<td><form:input path="starcastName3" readonly="${readonly}" onblur="javascript:validateSize('starcastName3',this.value,30);" tabindex="7" /></td>
				
					<td><form:label path="starcastName4"><spring:message code="label.starcastName4" /></form:label></td>
					<td><form:input path="starcastName4" readonly="${readonly}" onblur="javascript:validateSize('starcastName4',this.value,30);" tabindex="8" /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName5"><spring:message code="label.starcastName5" /></form:label></td>
					<td><form:input path="starcastName5" readonly="${readonly}" onblur="javascript:validateSize('starcastName5',this.value,30);" tabindex="9" /></td>
				
					<td><form:label path="starcastName6"><spring:message code="label.starcastName6" /></form:label></td>
					<td><form:input path="starcastName6" readonly="${readonly}" onblur="javascript:validateSize('starcastName6',this.value,30);" tabindex="10" /></td>
				</tr>
				<tr>
					<td><form:label path="director1"><spring:message code="label.director1" /></form:label></td>
					<td><form:input path="director1" readonly="${readonly}" onblur="javascript:validateSize('director1',this.value,30);" tabindex="11"/></td>
				
					<td><form:label path="director2"><spring:message code="label.director2" /></form:label></td>
					<td><form:input path="director2" readonly="${readonly}" onblur="javascript:validateSize('director2',this.value,30);" tabindex="12"/></td>
				</tr>
				<tr>
					<td><form:label path="director3"><spring:message code="label.director3" /></form:label></td>
					<td><form:input path="director3" readonly="${readonly}" onblur="javascript:validateSize('director3',this.value,30);" tabindex="13"/></td>
				
					<td><form:label path="creator1"><spring:message code="label.creator1" /></form:label></td>
					<td><form:input path="creator1" readonly="${readonly}" onblur="javascript:validateSize('creator1',this.value,30);" tabindex="14"/></td>
				</tr>
				<tr>
					<td><form:label path="creator2"><spring:message code="label.creator2" /></form:label></td>
					<td><form:input path="creator2" readonly="${readonly}" onblur="javascript:validateSize('creator2',this.value,30);"  tabindex="15"/></td>
				
					<td><form:label path="creator3"><spring:message code="label.creator3" /></form:label></td>
					<td><form:input path="creator3" readonly="${readonly}" onblur="javascript:validateSize('creator3',this.value,30);" tabindex="16"/></td>
				</tr>
				<tr>
					<td><form:label path="genre1"><spring:message code="label.genre1" /></form:label></td>
					<td>					
						<%-- <form:select path="genre1" items="${genreList}" disabled="${disabled}" /> --%>
						<c:if test="${readonly == true}">
		                	<form:select path="genre1" items="${genreList}" disabled="${disabled}" />
		               </c:if>
		               <c:if test="${readonly == false}">
		                	<form:select path="genre1" items="${genreList}" tabindex="17"/>
		               </c:if>
	                </td>
				
					<td><form:label path="genre2"><spring:message code="label.genre2" /></form:label></td>
					<td>					
						<%-- <form:select path="genre2" items="${genreList}" disabled="${disabled}" /> --%>
						<c:if test="${readonly == true}">
		                	<form:select path="genre2" items="${genreList}" disabled="${disabled}" />
		               </c:if>
		               <c:if test="${readonly == false}">
		                	<form:select path="genre2" items="${genreList}" tabindex="18"/>
		               </c:if>
	                </td>
				</tr>
				<tr>
					<td><form:label path="genre3"><spring:message code="label.genre3" /></form:label></td>
					<td>					
						<%-- <form:select path="genre3" items="${genreList}" disabled="${disabled}"/> --%>
						<c:if test="${readonly == true}">
		                	<form:select path="genre3" items="${genreList}" disabled="${disabled}" />
		               </c:if>
		               <c:if test="${readonly == false}">
		                	<form:select path="genre3" items="${genreList}" tabindex="19"/>
		               </c:if>
	                </td>
				
					<td><form:label path="genreExtra"><spring:message code="label.genreExtra" /></form:label></td>
					<td>					
						<%-- <form:select path="genreExtra" items="${genreList}" disabled="${disabled}" /> --%>
						<c:if test="${readonly == true}">
		                	<form:select path="genreExtra" items="${genreList}" disabled="${disabled}" />
		               </c:if>
		               <c:if test="${readonly == false}">
		                	<form:select path="genreExtra" items="${genreList}" tabindex="20"/>
		               </c:if>
	                </td>
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
					<td><%-- <form:input path="dvdLocation" readonly="${readonly}" onblur="javascript:validateSize('dvdLocation',this.value,20);" tabindex="24"/> --%>
					<form:select path="dvdLocation" items="${dvdLocationList}" multiple="true" /> 
					
					</td>
				</tr>
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

