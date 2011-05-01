<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="735" /></div>


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
			<div class="button gray"><span><input type="button" class="btn-submit" name="_eventId_cancel" value="Cancel" onclick="submitFormWithInputButton(this);"></span></div>
			<div class="button green"><span><input type="submit" class="btn-submit" name="_eventId_update" value="Update"></span></div>
		</fieldset>

	</form:form>
</div>

	<%@ include file="fragments/contactInfoInstructions.jspf"%>

<div class="clear"></div>

</body>