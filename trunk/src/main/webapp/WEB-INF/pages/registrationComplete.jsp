<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_5.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code -->
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
<!-- END ProvideSupport.com Graphics Chat Button Code -->
</div>
<!-- End: Live Chat Support -->

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
	<!-- 
	<c:if test="${not empty accountBean.membershipsBean.unrestrictedMemberships}">
		<fieldset>
			<legend>Your Membership Verification</legend>
			<p>Please print your digital membership card.</p>
			<div class="button green"><span><input class="btn-submit" type="button" onclick="window.open('<c:url value="http://services.ussa.org/MembershipCard/home.htm?ussaId=${accountBean.member.id}"></c:url>');" value="Print Verification" name="button"/></span></div>
			<c:if test="${not empty accountBean.membershipsBean.restrictedMemberships}">]
				<p>Your digital membership card will only display memberships not requiring screening until positive results are received by USSA.</p>
			</c:if>
		</fieldset>
	</c:if>
	-->
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
	
	<c:if test="${showFastStartCourseLink}">
		<fieldset>
			<legend>Fast Start Sport Coaching Course Required</legend>
			<%@ include file="/includes/fastStartCourseText.jsp"%>

			<br/>
			<c:url var="fastStartCourseUrl" value="http://my.dev.ussa.org/tools/courses/status/${accountBean.member.id}">
				<c:param name="srb" value="11392848"/>
				<c:param name="id" value="${accountBean.member.id}"/>
			</c:url>
			<p><font size="2" ><a href="${fastStartCourseUrl}" target="_BLANK">Go here to complete your coaching course.</a></font></p>
		</fieldset>
	</c:if>
	<c:if test="${accountBean.globalRescueBean.isInCart }">
		<fieldset>
			<legend>Your Global Rescue Information</legend>
			<p>You will receive a confirmation email with the details of your annual medical evacuation membership and your Global Rescue login information.  For Global Rescue help call 1-617-459-4200 or visit <a href="http://.www.globalrescue.com">www.globalrescue.com</a>	</p>
			<p>Your Annual Global Rescue Membership: ${accountBean.globalRescueBean.purchasedProduct.inventory.description }</br>
			Your amount paid is: \$${accountBean.globalRescueBean.purchasedProduct.inventory.amount }
			</p>
	
			
		</fieldset>
	</c:if>
	
	<div class="button green"><span><input class="btn-submit" type="button" onclick="window.open('<c:url value="http://my.ussa.org/portal"></c:url>');" value="Return to My USSA" name="button"/></span></div>
			

</div>
<div id="stg-twocol-secondary">
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr">
	<div></div>
	<p class="stg-omr-header">What next</p>
	<p>
		<c:url var="continueUrl" value="/AccountsProfile/profile/profileManagement.htm">
			<c:param name="_eventId_next" value="Continue"/>
			<c:param name="_flowExecutionKey" value="${flowExecutionKey}"/>
		</c:url>

		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<c:url var="membershipCardUrl" value="http://my.ussa.org/tools/membership-card/${accountBean.member.id}">
		
		</c:url>
		<br>
		Click <a href="${membershipCardUrl}" target="blank">Here</a> to print your digital USSA Digital Membership Card 
		<br>
		Click <a href="http://my.ussa.org/tools/linked-member-accounts">Here</a> to link additional USSA members to your My USSA account
		<br>
		Click <a href="http://my.ussa.org/portal">Here</a> to get started with your personalized My USSA member tools 
		<br>
	</p>
	</div></div></div></div>
</div>
<div class="clear"></div>

</form:form>

</body>

