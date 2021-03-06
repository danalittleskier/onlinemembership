<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_5.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code 
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
END ProvideSupport.com Graphics Chat Button Code -->

</div>
<!-- End: Live Chat Support -->

<div id="stg-pagetitle">Registration Complete</div>

<form:form commandName="accountBean" name="accountBean">
<%@ include file="/includes/messages.jsp"%>

<div id="stg-twocol-primary">
	<p>Your U.S. Ski & Snowboard registration is complete.</p>
	<fieldset>
		<legend>Your Registration Information</legend>
		<p>
			Your U.S. Ski & Snowboard ID is:
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
		<!-- 
		<c:if test="${not empty accountBean.member.nensaId}">
		<p>
			Your NENSA ID is:
			<strong><c:out value="${accountBean.member.nensaId}"/></strong>
		</p>
		</c:if>
		-->
	</fieldset>
	<!-- 
	<c:if test="${not empty accountBean.membershipsBean.unrestrictedMemberships}">
		<fieldset>
			<legend>Your Membership Verification</legend>
			<p>Please print your digital membership card.</p>
			<div class="button green"><span><input class="btn-submit" type="button" onclick="window.open('<c:url value="http://services.ussa.org/MembershipCard/home.htm?ussaId=${accountBean.member.id}"></c:url>');" value="Print Verification" name="button"/></span></div>
			<c:if test="${not empty accountBean.membershipsBean.restrictedMemberships}">]
				<p>Your digital membership card will only display memberships not requiring screening until positive results are received by U.S. Ski & Snowboard.</p>
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
			<c:url var="fastStartCourseUrl" value="http://my.ussa.org/ussec/index.php">
				
			</c:url>
			<p><font size="2" ><a href="${fastStartCourseUrl}" target="_BLANK">Go here to complete your coaching course.</a></font></p>
		</fieldset>
	</c:if>
	<c:if test="${accountBean.globalRescueBean.isInCart }">
		<fieldset>
			<legend>Your Global Rescue Information</legend>
			<c:choose>
				<c:when test="${empty accountBean.globalRescueBean.messages }">
					<p>You will receive a confirmation email with the details of your annual medical evacuation membership and your Global Rescue login information.  For Global Rescue help call 1-617-459-4200 or visit <a href="http://www.globalrescue.com" target="new">www.globalrescue.com</a>	</p>
					<p>Your Annual Global Rescue Membership: ${accountBean.globalRescueBean.purchasedProduct.inventory.description }<br/>
					Global Rescue GUID: ${accountBean.memberSeason.globalRescueGUID }<br>
					Your amount paid is: \$${accountBean.globalRescueBean.purchasedProduct.inventory.amount }
					</p>
				</c:when>
				<c:when test="${not empty accountBean.globalRescueBean.messages }">
					<font color=red>There has been a problem while creating your account with Global Rescue. </font> Please call Membership Services at 435-647-2666.<br/>
			<br/>
					When you call please report the following notes:<br/>
					<ul>
					<c:forEach var="messageString" items="${accountBean.globalRescueBean.messages }">
						<li>${messageString} 
					</c:forEach>
					</ul>
					<p>Your Annual Global Rescue Membership: ${accountBean.globalRescueBean.purchasedProduct.inventory.description }</br>
					Global Rescue GUID: ${accountBean.memberSeason.globalRescueGUID }<br>
					Your amount paid is: \$${accountBean.globalRescueBean.purchasedProduct.inventory.amount }
					</p>
				</c:when>
			</c:choose>
		
		
	</fieldset>
</c:if>

<div class="ussa-button green"><span><input class="btn-submit" type="button" onclick="window.open('<c:url value="http://my.ussa.org/portal"></c:url>');" value="Return to My USSA" name="button"/></span></div>
			

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
		Click <a href="http://my.ussa.org/portal" target="blank">Here</a> to go to your My U.S. Ski & Snowboard account home to do the following: 
		<br>
		1. Print your U.S. Ski & Snowboard membership card.
		<br>
		2. Link additional members to your account.
		<br>
		3. Use your personalized members tools.
		<br>
	</p>
	</div></div></div></div>
	<p>
	<b>U.S. Ski & Snowboard Rewards. It's easy!</b><br>

	<u>U.S. Ski & Snowboard Rewards</u> is your one-stop shop to discounts and cash back on thousands of products, services, retailers and restaurants. Get discounts on everything from lodging and movies to insurance! U.S. Ski & Snowboard Rewards saves you money and a portion of your purchase goes to support the athletes and programs that make us Best in the World!
	<li>Access U.S. Ski & Snowboard Rewards today at <a href="http://www.experticity.com" target="blank" >www.experticity.com </a> or through the U.S. Ski & Snowboard Rewards button in your My U.S. Ski & Snowboard account</li>
	<li>Create your U.S. Ski & Snowboard Rewards account</li>
    <li>Start shopping! Enjoy  special Weekly Deals featuring deep discounts on great products and new offers that come on the site virtually every day.  </li>
	</p>
</div>
<div class="clear"></div>

</form:form>

</body>

