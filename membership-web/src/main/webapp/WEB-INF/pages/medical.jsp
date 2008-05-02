<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_3.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Medical Information</div>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>

<fieldset>
	<legend>Primary Medical Insurance</legend>
	<label>* Do you have medical insurance?</label>
	<div class="radios">
		<form:radiobutton id="hasInsurance1"  path="hasInsurance" value="${true}" onclick="showHideInsurance()"/> <label for="hasInsurance1" class="radio">Yes</label>
		<form:radiobutton id="hasInsurance2"  path="hasInsurance" value="${false}" onclick="showHideInsurance()"/> <label for="hasInsurance2" class="radio">No</label>
	</div><br />
	<div id="insurance" style="display:none;">
		<label>* Insurance Company Name:</label>
		<form:input path="memberLegal.insuranceCompany" maxlength="50"/><br />
		<label>* Policy/ID Number:</label>
		<form:input path="memberLegal.insurancePolicy" maxlength="20"/><br />
		<label>Phone Number:</label>
		<form:input path="memberLegal.insurancePhone" maxlength="15"/><br />
	</div>
</fieldset>

	<script type="text/javascript" defer="defer">
		function showHideInsurance()
		{
			var hasInsuranceYes = document.getElementById('hasInsurance1');
			var insuranceDiv = document.getElementById('insurance');

			if(hasInsuranceYes.checked)
			{
				insuranceDiv.style.display = 'block';
			}
			else
			{
				insuranceDiv.style.display = 'none';
			}
		}

		showHideInsurance();
	</script>

<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="button" class="btn-green" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);">
	<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
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
					<p class="stg-omr-header">Medical Insurance</p>
					<ul id="stg-omr">
						<p>Members must have and maintain Medical/Accident insurance for the 
						duration of the membership year.  Failure to provide accurate information 
						demonstrating the existence of such insurance coverage for Member will 
						cause termination of membership and suspension of all rights to participate 
						in U.S. Ski and Snowboard Association activities.</p>
                        
                        <p>If the member does not hold primary Medical/Accident insurance (as noted above), 
                        or cannot provide the required information on such insurance, 
                        Member will need to execute the Medical Exception Agreement provided.</p>
                    </ul>
                </div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>

</body>
