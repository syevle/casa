<!doctype html public "-//w3c//dtd html 3.2//en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Demo of passing data from Child to parent window </title>
<script langauge="text/javascript">
function post_value(){
opener.document.frm.h_starcastName1.value = document.frm.c_name1.value;
opener.document.frm.h_starcastName2.value = document.frm.c_name2.value;
opener.document.frm.h_starcastName3.value = document.frm.c_name3.value;
opener.document.frm.h_starcastName4.value = document.frm.c_name4.value;
opener.document.frm.h_starcastName5.value = document.frm.c_name5.value;
opener.document.frm.h_starcastName6.value = document.frm.c_name6.value;

 self.close();
}
function disp_text1()
{
var w = document.frm.starcastName1.selectedIndex;
var selected_text1 = document.frm.starcastName1.options[w].text;
document.getElementById('c_name1').value = selected_text1;
}
function disp_text2()
{
var w = document.frm.starcastName2.selectedIndex;
var selected_text2 = document.frm.starcastName2.options[w].text;
document.getElementById('c_name2').value = selected_text2;
}
function disp_text3()
{
var w = document.frm.starcastName3.selectedIndex;
var selected_text3 = document.frm.starcastName3.options[w].text;
document.getElementById('c_name3').value = selected_text3;
}
function disp_text4()
{
var w = document.frm.starcastName4.selectedIndex;
var selected_text4 = document.frm.starcastName4.options[w].text;
document.getElementById('c_name4').value = selected_text4;
}
function disp_text5()
{
var w = document.frm.starcastName5.selectedIndex;
var selected_text5 = document.frm.starcastName5.options[w].text;
document.getElementById('c_name5').value = selected_text5;
}
function disp_text6()
{
var w = document.frm.starcastName6.selectedIndex;
var selected_text6 = document.frm.starcastName6.options[w].text;
document.getElementById('c_name6').value = selected_text6;
}
</script>
</head>

<body  onload="initializeMain();">

<form:form method="post" action="${action}" commandName="frmObject" name="frm" id="frm" class="formular">
<table>
<tr><td colspan="1">Starcast 1</td></tr>
<tr>
<td><form:select path="starcastName1" items="${starcastList}" size="5" onclick="disp_text1();"/></td>
<td><input type="text" name="c_name1" id="c_name1" readonly></td></tr>
<tr><td colspan="1">Starcast 2</td></tr>
<tr>
<td><form:select path="starcastName2" items="${starcastList}" size="5" onclick="disp_text2();"/></td>
<td><input type="text" name="c_name2" id="c_name2" readonly></td></tr>
<tr><td colspan="1">Starcast 3</td></tr>
<tr>
<td><form:select path="starcastName3" items="${starcastList}" size="5" onclick="disp_text3();"/></td>
<td><input type="text" name="c_name3" id="c_name3" readonly></td></tr>
<tr><td colspan="1">Starcast 4</td></tr>
<tr>
<td><form:select path="starcastName4" items="${starcastList}" size="5" onclick="disp_text4();"/></td>
<td><input type="text" name="c_name4" id="c_name4" readonly></td></tr>
<tr><td colspan="1">Starcast 5</td></tr>
<tr>
<td><form:select path="starcastName5" items="${starcastList}" size="5" onclick="disp_text5();"/></td>
<td><input type="text" name="c_name5" id="c_name5" readonly></td></tr>
<tr><td colspan="1">Starcast 6</td></tr>
<tr>
<td><form:select path="starcastName6" items="${starcastList}" size="5" onclick="disp_text6();"/></td>
<td><input type="text" name="c_name6" id="c_name6" readonly></td></tr>

<tr><td><input type=button value='Submit' onclick="post_value();"></td><td></td></tr>
</table>
</form:form>
</body>
<script>  
    function initializeMain() {
    	
    		document.frm.c_name1.value = window.opener.document.getElementById("starcastName1").value;
    		document.frm.c_name2.value = window.opener.document.getElementById("starcastName2").value;
    		document.frm.c_name3.value = window.opener.document.getElementById("starcastName3").value;
    		document.frm.c_name4.value = window.opener.document.getElementById("starcastName4").value;
    		document.frm.c_name5.value = window.opener.document.getElementById("starcastName5").value;
    		document.frm.c_name6.value = window.opener.document.getElementById("starcastName6").value;
   	 
    }
    </script>
</html>
 