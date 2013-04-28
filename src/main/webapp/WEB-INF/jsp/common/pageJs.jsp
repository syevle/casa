<%-- <script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery.validationEngine-en.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery.validationEngine.js"></script> --%>


	
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/media/js/jquery.dataTables.js"></script>
	
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		oTable = $('#example').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers"
		});
	});
</script>
  <script type="text/javascript">
  function validateSize(name,text,length)
  {
	  var  mLen = length;
	  var maxLength = parseInt(mLen);
	  if (text.length > maxLength) {
		  document.getElementById(name).focus();
		  document.getElementById(name).style.color = "#FF0000";
        	  alert("Max size is "+mLen)
      }else{
    	  document.getElementById(name).style.color = "#000000";
      }
  };
  function load(name)
  {
	  var  actualValue = document.getElementById(name).value;
	  var txtValue = parseInt(actualValue);
	  if (txtValue == 0) {
	  	document.getElementById(name).value = "";
	  }
  }
</script>
