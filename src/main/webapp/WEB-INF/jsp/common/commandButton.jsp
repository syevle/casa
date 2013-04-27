
<tr>
	<c:if test="${command == 'update'}">
		<td><input class="styled-button-1" type="button" value="Update"
			onclick="javaScript:isUpdate();return false;" />
		</td>
		<td><a href="${requestController}/list"><input type="button" class="styled-button-1" value="Cancel" /></a>
		</td>
		<!-- <td><input class="styled-button-1" type="submit"
			value="Close all prompt"
			onclick="$.validationEngine.closePrompt('.formError',true)" />
		</td> -->
	</c:if>
	<c:if test="${command == 'delete'}">
		<td><input class="styled-button-1" type="button" value="Delete"
			onclick="javaScript:deleteById(); return false;" />
		</td>
		<td><a href="${requestController}/list"><input type="button" class="styled-button-1" value="Cancel" /></a>
		</td>
	</c:if>
	<c:if test="${command == 'add'}">
		<td><input class="styled-button-1" type="button" value="ADD" onclick="javaScript:isAdd();return false;" />
		</td>
		<td><a href="${requestController}/list"><input type="button" class="styled-button-1" value="Cancel" /></a>
		</td>
	</c:if>
</tr>