<%@ include file="/common/taglibs.jsp"%>

<page:applyDecorator name="default">

	<head>
		<title>
			<fmt:message key="404.title"/>
		</title>
		<meta name="heading" content="<fmt:message key='404.title'/>"/>
	</head>

	<body>
	<div id="stg-pagetitle">
		<fmt:message key='404.title'/>
	</div>
	<div id="stg-onecol-primary">
		<p>
			<fmt:message key="404.message">
				<fmt:param>
					<c:url value="/"/>
				</fmt:param>
			</fmt:message>
		</p>
	</div>
	</body>
</page:applyDecorator>