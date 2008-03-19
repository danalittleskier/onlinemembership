<%@ include file="/common/taglibs.jsp"%>

<body>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="stg-content">
	<!-- Progress bar -->
	<div id="stg-progress"><img src="${ctx}/images/ussa/progress_1.gif" width="917" height="53" /></div>

	<div id="stg-pagetitle">Member Information</div>
	<p class="req-fields"><em>* Required Fields</em></p>
	<!-- LEFT column -->
	<div id="stg-twocol-primary">
		<form:form commandName="accountBean" name="accountBean">

		<spring:bind path="accountBean.*">
			<c:if test="${not empty status.errorMessages}">
				<c:set var="sectionHasFormErrors" scope="request" value="true"/>
				<c:forEach var="errorMsg" items="${status.errorMessages}">
					<c:out value="${errorMsg}" escapeXml="false" />
				</c:forEach>
			</c:if>
		</spring:bind>
			<fieldset>
			<legend>Personal Information</legend>


			<c:if test="${!empty accountBean.member.id }">
				<label for="">USSA Member #</label>
				<c:out value="${accountBean.member.id}" /><br />
			</c:if>
			<br />
				<label for="">First Name</label>
				<form:input path="member.firstName" size="30" maxlength="30" /><br />
				<label for="">MI</label>
				<input name="" type="text" class="text" /><br />
				<label for="">Last Name</label>
				<form:input path="member.lastName" size="30" maxlength="30"/><br />
				<label for="">Suffix</label>
				<input name="" type="text" class="text" /><br />
			</fieldset>
			<fieldset>
				<legend>Address & Phone</legend>
				<label for="">Company</label>
				<form:input path="address.company" size="30" maxlength="30"/><br />
				<label for="">Address 1</label>
				<form:input path="address.deliveryAddress" size="30" maxlength="30"/><br />
				<label for="">Address 2</label>
				<input name="" type="text" class="text" /><br />
				<label for="">City</label>
				<form:input path="address.city" size="30" maxlength="30"/><br />
				<label for="">State</label>

				<spring:bind path="accountBean.address.stateCode">
					<select name="address.stateCode">
						<option value=""></option>
						<c:forEach var="o" items="${accountBean.usStates}">
							<option value="<c:out value='${o.id}'/>" <c:if test="${o.id == status.value}">selected</c:if>>
								<c:out value='${o.id}'/>
							</option>
						</c:forEach>
					</select>
				</spring:bind>
				<br/>

				<label for="">Zip</label>
				<form:input path="address.postalCode" size="30" maxlength="30"/><br />
				<label for="">Country</label>
				<input name="" type="text" class="text" /><br />
				<label for="">Home Phone</label>
				<form:input path="address.phoneHome" size="30" maxlength="30"/><br />
				<label for="">Other Phone</label>
				<input name="" type="text" class="text" /><br />
				<label for="">Fax</label>
				<input name="" type="text" class="text" /><br />
				</fieldset>

				<c:if test="${empty accountBean.member.id }">
				<fieldset>
				<legend>Other Information</legend>
				<label for="">Gender</label>
				<form:input path="member.gender" size="30" maxlength="30"/><br />
				<label for="">Etnicity</label>
				<form:input path="member.etnicity" size="30" maxlength="30"/><br />
				</fieldset>
				</c:if>


			<c:if test="${accountBean.member.age < 18 }">
			<fieldset>
				<legend>Parent/Guardian #1</legend>
				<label for="">* First Name:</label>
				<input name="" type="text"  /><br />
				<label for="">* Last Name:</label>
				<input name="" type="text"  /><br />
				<label for="">* Relationship:</label>
				<div class="radios">
				<input name="relationship-1" id="relationship-1-1" type="radio" value="Mother" /> <label class="radio" for="relationship-1-1">Mother</label>
				<input name="relationship-1" id="relationship-1-2" type="radio" value="Father" /> <label class="radio" for="relationship-1-2">Father</label>
				<input name="relationship-1" id="relationship-1-3" type="radio" value="Guardian" /> <label class="radio" for="relationship-1-3">Guardian</label>
				</div><br />
				<label for="">* Email Address:</label>
				<input name="" type="text"  /><br />
			</fieldset>
			<fieldset>
				<legend>Parent/Guardian #2</legend>
				<label for="firstName">First Name:</label>
				<input name="firstName" id="firstName" type="text"  /><br />
				<label for="">Last Name:</label>
				<input name="" type="text"  /><br />
				Relationship:
				<div class="radios">
				<input name="relationship-2" id="relationship-2-1" type="radio" value="Mother" /> <label class="radio" for="relationship-2-1">Mother</label>
				<input name="relationship-2" id="relationship-2-2" type="radio" value="Father" /> <label class="radio" for="relationship-2-2">Father</label>
				<input name="relationship-2" id="relationship-2-3" type="radio" value="Guardian" /> <label class="radio" for="relationship-2-3">Guardian</label>
				</div><br />
				<label for="">Email Address:</label>
				<input name="" type="text"  /><br />
			</fieldset>
			</c:if>
			<fieldset class="buttons">
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
				<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
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
				I understand that by virtue of my membership in USSA, I must comply with USSA's Code of Conduct. I also understand that I may be required to participate in competition drug testing. By executing this form I agree to abide by and/or participate in such programs. I understand that failure to participate in competition drug testing will result in a sanction.</li>

              <li><strong>Beginning April 1 all registrations completed online
                will apply to the next competition season. If you wish to register
                for the current competition season from April 1 through June 30,
                you may do so by mail or fax. Please contact USSA Member Services
                for details at <a href="mailto:membership@ussa.org">membership@ussa.org</a>
                or by phone at 435.647.2666.</strong></li>
				<li>In order to complete renewal or registration, your primary medical insurance information will be required or you will be required to execute the Primary Medical Exception Agrement provided. You wil also be required to execute a release of liability.</li>
				<li>A $25 late fee will be added to your total if renewing after Oct. 15 for USSA membership categories other than youth competitors, first time members and officials. your division and/or state may assess late fees in addition to those assessed by USSA.</li>
				<li>If you are not a U.S.Citizen and wish to change your membership category you must renew by mail or fax.</li>
				<li>If you wish to renew an associate membership you must do so by mail or fax.</li>
			</ul>
		</div></div></div></div>
		<!-- BOX (END) -->
	</div>
	<div class="clear"></div>
</div>

</body>
