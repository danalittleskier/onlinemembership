<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<%@ include file="/common/meta.jsp" %>

	<title><decorator:title default="USSA Membership Registration"/></title>

	<%@ include file="/includes/scriptsAndStyles.jsp" %>

	<decorator:head/>
</head>

<body>
	<%@ include file="/includes/ussaHeaderAndNavigation.jsp" %>

	<!-------------------------------------------- Content ------------------------------------------------->
	<!-- Container -->
	<div id="stg-content">
		<decorator:body/>
	</div>

	<%@ include file="/includes/ussaFooter.jsp" %>
</body>
</html>