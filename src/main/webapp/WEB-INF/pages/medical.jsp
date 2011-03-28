<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_3.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Medical Information</div>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<%@ include file="fragments/medicalInfo.jspf"%>

<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="button" class="btn-green" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);">
	<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
</fieldset>

</form:form>
</div>

<%@ include file="fragments/medicalInfoInstructions.jspf"%>

<div class="clear"></div>

</body>
