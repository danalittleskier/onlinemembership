<%@ include file="/includes/taglibs.jsp" %>

<div id="stg-header">
	<!------------------------------------------- Advertisment ----------------------------------------------->
	<div class="page_advertise_wrapper">
		<a href="http://www.backcountry.com/?avad=4480_2574_ml_0" target="_blank"><img src="<c:url value='/images/ussa/ad1.gif'/>" border="none"></a>
		<img src="<c:url value='/images/ussa/TV_TuneIn.jpg'/>" border="0" ismap usemap="#topAdMap">
	</div>
	<map name="topAdMap">
		<area shape="rect" coords="64,34,104,71" href="http://www.usskiteam.com/public/tv.php?&dId=6" target="_blank">
		<area shape="rect" coords="115,34,172,69" href="http://www.ussnowboard.com/public/tv.php?&dId=7" target="_blank"/>
	</map>

	<!------------------------------------------- Page Header ----------------------------------------------->
	<div class=page_header_wrapper>
		<div class=img><a href="http://www.ussa.org/magnoliaPublic/ussa/en.html"><img src="<c:url value='/images/ussa/ussa.png'/>" style="border:none"></a></div>
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
								<input type="text" name="q" size="25" maxlength="255" value="Search" onfocus="this.select();"/>
								<!--<input type="submit" name="btnG" value="USSA Search"/>-->
								<a><img src="<c:url value='/images/ussa/magnifying_glass.png'/>" onclick="document.googleMini.submit();" border="0"></a>
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
				<li style="float:left;"><a href="http://www.ussa.org" title="USSA home" accesskey="1">USSA home</a></li>
				<li style="float:left;"><a href="<c:url value="/faq.html"/>" title="Registration FAQ" target="_BLANK">registration FAQ</a></li>
				<li style="float:left;"><a href="/accounts/j_acegi_logout" title="Log out">logout</a></li>
			</ul>
		</div>
	</div>
</div>
