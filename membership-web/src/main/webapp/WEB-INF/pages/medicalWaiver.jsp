<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_3.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Medical Insurance Waiver</div>

<!-- LEFT column -->
<div id="stg-onecol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p> Waiver...</p>

* I agree to the terms
<form:hidden id="insuranceWaiver" path="memberLegal.insuranceWaiver"/>
<input id="medicalWaiverControl" type="checkbox" onclick="updateCheckboxHidden('insuranceWaiver', this)"/>
<script type="text/javascript" defer="defer">
	updateCheckboxControl('insuranceWaiver');
</script>

</div></div></div></div>
<!-- BOX (END) -->

	<p>
		<fieldset class="buttons">
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="btn-green" name="_eventId_next" value="I Agree to the Terms Above">
			<input type="submit" class="btn-green" name="_eventId_back" value="Back">
		</fieldset>
	</p>


</form:form>
</div>

<div class="clear"></div>

</body>
