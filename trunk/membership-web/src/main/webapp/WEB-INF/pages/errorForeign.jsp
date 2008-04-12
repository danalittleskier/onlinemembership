<%@ include file="/common/taglibs.jsp"%>
<body>

<div id="stg-onecol-primary">
	<div class="stg-error-tl">
		<p>Registration Error</p>
	</div>
	<div class="stg-error-tr"></div>
	<div class="stg-error-content">
		<ul>
				<li>
					We are not processing foreign registrations or renewals on-line at this time. Please call support.
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
