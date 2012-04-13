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
		<div class="button green"><span><input type="submit" class="btn-submit" name="backToAccounts" value="Return to My USSA" onclick="window.location.href='http://my.ussa.org/portal'"/></span></div>
	</fieldset>
</div>

<div class="clear"></div>

</body>
