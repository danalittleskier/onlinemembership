<body>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->

  <div id="stg-progress"><img src="${ctx}/images/progress_1.gif" width="917" height="53" /></div>
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
		<div id="hide" style="display:none">
	      <input type="submit" class="button" id="update" name="_eventId_changeState" value="Update">
	    </div>


			<fieldset>
				<legend>Citizenship</legend>
				* Are you a US Citizen?
				<div class="radios">
				<input name="citizen" id="citizen1" type="radio" value="yes" onclick="document.getElementById('nation-code').style.display = 'none'" /> <label for="citizen1" class="radio">Yes</label>
				<input name="citizen" id="citizen2" type="radio" value="no" onclick="document.getElementById('nation-code').style.display = 'block'" /> <label for="citizen2" class="radio">No</label>
				</div><br />
				<div id="nation-code">
				<label for="">* Select Nation Code:</label>
				<select>
					<option selected></option>
					<option value="">[...Nation Code Data...]</option>
				</select><br />
				</div>
			</fieldset>

			<fieldset>
				<legend>State & Club</legend>
				<label for="">* State:</label>
		          <spring:bind path="accountBean.stateAffiliation"> <!-- onchange="javascript: changeState(this.options[this.selectedIndex].value);accountBean.submit();"  -->
		          <!-- onchange="javascript: changeState(this.options[this.selectedIndex].value);accountBean.submit();" -->
		            <select name="stateAffiliation" onchange="javascript: document.getElementById('update').click();">
		            <!-- <select name="${status.expression}" value="${status.value[index]}">
			    	<select name="stateId" onChange="javaScript: showAlert();accountBean.submit();"> -->
			          <option value=""></option>
			          <c:forEach var="o" items="${accountBean.usStates}">
			            <option value="${o.id}"
			              <c:if test="${o.id == status.value}">selected</c:if>>${o.id}</option>
			          </c:forEach>
			        </select>
			      </spring:bind>
				<br />
				<label for="">* Club:</label>
	          <spring:bind path="accountBean.clubId">
		    	<select name="clubId">
		          <option value=""></option>
		          <c:forEach var="o" items="${accountBean.clubsForState}">
		            <option value="${o.id}"
		              <c:if test="${o.id == status.value}">selected</c:if>>${o.name}</option>
		          </c:forEach>
		        </select>
		      </spring:bind><br />
			</fieldset>

			<fieldset>
				<legend>Division</legend>
				<label for="">* Division:</label>
	          <c:out value="${accountBean.member.divisionCode}" /><br />
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

	</div>
	<div class="clear"></div>
</div>

</body>
