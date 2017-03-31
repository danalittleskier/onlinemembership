<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code 
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
END ProvideSupport.com Graphics Chat Button Code -->

</div>
<!-- End: Live Chat Support -->

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

I understand that as an athlete licensed to compete in IPC Competitions, that I am participating in events which have ongoing international and historical significance, and as a condition of being licensed to compete and in consideration of the acceptance of my participation in IPC competitions I agree as follows; 
<br/>
1.	to be filmed, televised, photographed, identified and otherwise recorded during any IPC Competition, the IPC and anyone authorized by the IPC shall be entitled to keep these images and to use them for any purpose in relation to the promotion of the Paralympic Movement;
<br/>
2.	that all photographs and moving images taken by me at any IPC Competition, including those of athletes competing within any official venue during these competitions, shall be used solely for personal and non-commercial purposes, unless prior written consent is obtained from the IPC;
<br/>
3.	to comply with the IPC Handbook, the IPC Anti-Doping Code, the IPC Sports Technical Rules of the sport in which I am participating, and any other rules or regulations that apply to my participation at an IPC Competition (hereinafter referred to as 'the IPC Sport Rules').  These documents can be found on or linked through the IPC website or can be obtained upon written request to the IPC Headquarters and I understand that I am deemed to have read and understood them;
<br/>
4.	that my entries to IPC Competitions will be administered through my National Paralympic Committee (NPC) or IPC recognized National Sport Federation (if applicable);
<br/>
5.	to accept the criteria of eligibility, qualification and participation laid down by the IPC for the various IPC Competitions;
<br/>
6.	to abide by the principles of fair-play and non-violence and to behave myself accordingly during the IPC Competition;
<br/>
7.	not to take any substance or use methods prohibited by the IPC Anti-Doping Code;
<br/>
8.	not to participate or assist in any gambling or betting activities associated with any IPC event;
<br/>
9.	not to allow my image and/or name in relation to my sport performance(s) during the IPC Competition to be exploited by third parties for commercial purposes without prior approval by the IPC;
<br/>
10.	not to condition my participation in any IPC Competition on any financial return whatsoever;
<br/>
11.	to abide by the IPC rules and regulations for manufacturer trademarks on clothing and equipment for the IPC Competition; and
<br/>
12.	to abide by the classification rules and regulations of the IPC and the sport. 
<br/>
<u>ACCEPTANCE OF SPORTS TECHNICAL SUPREME AUTHORITY</u>
<br/>
I recognize and accept that the sports technical rules, being all rules which relate to competition, field-of-play and classification, for my sport during any IPC Competition are under the authority of the IPC and that any disputes which arise shall be resolved by the IPC whose decision on such matters is final and enforceable.
<br/>
I acknowledge and accept that decisions relating to the sports technical rules are not subject to further appeal beyond the decision made in the sport-specific appeal processes as set out in the respective sports technical rules or classification appeal processes as set out in the sport rules applicable to the respective IPC Competition for which I am competing.  I shall not institute any claim, arbitration or litigation, or seek any other form of relief in any other court or tribunal in relation to such decisions.
<br/>
<u>ACCEPTANCE OF BINDING ARBITRATION</u>
<br/>
I acknowledge and accept that any dispute outside the realm of the sports technical rules arising during the IPC Competitions shall be submitted exclusively to the Court of Arbitration for Sport (CAS). Any such dispute shall be determined in accordance with the CAS Code for Sports-Related Arbitration, save for competitions covered by the CAS Ad hoc arbitration rules where I agree that the Ad hoc rules shall govern the procedure for dispute resolution. The decisions of CAS are final, non-appealable and enforceable.
<br/>
<u>ACCEPTANCE OF RISK AND RELEASE OF CLAIMS</u>
<br/>
I acknowledge that I participate in the IPC Competitions at my own risk; I will take all reasonable measures to protect myself and other competitors, officials and spectators from suffering injury or other harm. I also acknowledge that I am responsible for all property I bring on to all IPC Competition sites and that the Local Organizing Committees and the IPC accept no responsibility for any loss or damage to such properties.
</br>

I release the LOCs, the IPC and the IPSF(s) governing my sport(s),and their respective executive members, directors, officers, employees, volunteers, contractors or agents, from any liability (to the extent permitted by law) for any loss, injury or damage suffered by me in relation to my participation in any IPC Competitions.<br/>
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
			<div class="ussa-button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
			<div class="ussa-button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
		</fieldset>
	</p>


</form:form>
</div>

<div class="clear"></div>

</body>
