<%@ include file="/includes/taglibs.jsp" %>

<head>
	<script type='text/javascript' src='<c:url value="/dwr/interface/rulesBL.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/dwr/interface/clubDao.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
	<script type="text/javascript">
		function changeCitizenship() {
			// assume they are a US citizen if citizen-yes is checked
			var isUsCitizen = document.getElementById("citizen-yes").checked;
			var nationCodeDiv = document.getElementById('nation-code');
			if (isUsCitizen) {
				nationCodeDiv.style.display = 'none';
				document.getElementById('nationCode').value = 'USA';
			} else {
				nationCodeDiv.style.display = 'block';
			}
			updateDivision();
		}

		var clubSelectId = 'clubSelect';

		function updateClubSelectOptions() {
			var stateCode = document.getElementById('stateSelect').value;
			var clubSelect = document.getElementById(clubSelectId);
			dwr.util.removeAllOptions(clubSelectId);
			clubSelect.options[0] = new Option('Loading...', '');
			clubDao.findByStateCode(stateCode, function(clubs) {
				dwr.util.removeAllOptions(clubSelectId);
				clubSelect.options[0] = new Option('', '-1');
				clubSelect.options[clubSelect.options.length] = new Option('No Club Affiliation', '0');
				dwr.util.addOptions(clubSelectId, clubs, 'id', 'name');
				updateDivision();
			});
		}

		var handleDivision = function(division)
		{
			var divisionCode = document.getElementById('divisionCode');
			var divisionDescription = document.getElementById('divisionDescription');
			var divisionSpan = document.getElementById('division');
			var zipCodeDiv = document.getElementById('zipCodeDiv');
			removeChildren(divisionSpan);
			if(division != null)
			{
				divisionCode.value = division.divisionCode;
				divisionDescription.value = division.description;
				divisionSpan.appendChild(document.createTextNode(division.description));
				zipCodeDiv.style.display='none';
			}
			else
			{
				zipCodeDiv.style.display='block';
				var span = document.createElement('span');
				span.className='error-text';
				divisionDescription.value = 'Undetermined';
				span.appendChild(document.createTextNode('Undetermined'));
				divisionSpan.appendChild(span);
			}
		};

		function updateDivision() {
			var nationCode = document.getElementById('nationCode').value;
			var stateCode = document.getElementById('stateSelect').value;
			var clubId = document.getElementById('clubSelect').value;
			var zipCode = document.getElementById('zipCode').value;

			var zipCodeDiv = document.getElementById('zipCodeDiv');
			zipCodeDiv.style.display='none';
			var divisionCode = document.getElementById('divisionCode');
			divisionCode.value = '';
			var divisionSpan = document.getElementById('division');
			removeChildren(divisionSpan);

			if(nationCode != '' && stateCode != '' && clubId >= 0)
			{
				divisionSpan.appendChild(document.createTextNode('Loading...'));

				rulesBL.determineDivision(nationCode, stateCode, clubId, zipCode, handleDivision);
				document.getElementById('zipCode').value = '';
			}
		}

		function handleEnterKey(element) {
			var e = window.event;
			if(e.keyCode && e.keyCode == 13) {// intercept the enter key
				e.returnValue = false;
				element.blur();
				return false;
			}
			return true;
		}
	</script>
</head>

<body>

<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53"/></div>
<div id="stg-pagetitle">Member Information</div>
<p class="req-fields"><em>* Required Fields</em></p>
<!-- LEFT column -->
<div id="stg-twocol-primary">
	<form:form commandName="accountBean" name="accountBean">

		<%@ include file="/includes/messages.jsp" %>

		<!-- only asking this for new registrations -->
		<c:choose>
			<c:when test="${empty accountBean.member.id}">
				<p><em>USSA is required to report on the participation of minorities in our athletic programs.</em></p>
				<fieldset>
					<legend>Ethnicity</legend>
					<br/>
					<label for="">* What is your ethnicity?</label>
					<form:select path="member.ethnicity">
						<form:option value=""></form:option>
						<form:option value="W">White</form:option>
						<form:option value="B">African American</form:option>
						<form:option value="H">Hispanic or Latino</form:option>
						<form:option value="A">Asian</form:option>
						<form:option value="I">American Indian or Alaska Native</form:option>
						<form:option value="N">Native Hawaiian</form:option>
						<form:option value="P">Other Pacific Islander</form:option>
						<form:option value="M">Mixed Race</form:option>
						<form:option value="Z">Prefer Not to Respond</form:option>
					</form:select>
				</fieldset>

				<fieldset>
					<legend>Citizenship</legend>
					<label>* Are you a US Citizen?</label>
					<div class="radios">
						<form:radiobutton id="citizen-yes" path="usCitizen" value="true" onclick="changeCitizenship();"/>
						<label for="citizen-yes" class="radio">Yes</label>
						<form:radiobutton id="citizen-no" path="usCitizen" value="false" onclick="document.getElementById('nationCode').value='';changeCitizenship();"/>
						<label for="citizen-no" class="radio">No</label>
					</div>
					<br/>
					<div id="nation-code" <c:if test="${not(accountBean.usCitizen == true)}">style="display:none;"</c:if>>
						<label for="">* Select Nation Code:</label>
						<form:select id="nationCode" path="member.nationCode" onchange="updateDivision();">
							<form:option value=""></form:option>
							<form:options items="${accountBean.nations}" itemValue="nationCode" itemLabel="description"/>
						</form:select>
						<br/>
					</div>
				</fieldset>
			</c:when>
			<c:otherwise>
				<form:hidden id="nationCode" path="member.nationCode"/>
			</c:otherwise>
		</c:choose>

		<fieldset>
			<legend>State & Club</legend>
			<label style="width: 100px;">* State:</label>
			<form:select id="stateSelect" path="member.stateCode" onchange="updateClubSelectOptions();">
				<form:option value=""></form:option>
				<form:options items="${accountBean.usStates}" itemValue="id" itemLabel="description"/>
			</form:select>
			<br/>
			<label style="width: 100px;">* Club:</label>
			<form:select id="clubSelect" path="clubId" onchange="updateDivision();">
				<form:option value="-1">&nbsp;</form:option>
				<form:option value="0">No Club Affiliation</form:option>
				<form:options items="${accountBean.clubsForState}" itemValue="id" itemLabel="name"/>
			</form:select>
			<br/>
		</fieldset>

		<fieldset>
			<legend>Division</legend>
			<div id="zipCodeDiv" style="display:none;">
				<p>We were unable to determine your division.<br/>Please provide the zip code of the area that you train in.</p>
				<label style="width: 100px;">Zip Code:</label>
				<form:input id="zipCode" path="clubZipCode" maxlength="10" onchange="updateDivision()" onkeydown="handleEnterKey(this)" onkeypress="handleEnterKey(this)"/>
				<br/>
			</div>
			<label style="width: 100px;">Division:</label>
			<form:hidden id="divisionCode" path="divisionCode"/>
			<form:hidden id="divisionDescription" path="divisionDescription"/>
			<span id="division" class="data-input"><c:out value="${accountBean.divisionDescription}"/></span>
			<br/>
		</fieldset>

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
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
		<p class="stg-omr-header">About Your Division</p>
		<p>Based on the information provided, you will be in the division shown. Division or state dues may apply and will be automatically added to your cart when applicable.</p>
		<%--
				  <p><em>Foreign members may be assigned to the foreign division or a
					  geographic division based on state affiliation and membership category. Division or state dues may apply.</em></p>
  --%>
	</div></div></div></div>
	<!-- BOX (END) -->
</div>

<div class="clear"></div>

</body>
