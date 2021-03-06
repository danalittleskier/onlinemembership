<%@ include file="/includes/taglibs.jsp"%>
<body>

<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_4.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code 
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
END ProvideSupport.com Graphics Chat Button Code -->

</div>
<!-- End: Live Chat Support -->
<div id="stg-pagetitle">U.S. Ski & Snowboard Concussion Policy </div>
<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<p>
This is a waiver of certain legal rights. Please read carefully. You must agree to its terms to complete the registration process.
</p>
<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p>

Any U.S. Ski & Snowboard athlete suspected of having sustained a concussion or traumatic brain injury must be removed immediately from participation in U.S. Ski & Snowboard sporting event (e.g. sanctioned training, practice, camps, competitions or tryouts), by the Technical Delegate or U.S. Ski & Snowboard member coach overseeing such sporting event.  The athlete will be prohibited from further participation until evaluated and cleared 
in writing to resume participation in U.S. Ski & Snowboard sporting events by a qualified health care provider trained in the evaluation and management of concussive head injuries.  The health care professional must certify to U.S. Ski & Snowboard in the clearance letter that he/she has successfully completed a continuing education course in the evaluation and management of concussive head injuries within three years of the day on which the written statement is made.
<br><br>Upon removal of an athlete from participation for a suspected concussion or traumatic brain injury, U.S. Ski & Snowboard TD or member coach making the removal must inform U.S. Ski & Snowboard Competition Services.  Athletes who have subsequently been medically cleared to resume participation must provide such medical clearance (as described above) to U.S. Ski & Snowboard Competition Services in order to be permitted to participate in U.S. Ski & Snowboard sporting events.
<br><br>
<b>About Concussion </b>
<br>
A concussion is a type of traumatic brain injury (TBI) caused by a bump, blow, or jolt to the head. Concussions can also occur from a fall or a blow to the body that causes the head and brain to move quickly back and forth. Doctors may describe a concussion as a "mild" brain injury because concussions are usually not life-threatening. Even so, their effects can be serious.
<br><br>
<b>Risk of Continued Participation</b>
<br>
A repeat concussion that occurs before the brain recovers from the first-usually within a short period of time (hours, days, or weeks)-can slow recovery or increase the likelihood of having long-term problems. In rare cases, repeat concussions can result in edema (brain swelling), permanent brain damage, and even death.
<br><br>
U.S. Ski & Snowboard recommends that Members review the Center for Disease Control's resources on concussion awareness at the following link:
<br>
<a href="http://www.cdc.gov/concussion/HeadsUp/online_training.html" target="new">http://www.cdc.gov/concussion/HeadsUp/online_training.html</a>

</p>


<form:hidden id="concussionWaiver" path="memberLegal.concussionWaiver"/>
<table width="100%">
	<tr>
		<td width="30px"><input id="concussionWaiverControl" type="checkbox" onclick="updateCheckboxHidden('concussionWaiver', this)"/></td>
		<td>* I HAVE CAREFULLY READ THE FOREGOING AND UNDERSTAND IT TO BE A LEGALLY BINDING RELEASE AND INDEMNITY AGREEMENT AND AGREE TO ITS TERMS</td>
	</tr>
</table>
<script type="text/javascript" defer="defer">
	updateCheckboxControl('concussionWaiver');
</script>

<c:if test="${accountBean.parentInfoRequired}">
	<br/>
	<h2>Parent Release</h2>
	<p>
		As the parent or guardian of the minor child Member indicated above, I hereby make and enter into each and every agreement,
		representation, waiver and release described here on behalf of myself, the Member, and any other parent or guardian of the
		Member, intending that they be binding on me, the Member, and our respective heirs, executors, administrators and assigns. 
		By entering my signature below I represent that I intend to give up my right, the right of the Member, and the right of
		any other parent or guardian to maintain any claim or suit against U.S. Ski & Snowboard arising out of the Member's participation in any
		Activities involving U.S. Ski & Snowboard in any way. I further agree to hold harmless, defend, and indemnify U.S. Ski & Snowboard of and from any claims
		from third parties arising from the minor child Members' participation in any activities affiliated with U.S. Ski & Snowboard.
	</p>
	<form:hidden id="releaseWaiverParentalConsent" path="releaseWaiverParentalConsent"/>
	<table width="100%">
		<tr>
			<td width="30px"><input id="releaseWaiverParentalConsentControl" type="checkbox" onclick="updateCheckboxHidden('releaseWaiverParentalConsent', this)"/></td>
			<td><span style="float:left;">* I, &nbsp;</span><form:input path="memberLegal.concussionGuardianName" cssStyle="float:left;"/><span style="float:left;"> CERTIFY, THAT I AM THE PARENT OR GUARDIAN OF THE MEMBER NAMED ABOVE.</span></td>
		</tr>
	</table>
	<script type="text/javascript" defer="defer">
		updateCheckboxControl('releaseWaiverParentalConsent');
	</script>
</c:if>

</div></div></div></div>
<!-- BOX (END) -->

	<p>
		<fieldset class="buttons">
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<div class="ussa-button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
			<div class="ussa-button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
		</fieldset>
	</p>
</form:form>
</div>
<div class="clear"></div>

</body>
