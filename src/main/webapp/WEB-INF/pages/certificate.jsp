<%@ include file="/includes/taglibs.jsp"%>
<head>
	<title>USSA Membership Confirmation</title>

	<link type="text/css" href="<c:url value='/styles/certificate.css'/>" rel="stylesheet"/>
</head>
<body>
<c:choose>
	<c:when test="${empty membershipsBean.unrestrictedMemberships and not empty membershipsBean.restrictedMemberships}">
		<%@ include file="/includes/backgroundScreeningText.jsp"%>
		<c:url var="ncsiUrl" value="https://www.ncsisafe.com/members/selfregbatchcodelanding.aspx">
			<c:param name="srb" value="11392848"/>
			<c:param name="id" value="${member.id}"/>
		</c:url>
		<p><a href="${ncsiUrl}" target="_BLANK">Go here to complete your backgound screening.</a></p>
	</c:when>
	<c:otherwise>
		<div class="closeWindowLink"><a href="#" onclick="window.close();">Close Window</a></div>
		<div class="header"><img src="<c:url value='images/ussa_lh_top.jpg'/>"/> </div>
		<p><strong>To Whom It May Concern:</strong></p>

		<p>This letter is to verify that the USSA membership dues have been paid for the <c:out value="${lastSeason}"/>/<c:out value="${currentSeason}"/> season for:</p>
		<table id="member-data">
			<tr>
				<td class="data-label">Name:</td>
				<td><c:out value="${member.lastName}"/>, <c:out value="${member.firstName}"/> <c:out value="${member.middleName}"/></td>
			</tr>
			<tr>
				<td class="data-label">USSA Member Number:</td>
				<td><c:out value="${member.id}"/></td>
			</tr>
			<tr>
				<td class="data-label">Year of Birth:</td>
				<td><c:out value="${yearOfBirth}"/></td>
			</tr>
			<c:if test="${hasFis}">
				<tr>
					<td class="data-label">FIS ID:</td>
					<td><c:out value="${member.fisId}"/></td>
				</tr>
			</c:if>
			<tr>
				<td class="data-label">Paid On:</td>
				<td><fmt:formatDate value="${memberSeason.appProcessDate}" pattern="MM/dd/yyyy"/></td>
			</tr>
			<tr>
				<td class="data-label">Division:</td>
				<td><c:out value="${member.division.description}"/></td>
			</tr>
			<tr>
				<td class="data-label">Division/State Dues:</td>
				<td>$<c:out value="${divisionDues}"/></td>
			</tr>
			<tr>
				<td class="data-label">Membership Type(s):</td>
				<td>
					<c:forEach var="inventory" items="${membershipsBean.unrestrictedMemberships}">
						<c:out value="${inventory.renewDescription}"/><br/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="data-label">
					Club Affiliation:
				</td>
				<td>
					<c:out value="${memberClubs}"/>
				</td>
			</tr>
		</table>

		<p>Please accept this letter as proof of membership and allow this member to participate in USSA events.</p>
		<p>Thank you for consideration in this matter.</p>
		<p>Sincerely,</p>
		<p>USSA Member Services</p>
		<div class="footer"><img src="<c:url value='images/ussa_lh_bottom.jpg'/>"/></div>

		<script type="text/javascript" defer="defer">
			window.print();
		</script>
	</c:otherwise>
</c:choose>

</body>
