<%@ include file="/common/taglibs.jsp"%>
<body>

<div id="stg-onecol-primary">
	<div class="stg-error-tl">
		<p>Registration Complete</p>
	</div>
	<div class="stg-error-tr"></div>
	<div class="stg-error-content">
		<ul>
				<li>
					You have already renewed your registration for this year. If you need to make changes to your registration please call suuport.
				</li>
		</ul>
	</div>

	<p>
		<fieldset class="buttons">
			<input type="button" class="btn-green" name="button" value="Go to Account Home" onclick="location.href='<c:url value="/authenticationRedirect.jsp"/>'">
		</fieldset>
	</p>

</div>

<div class="clear"></div>

</body>
