<%@ include file="/includes/taglibs.jsp"%>
<body>

<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_4.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">United States Ski and Snowboard Assumption of Risk and Release of Liability</div>
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

I understand that skiing and snowboarding in their various forms, as well as preparation for participation in, coaching, volunteering, officiating and related activities in alpine, nordic, freestyle, disabled, and snowboarding competitions and clinics (hereinafter collectively referred to as "Activities"), involve many RISKS, DANGERS and HAZARDS. These risks, dangers and hazards include, but are not limited to, changing weather and snow conditions, variations in steepness or terrain, natural and man-made obstacles and structures, equipment failure, collisions with objects or structures, being struck by skiers/riders or equipment, and exceeding one's own abilities. I further understand that ski and snowboard training and competition may be more hazardous than recreational skiing and snowboarding. I understand that INJURIES OF ALL TYPES ARE A COMMON AND ORDINARY OCCURRENCE of the Activities. I know that the risk of SEVERE INJURY and even DEATH exists in all training and competition locations and activities, including free skiing and riding. I also know that personal training, coaching, instruction, supervision and enforcement of rules by the United States Ski & Snowboard Association, its subsidiaries, affiliates, officers, directors, volunteers, employees, coaches, contractors and representatives, local ski clubs, competition organizers and sponsors, and ski and snowboard facility operators (hereinafter the term "USSA" shall be used to refer to all such persons and entities collectively) do not and cannot guarantee my safety.<br/><br/>

With full knowledge and understanding of the RISK OF SEVERE INJURY AND DEATH involved in ski and snowboard training and competition, I FREELY AND VOLUNTARILY ACCEPT AND FULLY ASSUME THE RISK THAT I MAY SUFFER TEMPORARY, PERMANENT OR EVEN FATAL INJURIES, even if I follow the instructions or advice of USSA. In consideration of USSA's acceptance of my membership application, and in spite of the risk of severe or permanent injury, or even death, the undersigned (hereinafter "Member") agrees to comply with and be bound by the following terms at all times, whether training or practicing for competition, or in competition.<br/><br/>

1.	Member hereby unconditionally WAIVES AND RELEASES ANY AND ALL CLAIMS, AND AGREES TO HOLD HARMLESS, DEFEND AND INDEMNIFY USSA FROM ANY CLAIMS, present or future, to Member or his/her property, or to any other person or property, for any loss, damage, expense, or injury (including DEATH), suffered by any person from or in connection with Member's participation in any Activities in which USSA is involved in any way, due to any cause whatsoever, INCLUDING NEGLIGENCE and/or breach of express or implied warranty on the part of USSA.<br/><br/>

2.	Member hereby RELIEVES USSA OF ANY DUTY TO PROTECT MEMBER FROM HARM in connection with any Activities in which USSA is involved in any way.<br/><br/>

3.	Member authorizes USSA to obtain medical care for, or transport him/her to a medical facility or hospital if, in the opinion of USSA, medical attention is required and Member is unable to make such decisions for himself/herself. Member agrees to pay all costs associated with such medical care and related transportation and shall DEFEND, INDEMNIFY AND HOLD HARMLESS USSA of and from the consequences of such decision and from any such costs incurred relating to the provision of medical care.  Member also authorizes disclosure of protected medical information necessary to provide, coordinate or manage member's healthcare consistent with the dictates of HIPAA and to the extent that such use or disclosure is required by law.<br/><br/>

4.	Member agrees never to utilize any run, course or facility for any training, practice or competition without first conducting his/her own thorough visual inspection of the run, course or facility.<br/><br/>

5.	This Agreement shall be construed in accordance with, and governed by the substantive laws of the State of Colorado, without reference to principles governing choice or conflicts of laws. In addition, Member agrees that all lawsuits for personal injury or related loss against USSA must be maintained in state courts sitting in Summit County, Utah or federal district courts sitting in the District of Utah, Central Division, and Member consents and agrees that jurisdiction and venue for such proceedings shall lie exclusively with such courts. In the event any portion of this release is found to be unenforceable, the remaining terms shall be fully enforceable.<br/><br/>
</p>


<form:hidden id="releaseWaiver" path="memberLegal.releaseWaiver"/>
<table width="100%">
	<tr>
		<td width="30px"><input id="releaseWaiverControl" type="checkbox" onclick="updateCheckboxHidden('releaseWaiver', this)"/></td>
		<td>* I HAVE CAREFULLY READ THE FOREGOING AND UNDERSTAND IT TO BE A LEGALLY BINDING RELEASE AND INDEMNITY AGREEMENT</td>
	</tr>
</table>
<script type="text/javascript" defer="defer">
	updateCheckboxControl('releaseWaiver');
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
			<td><span style="float:left;">* I, &nbsp;</span><form:input path="memberLegal.guardianName" cssStyle="float:left;"/><span style="float:left;">, CERTIFY AS PARENT OR GUARDIAN OF THE MEMBER INDICATED to the waiver as noted above.</span></td>
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
