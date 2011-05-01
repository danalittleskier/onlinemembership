<%@ include file="/includes/taglibs.jsp"%>
<body>

<div id="stg-onecol-primary">
	<div class="stg-error-tl">
		<p>Registration Not Complete</p>
	</div>
	<div class="stg-error-tr"></div>
	<div class="stg-error-content">
		<ul>
				<li>
					You have not registered or renewed your registration for this year. If you feel you have reached
					this message in error contact Member Services at 435-647-2666 or by email at membership@ussa.org.
				</li>
		</ul>
	</div>

	<p>
		<fieldset class="buttons">
			<div class="button blue"><span><input type="button" class="btn-submit" name="button" value="Go to Account Home" onclick="location.href='<c:url value="/dashboard.html"/>'"></span></div>
		</fieldset>
	</p>

</div>

<div class="clear"></div>

</body>
