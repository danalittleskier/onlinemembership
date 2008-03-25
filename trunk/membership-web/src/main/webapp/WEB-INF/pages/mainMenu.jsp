<%@ include file="/common/taglibs.jsp"%>

<body>

Accounts Dashboard Mockup

<hr/>


<table border="1" width="100%" id="table1">
	<tr>
		<td>New Registation</td>
		<td></td>
		<td><a href="<c:url value="/registration.html"/>">New Registration</a></td>
	</tr>
	<tr>
		<td>Under 18</td>
		<td>5139969</td>
		<td><a href="<c:url value="/registration.html?id=5139969"/>">Renew Membership</a></td>
	</tr>
	<tr>
		<td>Over 18</td>
		<td>158</td>
		<td><a href="<c:url value="/registration.html?id=158"/>">Renew Membership</a></td>
	</tr>
</table>


<div class="separator"></div>

</body>
