<%@ include file="/includes/taglibs.jsp" %>

<script type="text/javascript">
	var myAlert = function() {
		alert("yipee");
	};
</script>

<%-- Calendar Scripts and CSS --%>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-en.js'/>"></script>
<link type="text/css" href="<c:url value='/scripts/calendar/calendar-blue.css'/>" rel="stylesheet"/>

<%-- USSA Scripts --%>
<script type="text/javascript" src="<c:url value='/scripts/ussa/_sfHover.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/ussa/_quickLinks.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/ussa/_hscroller.js'/>"></script>

<%-- USSA Styles --%>
<link href="<c:url value='/images/ussa/favicon.ico'/>" rel="shortcut icon"/>
<link type="text/css" href="<c:url value='/styles/ussa/_headers.css'/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value='/styles/ussa/_globals.css'/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value='/styles/ussa/_layout.css'/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value='/styles/ussa/_menu.css'/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value='/styles/ussa/ussa_home.css'/>" rel="stylesheet"/>

<%-- Project Styles - MUST FOLLOW USSA STYLES --%>
<link type="text/css" href="<c:url value='/styles/main.css'/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value='/styles/newmain.css'/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value='/styles/stg-forms.css'/>" rel="stylesheet"/>

<%-- JQuery --%>
<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-1.2.3.min.js'/>"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict(); // JQuery conflicts with rich faces faces:jquery if noConflict is not called.
</script>

<%-- Project Scripts --%>
<script type="text/javascript" src="<c:url value='/scripts/common.js'/>"></script>

<%-- Greybox --%>
<script type="text/javascript">
	var GB_ROOT_DIR = "<c:url value='/scripts/greybox/'/>";
</script>
<script type="text/javascript" src="<c:url value='/scripts/greybox/AJS.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/greybox/AJS_fx.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/greybox/gb_scripts.js'/>"></script>
<link type="text/css" href="<c:url value='/scripts/greybox/gb_styles.css'/>" rel="stylesheet"/>
