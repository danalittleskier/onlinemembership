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

<div id="stg-pagetitle">U.S. Ski & Snowboard Assumption of Risk and Release of Liability</div>
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

I understand that skiing and snowboarding in their various forms, as well as preparation for participation in, coaching, volunteering, officiating and related activities in alpine, nordic, freestyle, adaptive, and snowboarding competitions and clinics (hereinafter collectively referred to as "Activities"), involve many <b>RISKS, DANGERS and HAZARDS</b>. These risks, dangers and hazards include, but are not limited to, changing weather and snow conditions, variations in steepness or terrain, natural and man-made obstacles and structures, equipment failure, collisions with objects or structures, being struck by skiers/riders or equipment, and exceeding one's own abilities. I further understand that ski and snowboard training and competition may be more hazardous than recreational skiing and snowboarding. I understand that <b>INJURIES OF ALL TYPES ARE A COMMON AND ORDINARY OCCURRENCE of the Activities</b>. I know that the risk of <b>SEVERE INJURY</b> and even <b>DEATH</b> exists in all training and competition locations and activities, including free skiing and riding. I also know that personal training, coaching, instruction, supervision and enforcement of rules by United States Ski & Snowboard, its subsidiaries, affiliates, officers, directors, volunteers, employees, coaches, contractors and representatives, local ski clubs, competition organizers and sponsors, and ski and snowboard facility operators (hereinafter the term "U.S. Ski & Snowboard" shall be used to refer to all such persons and entities collectively) do not and cannot guarantee my safety.<br/><br/>

With full knowledge and understanding of the <b>RISK OF SEVERE INJURY AND DEATH</b> involved in ski and snowboard training and competition, <b>I FREELY AND VOLUNTARILY ACCEPT AND FULLY ASSUME THE RISK THAT I MAY SUFFER TEMPORARY, PERMANENT OR EVEN FATAL INJURIES</b>, even if I follow the instructions or advice of U.S. Ski & Snowboard. 
<br><br> In consideration of U.S. Ski & Snowboard's acceptance of my membership application, and in spite of the risk of severe or permanent injury, or even death, the undersigned (hereinafter "Member") agrees to comply with and be bound by the following terms at all times, whether training or practicing for competition, or in competition.<br/><br/>

1.	Member hereby unconditionally <b>WAIVES AND RELEASES ANY AND ALL CLAIMS, AND AGREES TO HOLD HARMLESS, DEFEND AND INDEMNIFY U.S. Ski & Snowboard FROM ANY CLAIMS</b>, present or future, to Member or his/her property, or to any other person or property, for any loss, damage, expense, or injury (including <b>DEATH</b>), suffered by any person from or in connection with Member's participation in any Activities in which U.S. Ski & Snowboard is involved in any way, due to any cause whatsoever, <b>INCLUDING NEGLIGENCE</b> and/or breach of express or implied warranty on the part of U.S. Ski & Snowboard.<br/><br/>

2.	Member hereby <b>RELIEVES U.S. Ski & Snowboard OF ANY DUTY TO PROTECT MEMBER FROM HARM</b> in connection with any Activities in which U.S. Ski & Snowboard is involved in any way.<br/><br/>

3.	Member authorizes U.S. Ski & Snowboard to obtain medical care for, or transport him/her to a medical facility or hospital if, in the opinion of U.S. Ski & Snowboard, medical attention is required and Member is unable to make such decisions for himself/herself. Member agrees to pay all costs associated with such medical care and related transportation and shall <b>DEFEND, INDEMNIFY AND HOLD HARMLESS</b> U.S. Ski & Snowboard of and from the consequences of such decision and from any such costs incurred relating to the provision of medical care.  Member also authorizes disclosure of protected medical information necessary to provide, coordinate or manage member's healthcare consistent with the dictates of HIPAA and to the extent that such use or disclosure is required by law.<br/><br/>

4.	Member agrees never to utilize any run, course or facility for any training, practice or competition without first conducting his/her own thorough visual inspection of the run, course or facility.<br/><br/>

5.	This Agreement shall be construed in accordance with, and governed by the substantive laws of the State of Colorado, without reference to principles governing choice or conflicts of laws. In addition, Member agrees that all lawsuits for personal injury or related loss against U.S. Ski & Snowboard must be maintained in state courts sitting in Summit County, Utah or federal district courts sitting in the District of Utah, Central Division, and Member consents and agrees that jurisdiction and venue for such proceedings shall lie exclusively with such courts. In the event any portion of this release is found to be unenforceable, the remaining terms shall be fully enforceable.<br/><br/>
</p>


<form:hidden id="releaseWaiver" path="memberLegal.releaseWaiver"/>
<table width="100%">
	<tr>
		<td width="30px"><input id="releaseWaiverControl" type="checkbox" onclick="updateCheckboxHidden('releaseWaiver', this)"/></td>
		<td>* I HAVE CAREFULLY READ THE FOREGOING AND UNDERSTAND IT TO BE A LEGALLY BINDING RELEASE AND INDEMNITY AGREEMENT AND AGREE TO ITS TERMS</td>
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
		any other parent or guardian to maintain any claim or suit against U.S. Ski & Snowboard arising out of the Member's participation in any
		Activities involving U.S. Ski & Snowboard in any way. I further agree to hold harmless, defend, and indemnify U.S. Ski & Snowboard of and from any claims
		from third parties arising from the minor child Members' participation in any activities affiliated with U.S. Ski & Snowboard.
	</p>
	<form:hidden id="releaseWaiverParentalConsent" path="releaseWaiverParentalConsent"/>
	<table width="100%">
		<tr>
			<td width="30px"><input id="releaseWaiverParentalConsentControl" type="checkbox" onclick="updateCheckboxHidden('releaseWaiverParentalConsent', this)"/></td>
			<td><span style="float:left;">* I, &nbsp;</span><form:input path="memberLegal.guardianName" cssStyle="float:left;"/><span style="float:left;">CERTIFY, THAT I AM THE PARENT OR GUARDIAN OF THE MEMBER NAMED ABOVE.</span></td>
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
