<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/includes/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<%@ include file="/includes/meta.jsp" %>

	<title><decorator:title default="USSA Membership Registration"/></title>

	<%@ include file="/includes/scriptsAndStyles.jsp" %>

	<decorator:head/>
	<!--Start: Support Files for Live Help-->
<script language="javascript" type="text/javascript" src="http://im.ussa.org:9090/webchat/js/tooltips/domLib.js"></script>
<script language="javascript" type="text/javascript" src="http://im.ussa.org:9090/webchat/js/tooltips/domTT.js"></script>
<script language="JavaScript" type="text/javascript" src="http://im.ussa.org:9090/webchat/common.js"></script>
<script language="JavaScript" type="text/javascript" src="http://im.ussa.org:9090/webchat/jivelive.jsp"></script>
<!--End: Support Files for Live Help-->
</head>

<body>
	
	<!-------------------------------------------- Content ------------------------------------------------->
	<!-- Container -->
	<div class="scroll-membership">
		<div id="stg-content">
			<decorator:body/>
		</div>
	</div>

	
</body>
</html>
