<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>

<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>
<!-- LEFT column -->
<div id="stg-twocol-primary">

	<%@ include file="/includes/messages.jsp"%>

	<form:form commandName="accountBean" name="accountBean">

		<fieldset>
		<legend>Personal Information</legend>

		<c:if test="${!empty accountBean.member.id }">
			<label>USSA Member #</label>
			<span class="data-input"><c:out value="${accountBean.member.id}" /></span>
		</c:if>
			<label for="">* First Name</label>
			<form:input path="member.firstName" size="30" maxlength="30" /><br />
			<label for="">MI</label>
			<form:input path="member.middleName" size="30" maxlength="30" /><br />
			<label for="">* Last Name</label>
			<form:input path="member.lastName" size="30" maxlength="30"/><br />
			<label for="">Suffix</label>
			<form:input path="member.suffixName" size="30" maxlength="30"/><br />

			<c:choose>
				<c:when test="${!empty accountBean.member.id}">
					<label for="birthDate">Birth Date:</label>
					<span class="data-input"><c:out value="${accountBean.birthDate}"/></span>
				</c:when>
				<c:otherwise>
					<label for="birthDate">* Birth Date:</label>
					<form:input id="birthDate" path="birthDate" size="30" maxlength="30"/>
					<a title="Select Date" href="#" id="birthDateCalendar">
						<img class="stg-calendar-icon" width="23" height="24" border="0" name="calendar" src="<c:url value="/images/icon_calendar.gif"/>"/>
					</a>
					<script type="text/javascript" defer="defer">
						Calendar.setup(
							{
								inputField : "birthDate", // ID of the input field
								ifFormat : "%m/%d/%Y", // the date format
								button : "birthDateCalendar" // ID of the button
							}
						);
					</script>
				</c:otherwise>
			</c:choose>
			<br/>

			<c:if test="${empty accountBean.member.id}">
				<label for="gender">* Gender:</label>
				<div class="radios">
					<form:radiobutton id="gender1" path="member.gender" value="M"/>
					<label class="radio" for="gender1">Male</label>
					<form:radiobutton id="gender2" path="member.gender" value="F"/>
					<label class="radio" for="gender2">Female</label>
				</div><br/>
			</c:if>
		</fieldset>

		<c:if test="${accountBean.parentInfoRequired}">
			<%@ include file="/includes/parentInfoFields.jsp"%>
		</c:if>

		<fieldset>
			<legend>Address & Phone</legend>
			<label for="">Company</label>
			<form:input path="address.company" size="30" maxlength="30"/><br />
			<label for="">* Address 1</label>
			<form:input path="address.deliveryAddress" size="30" maxlength="40"/><br />
			<label for="">Address 2</label>
			<form:input path="address.secondaryAddress" size="30" maxlength="40"/><br />
			<label for="">* City</label>
			<form:input path="address.city" size="30" maxlength="30"/><br />
			<label for="">* State</label>

			<form:select path="address.stateCode">
				<form:option value=""></form:option>
				<form:options items="${accountBean.usStates}" itemValue="id" itemLabel="description"/>
			</form:select>
			<br/>

			<label for="">* Zip/Potal Code</label>
			<form:input path="address.postalCode" size="30" maxlength="30"/><br />
			<label for="">* Country</label>
			<form:input path="address.country" size="30" maxlength="30"/><br />
			<label for="">* Home/Primary Phone</label>
			<form:input path="address.phoneHome" size="30" maxlength="30"/><br />
			<label for="">Work Phone</label>
			<form:input path="address.phoneWork" size="30" maxlength="30"/><br />
			<label for="">Other Phone</label>
			<form:input path="address.phoneOther" size="30" maxlength="30"/><br />
			<label for="">Fax</label>
			<form:input path="address.phoneFax" size="30" maxlength="30"/><br />
			</fieldset>

		<fieldset class="buttons">
			<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
			<input type="submit" class="btn-green" name="_eventId_back" value="Cancel">
		</fieldset>

	</form:form>
</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">
	<!-- BOX (START) -->
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
		<p class="stg-omr-header">Online Membership Registration</p>
		<ul id="stg-omr">
			<li>Memberships, FIS registrations and IPC registrations are non-refundable</li>
			<li>Memberships expire annually on June 30</li>
			<li>Registration must be submitted only by those 18 &amp; older</li>
			<li>Code of Conduct:<br />
				I understand that by virtue of my membership in USSA, I must comply with USSA's
				Code of Conduct. I also understand that I may be required to participate in
				competition drug testing. By executing this form I agree to abide by and/or
				participate in such programs. I understand that failure to participate in competition
				drug testing will result in a sanction.
			</li>

			<li><strong>Beginning April 1 all registrations completed online
				will apply to the next competition season. If you wish to register
				for the current competition season from April 1 through June 30,
				you may do so by mail or fax. Please contact USSA Member Services
				for details at <a href="mailto:membership@ussa.org">membership@ussa.org</a>
				or by phone at 435.647.2666.</strong>
			</li>
			<li>
				In order to complete renewal or registration, your primary medical
				insurance information will be required or you will be required to
				execute the Primary Medical Exception Agrement provided. You wil
				also be required to execute a release of liability.
			</li>
			<li>
				A $25 late fee will be added to your total if renewing after Oct. 15
				for USSA membership categories other than youth competitors, first
				time members and officials. your division and/or state may assess late
				fees in addition to those assessed by USSA.
			</li>
			<li>If you are not a U.S.Citizen and wish to change your membership category you must renew by mail or fax.</li>
			<li>If you wish to renew an associate membership you must do so by mail or fax.</li>
		</ul>
	</div></div></div></div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>

</body>
