<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">IPC Waiver</div>

<!-- LEFT column -->
<div id="stg-onecol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
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

	<label for="fisWaiver1">I agree.</label>
	<form:radiobutton id="fisWaiver1" path="hasFisWaiverDisabled" value="${true}"/>
	<br/>
	<br/>
	<label for="fisWaiver2">No I don't agree. Please remove all IPC FIS items from my cart.</label>
	<form:radiobutton id="fisWaiver2" path="hasFisWaiverDisabled" value="${false}"/>

</p>
</div></div></div></div>
<!-- BOX (END) -->

	<p>
		<fieldset class="buttons">
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
			<input type="submit" class="btn-green" name="_eventId_back" value="Back">
		</fieldset>
	</p>


</form:form>
</div>

<div class="clear"></div>

</body>
