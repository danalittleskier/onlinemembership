<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>

<div style="float:right;margin-top:-20px;margin-left:0px;margin-right:5px;"><script>showChatButton('membership@workgroup.im.ussa.org');</script></div>

<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>
<!-- LEFT column -->
<div id="stg-twocol-primary">

	<%@ include file="/includes/messages.jsp"%>

	<form:form commandName="accountBean" name="accountBean">

		<%@ include file="/includes/parentInfoFields.jsp"%>

		<fieldset class="buttons">
			<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="button" class="btn-green" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);">
			<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
		</fieldset>
	</form:form>
</div>


<div class="clear"></div>

</body>
