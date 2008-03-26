<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_4.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Release Waiver</div>

<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">
<%@ include file="/includes/messages.jsp"%>

	<!-- BOX (START) -->
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
	<p>Waiver...</p>
	</div></div></div></div>
	<!-- BOX (END) -->
		<p>
		<form name="" action="" method="">
		<fieldset class="buttons">
			<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="brn-green" name="_eventId_next" value="Continue">
			<input type="submit" class="btn-green" name="_eventId_back" value="Back">
		</fieldset>
		</form>
		</p>
</div>
<div class="clear"></div>
</form:form>

</body>
