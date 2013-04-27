<style type="text/css" title="currentStyle">
@import "<%=request.getContextPath()%>/media/css/jquery-ui.css";
</style>

<%-- <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/media/js/jquery-1.9.1.js"></script> --%>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery-ui.js"></script>

<script>
  $(function() {
    var availableTags = [
      ${movieCodeList}
    ];
    $( "#id" ).autocomplete({
      source: availableTags
    });
  });
  
  $(function() {
	    var availableTags = [
	      ${movieNameList}
	    ];
	    $( "#movName" ).autocomplete({
	      source: availableTags
	    });
	  });
  
  $(function() {
	    var availableTags = [
	      ${genreAllList}
	    ];
	    $( "#genre1" ).autocomplete({
	      source: availableTags
	    });
	  }); 
  
 $(function() {
	    var availableTags = [
	      ${starcastAllList}
	    ];
	    $( "#starcastName1" ).autocomplete({
	      source: availableTags
	    });
	  });
  
  function validateFormForBlank()
  {
	  var flag = false;
	  var movieid=document.forms["frm"]["id"].value;
	  var movieName=document.forms["frm"]["movName"].value;
	  var starcastName=document.forms["frm"]["starcastName1"].value;
	  var genre=document.forms["frm"]["genre1"].value;
	  
	  var movieidflag = true;
	  var movieNameflag = true;
	  var starcastNameflag = true;
	  var genreflag = true;
	  
		  if (movieid==null || movieid==trim(movieid).length)
		    {
			  movieidflag = false;
		    }
		  if (movieName==null || movieName==trim(movieName).length)
		  {
			  movieNameflag = false;
		  }
		  
		  if (starcastName==null || starcastName==trim(starcastName).length)
		    {
			  starcastNameflag = false;
		    }
		  if (genre==null || genre==trim(genre).length)
		  {
			  genreflag = false;
		  }
		  if(movieidflag || movieNameflag || starcastNameflag || genreflag){
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
	  var movieName=document.forms["frm"]["movName"].getAttribute('readonly');
	  var starcastName=document.forms["frm"]["starcastName1"].value;
	  var genre=document.forms["frm"]["genre1"].value;
	  
	  var movieidflag = true;
	  var movieNameflag = true;
	  var starcastNameflag = true;
	  var genreflag = true;
	  
	  if(movieid || movieName){
		  return false;
	  }else{
		  return true;
	  }
  }
  

  </script>
 
<form:form method="GET" action="${requestController}/list" commandName="frmObject" name="frm" id="frm">
	<table>
		<tr>
			<td><form:label path="id"><spring:message code="label.movCode" /></form:label>
			</td><td><form:input path="id" readonly="${readonly}"  /></td>
		
			<td><form:label path="movName"><spring:message code="label.movName" /></form:label></td>
			<td><form:input path="movName" readonly="${readonly}" /></td>
		</tr>
		<tr>
			<td><form:label path="starcastName1"><spring:message code="label.starcastName" /></form:label></td>
			<td><form:input path="starcastName1" readonly="${readonly}" /></td>
		
			<td><form:label path="genre1"><spring:message code="label.genre" /></form:label></td>
			<td><form:input path="genre1" readonly="${readonly}" /></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="Serch" onclick="return validateFormForBlank();" /></td>
			<td><a href="${requestController}/list"><input type="button" value="Clear" /></a></td>
		</tr>
	</table>
</form:form>

