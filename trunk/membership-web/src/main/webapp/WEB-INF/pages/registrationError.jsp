<%@ include file="/includes/taglibs.jsp"%>
<body>

<div id="stg-twocol-primary">
	<spring:bind path="accountBean.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="stg-error-tl">
				<p>Registration Error</p>
			</div>
			<div class="stg-error-tr"></div>
			<div class="stg-error-content">
				<ul>
					<c:forEach var="errorMsg" items="${status.errorMessages}">
						<li>
							<c:out value="${errorMsg}" escapeXml="false" />
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</spring:bind>
	<fieldset id="buttonFieldSet" class="buttons">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input type="submit" class="btn-green" name="_eventId_back" value="Go to Account Home">
	</fieldset>
</div>

<div class="clear"></div>

</body>
