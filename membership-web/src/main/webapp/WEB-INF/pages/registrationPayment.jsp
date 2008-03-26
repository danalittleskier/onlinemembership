<body>

<!-------------------------------------------- Content ------------------------------------------------->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->


<div id="stg-progress"><img src="${ctx}/images/progress_5.gif" width="917" height="53" /></div>
  <div id="stg-pagetitle">Payment</div>
  <p class="req-fields"><em>* Required Fields</em></p>

<!--  ////////////////////////////////////////////////////////////////////////////////// -->


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
				<legend>Payment Information</legend>
				<label for="">USSA Registration Fees:</label>
				<span class="data-input"><strong><c:out value="${accountBean.cartBean.totalCost}" /></strong></span>
				<input type="hidden" name="amount" value="<c:out value="${accountBean.cartBean.totalCost}" />">
				<label for="">* Credit Card Type:</label>
				<span class="multiselect-margin">
				<select name="cardType">
					<option selected></option>
					<option>Visa</option>
				<option>Amex</option>
				<option>Discover</option>
				<option>MasterCard</option>
				</select><br />
				</span>
				<label for="">* Credit Card Number:</label>
				<input name="ccnum" type="text"  /><br />
				<label for="fname">* First Name:</label>
				<input name="" type="text"  /><br />
				<label for="lname">* Last Name:</label>
				<input name="" type="text"  /><br />
				<label for="">* Expiration Date:</label>
				<span class="multiselect-margin">
				<select name="month">
					<option selected></option>
					<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
				<option>7</option>
				<option>8</option>
				<option>9</option>
				<option>10</option>
				<option>11</option>
				<option>12</option>
				</select>
				<select name="year">
					<option selected></option>
					<option>2007</option>
			<option>2008</option>
			<option>2009</option>
			<option>2010</option>
			<option>2011</option>
			<option>2012</option>
			<option>2013</option>
			<option>2014</option>
			<option>2015</option>
				</select><br />
				</span>
				<span class="textfield-short">
				<label for="">* Security Code:</label>
				<input name="" type="text" /><br />
				</span>
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

	</div>
	<div class="clear"></div>
</div>

</body>
