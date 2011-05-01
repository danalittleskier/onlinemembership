<%@ include file="/includes/taglibs.jsp"%>
<body>

<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_4.gif'/>" width="735" /></div>

<div id="stg-pagetitle">United States Ski and Snowboard Concussion Policy </div>
<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

Please read carefully before signing.
<br>
<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p>

Any USSA athlete under the age of 18 years suspected of having sustained a concussion or traumatic head injury must be removed immediately from participation in USSA sporting event (e.g. sanctioned training, practice, camps, competitions or tryouts), by the Technical Delegate or USSA member coach overseeing such sporting event.  The minor athlete will be prohibited from further participation until evaluated and cleared 
in writing to resume participation in USSA sporting events by a qualified health care provider trained in the evaluation and management of concussive head injuries.  The health care professional must certify to USSA in the clearance letter that he/she has successfully completed a continuing education course in the evaluation and management of concussive head injuries within three years of the day on which the written statement is made.
Upon removal of a minor athlete from participation for a suspected concussion or traumatic head injury, the USSA TD or member coach making the removal must inform USSA Competition Services.  Athletes who have subsequently been medically cleared to resume participation must provide such medical clearance (as described above) to USSA Competition Service in order to be permitted to participate in USSA sporting events.
<br><br>
About Concussion 
<br>
A concussion is a type of traumatic brain injury (TBI) caused by a bump, blow, or jolt to the head. Concussions can also occur from a fall or a blow to the body that causes the head and brain to move quickly back and forth. Doctors may describe a concussion as a "mild" brain injury because concussions are usually not life-threatening. Even so, their effects can be serious.
<br><br>
Risk of Continued Participation
<br>
A repeat concussion that occurs before the brain recovers from the first-usually within a short period of time (hours, days, or weeks)-can slow recovery or increase the likelihood of having long-term problems. In rare cases, repeat concussions can result in edema (brain swelling), permanent brain damage, and even death.
<br><br>
The USSA recommends that Members review the Center for Disease Control's resources on concussion awareness at the following link:
<br>
<a href="http://www.cdc.gov/concussion/HeadsUp/online_training.html" target="new">http://www.cdc.gov/concussion/HeadsUp/online_training.html</a>

</p>


<form:hidden id="concussionWaiver" path="memberLegal.concussionWaiver"/>
<table width="100%">
	<tr>
		<td width="30px"><input id="concussionWaiverControl" type="checkbox" onclick="updateCheckboxHidden('concussionWaiver', this)"/></td>
		<td>* I HAVE CAREFULLY READ THE FOREGOING AND UNDERSTAND IT TO BE A LEGALLY BINDING RELEASE AND INDEMNITY AGREEMENT</td>
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
		any other parent or guardian to maintain any claim or suit against USSA arising out of the Member's participation in any
		Activities involving USSA in any way. I further agree to hold harmless, defend, and indemnify USSA of and from any claims
		from third parties arising from the minor child Members' participation in any activities affiliated with USSA.
	</p>
	<form:hidden id="releaseWaiverParentalConsent" path="releaseWaiverParentalConsent"/>
	<table width="100%">
		<tr>
			<td width="30px"><input id="releaseWaiverParentalConsentControl" type="checkbox" onclick="updateCheckboxHidden('releaseWaiverParentalConsent', this)"/></td>
			<td><span style="float:left;">* I, &nbsp;</span><form:input path="memberLegal.concussionGuardianName" cssStyle="float:left;"/><span style="float:left;">, CERTIFY AS PARENT OR GUARDIAN OF THE MEMBER INDICATED to the waiver as noted above.</span></td>
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
			<div class="button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
			<div class="button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
		</fieldset>
	</p>
</form:form>
</div>
<div class="clear"></div>

</body>
