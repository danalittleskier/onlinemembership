<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Release Waiver</div>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<fieldset>
<legend>Release Waiver</legend>

</fieldset>

<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="button" name="_eventId_next" value="Continue">
	<input type="submit" class="button" name="_eventId_back" value="Back">

</fieldset>


</form:form>
</div>


<!-- RIGHT column -->
<div id="stg-twocol-secondary">
<!-- BOX (START) -->

</div>
<div class="clear"></div>

</body>
