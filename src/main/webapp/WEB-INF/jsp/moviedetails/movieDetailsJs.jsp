<script>
	/* $(document).ready(function() {
	 $("#frm").validationEngine()
	 }); */
	function deleteById() {
		var conf = "";
		conf = confirm("Are you sure,you want to delete this Movie Details?")
		if (conf == true) {
			document.frm.action = "${requestController}/deleteById/"
					+ document.frm.id.value;
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
		conf = confirm("Are you sure,you want to update this Movie Details?")
		if (conf == true) {
			document.frm.submit();
		}
	}
	function isAdd() {
		var conf = "";
		conf = confirm("Are you sure,you want to Add this Movie Details?")
		if (conf == true) {
			document.frm.submit();
		}
	}
	function generateId(data) {
		var id = document.getElementById("id").value
		document.getElementById("id").value = data.value + id.substring(3);
	}
</script>