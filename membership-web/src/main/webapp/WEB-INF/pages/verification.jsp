<%@ include file="/common/taglibs.jsp" %>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_2.gif'/>" width="917" height="53"/></div>
<div id="stg-pagetitle">Verification</div>

<form:form commandName="accountBean" name="accountBean">

<!-- LEFT column -->
<div id="stg-twocol-primary">

<%@ include file="/includes/messages.jsp"%>

<p>Please verify that the following information is correct. If you would like to change any of the information, you may
	do so by choosing "Edit" below. When you have verified that the information is correct, click "Continue".</p>
<table id="stg-data-review">
	<tr>
		<th scope="col">Personal Information</th>
		<th scope="col" class="edit"><input style="float:right;" type="submit" class="link-submit" name="_eventId_editContactInfo" value="Edit"></th>
	</tr>
	<tr>
		<td class="data-label" scope="row">USSA Member #:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.id}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">First Name:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.firstName}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">MI:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.middleName}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Last Name:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.lastName}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Suffix:</td>
		<td class="data-text-none" scope="row">
			<c:out value="${accountBean.member.suffixName}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Gender:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.gender}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Ethnicity:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.ethnicity}"/>
		</td>
	</tr>
</table>
<table id="stg-data-review">
	<tr>
		<th scope="col">Address & Phone</th>
		<th scope="col" class="edit"><input style="float:right;" type="submit" class="link-submit" name="_eventId_editContactInfo" value="Edit"></th>
	</tr>
	<tr>
		<td class="data-label" scope="row">Company:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.company}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Address:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.deliveryAddress}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">City:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.city}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">State:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.stateCode}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Zip:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.postalCode}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Home Phone:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.phoneHome}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Other Phone:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.address.phoneOther}"/>
		</td>
	</tr>
	<tr>
		<td class="data-label" scope="row">Fax:</td>
		<td class="data-text-none" scope="row">
			<c:out value="${accountBean.address.phoneFax}"/>
		</td>
	</tr>
</table>
<table id="stg-data-review">
	<tr>
		<th scope="col">Membership Information</th>
		<th scope="col" class="edit"><input style="float:right;" type="submit" class="link-submit" name="_eventId_editSportMembership" value="Edit">
		</th>

	</tr>
	<tr>
		<td class="data-label" scope="row">Division:</td>
		<td class="data-text" scope="row">
			<c:out value="${accountBean.member.division.description}"/>
		</td>
	</tr>
	<c:forEach var="lineItem" items="${accountBean.cartBean.membershipLineItems}" varStatus="varStatus">
		<c:choose>
			<c:when test="${varStatus.first}">
				<tr>
					<td class="data-label" scope="row">Membership Type:</td>
					<td class="data-text" scope="row">
						<c:out value="${lineItem.description}"/>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="data-label" scope="row"></td>
					<td class="data-text" scope="row">
					<c:out value="${lineItem.description}"/>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</table>


<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
	<input type="submit" class="btn-green" name="_eventId_back" value="Back">

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
					<%@ include file="/includes/shoppingCartReadOnly.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>
</form:form>

</body>
