<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_5.gif'/>" width="735" /></div>

<div style="float:right;margin-top:-20px;margin-left:0px;margin-right:5px;"><script>showChatButton('membership@workgroup.im.ussa.org');</script></div>

<div id="stg-pagetitle">Registration Complete</div>

<form:form commandName="accountBean" name="accountBean">
<%@ include file="/includes/messages.jsp"%>

<div id="stg-twocol-primary">
	<p>Your USSA registration is complete.</p>
	<fieldset>
		<legend>Your Registration Information</legend>
		<p>
			Your USSA ID is:
			<strong><c:out value="${accountBean.member.id}"/></strong>
		</p>
		<p>
			Your payment confirmation number is:
			<strong><c:out value="${accountBean.paymentBean.completedTransactionId}"/></strong>
		</p>
		<p>
			Your amount paid is:
			<strong><c:out value="${accountBean.cartBean.totalFormatted}"/></strong>
		</p>
	</fieldset>
	<c:if test="${not empty accountBean.membershipsBean.unrestrictedMemberships}">
		<fieldset>
			<legend>Your Membership Verification</legend>
			<p>Please print your digital membership card.</p>
			<input class="btn-green" type="button" onclick="window.open('<c:url value="http://services.ussa.org/MembershipCard/home.htm?ussaId=${accountBean.member.id}"></c:url>');" value="Print Verification" name="button"/>
			<c:if test="${not empty accountBean.membershipsBean.restrictedMemberships}">]
				<p>Your digital membership card will only display memberships not requiring screening until positive results are received by USSA.</p>
			</c:if>
		</fieldset>
	</c:if>

	<c:if test="${not empty accountBean.membershipsBean.restrictedMemberships}">
		<fieldset>
			<legend>Background Screening Required</legend>
			<%@ include file="/includes/backgroundScreeningText.jsp"%>

			<br/>
			<c:url var="ncsiUrl" value="https://www.ncsisafe.com/members/selfregbatchcodelanding.aspx">
				<c:param name="srb" value="11392848"/>
				<c:param name="id" value="${accountBean.member.id}"/>
			</c:url>
			<p><font size="2" ><a href="${ncsiUrl}" target="_BLANK">Go here to complete your background screening.</a></font></p>
		</fieldset>
	</c:if>

</div>
<div id="stg-twocol-secondary">
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr">
	<div></div>
	<p class="stg-omr-header">When You're Finished...</p>
	<p>
		<c:url var="continueUrl" value="/registration.html">
			<c:param name="_eventId_next" value="Continue"/>
			<c:param name="_flowExecutionKey" value="${flowExecutionKey}"/>
		</c:url>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		You may continue to the <a href="${continueUrl}">USSA Account Dashboard</a>
		to use your account or <a href="<c:url value="/j_acegi_logout"/>">log out</a> to end your session.
	</p>
	</div></div></div></div>
</div>
<div class="clear"></div>

</form:form>

</body>
