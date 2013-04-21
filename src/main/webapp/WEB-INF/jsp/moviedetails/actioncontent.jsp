
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
					<td><form:input path="id" readonly="true" /></td>
				</tr>
				<tr>
					<td><form:label path="movName"><spring:message code="label.movName" /></form:label></td>
					<td><form:input path="movName" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="avalStatus"><spring:message code="label.avalStatus" /></form:label></td>
					<td>
					<form:select path="avalStatus" disabled="${disabled}">
	                  <form:option value="Y" label="Yes" />
	                  <form:option value="N" label="No" />
	                </form:select>
					</td>
				</tr>
				
				<tr>
					<td><form:label path="mediaFormat"><spring:message code="label.mediaFormat" /></form:label></td>
					<td>
					<form:select path="mediaFormat" onchange="generateId(this);">
	                  <form:option value="DVD" label="DVD" />
	                  <form:option value="BLR" label="BLR" />
	                  <form:option value="OTH" label="OTH" />
	                </form:select>
					</td>
				</tr>
				<tr>
					<td><form:label path="languages"><spring:message code="label.languages" /></form:label></td>
					<td><form:input path="languages" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="relDate"><spring:message code="label.relDate" /></form:label></td>
					<td><form:input path="relDate" readonly="true"  /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName1"><spring:message code="label.starcastName1" /></form:label></td>
					<td><form:input path="starcastName1" readonly="${readonly}" /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName2"><spring:message code="label.starcastName2" /></form:label></td>
					<td><form:input path="starcastName2" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName3"><spring:message code="label.starcastName3" /></form:label></td>
					<td><form:input path="starcastName3" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName4"><spring:message code="label.starcastName4" /></form:label></td>
					<td><form:input path="starcastName4" readonly="${readonly}" /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName5"><spring:message code="label.starcastName5" /></form:label></td>
					<td><form:input path="starcastName5" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="starcastName6"><spring:message code="label.starcastName6" /></form:label></td>
					<td><form:input path="starcastName6" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="director1"><spring:message code="label.director1" /></form:label></td>
					<td><form:input path="director1" readonly="${readonly}" /></td>
				</tr>
				<tr>
					<td><form:label path="director2"><spring:message code="label.director2" /></form:label></td>
					<td><form:input path="director2" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="director3"><spring:message code="label.director3" /></form:label></td>
					<td><form:input path="director3" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="creator1"><spring:message code="label.creator1" /></form:label></td>
					<td><form:input path="creator1" readonly="${readonly}" /></td>
				</tr>
				<tr>
					<td><form:label path="creator2"><spring:message code="label.creator2" /></form:label></td>
					<td><form:input path="creator2" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="creator3"><spring:message code="label.creator3" /></form:label></td>
					<td><form:input path="creator3" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="genre1"><spring:message code="label.genre1" /></form:label></td>
					<td>					
						<form:select path="genre1">
						  <form:option value="" label="SELECT" />
		                  <form:option value="ACTION" label="ACTION" />
		                  <form:option value="CRIMAL" label="CRIMAL" />
		                  <form:option value="COMEDY" label="COMEDY" />
		                </form:select>
	                </td>
				</tr>
				<tr>
					<td><form:label path="genre2"><spring:message code="label.genre2" /></form:label></td>
					<td>					
						<form:select path="genre2">
						<form:option value="" label="SELECT" />
		                  <form:option value="ACTION" label="ACTION" />
		                  <form:option value="CRIMAL" label="CRIMAL" />
		                  <form:option value="COMEDY" label="COMEDY" />
		                </form:select>
	                </td>
				</tr>
				<tr>
					<td><form:label path="genre3"><spring:message code="label.genre3" /></form:label></td>
					<td>					
						<form:select path="genre3">
						<form:option value="" label="SELECT" />
		                  <form:option value="ACTION" label="ACTION" />
		                  <form:option value="CRIMAL" label="CRIMAL" />
		                  <form:option value="COMEDY" label="COMEDY" />
		                </form:select>
	                </td>
				</tr>
				<tr>
					<td><form:label path="genreExtra"><spring:message code="label.genreExtra" /></form:label></td>
					<td>					
						<form:select path="genreExtra">
						<form:option value="" label="SELECT" />
		                  <form:option value="ACTION" label="ACTION" />
		                  <form:option value="CRIMAL" label="CRIMAL" />
		                  <form:option value="COMEDY" label="COMEDY" />
		                </form:select>
	                </td>
				</tr>
				<tr>
					<td><form:label path="country"><spring:message code="label.country" /></form:label></td>
					<td><form:input path="country" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="tagLine"><spring:message code="label.tagLine" /></form:label></td>
					<td><form:input path="tagLine" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="dvdLocation"><spring:message code="label.dvdLocation" /></form:label></td>
					<td><form:input path="dvdLocation" readonly="${readonly}" /></td>
				</tr>
				<tr>
					<td><form:label path="movPath"><spring:message code="label.movPath" /></form:label></td>
					<td><form:input path="movPath" readonly="${readonly}"  /></td>
				</tr>
				<tr>
					<td><form:label path="entryDt"><spring:message code="label.entryDt" /></form:label></td>
					<td><form:input path="entryDt" readonly="true"  /></td>
				</tr>
				<tr>
					<td><form:label path="uplodReq"><spring:message code="label.uplodReq" /></form:label></td>
					<td>					
						<form:select path="uplodReq" disabled="${disabled}">
		                  <form:option value="Y" label="Yes" />
		                  <form:option value="N" label="No" />
		                </form:select>
	                </td>
				</tr>				
				
				<%@ include file="../common/commandButton.jsp"%>
			</table>
		</form:form>
	</div>
</div>

