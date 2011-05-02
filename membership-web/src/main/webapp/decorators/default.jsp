<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/includes/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<%@ include file="/includes/meta.jsp" %>

	<title><decorator:title default="USSA Membership Registration"/></title>

	<%@ include file="/includes/scriptsAndStyles.jsp" %>

	<decorator:head/>

</head>

<body>
	
	<!-------------------------------------------- Content ------------------------------------------------->
	<!-- Container -->
	<div class="scroll-membership">
		<div id="stg-content">
			<decorator:body/>
		</div>
	</div>

	<script>
		parent.$.colorbox.resize({innerWidth:"975", innerHeight:"640", transition:"elastic", speed:100});
	</script>
</body>
</body>
</html>
