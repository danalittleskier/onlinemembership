<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_3.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Medical Insurance Waiver</div>

<!-- LEFT column -->
<div id="stg-onecol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<p>
This is a waiver of certain legal rights.  Please read carefully before signing.
</p>

<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p>

This Agreement is made and entered into on the date set forth below by and between the undersigned member (hereinafter "Member") for and in favor or the United States Ski and Snowboard Association, its officers, directors, employees, coaches, trainers, contractors, volunteers, members or other representatives (hereinafter collectively "USSA").<br/><br/>

WHEREAS, Member desires to participate in USSA training, competition or activities related to USSA sanctioned, sponsored or conducted competitions; and WHEREAS, all members of USSA are required independently to obtain and maintain throughout the course of their membership in USSA primary medical and accident insurance prior to participating in any training, competition or activities related to USSA sanctioned, sponsored or conducted competitions; and WHEREAS, notwithstanding the foregoing policy pertaining to medical and accident insurance, Member has represented to USSA the existence of special circumstances under which Member seeks to be excused from the requirement to obtain such medical and accident insurance; and NOW, THEREFORE, in consideration of USSA's granting of an exception from its policy concerning independent medical and accident insurance and allowing Member to participate in USSA training, competition or activities related to USSA sanction, sponsored or conducted competitions, Member hereby warrants, represents and agrees as follows:<br/><br/>

1.  Member understands and agrees that by participating in any type or form of USSA training, competition or related activities, Member may be placing his life and health at serious risk.  Member understands and agrees that the risks to Member's life and health as a result of participating in USSA training, competition or related activities may exist in all places, at all times, and in all activities.  Member also acknowledges and agrees that safety equipment, proficiency checks, supervision, enforcement of rules, and close and careful adherence to instructions from coaches, officials and other USSA representatives will not reduce or eliminate the risks to Member's health and safety.  MEMBER FREELY ACCEPTS AND FULLY ASSUMES THE RISK THAT HE/SHE CAN GET HURT AND/OR CAN DIE, even if Member follows the instructions or advice of USSA.<br/><br/>

2.  MEMBER EXPRESSLY RELIEVES USSA FROM ANY DUTY TO PROTECT HIM/HER FROM HARM OF ANY KIND, and agrees that even if USSA chooses to implement safety procedures, such actions shall not alter the fact that USSA has no duty to protect Member.<br/><br/>

3.  MEMBER HEREBY WAIVES AND RELEASES ANY AND ALL CLAIMS that Member or his/her heirs have or may have in the future against USSA for any loss, damage, expense, injury or death, suffered from or in connection with Member's participation in any type of USSA training, competition or related activity or program, due to any cause whatsoever, INCLUDING NEGLIGENCE ON THE PART OF USSA.<br/><br/>

4.  Member understands that portions of this Agreement may be unenforceable under the laws of certain states, and it is Member's intention that this Agreement be fully enforced. Member hereby agrees that ANY DISPUTE ARISING UNDER THIS AGREEMENT OR IN CONNECTION WITH MEMBER'S PARTICIPATION IN ANY USSA ACTIVITY MAY NOT BE PURSUED IN ANY COURT OTHER THAN THE STATE AND FEDERAL COURTS SITUATED WITHIN THE STATE OF UTAH, and that COLORADO LAW SHALL GOVERN EXCLUSIVELY IN ANY SUCH DISPUTE.<br/><br/>

5.  In the event that any provision herein, or any other contract between Member and USSA, which was intended to exculpate USSA from any liability is found by any court to be unenforceable for any reason, Member hereby agrees to DEFEND, INDEMNIFY, AND HOLD HARMLESS USSA from any and all liability arising from participation in any USSA activity.<br/><br/>

By his/her signature below, MEMBER CERTIFIES THAT HE/SHE HAS READ AND UNDERSTOOD THIS AGREEMENT, and agrees in full with its terms, intend that it be binding on Member, his/her heirs, executors, administrators and assigns, and that it remain in full force and effect for as long as Member participates in USSA training, competition and related programs and activities without independent medical and accident insurance.<br/><br/>

THIS DOCUMENT DEPRIVES YOU OF ANY LEGAL RIGHT TO SUE USSA, EVEN FOR ITS OWN NEGLIGENCE.  DO NOT AGREE TO THIS DOCUMENT UNLESS YOU HAVE READ IT IN ITS ENTIRETY.  SEEK THE ADVICE OF LEGAL COUNSEL IF YOU ARE UNSURE OF ITS EFFECT.<br/><br/>

</p>

<form:hidden id="insuranceWaiver" path="memberLegal.insuranceWaiver"/>
<script type="text/javascript" defer="defer">
	updateCheckboxControl('insuranceWaiver');
</script>
<table width="100%">
	<tr>
		<td width="30px"><input id="insuranceWaiverControl" type="checkbox" onclick="updateCheckboxHidden('insuranceWaiver', this)"/></td>
		<td>* I HAVE CAREFULLY READ THE FOREGOING AND UNDERSTAND IT TO BE A LEGALLY BINDING RELEASE AND INDEMNITY AGREEMENT</td>
	</tr>
</table>

<c:if test="${accountBean.parentInfoRequired}">
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
	<form:hidden id="medicalWaiverParentalConsent" path="medicalWaiverParentalConsent"/>
	<table width="100%">
		<tr>
			<td width="30px"><input id="medicalWaiverParentalConsentControl" type="checkbox" onclick="updateCheckboxHidden('medicalWaiverParentalConsent', this)"/></td>
			<td>* CERTIFICATION OF PARENT OR GUARDIAN REQUIRED HERE FOR MEMBERS UNDER THE AGE OF 18</td>
		</tr>
	</table>
	<script type="text/javascript" defer="defer">
		updateCheckboxControl('medicalWaiverParentalConsent');
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
