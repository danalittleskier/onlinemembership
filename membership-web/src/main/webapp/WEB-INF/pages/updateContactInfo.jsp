<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>

<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>
<!-- LEFT column -->
<div id="stg-twocol-primary">

	<%@ include file="/includes/messages.jsp"%>

	<form:form commandName="accountBean" name="accountBean">

		<%@ include file="fragments/contactInfo.jspf"%>

		<fieldset id="buttonFieldSet" class="buttons">
			<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="button" class="btn-green" name="_eventId_cancel" value="Cancel" onclick="submitFormWithInputButton(this);">
			<input type="submit" class="btn-green" name="_eventId_update" value="Update">
		</fieldset>

	</form:form>
</div>

	<%@ include file="fragments/contactInfoInstructions.jspf"%>

</div>
<div class="clear"></div>

</body>