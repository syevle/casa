<!doctype html public "-//w3c//dtd html 3.2//en">
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
var w = document.frm.mylist1.selectedIndex;
var selected_text1 = document.frm.mylist1.options[w].text;
document.getElementById('c_name1').value = selected_text1;
}
function disp_text2()
{
var w = document.frm.mylist2.selectedIndex;
var selected_text2 = document.frm.mylist2.options[w].text;
document.getElementById('c_name2').value = selected_text2;
}
function disp_text3()
{
var w = document.frm.mylist3.selectedIndex;
var selected_text3 = document.frm.mylist3.options[w].text;
document.getElementById('c_name3').value = selected_text3;
}

</script>
</head>

<body>

<form name="frm" method=post action=''>
<table>
<tr><td>
<select name="mylist1" id="mylist1" size="4" multiple="multiple" onclick="disp_text1();">
    <option value="1">item 1</option>
    <option value="2">item 2</option>
    <option value="3">item 3</option>
    <option value="4">item 4</option>
    <option value="0">All</option>
</select></td>
<td><input type="text" name="c_name1" id="c_name1" readonly></td></tr>
<tr><td>
<select name="mylist2" id="mylist2" size="4" multiple="multiple" onclick="disp_text2();">
    <option value="1">item 1</option>
    <option value="2">item 2</option>
    <option value="3">item 3</option>
    <option value="4">item 4</option>
    <option value="0">All</option>
</select></td>
<td><input type="text" name="c_name2" id="c_name2" readonly></td></tr>
<tr><td>
<select name="mylist3" id="mylist3" size="4" multiple="multiple" onclick="disp_text3();">
    <option value="1">item 1</option>
    <option value="2">item 2</option>
    <option value="3">item 3</option>
    <option value="4">item 4</option>
    <option value="0">All</option>
</select></td>
<td><input type="text" name="c_name3" id="c_name3" readonly></td></tr>


<tr><td><input type=button value='Submit' onclick="post_value();"></td><td></td></tr>
</table>
</form>
</body>
</html>
 