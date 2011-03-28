<%@ include file="/includes/taglibs.jsp" %>

<page:applyDecorator name="default">

	<head>
		<title>
			<fmt:message key="403.title"/>
		</title>
		<meta name="heading" content="<fmt:message key='403.title'/>"/>
	</head>

	<body>
	<div id="stg-pagetitle">
		<fmt:message key='403.title'/>
	</div>
	<div id="stg-onecol-primary">
		<p>
			<fmt:message key="403.message">
				<fmt:param>
					<c:url value="/"/>
				</fmt:param>
			</fmt:message>
		</p>
	</div>
	</body>
</page:applyDecorator>