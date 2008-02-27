<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
</head>

<p><fmt:message key="mainMenu.message"/></p>

    <br/><a href="${ctx}/contact.html?id=158">Georgee</a><br/>
    <a href="${ctx}/contact.html?id=183">Gloria</a><br/>
    <a href="${ctx}/contact.html">Blank</a><br/>
<br/><a href="${ctx}/registration.html?id=158">Registration: Georgee</a>
<br/><a href="${ctx}/registration.html?id=1323781">Registration: Harris</a>
<br/><a href="${ctx}/registration.html">Registration: New Member</a>

<div class="separator"></div>

<ul class="glassList">
    <li>
        <a href="<c:url value='/userform.html'/>"><fmt:message key="menu.user"/></a>
    </li>
    <li>
        <a href="<c:url value='/fileupload.html'/>"><fmt:message key="menu.selectFile"/></a>
    </li>
</ul>
