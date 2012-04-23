<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/includes/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<%@ include file="/includes/meta.jsp" %>

	<title><decorator:title default="USSA Membership Registration"/></title>

	<%@ include file="/includes/scriptsAndStyles.jsp" %>

	<decorator:head/>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-27032045-1']);
  _gaq.push(['_setDomainName', 'ussa.org']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script>
function ShowFamilyMembershipsTableShow(){
	jQuery("#GlobalRescueFamilyMembership").show();
}
function ShowFamilyMembershipsTableHide(){
	jQuery("#GlobalRescueFamilyMembership").hide();
}
</script>
<script>
			$(document).ready(function(){
				$("#CoachInfo").colorbox({width:"75%", height:"75%", scrolling:true, inline:true, open:true, overlayClose:false, escKey:false, href:"#CoachInfoText"});
			});
	</script>
</head>

<body>
	<!--Start: page-wrapper --><div id="page-wrapper">

        <!--Start: page --><div id="page">
        
                  <!--Start: Socialbar --><div id="Socialbar">
                   <ul>
                      <li class="last"><img src="./styles/images/logos/ussa_sub-logo.png" width="101" height="29" border="0"></li>
                      <li><a href="http://my.ussa.org" target="_blank"><img src="./styles/images/social-icons/my_ussa_blue.png" width="29" height="28"></a></li>
                      <li><a href="http://ussa.org/rss" target="_blank"><img src="./styles/images/social-icons/rss_icon.png" width="29" height="28"></a></li>
                      <li><a href="http://ussa.org/rss" target="_blank"><img src="./styles/images/social-icons/tv_icon.png" width="31" height="29"></a></li>
                      <li><a href="http://twitter.com/usskiteam" target="_blank"><img src="./styles/images/social-icons/twitter_icon.png" width="29" height="28"></a></li>
                      <li><a href="http://www.facebook.com/pages/USSA-Membership/245655722126152" target="_blank"><img src="./styles/images/social-icons/facebook_icon.png" width="29" height="29"></a></li>
                   </ul>
                  <!--End: Socialbar --></div>
        
                  <!--Start: Logo --><div id="MainLogoUSSA"> <a href="http://ussa.org" target="_blank"><img src="./styles/images/logos/ussa_logo.png" width="207" height="80"></a>
                  <!--End: Logo --></div>
        
                  <!--Start: Navbar --><div id="Navbar">
                      <ul>
                          <li class="first"><a href="http://ussa.org/about" target="_blank">About</a></li>
                          <li><a href="http://usskiteam.com/alpine" target="_blank">Alpine</a></li>
                          <li><a href="http://usskiteam.com/freestyle" target="_blank">Freestyle</a></li>
                          <li><a href="http://usskiteam.com/nordic" target="_blank">Nordic</a></li>
                          <li><a href="http://usfreeskiing.com" target="_blank">Freeskiing</a></li>
                          <li><a href="http://ussnowboarding.com" target="_blank">Snowboarding</a></li>
                          <li><a href="http://ussa.org/foundation" target="_blank">Foundation</a></li>
                          <li class="last"><a href="http://shop.usskiteam.com" target="_blank">Shop</a></li>
                      </ul>
                  <!--End: Navbar --></div>
  <div style="position:absolute;top:130px;left:20px;"><a href="http://my.ussa.org" target="_blank">Return to My USSA</a></div>
  <center><h1 style="font-size: 2em; line-height: 1.5em;color:#333333;">Online Membership Registration</h1></center>
                  <!--Start: main-wrapper -->
<table cellpadding="0" cellspacing="0" border="0" style="width:970px;overflow:hidden;">
                    	<tr><td>
                        	<!--Start: Content -->
	<!-------------------------------------------- Content ------------------------------------------------->
	<!-- Container -->
	<div class="scroll-membership">
		<div id="stg-content">
			<decorator:body/>
		</div>
	</div>

	<!--  <script>
		parent.$.colorbox.resize({innerWidth:"975", innerHeight:"640"});
	</script> -->
	

 <!--End: Content -->
                        </td></tr>
                    </table>
                  <!--End: main-wrapper -->
        			
                  <!--Start: footer-social-bar --><div id="footer-social-bar">
                      <table cellspacing="0" cellpadding="0" border="0">
                          <tbody><tr>
                              <td width="250">&nbsp;&nbsp;&nbsp;&nbsp;In partnership with</td>
                              <td>Proud sponsors</td>
                              <td width="28" align="right"><a href="http://ussa.org/ussa/ussa-partners-suppliers" target="_blank"><img width="23" height="22" alt="" src="./styles/images/core/footer/snowconnect_icon.png"></a></td>
                              <td width="28" align="right"><a target="_blank" href="http://www.facebook.com/pages/USSA-Membership/245655722126152"><img width="23" height="22" alt="" src="./styles/images/core/footer/facebook_icon.png"></a></td>
                              <td width="28" align="right"><a target="_blank" href="http://twitter.com/usskiteam"><img width="22" height="22" alt="" src="./styles/images/core/footer/twitter_icon.png"></a></td>
                              <td width="28" align="right"><a href="http://ussa.org/rss" target="_blank"><img width="22" height="22" alt="" src="./styles/images/core/footer/rss_icon.png"></a></td>
                              <td width="28" align="right"><a href="http://ussa.org/tv" target="_blank"><img width="22" height="22" alt="" src="./styles/images/core/footer/tv_icon.png"></a></td>
                          </tr>
                      </tbody></table>
                  <!--End: footer-social-bar --></div>
                  
                  
                  
                  <!--Start: Ussa footer -->
                    <div style="clear:both;"></div>
                  	<div id="footer-separator-bar">&nbsp;</div>
                    <div id="footer-text-bar">
                    				<ul class="menu"><li class="first leaf"><a title="" href="http://ussa.org" target="_blank">Home</a></li>
                                        <li class="leaf"><a title="" href="http://ussa.org/contact" target="_blank">Contact Us</a></li>
                                        <li class="leaf"><a title="" href="http://ussa.org/about" target="_blank">About USSA</a></li>
                                        <li class="leaf"><a title="" href="http://ussa.org/media-center" target="_blank">Media Center</a></li>
                                        <li class="leaf"><a title="" href="http://my.ussa.org/myussa" target="_blank">Member Service</a></li>
                                        <li class="leaf"><a title="" href="http://foundation.ussa.org/foundation" target="_blank">Donate</a></li>
                                        <li class="leaf"><a title="" href="http://shop.usskiteam.com" target="_blank">Shop</a></li>
                                        <li class="leaf"><a title="" href="http://ussa.org/ussa/ussa-partners-suppliers" target="_blank">Partners</a></li>
                                        <li class="leaf"><a title="" href="http://ussa.org/terms-of-use" target="_blank">Terms of Use</a></li>
                                        <li class="last leaf"><a title="" href="http://ussa.org/copyright" target="_blank">Copyright</a></li>
                                    </ul>
                    </div>
                  <!--End: Ussa footer -->
                  <div style="clear:both;"></div><br><br><br>
                  
                  
        <!--End: page --></div>
<!--End: page-wrapper --></div>
</body>
</body>
</html>
