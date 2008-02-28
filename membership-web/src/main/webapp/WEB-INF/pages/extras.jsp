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
<link rel="stylesheet" type="text/css" href="main.css">
<link rel="stylesheet" type="text/css" href="stg-forms.css">
</head>

<body>
<div id="stg-header">
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
        <img src="${ctx}/images/ussa/TV_TuneIn.jpg" border="0" ismap usemap="#topAdMap">
</div>
<map name="topAdMap">
	<area shape="rect" coords="64,34,104,71" href="http://www.usskiteam.com/public/tv.php?&dId=6" target="_blank">
	<area shape="rect" coords="115,34,172,69" href="http://www.ussnowboard.com/public/tv.php?&dId=7" target="_blank"/>
</map>

<!------------------------------------------- Page Header ----------------------------------------------->
<div class=page_header_wrapper>
	<div class=img><a href="http://www.ussa.org/magnoliaPublic/ussa/en.html"><img src="${ctx}/images/ussa/ussa.png" style="border:none"></a></div>
	<div class=title>
		<h1>United States Ski and Snowboard Association</h1>
		An Olympic sports organization dedicated to fielding the <br> best skiing and snowboarding teams in the world
	</div>
	<div class=menu>
		<div class=links>
			<a href="http://www.usskiteam.com">US Ski Team &raquo;</a>&nbsp;&nbsp;
			<a href="http://www.ussnowboarding.com">US Snowboarding &raquo;</a>&nbsp;&nbsp;
			<a href="http://shop.usskiteam.com">Shop &raquo;</a>&nbsp;&nbsp;
		</div>
		<div class=search>
		<!-- Search My Google Mini -->
		<form method="get" action="http://204.117.0.114/search" name="googleMini">
			<table>
				<tr>
					<td>
						<input type="textbox" name="q" size="25" maxlength="255" value="Search" onfocus="javascript:this.select();"/>
						<!--<input type="submit" name="btnG" value="USSA Search"/>-->
						<a><img src="${ctx}/images/ussa/magnifying_glass.png" onclick="javascript:document.googleMini.submit();" border="0"></a>
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
				<li><a href="http://www.ussa.org" title="USSA home" accesskey="1">USSA home</a>
				<li><a href="#" title="contact us" accesskey="2">contact us</a></li>
			</ul>
		</div>
	</div>
</div>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->

  <div id="stg-progress"><img src="${ctx}/images/ussa/progress_1.gif" width="917" height="53" /></div>
  <div id="stg-pagetitle">Member Information</div>

	<!-- LEFT column -->
	<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<spring:bind path="accountBean.*">
			<c:if test="${not empty status.errorMessages}">
				<c:set var="sectionHasFormErrors" scope="request" value="true"/>
						<c:forEach var="errorMsg" items="${status.errorMessages}">
							<c:out value="${errorMsg}" escapeXml="false" />
						</c:forEach>
			</c:if>
		</spring:bind>

<fieldset>
				<legend>Membership  Information</legend>

	<table border="0" width="100%" id="table1">
		<tr>
			<td width="180"><font size="2">Magazine Subscription</font></td>
			<td><select size="1" name="D1">
			<option value="MAGSB">Snowboard Magazine</option>
			<option value="MRCE">Ski Racing Magazine</option>
			<option value="MRLTD">Ski Racing Magazine-Limited Issues</option>
			<option value="MTRX">Ski Trax Magazine</option>
			<option value="NONE">No Magazine</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Contribution</font></td>
			<td><input type="text" name="T1" size="20" value="$0.00"></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><b><font size="2">Shirts ( $20 each )</font></b></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><font size="2">Alpine </font><i><font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			view image</font></i></td>
			<td><select size="1" name="D3">
			<option selected value="S">Small</option>
			<option value="M">Medium</option>
			<option value="L">Large</option>
			<option value="X">X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Freestyle </font><i><font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			view image</font></i></td>
			<td><select size="1" name="D4">
			<option value="S">Small</option>
			<option value="M">Medium</option>
			<option value="L">Large</option>
			<option value="X">X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Cross Country </font><i>
			<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; view image</font></i></td>
			<td><select size="1" name="D5">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Jumping / NC </font><i>
			<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			view image</font></i></td>
			<td><select size="1" name="D6">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Snowboarding </font><i>
			<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; view
			image</font></i></td>
			<td><select size="1" name="D7">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">U.S. Ski Team General</font></td>
			<td><select size="1" name="D8">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><font size="2"><b>Decals (pack of 5 for $5)</b></font></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><font size="2">U.S. Snowboarding</font></td>
			<td><select size="1" name="D9">
			<option>5</option>
			<option>12</option>
			<option>20</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">U.S. Ski Team</font></td>
			<td><select size="1" name="D10">
			<option>5</option>
			<option>12</option>
			<option>20</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		</table>

</fieldset>

 <fieldset class="buttons">
				<label></label>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
             <input type="submit" class="button" name="_eventId_back" value="<fmt:message key='button.back'/>">
             <input type="submit" class="button" name="_eventId_next" value="<fmt:message key='button.next'/>">

				</fieldset>

</form:form>
	</div>

	<!-- RIGHT column -->
	<div id="stg-twocol-secondary">
		<!-- BOX (START) -->
		<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
			<p class="stg-omr-header">Membership Cart</p>
			    	* Indicate required field<p>&nbsp;</div>

  <display:table name="accountBean.shoppingCart" requestURI="" sort="list" defaultsort="1">
    <display:caption>Shopping Cart</display:caption>
    <display:column property="description" title="Description" sortable="false" />
    <display:column property="amount" title="Amount" sortable="false" />
  </display:table>
  <table border="0">
  <tr><td colspan="2" align="right"><i>total</i> $${accountBean.total }</td></tr>
  </table>
		</div></div></div></div>
		<!-- BOX (END) -->
	</div>
	<div class="clear"></div>
</div>



	<!--------------------------------------------- Footer ------------------------------------------------->
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
	<img src="${ctx}/images/ussa/TV_TuneIn.jpg" ismap usemap="#bottomAdMap" border="0">

	<div class=bottom_menu>
		<a href="http://www.ussa.org/magnoliaPublic/ussa/en/contact.html">contact us</a>
		<a href="http://www.ussa.org/magnoliaPublic/ussa/en/about/jobs.html">jobs</a>
		<a href="http://www.ussa.org/magnoliaPublic/ussa/en/about/media.html">press/media center</a>
		| &copy 2007 USSA - All rights reserved.
		<a href="http://www.ussa.org/magnoliaPublic/other/globalfooter/termsofuse.html">terms of use</a>
		<a href="http://www.ussa.org/magnoliaPublic/other/globalfooter/privacypolicy.html">privacy policy</a>
		<a href="http://www.olyparks.com" target="_blank">utah olympic parks</a>
	</div>

    <div class=dotted_separator></div>
    <img src="${ctx}/images/ussa/combined_logos_color.gif" alt="" height="37" ismap usemap="#teamMap" border="0">
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

