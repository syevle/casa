<!doctype html public "-//w3c//dtd html 3.2//en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Demo of passing data from Child to parent window </title>
<script langauge="text/javascript">
function post_value(){
opener.document.frm.h_creator1.value = document.frm.c_name1.value;
opener.document.frm.h_creator2.value = document.frm.c_name2.value;
opener.document.frm.h_creator3.value = document.frm.c_name3.value;

 self.close();
}
function disp_text1()
{
var w = document.frm.creator1.selectedIndex;
var selected_text1 = document.frm.creator1.options[w].text;
document.getElementById('c_name1').value = selected_text1;
}
function disp_text2()
{
var w = document.frm.creator2.selectedIndex;
var selected_text2 = document.frm.creator2.options[w].text;
document.getElementById('c_name2').value = selected_text2;
}
function disp_text3()
{
var w = document.frm.creator3.selectedIndex;
var selected_text3 = document.frm.creator3.options[w].text;
document.getElementById('c_name3').value = selected_text3;
}

</script>
</head>

<body  onload="initializeMain();">

<form:form method="post" action="${action}" commandName="frmObject" name="frm" id="frm" class="formular">
<table>
<tr><td colspan="1">Creator 1</td></tr>
<tr>
<td><form:select path="creator1" items="${creatorList}" size="5" onclick="disp_text1();"/></td>
<td><input type="text" name="c_name1" id="c_name1" readonly></td></tr>
<tr><td colspan="1">Creator 2</td></tr>
<tr><td><form:select path="creator2" items="${creatorList}" size="5" onclick="disp_text2();"/></td>
<td><input type="text" name="c_name2" id="c_name2" readonly></td></tr>
<tr><td colspan="1">Creator 3</td></tr>
<tr><td><form:select path="creator3" items="${creatorList}" size="5" onclick="disp_text3();"/></td>
<td><input type="text" name="c_name3" id="c_name3" readonly></td></tr>


<tr><td><input type=button value='Submit' onclick="post_value();"></td><td></td></tr>
</table>
</form:form>
</body>
<script>  
    function initializeMain() {
    	
    		document.frm.c_name1.value = window.opener.document.getElementById("h_creator1").value;
    		document.frm.c_name2.value = window.opener.document.getElementById("h_creator2").value;
    		document.frm.c_name3.value = window.opener.document.getElementById("h_creator3").value;
   	 
    }
    </script>
</html>
 