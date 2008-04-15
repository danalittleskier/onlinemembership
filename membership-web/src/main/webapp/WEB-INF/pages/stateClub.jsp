<%@ include file="/common/taglibs.jsp" %>

<head>
	<script type="text/javascript">
		function changeCitizenship()
		{
			var isUsCitizen = !document.getElementById("citizen2").checked;
			var nationCodeDiv = document.getElementById('nation-code');
			if(isUsCitizen)
			{
				nationCodeDiv.style.display = 'none';
				document.getElementById('nationCode').value='USA';
			}
			else
			{
				nationCodeDiv.style.display = 'block';
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

		<c:if test="${empty accountBean.member.id}">
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
			<label for="">* Are you a US Citizen?</label>
			<div class="radios">
				<form:radiobutton id="citizen1" path="usCitizen" value="true" onclick="changeCitizenship(); document.getElementById('update').click();"/>
				<label for="citizen1" class="radio">Yes</label>
				<form:radiobutton id="citizen2" path="usCitizen" value="false" onclick="changeCitizenship()"/>
				<label for="citizen2" class="radio">No</label>
			</div>
			<br/>

			<div id="nation-code">
				<label for="">* Select Nation Code:</label>
				<form:select id="nationCode" path="member.nationCode" onchange="document.getElementById('update').click();">
					<form:option value=""></form:option>
					<form:options items="${accountBean.nations}" itemValue="nationCode" itemLabel="description"/>
				</form:select>
				<br/>
			</div>
		</fieldset>

		<script type="text/javascript" defer="defer">
			changeCitizenship();
		</script>
		</c:if>

		<fieldset>
			<legend>State & Club</legend>
			<label style="width: 100px;" for="">* State:</label>
			<form:select path="member.stateCode" onchange="document.getElementById('update').click();">
				<form:option value=""></form:option>
				<form:options items="${accountBean.usStates}" itemValue="id" itemLabel="description"/>
			</form:select>
			<br/>
			<label style="width: 100px;" for="">* Club:</label>
			<form:select path="clubId" onchange="document.getElementById('update').click();">
				<form:option value=""></form:option>
				<form:options items="${accountBean.clubsForState}" itemValue="id" itemLabel="name"/>
			</form:select>
			<br/>
			<div id="hide" style="display:none">
				<input type="submit" class="button" id="update" name="_eventId_changeState" value="Update">
			</div>
		</fieldset>

		<fieldset>
			<legend>Division</legend>
			<label style="width: 100px;">Division:</label>
			<span class="data-input"><c:out value="${accountBean.member.division.description}"/></span>
			<br/>
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
