<!doctype html public "-//w3c//dtd html 3.2//en">
<html>
<head>
<title>Demo of passing data from Child to parent window </title>
<script langauge="text/javascript">
function post_value(){
opener.document.frm.h_starcastName1.value = document.frm.c_name.value;
 self.close();
}
function disp_text()
{
var w = document.frm.mylist.selectedIndex;
var selected_text = document.frm.mylist.options[w].text;
document.getElementById('c_name').value = selected_text;
}
</script>
</head>

<body>

<form name="frm" method=post action=''>
<!-- <table border=0 cellpadding=0 cellspacing=0 width=250>

 <tr><td align="center">  Your name<input type="text" name="c_name" size=12 value=test> <input type=button value='Submit' onclick="post_value();">
  </td></tr>
</table> -->

<table><tr>
<select name="mylist" id="mylist" size="4" multiple="multiple" onclick="disp_text();">
    <option value="1">item 1</option>
    <option value="2">item 2</option>
    <option value="3">item 3</option>
    <option value="4">item 4</option>
    <option value="0">All</option>
</select>
</tr>
<tr>
<input type="text" name="c_name" id="c_name" readonly>
</tr>
<tr>
<input type=button value='Submit' onclick="post_value();">
</tr>
</table>
</form>


</body>

</html>
 