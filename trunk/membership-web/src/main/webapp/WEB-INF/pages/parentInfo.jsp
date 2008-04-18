<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>

<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>
<!-- LEFT column -->
<div id="stg-onecol-primary">

	<%@ include file="/includes/messages.jsp"%>

	<form:form commandName="accountBean" name="accountBean">

		<%@ include file="/includes/parentInfoFields.jsp"%>

		<p>
			<fieldset class="buttons">
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
				<input type="submit" class="btn-green" name="_eventId_back" value="Back">
				<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
			</fieldset>
		</p>
	</form:form>
</div>


<div class="clear"></div>

</body>
