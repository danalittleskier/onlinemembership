<%@ include file="/common/taglibs.jsp" %>

<head>
	<title>Accounts Dashboard Page</title>
</head>

<body>
<div id="stg-pagetitle">Mock Login</div>
<div id="stg-onecol-primary">
<form method="post" action="<c:url value='/j_security_check'/>" onsubmit="saveUsername(this);return validateForm(this)">
	<fieldset style="padding-bottom: 0">
		<ul>
			<c:if test="${param.error != null}">
				<li class="error">
					<fmt:message key="errors.password.mismatch"/>
						<%--${sessionScope.ACEGI_SECURITY_LAST_EXCEPTION.message}--%>
				</li>
			</c:if>
			<li>
				<label for="j_username" class="required desc">
					<fmt:message key="label.username"/>
					<span class="req">*</span>
				</label>
				<input type="text" class="text medium" name="j_username" id="j_username" tabindex="1"/>
			</li>

			<li>
				<label for="j_password" class="required desc">
					<fmt:message key="label.password"/>
					<span class="req">*</span>
				</label>
				<input type="password" class="text medium" name="j_password" id="j_password" tabindex="2"/>
			</li>

		</ul>
		<fieldset class="buttons">
			<input type="submit" class="brn-green" name="login" value="Login" tabindex="4"/>
		</fieldset>
	</fieldset>
</form>
</div>

<%@ include file="/scripts/login.js" %>

</body>