<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

	<%@ include file="/includes/messages.jsp" %>
	<div id="hide" style="display:none">
		<input type="submit" class="button" id="update" name="_eventId_change" value="Update">
	</div>

	<fieldset>
		<legend>Sport & Membership Type</legend>
		<label for="">* Sport:</label>
		<form:select path="sportId" onchange="document.getElementById('update').click();">
			<form:option value="">Select a Sport</form:option>
			<form:option value="ALP">Alpine</form:option>
			<form:option value="DAL">Disabled Alpine</form:option>
			<form:option value="BRD">Snowboarding</form:option>
			<form:option value="FRE">Freestyle</form:option>
			<form:option value="JNC">Jumping/Nordic Combined</form:option>
			<form:option value="XC">Cross Country</form:option>
			<form:option value="DXC">Disabled Cross Country</form:option>
		</form:select>
		<br/>
		<c:if test="${fn:length(accountBean.memberships) != 0}">
			<label for="">* Membership:</label>
			<form:select path="membershipId">
				<form:option value=""></form:option>
				<form:options items="${accountBean.memberships}" itemValue="id" itemLabel="description"/>
			</form:select>
			<input type="submit" class="btn-green-sm" name="_eventId_add" value="Add">
		</c:if>
		<br/>
	</fieldset>
	<fieldset>
		<legend>Communication Options</legend>
		<table style="margin-left:195px;">
			<tr>
				<td valign="top" class="checkbox">
					<form:hidden id="receiveEmail" path="member.receiveEmail"/>
					<input id="receiveEmailControl" type="checkbox" onclick="updateCheckboxHidden('receiveEmail', this)"/>
					<script type="text/javascript" defer="defer">
						updateCheckboxControl('receiveEmail');
					</script>
				</td>
				<td valign="top" width="100%">
					<label for="receiveEmailControl" class="checkbox" style="text-align:left; width:auto;">
					Subscribe to the email list of your division and/or state organization for important member communications.
					</label>
				</td>
			</tr>
			<tr>
				<td valign="top" class="checkbox">
					<form:hidden id="privateAddress" path="member.privateAddress"/>
					<input id="privateAddressControl" type="checkbox" onclick="updateCheckboxHidden('privateAddress', this)"/>
					<script type="text/javascript" defer="defer">
						updateCheckboxControl('privateAddress');
					</script>
				</td>
				<td valign="top" width="100%">
					<label for="privateAddressControl" class="checkbox" style="text-align:left; width:auto;">
					Request Mail Privacy Status. By doing so you will not receive special offers from our carefully screened list of vendors.
					</label>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>Contribution</legend>
		<p><strong>This section is optional.</strong> Your contribution will go to the sport of your primary membership. Thank you!</p>
		<label for="contributionAmount">Contribution Amount:</label>
		<form:input id="contributionAmount" path="contributionAmount"/>
	</fieldset>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
		<input type="submit" class="btn-green" name="_eventId_back" value="Back">

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

</body>


