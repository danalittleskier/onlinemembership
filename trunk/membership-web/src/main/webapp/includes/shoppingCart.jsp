<%@ include file="/common/taglibs.jsp"%>
<p class="stg-omr-header">Your Membership Fees</p>

<display:table name="accountBean.cartBean.lineItems" requestURI="" sort="list" defaultsort="1" id="cart" >
	<display:column title="Membership/Items" sortable="false" class="item">
		<c:out value="${cart.description}"/><br/>
		<c:if test="${cart.discount != null}">
			(Disounted <c:out value="${cart.discountFormatted}"/> from <c:out value="${cart.amountFormatted}"/>)
		</c:if>
	</display:column>
	<display:column title="&nbsp;" sortable="false" class="item-remove">
		<c:url var="deleteUrl" value="registration.html">
			<c:param name="_flowExecutionKey" value="${flowExecutionKey}"/>
			<c:param name="_eventId_remove" value="Remove"/>
			<c:param name="id" value="${cart.inventory.id}"/>
		</c:url>
		<img src="<c:url value='/images/icon_trashcan.gif'/>" width="10" height="11" /><a href="${deleteUrl}">Remove</a></td>
	</display:column>
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