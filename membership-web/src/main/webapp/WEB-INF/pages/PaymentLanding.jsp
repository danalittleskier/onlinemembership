<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_2.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Membership Payment</div>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

	<%@ include file="/includes/messages.jsp"%>

<fieldset>
	<legend>Membership Payment</legend>

	<table border="0" width="100%" id="table1">
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Credit Card Results</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		</table>

</fieldset>

	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input type="submit" class="button" name="_eventId_back" value="<fmt:message key='button.back'/>">&nbsp;
	</fieldset>

</form:form>
</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">
	<!-- BOX (START) -->
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
		<p class="stg-omr-header">Membership Payment</p>
		<ul id="stg-omr">
			<b>Membership Payment</b><p>&nbsp;
	</div></div></div></div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>
</body>
