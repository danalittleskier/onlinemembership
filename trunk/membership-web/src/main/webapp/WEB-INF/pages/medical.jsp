<body>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->

  <div id="stg-progress"><img src="${ctx}/images/progress_2.gif" width="917" height="53" /></div>
  <div id="stg-pagetitle">Medical Information</div>

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
				<legend>Medical  Information</legend>

	<table border="0" width="100%" id="table1">
		<tr>
			<td><font size="2">* Primary Medical Insurance Co.</font></td>
			<td><input type="text" name="T1" size="20"></td>
		</tr>
		<tr>
			<td><font size="2">* Policy Number</font></td>
			<td><input type="text" name="T2" size="20"></td>
		</tr>
		<tr>
			<td><font size="2">Phone Number</font></td>
			<td><input type="text" name="T3" size="20"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><font size="1"></font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		</table>

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
			<p class="stg-omr-header">Medical Information</p>
			<ul id="stg-omr">
				<b>Medical Insurance</b><p>Members must have and maintain Medical/Accident
	insurance for duration of membership year.&nbsp; Failure to provide accurate
	information demonstration the existence of such insurance coverage for
	Member will prevent processing of this application and cause termination of
	membership and suspension of all rights to participate in U.S. Ski and
	Snowboard Association activities, unless Member timely returns a fully
	executed Medial Exception Agreement ( which can be obtained by contacting
	Member Services at&nbsp; 435.647.2666 or via fax at 435.647.2052).&nbsp;
		</div></div></div></div>
		<!-- BOX (END) -->
	</div>
	<div class="clear"></div>
</div>

</body>
