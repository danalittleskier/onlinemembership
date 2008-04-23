<%@ include file="/includes/taglibs.jsp" %>

<head>
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
			dwr.util.removeAllOptions(clubSelectId);
			dwr.util.addOptions(clubSelectId, ['loading...']); // add blank option
			clubDao.findByStateCode(stateCode, function(clubs) {
				dwr.util.removeAllOptions(clubSelectId);
				dwr.util.addOptions(clubSelectId, ['']); // add blank option
				dwr.util.addOptions(clubSelectId, clubs, 'id', 'name');
				updateDivision();
			});
		}

		function updateDivision() {
			var divisionSpan = document.getElementById('division');
			removeChildren(divisionSpan);
			var nationCode = document.getElementById('nationCode').value;
			if (nationCode == 'USA') {
				var clubId = document.getElementById('clubSelect').value;
				if (clubId) {
					clubDao.getClub(clubId, function(club){
						divisionSpan.appendChild(document.createTextNode(club.division.description))
					});
				}
			} else {
				divisionSpan.appendChild(document.createTextNode('Foreign'))
			}
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
						<form:radiobutton id="citizen-no" path="usCitizen" value="false" onclick="changeCitizenship();"/>
						<label for="citizen-no" class="radio">No</label>
					</div>
					<br/>
					<script type="text/javascript" defer="defer">
						changeCitizenship();
					</script>

					<div id="nation-code">
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
			<label style="width: 100px;" for="">* State:</label>
			<form:select id="stateSelect" path="member.stateCode" onchange="updateClubSelectOptions();">
				<form:option value=""></form:option>
				<form:options items="${accountBean.usStates}" itemValue="id" itemLabel="description"/>
			</form:select>
			<br/>
			<label style="width: 100px;" for="">Club:</label>
			<form:select id="clubSelect" path="clubId" onchange="updateDivision();">
				<form:option value=""></form:option>
				<form:options items="${accountBean.clubsForState}" itemValue="id" itemLabel="name"/>
			</form:select>
			<br/>
		</fieldset>

		<fieldset>
			<legend>Division</legend>
			<label style="width: 100px;">Division:</label>
			<span id="division" class="data-input"><c:out value="${accountBean.member.division.description}"/></span>
			<br/>
		</fieldset>


		<fieldset class="buttons">
			<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="btn-green" name="_eventId_back" value="Back">
			<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
		</fieldset>

	</form:form>
</div>


<!-- RIGHT column -->
<div id="stg-twocol-secondary">
	<!-- BOX (START) -->
	<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
		<p class="stg-omr-header">About Your Division</p>
		<p>Based on the information provided, you will be in the division shown. Division or state dues may apply.</p>
		<%--
				  <p><em>Foreign members may be assigned to the foreign division or a
					  geographic division based on state affiliation and membership category. Division or state dues may apply.</em></p>
  --%>
	</div></div></div></div>
	<!-- BOX (END) -->
</div>

<div class="clear"></div>

</body>
