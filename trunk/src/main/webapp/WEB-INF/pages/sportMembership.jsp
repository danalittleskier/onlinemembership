<%@ page import="org.ussa.model.Inventory" %>
<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code -->
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
<!-- END ProvideSupport.com Graphics Chat Button Code -->
</div>
<!-- End: Live Chat Support -->

<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em><br>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

	<%@ include file="/includes/messages.jsp" %>

	<%-- Yes, this messages thing is a bit of a hack... need to learn more about Spring MVC messages. Hopefully there is a better way to do this. --%>
	<c:if test="${not empty messages}">
		<div class="stg-success-tl">
			<p>Just so you know...</p>
		</div>
		<div class="stg-success-tr"></div>
		<div class="stg-success-content">
			<ul>
			<c:forEach var="messageBean" items="${messages}">
				<li>
					<fmt:message key="${messageBean.resourceKey}">
						<c:forEach var="parameter" items="${messageBean.params}">
							<fmt:param value="${parameter}"/>
						</c:forEach>
					</fmt:message>
				</li>
			</c:forEach>
			</ul>
		</div>
		<%session.removeAttribute("messages");%>
	</c:if>

	<spring:bind path="accountBean.messageBean.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="stg-error-tl">
				<p>Note</p>
			</div>
			<div class="stg-success-tr"></div>
			<div class="stg-success-content">
				<ul>
					<c:forEach var="errorMsg" items="${status.errorMessages}">
						<li>
							<c:out value="${errorMsg}" escapeXml="false" />
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</spring:bind>

	<div id="hide" style="display:none">
		<input type="button" class="button" id="update" name="_eventId_change" value="Update" onclick="submitFormWithInputButton(this);">
	</div>

	<fieldset>
		<legend>Sport & Membership Type</legend>
		<p>If renewing, please check the membership cart to the right as your membership may already be in your cart. 
		Be certain you are choosing the appropriate membership prior to completing your registration.</p>
		<p><a href="<c:url value="/sportInfo.html"/>" target="_BLANK" style="display:block; margin-top:3px;">View Membership Descriptions...</a></p>
		<label for="">* Sport:</label>
		<form:select path="sportId" onchange="document.getElementById('update').click();">
			<form:option value="">Select a Sport</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_ALP%>">Alpine</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_DAL%>">Adaptive Alpine</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_BRD%>">Snowboarding</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_FRE%>">Freestyle/Freesking</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_JNC%>">Jumping/Nordic Combined</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_XC%>">Cross Country</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_DXC%>">Adaptive Cross Country</form:option>
		</form:select> 
		<br/>
		<c:if test="${fn:length(accountBean.memberships) != 0}">
			<label for="">* Membership:</label>
			<form:select path="membershipId">
				<form:option value=""></form:option>
				<form:options items="${accountBean.memberships}" itemValue="id" itemLabel="description"/>
			</form:select>
			<label></label>
			<div class="button blue"><span><input type="button" class="btn-submit" name="_eventId_add" value="Add Membership to Cart" onclick="submitFormWithInputButton(this);"></span></div>
		</c:if>
		<label style="width:300px;">Note: Members may choose more than one sport.</label>
	</fieldset>
	<c:if test="${not empty accountBean.fisItems}">
		<fieldset>
			<legend>FIS Registrations</legend>
			<table style="margin-left:195px;">
				<c:forEach var="fisItem" items="${accountBean.fisItems}" varStatus="varStatus">
					<tr>
						<td valign="top" class="checkbox">
							<form:checkbox id="fis${varStatus.index}" path="fisOptions" value="${fisItem.id}"/>
						</td>
						<td valign="top" width="100%">
							<label for="fis${varStatus.index}" class="checkbox" style="text-align:left; width:auto;">
								<c:out value="${fisItem.renewDescription}"/>
							</label>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br/>
			
		</fieldset>
	</c:if>
	<c:if test="${not empty accountBean.magazineItems}">
		<fieldset>
			<legend>Magazine Options</legend>
			<table style="margin-left:195px;">
					
				<c:forEach var="magazineItem" items="${accountBean.magazineItems}" varStatus="varStatus">
					<tr>
						<td valign="top" class="checkbox">
							<form:radiobutton id="mag${varStatus.index}" path="magazineOption" value="${magazineItem.id}"/>
						</td>
						<script type="text/javascript" defer="defer">
							if('MRCE' == document.getElementById('mag0').value)
							{
								document.getElementById('mag0').checked = "checked";
							}
						</script>
						<td valign="top" width="100%">
							<label for="mag${varStatus.index}" class="checkbox" style="text-align:left; width:auto;">
								<c:out value="${magazineItem.renewDescription}"/>
							</label>
						</td>
					</tr>
				</c:forEach>
				  <tr>
						<td valign="top" class="checkbox">
							<form:radiobutton id="mag${varStatus.index}" path="magazineOption" value=""/>
						</td>
						<td valign="top" width="100%">
							<label for="mag${varStatus.index}" class="checkbox" style="text-align:left; width:auto;">
								None
							</label>
						</td>
					</tr>
					
			</table>
			*By opting in to Ski Racing, you agree to receive digital issues by email.
		</fieldset>
	</c:if>
	<fieldset>
		<legend>Member Communication</legend>
		<table>
			<tr>
				<td class="checkbox">
					<form:hidden id="receiveEmail" path="member.receiveDivisionEmail"/>
					<label for="receiveEmailControl" class="checkbox" style="text-align:left; width:auto;">
						<input id="receiveEmailControl" type="checkbox" onclick="updateCheckboxHidden('receiveEmail', this)"/>
						<script type="text/javascript" defer="defer">
							updateCheckboxControl('receiveEmail');
						</script>
						Subscribe to your division or state email list for important communications such as schedule and event information.<br>
						<span style="padding-left:15px; font-size:11px;">(Only applies for divisions and states participating in USSA's dues management program)</span>
					</label>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td class="checkbox">
					<form:hidden id="privateAddress" path="member.privateAddress"/>
					<label for="privateAddressControl" class="checkbox" style="text-align:left; width:auto; font-size:9px;">
						<input id="privateAddressControl" type="checkbox" onclick="updateCheckboxHidden('privateAddress', this)"/>
						<script type="text/javascript" defer="defer">
							updateCheckboxControl('privateAddress');
						</script>
						Request Mail Privacy Status. By doing so you will not receive special offers from our carefully screened list of vendors.
					</label>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>Contribution <i>(optional)</i></legend>
		<p>Your generous contribution will help support U.S. Ski Team, U.S. Freeride and U.S. Snowboarding athletes in their quest for victory. Thank you!</p>
		<label for="">Sport:</label>
		<form:select path="contributionSportId">
			<form:option value="">Select a Sport</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_ALP%>">Alpine</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_DAL%>">Adaptive Alpine</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_BRD%>">Snowboarding</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_FRE%>">Freestyle/Freeskiing</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_JNC%>">Jumping/Nordic Combined</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_XC%>">Cross Country</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_DXC%>">Disabled Cross Country</form:option>
		</form:select><br/>
		<label for="contributionAmount">Contribution Amount:</label>
		<form:input id="contributionAmount" path="contributionAmount" maxlength="10"/>
	</fieldset>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<!--<input type="submit" class="btn-green" name="_eventId_update" value="Update Cart">-->
		<div class="button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
		<div class="button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
	</fieldset>
</form:form>
</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">
	<!-- BOX (START) -->
	<div class="stg-bl">
		<div class="stg-br">
			<div class="stg-tl">
				<div class="stg-tr">
					<div></div>
					<%@ include file="/includes/shoppingCart.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>
<c:choose>
<c:when test="${showBothWarnings}">
<a id="CoachInfo" href="javascript:void(0)"></a>
<div style="display:none;">
	<div id="CoachInfoText">
	<p>COACHES and OFFICIALS </p>
		<ul>
		<li>All Coaches and Officials are required to undergo background screening.</li>
		<li>Coach and official memberships are not valid until positive background screening results are received by USSA.</li>
		<li>Your digital membership card will only display those memberships which do not require screening until positive results are received by USSA.</li>
		<li>Background screening registration must be complete within 10 days of membership registration.</li>
		<li>Failure to register within 10 days will result in inactivation of membership until the USSA receives a green light determination on your screening.</li>
		<li>Please allow several weeks for screening process to be completed. Individual screening times will vary. Foreign screening may take a month or more.</li>
		<li>Coaches and officials under age 18, click <a href="http://my.ussa.org/ussa/ussa-member-services-background-screening" target="_blank">here</a> for requirements in lieu of background screening</li>
		<li>A link to register for background screening will be provided on the final registration page <b>if screening is required.</b></li>
		</ul>
	<br><br>
	<ul>
	<p>COACHES </p>
		<ul>
		<li>The mandatory $10 Fast Start Coaching Course was added to your cart.</li>
		<li>All Coaches without certifications are required to take the safe sport course.</li>
		<li>Coach memberships are not valid until required the safe sport course is completed.</li>
		<li>A link to the Fast Start Coaching course will be provided on the confirmation page. </li>
		<li>Your digital membership card will only display those memberships which do not require screening until positive results are received by USSA.</li>
		
		</ul>
		<br><a href="javascript:$('CoachInfo').colorbox.close()" class="button blue" ><span>Close Window</span></a>
		
	</div>
</div>
	<%session.removeAttribute("showBothWarnings");%>
</c:when>
<c:when test="${showBackgroundScreening}">
<a id="CoachInfo" href="javascript:void(0)"></a>
<div style="display:none;">
	<div id="CoachInfoText">
  <p>COACHES and OFFICIALS </p>
	<ul>
	<li>All Coaches and Officials are required to undergo background screening.</li>
	<li>Coach and official memberships are not valid until positive background screening results are received by USSA.</li>
	<li>Your digital membership card will only display those memberships which do not require screening until positive results are received by USSA.</li>
	<li>Background screening registration must be complete within 10 days of membership registration.</li>
	<li>Failure to register within 10 days will result in inactivation of membership until the USSA receives a green light determination on your screening.</li>
	<li>Please allow several weeks for screening process to be completed. Individual screening times will vary. Foreign screening may take a month or more.</li>
	<li>Coaches and officials under age 18, click <a href="http://my.ussa.org/ussa/ussa-member-services-background-screening" target="_blank">here</a> for requirements in lieu of background screening</li>
	<li>A link to register for background screening will be provided on the final registration page <b>if screening is required.</b></li>
	</ul>
		<br><a href="javascript:$('CoachInfo').colorbox.close()" class="button blue" ><span>Close Window</span></a>
		
	</div>
</div>
	<%session.removeAttribute("showBackgroundScreening");%>
</c:when>
<c:when test="${showFastStartCourse}">
<a id="CoachInfo" href="javascript:void(0)"></a>
<div style="display:none;">
	<div id="CoachInfoText">
	<p>COACHES </p>
		<ul>
		<li>The mandatory $10 Fast Start Coaching Course was added to your cart.</li>
		<li>All Coaches without certifications are required to take the safe sport course.</li>
		<li>Coach memberships are not valid until required the safe sport course is completed.</li>
		<li>A link to the Fast Start Coaching course will be provided on the confirmation page. </li>
		<li>Your digital membership card will only display those memberships which do not require screening until positive results are received by USSA.</li>
		
		</ul>
		<br><a href="javascript:$('CoachInfo').colorbox.close()" class="button blue" ><span>Close Window</span></a>
		
	</div>
</div>
	<%session.removeAttribute("showFastStartCourse");%>
</c:when>
</c:choose>
<!-- 
<c:if test="${showBackgroundScreening}">
	<script type="text/javascript" defer="defer">
		GB_showCenter('USSA Background Screening Information', '<c:url value="/backgroundScreeningPopup.html"/>', 515, 625);
	</script>
	<%session.removeAttribute("showBackgroundScreening");%>
</c:if>

<c:if test="${showFastStartCourse}">
	<script type="text/javascript" defer="defer">
		GB_showCenter('USSA Safe Sport Information', '<c:url value="/fastStartCoursePopup.html"/>', 515, 625);
	</script>
	<%session.removeAttribute("showFastStartCourse");%>
</c:if>
-->
</body>


