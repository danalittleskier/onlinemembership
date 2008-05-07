<%@ include file="/includes/taglibs.jsp"%>
<p class="stg-omr-header">Your Membership Fees</p>

<c:set var="cart" value="${accountBean.cartBean}"/>

<c:choose>
	<c:when test="${empty cart.lineItems}">
		Nothing found to display.
	</c:when>
	<c:otherwise>
		<table id="cart">
			<tr>
				<th scope="col" class="item" colspan="2">Membership/Items</th>
				<th scope="col">Qty</th>
				<th scope="col">Amount</th>
			</tr>
			<c:set var="category" scope="request" value="Memberships"/>
			<c:set var="lineItems" scope="request" value="${cart.memberships}"/>
			<%@ include file="/includes/shoppingCartCategoryInclude.jsp"%>
			<c:set var="category" scope="request" value="FIS/IPC Registrations"/>
			<c:set var="lineItems" scope="request" value="${cart.fis}"/>
			<%@ include file="/includes/shoppingCartCategoryInclude.jsp"%>
			<c:set var="category" scope="request" value="Division/State Dues"/>
			<c:set var="lineItems" scope="request" value="${cart.dues}"/>
			<%@ include file="/includes/shoppingCartCategoryInclude.jsp"%>
			<c:set var="category" scope="request" value="Magazine"/>
			<c:set var="lineItems" scope="request" value="${cart.magazines}"/>
			<%@ include file="/includes/shoppingCartCategoryInclude.jsp"%>
			<c:set var="category" scope="request" value="Bonus Packs"/>
			<c:set var="lineItems" scope="request" value="${cart.bonusPacks}"/>
			<%@ include file="/includes/shoppingCartCategoryInclude.jsp"%>
			<c:set var="category" scope="request" value="Other"/>
			<c:set var="lineItems" scope="request" value="${cart.other}"/>
			<%@ include file="/includes/shoppingCartCategoryInclude.jsp"%>
		</table>
		<table id="carttotal">
			<tr>
				<td class="total">Total</td>
				<td class="price">
					<c:out value="${cart.totalFormatted}"/>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>

<%--
<display:table name="accountBean.cartBean.lineItems" requestURI="" sort="list" id="cart" >
	<display:column title="Membership/Items" sortable="false" class="item">
		<c:out value="${cart.description}"/><br/>
		<c:if test="${cart.discount != null}">
			<c:out value="${cart.discountFormatted}"/> Discount
		</c:if>
	</display:column>
	<display:column title="&nbsp;" sortable="false" class="item-remove">
		<c:if test="${!(cart.inventory.inventoryType eq divisionDues) and !(cart.inventory.inventoryType eq stateDues) and !(cart.inventory.id eq ussaLate)}">
			<c:url var="deleteUrl" value="/registration.html">
				<c:param name="id" value="${cart.inventory.id}"/>
				<c:param name="_eventId_remove" value="Remove"/>
				<c:param name="_flowExecutionKey" value="${flowExecutionKey}"/>
			</c:url>
			<img src="<c:url value='/images/icon_trashcan.gif'/>" width="10" height="11" /><a href="${deleteUrl}">Remove</a></td>
		</c:if>
	</display:column>
	<display:column property="qty" title="Qty" sortable="false" class="price"/>
	<display:column property="lineItemTotalFormatted" title="Amount" sortable="false" class="price"/>
</display:table>
--%>

