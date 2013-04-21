<script>
/* $(document).ready(function() {
	$("#frm").validationEngine()
}); */
function deleteById() {
	var conf = "";
	conf = confirm("Are you sure,you want to delete this Site?")
	if (conf == true) {
		document.frm.action = "${requestController}/deleteById/"+ document.frm.id.value;
		document.frm.submit();
	}
}
function cancel() {
	document.frm.method = "GET"
	document.frm.action = "${requestController}/list";
	document.frm.submit();

}

function isUpdate() {
	var conf = "";
	conf = confirm("Are you sure,you want to update this Site?")
	if (conf == true) {
		document.frm.submit();
	}
}
</script>