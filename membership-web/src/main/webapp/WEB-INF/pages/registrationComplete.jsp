<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_5.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Membership Registration Complete</div>

<!-- Content -->
<div id="stg-onecol-wide">
<form:form commandName="accountBean" name="accountBean">
<%@ include file="/includes/messages.jsp"%>

<!-- LEFT column -->
<div id="stg-twocol-primary">

	<!-- Success message -->
	<div class="stg-success-tl">
		<p>Success!</p>
	</div>
	<div class="stg-success-tr"></div>
	<div class="stg-success-content">
		<p>Your registration/renewal is complete for the ____ season!</p>
	</div>
	<!-- End success message -->

	<fieldset>
		<legend>Additional Instructions</legend>
		blah blah blah....
	</fieldset>

</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">
	<!-- BOX (START) -->
	<div class="stg-bl">
		<div class="stg-br">
			<div class="stg-tl">
				<div class="stg-tr">
					<div></div>
					<p class="stg-omr-header">Paid Membership Fees</p>

					<display:table name="accountBean.cartBean.lineItems" requestURI="" sort="list" defaultsort="1" id="cart" >
						<display:column property="description" title="Membership/Items" sortable="false" class="item"/>
						<display:column property="qty" title="Qty" sortable="false" class="price"/>
						<display:column property="lineItemTotalFormatted" title="Amount" sortable="false" class="price"/>
					</display:table>

					<table id="carttotal">
						<tr>
							<td class="total">Total</td>
							<td class="price">
								<c:out value="${accountBean.cartBean.totalFormatted}"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>


<p>
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<input type="submit" class="btn-green" name="_eventId_next" value="Go to Account Home">
	</fieldset>
</p>

</form:form>
</div>
<div class="clear"></div>

</body>
