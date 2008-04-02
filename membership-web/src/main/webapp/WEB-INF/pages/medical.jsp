<%@ include file="/common/taglibs.jsp"%>
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
	<input name="citizen" id="have-medical-1" type="radio" value="yes" class="radio" onclick="document.getElementById('insurance').style.display = 'block'" /> <label for="have-medical-1" class="radio">Yes</label>
	<input name="citizen" id="have-medical-2" type="radio" value="no" class="radio" onclick="document.getElementById('insurance').style.display = 'none'" /> <label for="have-medical-2" class="radio">No</label>
	</div><br />
	<div id="insurance" style="display:none;">
		<label>* Insurance Company Name:</label>
		<form:input path="memberLegal.insuranceCompany"/><br />
		<label>* Policy/ID Number:</label>
		<form:input path="memberLegal.insurancePolicy"/><br />
		<label>Phone Number:</label>
		<form:input path="memberLegal.insurancePhone"/><br />
	</div>
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
					<p class="stg-omr-header">Medical Information</p>
					<ul id="stg-omr">
						<b>Medical Insurance</b>

						<p>Members must have and maintain Medical/Accident
							insurance for duration of membership year.&nbsp; Failure to provide accurate
							information demonstration the existence of such insurance coverage for
							Member will prevent processing of this application and cause termination of
							membership and suspension of all rights to participate in U.S. Ski and
							Snowboard Association activities, unless Member timely returns a fully
							executed Medial Exception Agreement ( which can be obtained by contacting
							Member Services at&nbsp; 435.647.2666 or via fax at 435.647.2052).&nbsp;
				</div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>

</body>
