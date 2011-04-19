<%@ include file="/includes/taglibs.jsp"%>
<body>

<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_4.gif'/>" width="917" height="53" /></div>

<div style="float:right;margin-top:-20px;margin-left:0px;margin-right:5px;"><script>showChatButton('membership@workgroup.im.ussa.org');</script></div>

<div id="stg-pagetitle">United States Ski and Snowboard Concussion Waiver</div>
<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<p>
This is a waiver of certain legal rights.  Please read carefully before signing.
</p>

<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p>

Any USSA athlete suspected of having sustained a concussion or traumatic head injury will be removed immediately from participation in USSA <br>
sporting event (eg. sanctioned training, practice, camps, competitions or tryouts), by the Technical Delegate or USSA member coach overseeing <br>
such sporting event, and will be prohibited from further participation until the athlete is evaluated and cleared to resume participation in USSA <br>
sporting events by a qualified health care provider who is trained in the evaluation and management of a concussion, and who has successfully <br>
completed a continuing education course in the evaluation and management of a concussion within three years before the day on which the written <br>
statement is made, and who notifies USSA of such clearance and that he/she is qualified to authorize such clearance according this USSA's concussion policy.
</p>


<form:hidden id="concussionWaiver" path="memberLegal.concussionWaiver"/>
<table width="100%">
	<tr>
		<td width="30px"><input id="releaseWaiverControl" type="checkbox" onclick="updateCheckboxHidden('concussionWaiver', this)"/></td>
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
			<input type="button" class="btn-green" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);">
			<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
		</fieldset>
	</p>
</form:form>
</div>
<div class="clear"></div>

</body>
