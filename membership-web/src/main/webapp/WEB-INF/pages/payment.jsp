<%@ include file="/includes/taglibs.jsp"%>
<head>
	<script type="text/javascript">
		function disableButtonsAndSubmit()
		{
			var backButton = document.getElementById("backButton");
			backButton.disabled = true;

			var continueButton = document.getElementById("continueButton");
			continueButton.value = "Processing ...";
			continueButton.disabled = true;
			submitFormWithInputButton(continueButton);
		}
	</script>
</head>

<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_5.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Payment</div>
<p class="req-fields"><em>* Required Fields</em></p>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

	<fieldset>
		<legend>Payment Information</legend>
		<label for="">USSA Registration Fees:</label>
		<span class="data-input" style="float:none;"><strong><c:out value="${accountBean.cartBean.total}" /></strong></span>
		<label for="">* Credit Card Type:</label>
		<span class="multiselect-margin">
			<form:select path="paymentBean.paymentType">
				<form:option value=""></form:option>
				<form:option value="Visa">Visa</form:option>
				<form:option value="Mastercard">MasterCard</form:option>
			</form:select><br/>
		</span>
		<label for="">* Credit Card Number:</label>
		<form:input path="paymentBean.cardNumber" maxlength="16"/><br/>
		<label for="">* Expiration Date:</label>
		<span class="multiselect-margin">
			<form:select path="paymentBean.expireMonth">
				<form:option value=""></form:option>
				<form:option value="01">1</form:option>
				<form:option value="02">2</form:option>
				<form:option value="03">3</form:option>
				<form:option value="04">4</form:option>
				<form:option value="05">5</form:option>
				<form:option value="06">6</form:option>
				<form:option value="07">7</form:option>
				<form:option value="08">8</form:option>
				<form:option value="09">9</form:option>
				<form:option value="10">10</form:option>
				<form:option value="11">11</form:option>
				<form:option value="12">12</form:option>
			</form:select>
			<form:select path="paymentBean.expireYear">
				<form:option value=""></form:option>
				<form:options items="${accountBean.years}"/>
			</form:select><br/>
		</span>
		<span class="textfield-short">
		<label for="">* Security Code:</label>
		<form:input path="paymentBean.securityCode" maxlength="4"/><br/>
		</span>

		<br/><br/>
		<p style="clear: left;">Please note your billing address below if different from your mailing address:</p>
<%--
		<label for="">* First Name:</label>
		<form:input path="paymentBean.firstName" maxlength="50"/><br/>
		<label for="">* Last Name:</label>
		<form:input path="paymentBean.lastName" maxlength="50"/><br/>
--%>
		<label for="">Billing Address (enter only the NUMERIC portion):</label>
		<form:input path="paymentBean.address" maxlength="60"/><br/>
<%--
		<label for="">* City:</label>
		<form:input path="paymentBean.city" maxlength="40"/><br/>
--%>
		<label for="">Zip/Postal Code:</label>
		<form:input path="paymentBean.zip" maxlength="20"/><br/>
	</fieldset>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input id="backButton" type="button" class="btn-green" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);">
		<input id="continueButton" type="submit" class="btn-green" name="_eventId_next" value="Continue" onclick="disableButtonsAndSubmit();">
		<br><br><br>
	</fieldset>
</form:form>
</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">
<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
<p>DO NOT press Continue multiple times.<br>
<p>Doing so may result in your bank placing a hold on these funds.  Your bank may take a few 
		days to clear these holds on your account.<br>
		If you receive a decline message please call USSA Member Services for assistance at 435.647.2666
		</p>
		
</div></div></div></div>
</div>
<div class="clear"></div>

</body>
