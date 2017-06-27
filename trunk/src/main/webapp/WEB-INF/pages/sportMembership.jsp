<%@ page import="org.ussa.model.Inventory" %>
<%@ include file="/includes/taglibs.jsp"%>

  <meta charset="utf-8">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });

  $(function() {

    $( "#from" ).datepicker({
      dateFormat: 'mm/dd/yy',
      defaultDate: 0,
      changeMonth: true,
      numberOfMonths: 2,
      showOtherMonths: true,
      selectOtherMonths: true,
      required: true,
      minDate: 0,
      showOn: "focus",
      onSelect: function(dateText, inst) {
          var nyd = new Date(dateText);
          nyd.setDate(nyd.getDate() + 6);
          $('#to').datepicker("option", {
              minDate: new Date(dateText),
              maxDate: nyd
          });
      },
      onClose: function() {
          $(this).focus();
      }
    });
    $( "#to" ).datepicker({
     dateFormat: 'mm/dd/yy',
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 2,
      showOtherMonths: true,
      selectOtherMonths: true,
      required: true,
      showOn: "focus",
      onClose: function() {
          $(this).focus();
      }
    });
  });
  
  function uploadDatePicker(){
	  var membOption = document.getElementById('membershipOption').value;
	  var shortTermShow = document.getElementById('shortTermPicker');
	  if(membOption.indexOf('ST') > -1){
		  shortTermShow.style.display = 'block';
	  }
	  else{
		  shortTermShow.style.display = 'none';
	  }
  }
  </script>


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
		<p><font color="blue">If renewing, please check the membership cart to the right as your membership may already be in your cart. 
		Be certain you are choosing the appropriate membership prior to completing your registration.</font></p>
		<p><a href="<c:url value="/sportInfo.html"/>" target="_BLANK" style="display:block; margin-top:3px;">View Membership Descriptions...</a></p>
		<label for="">* Sport:</label>
		<form:select path="sportId" onchange="document.getElementById('update').click();">
			<form:option value="">Select a Sport</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_ALP%>">Alpine</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_BRD%>">Snowboarding</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_FRE%>">Freestyle/Freeskiing</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_JNC%>">Jumping/Nordic Combined</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_XC%>">Cross Country</form:option>

		</form:select> 
		<br/>
		<c:if test="${fn:length(accountBean.memberships) != 0}">
			<label for="">* Membership:</label>
			<form:select path="membershipId" id="membershipOption" onchange="uploadDatePicker();">
				<form:option value=""></form:option>
				<form:options items="${accountBean.memberships}" itemValue="id" itemLabel="description"/>
			</form:select>
			<label></label>
			<br>
		
		<div id="shortTermPicker">
				<label for="">Pick the race date:  (only applicable for Short Term memberships)</label>
				<br><label for="from">From</label>
				<form:input id="from" path="membershipFrom"/>
				<label for="to">to</label>
				<form:input id="to" path="membershipTo"/>
		</div>
			<div style="clear:both;"></div>
			<div class="ussa-button-blue" style="margin-left:190px;margin-top:0px;margin-bottom:10px;"><span><input type="button" class="btn-submit" name="_eventId_add" value="Add Membership to Cart" onclick="submitFormWithInputButton(this);"></span></div>
		</c:if>
		<br>
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
			<legend>Magazine and Digital Content Options</legend>
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
		</fieldset>
	</c:if>
	<fieldset>
		<legend>Member Communication</legend>
		<table>
			<tr><!-- 
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
				-->
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
		<p>Your generous contribution will help support U.S. Ski Team, U.S. Freeski and U.S. Snowboard athletes in their quest for victory. Thank you!</p>
		<label for="">Sport:</label>
		<form:select path="contributionSportId">
			<form:option value="">Select a Sport</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_ALP%>">Alpine</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_BRD%>">Snowboarding</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_FRE%>">Freestyle/Freeskiing</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_JNC%>">Jumping/Nordic Combined</form:option>
			<form:option value="<%=Inventory.SPORT_CODE_XC%>">Cross Country</form:option>
		</form:select><br/>
		<label for="contributionAmount">Contribution Amount:</label>
		<form:input id="contributionAmount" path="contributionAmount" maxlength="10"/>
	</fieldset>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<!--<input type="submit" class="btn-green" name="_eventId_update" value="Update Cart">-->
		
		<div class="ussa-button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
		<div class="ussa-button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
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
	<p>COACHES, OFFICIALS, and VOLUNTEER MEMBERS</p>
		<ul>
		<li>All Coaches, Officials, Volunteer Members are required to undergo background screening.</li>
		<li>Coaches, Officials, and Volunteer Members memberships are not valid until positive background screening results are received by U.S. Ski & Snowboard.</li>
		<li>Your digital membership card will only display those memberships which do not require screening until positive results are received by U.S. Ski & Snowboard.</li>
		<li>Background screening registration must be complete within 10 days of membership registration.</li>
		<li>Failure to register within 10 days will result in inactivation of membership until U.S. Ski & Snowboard receives a green light determination on your screening.</li>
		<li>Please allow several weeks for screening process to be completed. Individual screening times will vary. Foreign screening may take a month or more.</li>
		<li>Coaches, officials, and volunteer members under age 18, click <a href="http://my.ussa.org/ussa/ussa-member-services-background-screening" target="_blank">here</a> for requirements in lieu of background screening</li>
		<li>A link to register for background screening will be provided on the final registration page <b>if screening is required.</b></li>
		</ul>
	<br><br>
	<ul>
	<p>COACHES </p>
		<ul>
		<li>The Fast Start coaching course has been added to your cart.</li>
		<li>All coach members who have not completed their first level of U.S. Ski & Snowboard certification must take the <a href="http://my.ussa.org/news/fast-start-coaching-course-faqs" target="_blank">U.S. Ski & Snowboard Fast Start on-line coaching course.</a></li>
		<li><strong>If you believe you are a Level 100 or higher certified coach, stop now and contact U.S. Ski & Snowboard Sport Education at <a href="mailto:education@ussa.org">education@ussa.org</a> or call 435.647.2050 before completing your U.S. Ski & Snowboard member registration.</strong></li>
		<li>Your coach membership is not valid until the Fast Start coaching course has been completed.</li>
		<li> A link to the Fast Start coaching course will be provided on the confirmation page.</li>
		<li>You must complete the Fast Start coaching course within 14 days. The course takes approximately one hour.</li>
		<li>Your digital membership card will only display those memberships which do not require screening or the Fast Start course until positive results are received by U.S. Ski & Snowboard.</li>
		<li> Upon completion, coaches will receive a $20 credit to be used in the same season toward a U.S. Ski & Snowboard coaching clinic or many of the educational DVDs and CDs offered by U.S. Ski & Snowboard Sport Education at the Education Shop</li>
		</ul>
		<br>
		<div class="ussa-button-blue"><span><a href="javascript:$('CoachInfo').colorbox.close()">Close Window</a></span></div>
		
	</div>
</div>
	<%session.removeAttribute("showBothWarnings");%>
</c:when>
<c:when test="${showBackgroundScreening}">
<a id="CoachInfo" href="javascript:void(0)"></a>
<div style="display:none;">
	<div id="CoachInfoText">
  <p>COACHES, OFFICIALS, and VOLUNTEER MEMBERS</p>
	<ul>
	<li>All Coaches, Officials, Volunteer Members are required to undergo background screening.</li>
	<li>Coaches, Officials, and Volunteer Members memberships are not valid until positive background screening results are received by U.S. Ski & Snowboard.</li>
	<li>Your digital membership card will only display those memberships which do not require screening until positive results are received by U.S. Ski & Snowboard.</li>
	<li>Background screening registration must be complete within 10 days of membership registration.</li>
	<li>Failure to register within 10 days will result in inactivation of membership until U.S. Ski & Snowboard receives a green light determination on your screening.</li>
	<li>Please allow several weeks for screening process to be completed. Individual screening times will vary. Foreign screening may take a month or more.</li>
	<li>Coaches, officials, and volunteer members under age 18, click <a href="http://my.ussa.org/ussa/ussa-member-services-background-screening" target="_blank">here</a> for requirements in lieu of background screening</li>
	<li>A link to register for background screening will be provided on the final registration page <b>if screening is required.</b></li>
	</ul>
		<br><div class="ussa-button-blue"><span><a href="javascript:$('CoachInfo').colorbox.close()">Close Window</a></span></div>
		
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
			<li>The Fast Start coaching course has been added to your cart.</li>
			<li>All coach members who have not completed their first level of U.S. Ski & Snowboard certification must take the <a href="http://my.ussa.org/news/fast-start-coaching-course-faqs" target="_blank">U.S. Ski & Snowboard Fast Start on-line coaching course.</a></li>
			<li><strong>If you believe you are a Level 100 or higher certified coach, stop now and contact U.S. Ski & Snowboard Sport Education at <a href="mailto:education@ussa.org">education@ussa.org</a> or call 435.647.2050 before completing your U.S. Ski & Snowboard member registration.</strong></li>
			<li>Your coach membership is not valid until the Fast Start coaching course has been completed.</li>
			<li> A link to the Fast Start coaching course will be provided on the confirmation page.</li>
			<li>You must complete the Fast Start coaching course within 14 days. The course takes approximately one hour.</li>
			<li>Your digital membership card will only display those memberships which do not require screening or the Fast Start course until positive results are received by U.S. Ski & Snowboard.</li>
			<li> Upon completion, coaches will receive a $20 credit to be used in the same season toward a U.S. Ski & Snowboard coaching clinic or many of the educational DVDs and CDs offered by U.S. Ski & Snowboard Sport Education at the Education Shop</li>
		</ul>
		<br><div class="ussa-button-blue"><span><a href="javascript:$('CoachInfo').colorbox.close()">Close Window</a></span></div>
		
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


