<%@ page language="java" isErrorPage="true" %>
<%@ include file="/includes/taglibs.jsp" %>

<page:applyDecorator name="default">
	<head>
		<title>
			<fmt:message key="errorPage.title"/>
		</title>
	</head>

	<body>
	<div id="stg-pagetitle">
		<fmt:message key='errorPage.title'/>
	</div>
	<div id="stg-onecol-primary">
		<h1>
			<fmt:message key="errorPage.heading"/>
		</h1>
		<% if (exception != null)
		{ %>
		<pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
		<% }
		else if (request.getAttribute("javax.servlet.error.exception") != null)
		{ %>
			<pre><% ((Exception) request.getAttribute("javax.servlet.error.exception")).printStackTrace(new java.io.PrintWriter(out)); %></pre>
		<% } %>
	</div>
	</body>
</page:applyDecorator>
