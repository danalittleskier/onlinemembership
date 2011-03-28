<%@ page import="org.ussa.model.Inventory" %>
<%@ include file="/includes/taglibs.jsp"%>

<c:set var="divisionDues"><%=Inventory.INVENTORY_TYPE_DIVISION_DUES%></c:set>
<c:set var="stateDues"><%=Inventory.INVENTORY_TYPE_STATE_DUES%></c:set>
<c:set var="ussaLate"><%=Inventory.INV_ID_MEMBER_LATE_FEE%></c:set>

<c:if test="${not empty lineItems}">
	<tr>
		<td style="font-weight:bold;"><c:out value="${category}"/></td>
		<td class="item-remove"></td>
		<td class="price"></td>
		<td class="price"></td>
	</tr>
	<c:forEach var="lineItem" items="${lineItems}">
		<tr>
			<td class="item">
				<c:out value="${lineItem.description}"/><br/>
				<c:if test="${lineItem.discount != null}">
					<c:out value="${lineItem.discountFormatted}"/> Discount
				</c:if>
			</td>
			<td class="item-remove">
				<c:if test="${empty param.readOnly and !(lineItem.inventory.inventoryType eq divisionDues) and !(lineItem.inventory.inventoryType eq stateDues) and !(lineItem.inventory.id eq ussaLate)}">
					<c:url var="deleteUrl" value="/registration.html">
						<c:param name="id" value="${lineItem.inventory.id}"/>
						<c:param name="_eventId_remove" value="Remove"/>
						<c:param name="_flowExecutionKey" value="${flowExecutionKey}"/>
					</c:url>
					<img src="<c:url value='/images/icon_trashcan.gif'/>" width="10" height="11" alt="Remove from cart"/><a href="${deleteUrl}">Remove</a>
				</c:if>
			</td>
			<td class="price"><c:out value="${lineItem.qty}"/></td>
			<td class="price"><c:out value="${lineItem.lineItemTotalFormatted}"/></td>
		</tr>
	</c:forEach>
</c:if>
