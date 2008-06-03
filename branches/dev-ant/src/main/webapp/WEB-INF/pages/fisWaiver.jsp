<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">FIS Waiver</div>

<!-- LEFT column -->
<div id="stg-onecol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p>

I, the undersigned, understanding that my acceptance of this declaration does not limit the obligation of the organizer to prepare and maintain competitions courses in accordance with FIS rules and FIS safety standards then in effect, make the following declaration
<br/>
1. FIS RULES; REGULATIONS AND PROCEDURES
<br/>
I understand and accept that my participation at any event, which is part of the FIS calendar is subject to my acceptance of all FIS rules applicable in connection with such event. I therefore agree to be submitted to such rules, regulations and procedures and to the jurisdiction of the bodies which are in charge of applying them, including but not limited to the exclusive jurisdiction of the Court of Arbitration for Sport CAS in Lausanne, Switzerland, where such is provided by FIS rules.
<br/>
2. ACKNOWLEDGEMENT OF RISKS
<br/>
I am fully aware and conscious of the potential risks involved in competition skiing activities and of the risk caused by speed and gravitational forces, be it during training or during the actual competition. I recognize that there are risks linked with the attempt to achieve competitive results which requires me to stretch my physical abilities. I also know and accept that the risks factors include environmental conditions, technical equipment, and atmospheric influences as well as natural or manmade obstacles. I am further aware that certain movements or actions cannot always be anticipated or controlled and therefore cannot be avoided or prevented through safety measures.
Consequently, I know and accept that when I engage in such competitive activities, my physical integrity and, in extreme cases, even my life may be at risk.
Furthermore, I know and accept that the above mentioned dangers linked with my participation may threaten third parties within the competition and training area.
I will conduct my own inspection of training and competition courses. I will immediately notify the jury of any safety concerns I may have. I understand that I am responsible for the choice of the appropriate equipment and of its condition, for the speed at which I race and for the selection of my line through the course.
<br/>
3. PERSONAL LIABILITY
<br/>
I understand that I may be found personally liable to third parties for damages arising from bodily injury or property damage they may suffer as a result of an occurrence linked with my participation in training or competition. I agree that it is not the responsibility of the organizer to inspect or supervise my equipment.
<br/>
4. RELEASE
<br/>
To the extent permitted by the applicable law, I release FIS, my National Association, and the organizers and their respective members, directors, officers, employees, volunteers, contractors and agents from any liability for any loss, injury, or damage suffered in relation to my participation in FIS sanctioned competition or training.
<br/>
5. DISPUTE RESOLUTION
<br/>
Without limitation to the jurisdiction of any body of competent jurisdiction in connection with the application of the FIS Rules, regulations and procedures to which I submit as a consequence of my participation at events that are published in the FIS calendar (see point 1 above), I agree that any dispute which is not to be adjudicated in application of procedures provided for by the FIS Rules, regulations and procedures, but which arises between myself and the FIS and/or the organizer of an event in the FIS calendar, including but not limited to claims for damages of either party against the other arising out of occurrences (acts or omissions) linked with my participation to such an event shall be governed by Swiss law and exclusively settled by arbitration before the Court of Arbitration for Sport (�CAS�) in Lausanne in accordance with the CAS rules then in effect.
This Declaration be governed and construed according to Swiss law and to the extent permitted by applicable law shall also be binding on my heirs, successors, beneficiaries, next of kin or assigns who might pursue any legal action in connection with the same.
I have read the above Athlete�s Declaration.
For Athletes of minority age: (according to national laws): This is to certify that, as parent/guardian of this participant, I do consent to his/her agreement to be bound by each of the terms and conditions identified above.

</p>
	<label for="fisWaiver1">I agree.</label>
	<form:radiobutton id="fisWaiver1" path="hasFisWaiver" value="${true}"/>
	<br/>
	<br/>
	<label for="fisWaiver2">No I don't agree. Please remove all FIS items from my cart.</label>
	<form:radiobutton id="fisWaiver2" path="hasFisWaiver" value="${false}"/>

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