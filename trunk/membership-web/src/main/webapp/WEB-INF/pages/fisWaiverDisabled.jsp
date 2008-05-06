<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">IPC Registration Assumption of Risk and Release of Liability</div>

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
	Your USSA and FIS (or IPC) registrations must be received one week
	prior to list deadlines to guarantee inclusion on the FIS or IPC list.
	For specific list dates for your sport, please call Member Services at
	435.647.2666.
</p>

<p>

I make the following declaration:
<br/>
1. Identification of Risk
<br/>
I am fully aware and conscious of the dangers involved in the performance of all IPC – Winter Sports calendared sports and of the dangers caused by gravitational forces, be it during training runs or during the actual competition. I recognize that there is a risk in reaching excellent results which requires me to stretch my physical abilities to the absolute limit. I know andaccept, that by engaging in such competitive sports, life and physical safety could be en-dangered.Furthermore, I know and accept that the above mentioned dangers may threaten anyone within the com-petition and training area, and may include environmental conditions, technical equipment, and atmospheric influen-ces as well as natural or manmade obstacles. I am aware that certain movements or actions cannot always be anti-cipated or controlled and therefore cannot be avoided or prevented through safety measures.
<br/>
2. Acknowledgment of Risk
<br/>
I acknowledge that it is up to me personally to assess whether any competition or training course is too difficult for me. I agree that I will conduct my own inspection and I will immediately notify the jury of any obvious safety concerns I may have. By star-ting in a competition, or taking part in training I acknowledge acceptance of the suitability and condition of the courses. I also agree that I am responsible for the choice of the equipment Ihave used and for the selection of my line through the course and my ability to handle this line .
<br/>
3. Personal Liability
<br/>
I understand that I may be found personally liable to third parties for damages arising from bodily injury or property damage they have suffered as a result of my participation in training or competition. I agree that it is not the responsibility
of the organizer to inspect or supervise my equipment. I agree that I will make myself familiar with the aplicable competition regulations before taking part in such competition.
<br/>
4. Dispute Resolution
<br/>
I agree that prior to commencing a claim in any court of competent jurisdiction, I shall first submit my claim before an Arbitration Court which is constituted in accordance with the Statutes and Regulations of the Court of Arbitration for Sport (CAS). In case I am not in agreement with the decision of this Court, I am free to bring or re-institute any such claim before any Court of compe-tent jurisdiction.
This Athlete's Declaration is also binding on any relatives, personal representatives, heirs, successors, beneficiaries, next of kin or assigns who might pursue any legal action.
<br/>
<br/>

I have read the above Athlete's Declaration.
<br/>
<br/>

CAN/USA:
Notwithstanding anything contained herein to the contrary, I agree that for all activities organized by my National Federation or affiliated club, the National Federation may elect to enforce any waiver, release of liability, indem-nity or hold harmless agreement made with me that is more restrictive than this Athlete's Declaration and I agree to bound by that election.
<br/>
<br/>

For Athletes of minority age (according to national laws):
This is to certify that ,as parent/guardian of this participant, I do consent to his/her agreement to be bound by each of the terms and conditions identified above.
<br/>

</p>

	<fieldset style="border:none;">
		<div class="radios">
			<form:radiobutton id="fisWaiver1" path="memberLegal.ipcReleaseForm" value="Y"/>
			<label for="fisWaiver1" class="radio">I agree.</label>
			<form:radiobutton id="fisWaiver2" path="memberLegal.ipcReleaseForm" value="N"/>
			<label for="fisWaiver2" class="radio">No I don't agree. Please remove all IPC FIS items from my cart.</label>
		</div>
	</fieldset>

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
		<form:hidden id="fisWaiverDisabledParentalConsent" path="fisWaiverDisabledParentalConsent"/>
		<table width="100%">
			<tr>
				<td width="30px"><input id="fisWaiverDisabledParentalConsentControl" type="checkbox" onclick="updateCheckboxHidden('fisWaiverDisabledParentalConsent', this)"/></td>
				<td>* CERTIFICATION OF PARENT OR GUARDIAN REQUIRED HERE FOR MEMBERS UNDER THE AGE OF 18</td>
			</tr>
		</table>
		<script type="text/javascript" defer="defer">
			updateCheckboxControl('fisWaiverDisabledParentalConsent');
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
