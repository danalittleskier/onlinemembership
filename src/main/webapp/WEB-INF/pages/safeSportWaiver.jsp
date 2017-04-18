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
<div id="stg-pagetitle">United States Ski SafeSport </div>
<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

Please read carefully. You must agree to its terms to complete the registration process.
<br>
<!-- BOX (START) -->
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>


<p>
<br>

As of March 13, 2017, United States Ski and Snowboard has adopted the SafeSport Code ("The Code") promulgated by the U.S. Center for SafeSport.  
The Code replaces any previous policies adopted by USSA and can be located at the below weblink.  
All USSA members are required to review and accept the Code found here:
</p>
<br>
<a href="https://77media.blob.core.windows.net/uscss/1488581091937.2017-03-03---safesport-code---final.pdf" target="blank">https://77media.blob.core.windows.net/uscss/1488581091937.2017-03-03---safesport-code---final.pdf</a>
<br>
<p>
Finally, any USSA member who has reason to suspect that minors are being sexually or physically abused must report their suspicions to 
local child protection authorities/police and to the US Center for Safesport at www.safesport.org.  
Please err on the side of caution.  It is not your job to investigate your suspicions.  If in doubt, you must report.
 
</p>
<form:hidden id="safeSportWaiver" path="memberLegal.safeSportWaiver"/>
<table width="100%">
	<tr>
		<td width="30px"><input id="safeSportWaiverControl" type="checkbox" onclick="updateCheckboxHidden('safeSportWaiver', this)"/></td>
		<td>* I HAVE CAREFULLY READ THE FOREGOING AND UNDERSTAND AND AGREE TO ITS TERMS</td>
	</tr>
</table>
<script type="text/javascript" defer="defer">
	updateCheckboxControl('safeSportWaiver');
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
			<td><span style="float:left;">* I, &nbsp;</span><form:input path="memberLegal.safeSportGuardianName" cssStyle="float:left;"/><span style="float:left;"> CERTIFY, THAT I AM THE PARENT OR GUARDIAN OF THE MEMBER NAMED ABOVE.</span></td>
		</tr>
	</table>
	<script type="text/javascript" defer="defer">
		updateCheckboxControl('releaseWaiverParentalConsent');
	</script>
</c:if>
<br>

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

