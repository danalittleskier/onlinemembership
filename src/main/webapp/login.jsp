<%@ include file="/includes/taglibs.jsp" %>

<head>
	<title>U.S. Ski & Snowboard Membership Login Page</title>
</head>

<body>
<div id="stg-pagetitle">U.S. Ski & Snowboard Membership Login</div>
<div id="stg-onecol-primary">
<form method="post" action="<c:url value='/j_security_check'/>">
	<fieldset style="padding-bottom: 0">
		<ul>
			<c:if test="${param.error != null}">
				<li class="error">
					<fmt:message key="errors.password.mismatch"/>
					${sessionScope.ACEGI_SECURITY_LAST_EXCEPTION.message}
				</li>
			</c:if>

			<li>
				<label for="j_username" class="required desc">
					<fmt:message key="label.username"/>
					<span class="req">*</span>
				</label>
				<input type="text" name="j_username" id="j_username" tabindex="1"/>
			</li>

			<li>
				<label for="j_password" class="required desc">
					<fmt:message key="label.password"/>
					<span class="req">*</span>
				</label>
				<input type="password" name="j_password" id="j_password" tabindex="2"/>
			</li>

		</ul>
		<fieldset class="buttons">
			<input type="submit" class="brn-green" name="login" value="Login" tabindex="4"/>
		</fieldset>
	</fieldset>
</form>
</div>

</body>