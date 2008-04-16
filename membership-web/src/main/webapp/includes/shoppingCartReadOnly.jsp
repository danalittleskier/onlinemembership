<%@ include file="/common/taglibs.jsp"%>
<p class="stg-omr-header">Your Membership Fees</p>

<display:table name="accountBean.cartBean.lineItems" requestURI="" sort="list" id="cart" >
	<display:column title="Membership/Items" sortable="false" class="item">
		<c:out value="${cart.description}"/><br/>
		<c:if test="${cart.discount != null}">
			<c:out value="${cart.discountFormatted}"/> discount
		</c:if>
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