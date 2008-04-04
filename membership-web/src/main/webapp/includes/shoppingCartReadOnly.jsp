<%@ include file="/common/taglibs.jsp"%>
<p class="stg-omr-header">Your Membership Fees</p>

<display:table name="accountBean.cartBean.lineItems" requestURI="" sort="list" defaultsort="1" id="cart" >
	<display:column property="description" title="Membership/Items" sortable="false" class="item"/>
	<display:column property="qty" title="Qty" sortable="false" class="price"/>
	<display:column property="lineItemTotal" title="Amount" sortable="false" class="price"/>
</display:table>

<table id="carttotal">
	<tr>
		<td class="total">Total</td>
		<td class="price">
			<c:out value="${accountBean.cartBean.total}"/>
		</td>
	</tr>
</table>
