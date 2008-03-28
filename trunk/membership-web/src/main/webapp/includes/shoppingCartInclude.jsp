<%@ include file="/common/taglibs.jsp"%>
<p class="stg-omr-header">Your Membership Fees</p>

<display:table name="accountBean.shoppingCart" requestURI="" sort="list" defaultsort="1" id="cart">
	<display:column property="description" title="Description" sortable="false" class="item"/>
	<display:column property="amount" title="Amount" sortable="false" class="price"/>
</display:table>

<table id="carttotal">
	<tr>
		<td class="total">Total</td>
		<td class="price">
			<c:out value="${accountBean.cartBean.totalCost}"/>
		</td>
	</tr>
</table>
