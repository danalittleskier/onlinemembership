<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>USSA Membership Certificate</title>
</head>
<body onload="this.window.print();">
	<h1>USSA</h1>
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
		<tr>
			<td class="data-label">FIS ID:</td>
			<td>TODO</td>
		</tr>
		<tr>
			<td class="data-label">Paid On:</td>
			<td>TODO</td>
		</tr>
		<tr>
			<td class="data-label">Division:</td>
			<td><c:out value="${member.division.description}"/></td>
		</tr>
		<tr>
			<td class="data-label">Division Dues:</td>
			<td>TODO</td>
		</tr>
		<tr>
			<td class="data-label">Membership Type(s):</td>
			<td>TODO</td>
		</tr>
	</table>

	<p>Please accept this letter as proof of membership and allow this member to participate in USSA events.</p>
	<p>Thank you for consideration in this matter.</p>
	<p>Sincerely,</p>
	<p>USSA Member Services</p>
</body>
