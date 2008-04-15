<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_5.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Membership Registration Complete</div>

<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">
<%@ include file="/includes/messages.jsp"%>

<div id="stg-pagetitle">Registration Complete</div>
<div id="stg-twocol-primary">
	<p>Your USSA registration is complete.</p>
	<fieldset>
		<legend>Your Registration Information</legend>
		<p>
			Your payment confirmation number is:
			<strong><c:out value="${accountBean.member.id}"/></strong>
		</p>
		<p>
			Your payment amount is:
			<strong><c:out value="${accountBean.cartBean.totalFormatted}"/></strong>
		</p>
	</fieldset>
	<fieldset>
		<legend>Your Membership Certificate</legend>
		<p>Please print your Membership Certificate and store it in a safe place for future use as proof of membership for USSA event participation.</p>
		<input class="btn-green" type="button" onclick="window.open('<c:url value="/certificate.html"/>');" value="Print Certificate" name="button"/>
	</fieldset>
</div>
<div id="stg-twocol-secondary">
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr">
	<div></div>
	<p class="stg-omr-header">When You're Finished...</p>
	<p>
		You may continue to the <a href="<c:url value="/dashboardRedirect.jsp"/>">USSA Member Home Page</a>
		to continue using your account or <a href="<c:url value="/logout.html"/>">log out</a> to end your session.
	</p>
	</div></div></div></div>
</div>
<div class="clear"/>

</form:form>
</div>
<div class="clear"></div>

</body>
