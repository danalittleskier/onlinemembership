<%@ include file="/includes/taglibs.jsp"%>
<head>

	
	
	<!-- <script src="colorbox/jquery.colorbox.js"></script>-->
	
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
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code -->
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
<!-- END ProvideSupport.com Graphics Chat Button Code -->
	<%-- No longer need the Full Page View link since we no longer run the app in a popup window.
	<c:choose>
	<c:when test="${!empty accountBean.member.id }"> 
			<a href="https://login.dev.ussa.org/membership-web/registration.html?id=<c:out value="${accountBean.member.id}" />" target="new">Full Page View</a></span>			
	</c:when>
	<c:otherwise>
		<a href="https://login.dev.ussa.org/membership-web/registration.html?add=new" target="new">Full Page View</a></span>			
	</c:otherwise>
	</c:choose>
	--%>
</div>
<!-- End: Live Chat Support -->

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
			<%--<div class="button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>--%>
			
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
<li style="padding-bottom:4px;">You are registering for the 2012-13 competition season.</li>
<li style="padding-bottom:4px;"><strong>USSA and FIS registrations are non-refundable or transferrable.</strong></li>
<li style="padding-bottom:4px;">Memberships expire annually on June 30 </li>
<li style="padding-bottom:4px;">If registering a minor, parent or legal guardian must complete registration using minor's name and date of birth.</li>
<li style="padding-bottom:4px;"> Registration must be submitted by an authorized card-holder only.</li>
<li style="padding-bottom:4px;">If you are not a U.S. citizen and wish to register or renew your membership, you must do so by mail or fax.</li>
<li>Code of Conduct:<br>I understand that by virtue of my membership in USSA, I must comply with USSA's Code of Conduct. I also understand that I may be required to participate in competition drug testing. By executing this form, I agree to abide by and/or participate in such programs. I understand that failure to participate in competition drug testing will result in a sanction.<br><br></li>
</ul><br>
		<div class="ussa-button-blue"><span><a href="javascript:$('membership-application-hidden-link').colorbox.close()">Close Window</a></span></div>
		</div>
	</div>
<!--End: Code for pop-up text -->
</body>
