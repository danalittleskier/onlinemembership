<%@ include file="/includes/taglibs.jsp"%>
<body>
<form:form commandName="accountBean" name="accountBean">
  <div id="stg-pagetitle">Duplicate Account(s) Found</div>
	<!-- Content -->
	<div id="stg-onecol-primary">
		<!-- BOX (START) -->
		<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>

			<div id="center-notice">
				<p><strong>One or more accounts with a similar name and birth date is/are already on record.</strong></p>
				<p>Select your account below and click "Continue" to renew your membership.</p>
				<p><strong>ATTENTION</strong>: Please read before continuing the registration process. Members found falsifying registration information will be immediately placed on inactive for the remainder of the competition season with no refund provided.  If you have any question as to your membership history with USSA please contact Member Services before continuing the registration process.</p> 
				<table>
					<tr>
						<th></th>
						<th>Name</th>

						<th>Birth Date</th>
					</tr>
                    <c:forEach items="${accountBean.duplicateMembers}" var="member">
                        <tr>
                            <td class="short"><input type="radio" name="id" value="${member.id}"/></td>
                            <td><c:out value="${member.firstName}"/>&nbsp;<c:out value="${member.lastName}"/></td>
                            <td><fmt:formatDate value="${member.birthDate}" pattern="MM/dd/yyyy"/></td>
                        </tr>
                    </c:forEach>
				</table>
				<br />
                <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
				<input type="button" class="btn-green" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);">
				<input type="submit" name="_eventId_next" value="Continue" class="btn-green"/>
				<em><p>If you believe your account is not shown, please call USSA Member Services at <br />
				(435) 647-2666 to complete your registration.</p></em>
				<div class="clear"></div>
			</div>
		</div></div></div></div> 
		<!-- BOX (END) -->
	</div>

	<div class="clear"></div>
</form:form>
</body>
