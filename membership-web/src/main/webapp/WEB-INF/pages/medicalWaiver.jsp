<body>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->

  <div id="stg-progress"><img src="${ctx}/images/progress_1.gif" width="917" height="53" /></div>
  <div id="stg-pagetitle">Release Waiver</div>

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
				<legend>Release Waiver</legend>

</fieldset>

<fieldset class="buttons">
				<label></label>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="button" name="_eventId_next" value="Continue">
             <input type="submit" class="button" name="_eventId_back" value="Back">

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
