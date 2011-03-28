<%@ include file="/includes/taglibs.jsp" %>

<head>
	<title>Data Access Error</title>
    <meta name="heading" content="Data Access Failure"/>
    <meta name="menu" content="AdminMenu"/>
</head>

<body>
<p>
    <c:out value="${requestScope.exception.message}"/>
</p>

<!--
<%
Exception ex = (Exception) request.getAttribute("exception");
ex.printStackTrace(new java.io.PrintWriter(out));
%>
-->

</body>

<a href="mainMenu.html" onclick="history.back();return false">&#171; Back</a>
