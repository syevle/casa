<table>
<tr>
	<c:if test="${command == 'update'}">
		<td><input class="styled-button-1" type="button" value="Update" onclick="javaScript:isUpdate();return false;" tabindex="50" />
		</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="styled-button-1" value="Cancel" onclick="goBack()" tabindex="51"/></td>
		
	</c:if>
	<c:if test="${command == 'delete'}">
		<td><input class="styled-button-1" type="button" value="Delete" onclick="javaScript:deleteById(); return false;" tabindex="50"/>
		</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="styled-button-1" value="Cancel" onclick="goBack()" tabindex="51" /></td>
	</c:if>
	<c:if test="${command == 'add'}">
		<td><input class="styled-button-1" type="button" value="ADD" onclick="javaScript:isAdd();return false;" tabindex="50"/>
		</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="styled-button-1" value="Cancel" onclick="goBack()" tabindex="51" /></td>
	</c:if>
	<c:if test="${command == 'get'}">
		<td><input type="button" class="styled-button-1" value="Cancel" onclick="goBack()" tabindex="51" /></td>
		<td></td>
	</c:if>
</tr>
</table>