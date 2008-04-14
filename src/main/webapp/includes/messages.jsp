<%@ include file="/common/taglibs.jsp"%>
<spring:bind path="accountBean.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="stg-error-tl">
			<p>Please correct the following</p>
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
