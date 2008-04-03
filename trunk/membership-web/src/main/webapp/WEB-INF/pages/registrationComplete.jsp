<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_4.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Membership Registration</div>

<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">
<%@ include file="/includes/messages.jsp"%>


<!-- BOX (START) -->
<div class="stg-bl">
	<div class="stg-br">
		<div class="stg-tl">
			<div class="stg-tr">
				<div></div>
				<p>
					Membership Registration:
					<c:out value="${accountBean.cartBean.total}"/>
					<br/>
					Registration Details:
					<c:out value="${accountBean.paymentTransactionCode}"/>

					<c:if test="${accountBean.paymentTransactionCode} == 'success'">

						Your registration is complete - click link to return to dashboard.
						<br/>

					</c:if>
					<c:if test="${accountBean.paymentTransactionCode} != 'success'">

						Your registration is not complete - click back to retry payment.
						<br/>

					</c:if>
				</p>
			</div>
		</div>
	</div>
</div>
<!-- BOX (END) -->

<p>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input type="submit" class="btn-green" name="_eventId_back" value="Back">
	</fieldset>
</p>

</form:form>
</div>
<div class="clear"></div>

</body>
