<style type="text/css" title="currentStyle">
@import "<%=request.getContextPath()%>/media/css/jquery-ui.css";
</style>

<%-- <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/media/js/jquery-1.9.1.js"></script> --%>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery-ui.js"></script>

<script>
  $(function() {
    var availableTags = [
      ${siteIdList}
    ];
    $( "#id" ).autocomplete({
      source: availableTags
    });
  });
  
  $(function() {
	    var availableTags = [
	      ${nameList}
	    ];
	    $( "#name" ).autocomplete({
	      source: availableTags
	    });
	  });
  
  
  function validateFormForBlank()
  {
	  var flag = false;
	  var movieid=document.forms["frm"]["id"].value;
	  var movieName=document.forms["frm"]["name"].value;
	  
	  var movieidflag = true;
	  var movieNameflag = true;
	  
		  if (movieid==null || movieid==trim(movieid).length)
		    {
			  movieidflag = false;
		    }
		  if (movieName==null || movieName==trim(movieName).length)
		  {
			  movieNameflag = false;
		  }
		  
		 
		  if(movieidflag || movieNameflag){
			  return validateFormForSecondSubmit();
		  }else{
			  return false;
		  }
  }
  function trim(str) {
	    return str.replace(/^\s+|\s+$/g,'');
	}
  function validateFormForSecondSubmit()
  {
	  var movieid=document.forms["frm"]["id"].getAttribute('readonly');
	  var movieName=document.forms["frm"]["name"].getAttribute('readonly');
	  
	  var movieidflag = true;
	  var movieNameflag = true;
	  
	  if(movieid || movieName){
		  return false;
	  }else{
		  return true;
	  }
  }
  

  </script>
 
<form:form method="POST" action="${requestController}/list" commandName="frmObject" name="frm" id="frm">
	<table>
		<tr>
			<td><form:label path="id"><spring:message code="label.siteId" /></form:label>
			</td><td><form:input path="id" readonly="${readonly}"  /></td>
		
			<td><form:label path="name"><spring:message code="label.siteName" /></form:label></td>
			<td><form:input path="name" readonly="${readonly}" /></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="Serch" onclick="return validateFormForBlank();" /></td>
			<td><a href="${requestController}/list"><input type="button" value="Clear" /></a></td>
		</tr>
	</table>
</form:form>

