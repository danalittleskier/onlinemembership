<%@ include file="/includes/taglibs.jsp"%>
<head>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
	<link media="screen" rel="stylesheet" href="colorbox/colorbox.css" />
	<script src="colorbox/jquery.colorbox.js"></script>
<% 	if(session.getAttribute("FirstTime") == null){ %>
	<script>
			$(document).ready(function(){
				$(".membership-application-hidden-link").colorbox({width:"75%", height:"75%", scrolling:true, inline:true, open:true, overlayClose:false, escKey:false, href:"#membership-application-important-text"});
			});
	</script>
<%} %>	
</head>
<body>
<%
if(session.getAttribute("FirstTime") == null){
   String yes = "yes";
   session.setAttribute( "FirstTime", yes  );
}
%>

<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="735" /></div>


<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>
<!-- LEFT column -->
<div id="stg-twocol-primary">

	<%@ include file="/includes/messages.jsp"%>

	<form:form commandName="accountBean" name="accountBean">

		<%@ include file="fragments/contactInfo.jspf"%>

		<fieldset id="buttonFieldSet" class="buttons">
			<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<!--<div class="button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>-->
			<a class="button blue" href="/AccountsProfile/profile/profileManagement.htm"><span>Return to USSA Profile Manager</span></a>&nbsp;&nbsp;
			<div class="button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
		</fieldset>

	</form:form>
</div>

	<%@ include file="fragments/contactInfoInstructions.jspf"%>

<div class="clear"></div>
<!--Start: Code for pop-up text -->
<a class="membership-application-hidden-link" href="#"></a>
<div style='display:none'>
		<div id="membership-application-important-text" style="padding:10px; background:#fff;">
		<div style="font-size:18px;font-weight:bold;text-align:center;">PLEASE READ CAREFULLY</div>
       <ul>
<li style="padding-bottom:4px;">You are registering for the 2011-12 competition season.</li>
<li style="padding-bottom:4px;"><strong>USSA and FIS registrations are non-refundable or transferrable.</strong></li>
<li style="padding-bottom:4px;">Memberships expire annually on June 30 </li>
<li style="padding-bottom:4px;">If registering a minor, parent or legal guardian must complete registration using minor's name and date of birth.</li>
<li style="padding-bottom:4px;"> Registration must be submitted by an authorized card-holder only.</li>
<li style="padding-bottom:4px;">If you are not a U.S. citizen and wish to register or renew your membership, you must do so by mail or fax.</li>
<li>Code of Conduct:<br>I understand that by virtue of my membership in USSA, I must comply with USSA's Code of Conduct. I also understand that I may be required to participate in competition drug testing. By executing this form, I agree to abide by and/or participate in such programs. I understand that failure to participate in competition drug testing will result in a sanction.</li>
</ul><a href="javascript:$('membership-application-hidden-link').colorbox.close()" class="button blue" ><span>Close Window</span></a>
		</div>
	</div>
<!--End: Code for pop-up text -->
</body>
