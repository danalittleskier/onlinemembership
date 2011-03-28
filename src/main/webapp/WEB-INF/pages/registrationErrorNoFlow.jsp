<%@ include file="/includes/taglibs.jsp"%>
<body>

<div id="stg-twocol-primary">
		<c:if test="${not empty errorMessages}">
			${status.class.name}
			<div class="stg-error-tl">
				<p>Registration Error</p>
			</div>
			<div class="stg-error-tr"></div>
			<div class="stg-error-content">
				<ul>
					<c:forEach var="errorMsg" items="${errorMessages}">
						<li>
							<c:out value="${errorMsg}" escapeXml="false" />
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	<fieldset id="buttonFieldSet" class="buttons">
		<input type="submit" class="btn-green" name="backToAccounts" value="Go to Account Home" onclick="window.location.href='/accounts/dashboard.html'"/>
	</fieldset>
</div>

<div class="clear"></div>

</body>
