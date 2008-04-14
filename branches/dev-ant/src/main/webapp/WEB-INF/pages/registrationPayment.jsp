<%@ include file="/common/taglibs.jsp"%>
<head>
	<script type="text/javascript">
		function disableButtons(form)
		{
			var continueButton = document.getElementById("continueButton");
			continueButton.value = "Processing ...";
//			continueButton.disabled = true;
			var backButton = document.getElementById("backButton");
//			backButton.disabled = true;
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
<form:form commandName="accountBean" name="accountBean" onsubmit="disableButtons(this)">

<%@ include file="/includes/messages.jsp"%>

	<fieldset>
		<legend>Payment Information</legend>
		<label for="">USSA Registration Fees:</label>
		<span class="data-input"><strong><c:out value="${accountBean.cartBean.total}" /></strong></span>
		<label for="">* Credit Card Type:</label>
		<span class="multiselect-margin">
			<form:select path="paymentBean.paymentType">
				<form:option value=""></form:option>
				<form:option value="Visa">Visa</form:option>
				<form:option value="MasterCard">MasterCard</form:option>
			</form:select><br/>
		</span>
		<label for="">* Credit Card Number:</label>
		<form:input path="paymentBean.cardNumber"/><br/>
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
		<form:input path="paymentBean.securityCode"/><br/>
		</span>
	</fieldset>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input id="continueButton" type="submit" class="btn-green" name="_eventId_next" value="Continue">
		<input id="backButton" type="submit" class="btn-green" name="_eventId_back" value="Back">
	</fieldset>
</form:form>
</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">

</div>
<div class="clear"></div>

</body>
