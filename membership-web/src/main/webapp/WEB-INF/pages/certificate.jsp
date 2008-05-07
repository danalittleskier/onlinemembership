<%@ include file="/includes/taglibs.jsp"%>
<head>
	<title>USSA Membership Certificate</title>

	<link type="text/css" href="<c:url value='/styles/certificate.css'/>" rel="stylesheet"/>
</head>
<body>
<c:choose>
	<c:when test="${restricted}">
		<%@ include file="/includes/backgroundScreeningText.jsp"%>
		<p><a href="https://www.ncsisafe.com" target="_BLANK">Go here to complete your backgound screening.</a></p>
	</c:when>
	<c:otherwise>
		<div class="closeWindowLink"><a href="#" onclick="window.close();">Close Window</a></div>
		<div class="header"><img src="<c:url value='images/ussa/ussa-header-icon.png'/>"/> </div>
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
					<c:forEach var="memberTransaction" items="${membershipTransactions}">
						<c:out value="${memberTransaction.inventory.renewDescription}"/><br/>
					</c:forEach>
				</td>
			</tr>
			<c:if test="${not empty member.clubName}">
				<tr>
					<td class="data-label">
						Club Affiliation:
					</td>
					<td>
						<c:out value="${member.clubName}"/>
					</td>
				</tr>
			</c:if>
		</table>

		<p>Please accept this letter as proof of membership and allow this member to participate in USSA events.</p>
		<p>Thank you for consideration in this matter.</p>
		<p>Sincerely,</p>
		<p>USSA Member Services</p>
		<div class="footer"><img src="<c:url value='images/ussa/ussa-footer-icon.png'/>"/></div>
	</c:otherwise>
</c:choose>

<script type="text/javascript" defer="defer">
	window.print();
</script>
</body>