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
				<legend>Personal Information</legend>


			<c:if test="${!empty accountBean.member.id }">
			<label for="">USSA Member #</label>
				<c:out value="${accountBean.member.id}" /><br />
	  			 <!-- <tr>
	      		<td class="label" colspan="1">USSA Member #:</td>
	    		  <td colspan="2">
	      		 <form:input path="member.id" size="30" maxlength="30"/>
	      		</td>
	    		</tr>    -->
		</c:if>
<br />
				<label for="">First Name</label>
				<form:input path="member.firstName" size="30" maxlength="30"/><br />
				<label for="">MI</label>
				<input name="" type="text" class="text" /><br />
				<label for="">Last Name</label>
				<form:input path="member.lastName" size="30" maxlength="30"/><br />
				<label for="">Suffix</label>
				<input name="" type="text" class="text" /><br />
			</fieldset>
			<fieldset>
				<legend>Address & Phone</legend>
				<label for="">Company</label>
				<form:input path="address.company" size="30" maxlength="30"/><br />
				<label for="">Address 1</label>
				<form:input path="address.deliveryAddress" size="30" maxlength="30"/><br />
				<label for="">Address 2</label>
				<input name="" type="text" class="text" /><br />
				<label for="">City</label>
				<form:input path="address.city" size="30" maxlength="30"/><br />
				<label for="">State</label>

			<spring:bind path="accountBean.address.stateCode">
	    	<select name="address.stateCode">
	          <option value=""></option>
	          <c:forEach var="o" items="${accountBean.usStates}">
	            <option value="<c:out value='${o.id}'/>"
	              <c:if test="${o.id == status.value}">selected</c:if>><c:out value='${o.id}'/></option>
	          </c:forEach>
	        </select>
	      </spring:bind><br />

				<label for="">Zip</label>
				<form:input path="address.postalCode" size="30" maxlength="30"/><br />
				<label for="">Country</label>
				<input name="" type="text" class="text" /><br />
				<label for="">Home Phone</label>
				<form:input path="address.phoneHome" size="30" maxlength="30"/><br />
				<label for="">Other Phone</label>
				<input name="" type="text" class="text" /><br />
				<label for="">Fax</label>
				<input name="" type="text" class="text" /><br />
				</fieldset>

				<c:if test="${empty accountBean.member.id }">
				<fieldset>
				<legend>Other Information</legend>
				<label for="">Gender</label>
				<form:input path="member.gender" size="30" maxlength="30"/><br />
				<label for="">Etnicity</label>
				<form:input path="member.etnicity" size="30" maxlength="30"/><br />
				</fieldset>
				</c:if>

				<c:if test="${accountBean.member.age < 18 }">
				<fieldset>
				<legend>Parent Information</legend>
				<label for="">Parent / Guardian 1</label>
				<input name="" type="text" class="text" /><br /><br />
				<label for="">Parent / Guardian 2</label>
				<input name="" type="text" class="text" /><br /><br /><br />
				</fieldset>
				</c:if>


			<fieldset class="buttons">
				<label></label>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
             <input type="submit" class="button" name="_eventId_next" value="<fmt:message key='button.next'/>">
			</fieldset>





		</form:form>
	</div>

	<!-- RIGHT column -->
	<div id="stg-twocol-secondary">
		<!-- BOX (START) -->
		<div class="stg-bl"><div class="stg-br"><div class="stg-tl"><div class="stg-tr"><div></div>
			<p class="stg-omr-header">Online Membership Registration</p>
			<ul id="stg-omr">
				<li>Memberships, FIS registrations and IPC registrations are non-refundable</li>
				<li>Memberships expire annually on June 30</li>
				<li>Registration must be submitted only by those 18 &amp; older</li>
				<li>Code of Conduct:<br />
				I understand that by virtue of my membership in USSA, I must comply with USSA's Code of Conduct. I also understand that I may be required to participate in competition drug testing. By executing this form I agree to abide by and/or participate in such programs. I understand that failure to participate in competition drug testing will result in a sanction.</li>

              <li><strong>Beginning April 1 all registrations completed online
                will apply to the next competition season. If you wish to register
                for the current competition season from April 1 through June 30,
                you may do so by mail or fax. Please contact USSA Member Services
                for details at <a href="mailto:membership@ussa.org">membership@ussa.org</a>
                or by phone at 435.647.2666.</strong></li>
				<li>In order to complete renewal or registration, your primary medical insurance information will be required or you will be required to execute the Primary Medical Exception Agrement provided. You wil also be required to execute a release of liability.</li>
				<li>A $25 late fee will be added to your total if renewing after Oct. 15 for USSA membership categories other than youth competitors, first time members and officials. your division and/or state may assess late fees in addition to those assessed by USSA.</li>
				<li>If you are not a U.S.Citizen and wish to change your membership category you must renew by mail or fax.</li>
				<li>If you wish to renew an associate membership you must do so by mail or fax.</li>
			</ul>
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

