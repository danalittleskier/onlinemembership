<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>USSA Membership Registration</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<!-- USSA Scripts -->
<script src="/scripts/_sfHover.js" type="text/javascript"></script>
<script src="/scripts/_quickLinks.js" type="text/javascript"></script>
<script src="/scripts/_hscroller.js"></script>
<!-- USSA Styles -->
<link href="/styles/data/favicon.ico" rel="shortcut icon"/>
<link href="${ctx}/styles/ussa/_headers.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/styles/ussa/_globals.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/styles/ussa/_layout.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/styles/ussa/_menu.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/ussa/ussa_home.css">
<!-- STG Styles - MUST FOLLOW USSA STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/styles/ussa/main.css">
</head>

<body>
<div id="header">
<!------------------------------------------- Advertisment ----------------------------------------------->
<div class="page_advertise_wrapper">
	<script language="Javascript1.1" src="http://ad.doubleclick.net/adj/a.site102.tmus;tile=1;dcopt=ist;abr=!webtv;sz=720x90;ord=' + ord + '?"></script>
		<script>
			<!--
			if ((!document.images && navigator.userAgent.indexOf('Mozilla/2.') >= 0) || navigator.userAgent.indexOf("WebTV") >= 0) {
				document.write('<a href="http://ad.doubleclick.net/jump/a.site102.tmus;tile=1;sz=720x90;ord=' + ord + '?" target="_blank">');
				document.write('<img src="http://ad.doubleclick.net/ad/a.site102.tmus;tile=1;sz=720x90;ord=' + ord + '?" width="720" height="90" border="0" alt=""></a>');
			}
			//-->
		</script>
		<noscript>
			<a href="http://ad.doubleclick.net/jump/a.site102.tmus;tile=1;sz=720x90;ord=123456789?" target="_blank">
			<img src="http://ad.doubleclick.net/ad/a.site102.tmus;tile=1;sz=720x90;ord=123456789?" width="720" height="90" border="0" alt=""></a>
		</noscript>
        <img src="/images/ussa-base/TV_TuneIn.jpg" border="0" ismap usemap="#topAdMap">
</div>
<map name="topAdMap">
	<area shape="rect" coords="64,34,104,71" href="http://www.usskiteam.com/public/tv.php?&dId=6" target="_blank">
	<area shape="rect" coords="115,34,172,69" href="http://www.ussnowboard.com/public/tv.php?&dId=7" target="_blank"/>
</map>

<!------------------------------------------- Page Header ----------------------------------------------->
<div class=page_header_wrapper>
	<div class=img><a href="http://www.ussa.org/magnoliaPublic/ussa/en.html"><img src="/images/ussa-base/ussa.png" style="border:none"></a></div>
	<div class=title>
		<h1>United States Ski and Snowboard Association</h1>
		An Olympic sports organization dedicated to fielding the <br> best
		skiing and snowboarding teams in the world
	</div>
	<div class=menu>
		<div class=links>
			<a href="http://www.usskiteam.com">US Ski Team »</a>&nbsp;&nbsp;
			<a href="http://www.ussnowboarding.com">US Snowboarding »</a>&nbsp;&nbsp;
			<a href="http://shop.usskiteam.com">Shop »</a>&nbsp;&nbsp;
		</div>
		<div class=search>
		<!-- Search My Google Mini -->
		<form method="get" action="http://204.117.0.114/search" name="googleMini">
			<table>
				<tr>
					<td>
						<input type="textbox" name="q" size="25" maxlength="255" value="Search" onfocus="javascript:this.select();"/>
						<!--<input type="submit" name="btnG" value="USSA Search"/>-->
						<a><img src="/images/ussa-base/magnifying_glass.png" onclick="javascript:document.googleMini.submit();" border="0"></a>
						<input type="hidden" name="site" value="USSA"/>
						<input type="hidden" name="client" value="ussa"/>
						<input type="hidden" name="proxystylesheet" value="ussa"/>
						<input type="hidden" name="output" value="xml_no_dtd"/>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</div>
</div>
<!------------------------------------------- Navigation ----------------------------------------------->
<div class=topmenu_wrapper>
	<div class=topmenu>
		<div class=left>
			<ul id="nav">
				<li><a href="http://www.ussa.org" title="USSA home" accesskey="1">
				USSA home</a>
				<li><a href="#" title="contact us" accesskey="2">contact us</a></li>
			</ul>
		</div>
	</div>
</div>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="content">

	<!-- Progress bar -->
	<div id="progress">Progress Bar</div>

	<!-- LEFT column -->
	<div id="twocol-primary">
		<p><form:form commandName="member" name="member">
   <table class="formtable">
	<tbody>
		<spring:bind path="member.*">
			<c:if test="${not empty status.errorMessages}">
				<c:set var="sectionHasFormErrors" scope="request" value="true"/>
				<tr>
					<td colspan="3" class="formerrors">
						<c:forEach var="errorMsg" items="${status.errorMessages}">
							<c:out value="${errorMsg}" escapeXml="false" />
						</c:forEach>
					</td>
				</tr>
			</c:if>
		</spring:bind>

	<table border="0" width="100%" id="table1">
		<tr>
			<td width="189"><b><font size="2">PERSONAL INFORMATION</font></b></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189"><font size="2">USSA Member</font></td>
			<td><form:input path="member.id" size="30" maxlength="30"/></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* First Name</font></td>
			<td><form:input path="member.firstName" size="30" maxlength="30"/></td>
		</tr>
		<tr>
			<td width="189"><font size="2">MI</font></td>
			<td><input type="text" name="T3" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* Last Name</font></td>
			<td><form:input path="member.lastName" size="30" maxlength="30"/></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Suffix</font></td>
			<td><input type="text" name="T5" size="20"></td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189"><font size="2"><b>ADDRESS &amp; PHONE</b></font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189"><font size="2">Company</font></td>
			<td><input type="text" name="T6" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* Address 1</font></td>
			<td><input type="text" name="T7" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Address 2</font></td>
			<td><input type="text" name="T8" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* City</font></td>
			<td><input type="text" name="T9" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* State</font></td>
			<td><input type="text" name="T10" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* Country</font></td>
			<td><input type="text" name="T11" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* Zip / Postal Code</font></td>
			<td><input type="text" name="T12" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">* Home Phone</font></td>
			<td><input type="text" name="T13" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Other Phone</font></td>
			<td><input type="text" name="T14" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Fax Phone</font></td>
			<td><input type="text" name="T15" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Gender</font></td>
			<td><input type="text" name="T16" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Ethnicity</font></td>
			<td><select size="1" name="D2">
			<option>White</option>
			<option>Black</option>
			<option>Hispanic or Latiino</option>
			<option>Asian</option>
			<option>American Indian or Alaska Native</option>
			<option>Native Hawaiian or Other Pacific Islander</option>
			<option>Mixed Race</option>
			<option>Prefer not to respond</option>
			</select></td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189"><font size="2"><b>If Under 18 (DISPLAY)</b></font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189"><font size="2">Parent / Guardian 1</font></td>
			<td><input type="text" name="T17" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">First Name</font></td>
			<td><input type="text" name="T18" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Last Name</font></td>
			<td><input type="text" name="T19" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Relationship</font></td>
			<td><select size="1" name="D1">
			<option>Father</option>
			<option>Mother</option>
			<option>Guardian</option>
			</select></td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="189"><font size="2">Parent / Guardian 1</font></td>
			<td><input type="text" name="T20" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">First Name</font></td>
			<td><input type="text" name="T21" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Last Name</font></td>
			<td><input type="text" name="T22" size="20"></td>
		</tr>
		<tr>
			<td width="189"><font size="2">Relationship</font></td>
			<td><select size="1" name="D3">
			<option>Father</option>
			<option>Mother</option>
			<option>Guardian</option>
			</select></td>
		</tr>
		<tr>
			<td width="189">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		</table></p>
	</div>

	<!-- RIGHT column -->
	<div id="twocol-secondary">
		<p>Secondary Content (RIGHT)</p>
		<!-- BOX (START) -->
		<div class="bl"><div class="br"><div class="tl"><div class="tr"><div class="border-left"></div>
			<table border="0" width="100%" id="table2">
		<tr>
			<td>
			<p align="center"><b><font size="2">Online Membership Registration</font></b></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<ul>
				<li><font size="1">Memberships, FIS registrations and IPC
				registrations are non-refundable</font></li>
			</ul>
			</td>
		</tr>
		<tr>
			<td>
			<ul>
				<li><font size="1">Memberships expire annually on June 30.</font></li>
			</ul>
			</td>
		</tr>
		<tr>
			<td>
			<ul>
				<li><font size="1">Registration must be submitted only by those
				18 or older.</font></li>
			</ul>
			</td>
		</tr>
		<tr>
			<td>
			<ul>
				<li><font size="1">Code of Conduct:</font></li>
			</ul>
			<p><font size="1">I understand</font></td>
		</tr>
		<tr>
			<td height="22">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			&nbsp;</td>
		</tr>
	</table>
		</div></div></div></div>
		<!-- BOX (END) -->
	</div>

</div>

<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    		<input type="hidden" name="_eventId" value="submit">
    		<p align="center">
    		<input type="submit" value="next" />&nbsp;
    		</form:form>
    		<hr>
<!--------------------------------------------- Footer ------------------------------------------------->
</p>
<div class=bottom_wrapper>
	<script language="Javascript1.1" src="http://ad.doubleclick.net/adj/a.site102.tmus;tile=2;dcopt=ist;abr=!webtv;sz=720x90;ord=' + ord + '?"></script>
	<script>
		<!--
		if ((!document.images && navigator.userAgent.indexOf('Mozilla/2.') >= 0) || navigator.userAgent.indexOf("WebTV") >= 0) {
		document.write('<a href="http://ad.doubleclick.net/jump/a.site102.tmus;tile=2;sz=720x90;ord=' + ord + '?" target="_blank">');
		document.write('<img src="http://ad.doubleclick.net/ad/a.site102.tmus;tile=2;sz=720x90;ord=' + ord + '?" width="720" height="90" border="0" alt=""></a>');
		}
		//-->
	</script>
	<noscript>
		<a href="http://ad.doubleclick.net/jump/a.site102.tmus;tile=2;sz=720x90;ord=123456789?" target="_blank">
		<img src="http://ad.doubleclick.net/ad/a.site102.tmus;tile=2;sz=720x90;ord=123456789?" width="720" height="90" border="0" alt=""></a>
	</noscript>
	<img src="/images/ussa-base/TV_TuneIn.jpg" ismap usemap="#bottomAdMap" border="0">

	<div class=bottom_menu>
		<a href="http://www.ussa.org/magnoliaPublic/ussa/en/contact.html">
		contact us</a>
		<a href="http://www.ussa.org/magnoliaPublic/ussa/en/about/jobs.html">
		jobs</a>
		<a href="http://www.ussa.org/magnoliaPublic/ussa/en/about/media.html">
		press/media center</a>
		| © 2007 USSA - All rights reserved.
		<a href="http://www.ussa.org/magnoliaPublic/other/globalfooter/termsofuse.html">
		terms of use</a>
		<a href="http://www.ussa.org/magnoliaPublic/other/globalfooter/privacypolicy.html">
		privacy policy</a>
		<a href="http://www.olyparks.com" target="_blank">utah olympic parks</a>
	</div>

    <div class=dotted_separator></div>
    <img src="/images/ussa-base/combined_logos_color.gif" alt="" height="37" ismap usemap="#teamMap" border="0">
    <!-- Image Maps -->
    <map name="bottomAdMap">
        <area shape="rect" coords="64,34,104,71" href="http://www.usskiteam.com/public/tv.php?&dId=6" target="_blank">
        <area shape="rect" coords="115,34,172,69" href="http://www.ussnowboard.com/public/tv.php?&dId=7" target="_blank">
    </map>
    <map name="teamMap">
        <area shape="rect" coords="1,1,40,35" href="http://www.usskiteam.com" target="_blank"/>
        <area shape="rect" coords="51,1,105,30" href="http://www.ussnowboarding.com" target="_blank"/>
    </map>
</div>

</body>

</html>
