<body>

<!-------------------------------------------- Content ------------------------------------------------->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->


<div id="stg-progress"><img src="${ctx}/images/progress_4.gif" width="917" height="53" /></div>
  <div id="stg-pagetitle">Release Waiver</div>

	<!-- Content -->
	<div id="stg-onecol-wide">
	<form:form commandName="accountBean" name="accountBean">
	<spring:bind path="accountBean.*">
			<c:if test="${not empty status.errorMessages}">
				<c:set var="sectionHasFormErrors" scope="request" value="true"/>
						<c:forEach var="errorMsg" items="${status.errorMessages}">
							<c:out value="${errorMsg}" escapeXml="false" />
						</c:forEach>
			</c:if>
		</spring:bind>

		<!-- BOX (START) -->
		<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
		<p>Waiver...</p>
		</div></div></div></div>
		<!-- BOX (END) -->
			<p>
			<form name="" action="" method="">
				<fieldset class="buttons">
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
			<input type="submit" class="btn-green" name="_eventId_next" value="I Agree to the Terms Above">
             <input type="submit" class="btn-green" name="_eventId_back" value="Back">

  </fieldset>
			</form>
			</p>
	</div>
	<div class="clear"></div>
	</form:form>
</div>

</body>
