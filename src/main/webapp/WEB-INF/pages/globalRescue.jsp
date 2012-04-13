<%@ page import="org.ussa.model.Inventory" %>
<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code -->
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
<!-- END ProvideSupport.com Graphics Chat Button Code -->
</div>
<!-- End: Live Chat Support -->

<div id="stg-pagetitle">Global Rescue Medical Evacuation and Travel Assistance</div>
<p class="req-fields"><em>* Required Fields</em><br>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

	<%@ include file="/includes/messages.jsp" %>

	<%-- Yes, this messages thing is a bit of a hack... need to learn more about Spring MVC messages. Hopefully there is a better way to do this. --%>
	<c:if test="${not empty messages}">
		<div class="stg-success-tl">
			<p>Just so you know...</p>
		</div>
		<div class="stg-success-tr"></div>
		<div class="stg-success-content">
			<ul>
			<c:forEach var="messageBean" items="${messages}">
				<li>
					<fmt:message key="${messageBean.resourceKey}">
						<c:forEach var="parameter" items="${messageBean.params}">
							<fmt:param value="${parameter}"/>
						</c:forEach>
					</fmt:message>
				</li>
			</c:forEach>
			</ul>
		</div>
		<%session.removeAttribute("messages");%>
	</c:if>

	<spring:bind path="accountBean.messageBean.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="stg-error-tl">
				<p>Note</p>
			</div>
			<div class="stg-success-tr"></div>
			<div class="stg-success-content">
				<ul>
					<c:forEach var="errorMsg" items="${status.errorMessages}">
						<li>
							<c:out value="${errorMsg}" escapeXml="false" />
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</spring:bind>

	<div id="hide" style="display:none">
		<input type="button" class="button" id="update" name="_eventId_change" value="Update" onclick="submitFormWithInputButton(this);">
	</div>

	<!-- Start: Global Rescue HTML -->
			<table border="0" cellpadding="0" cellspacing="0" width="560">
<tr>
  <td>&nbsp;</td>
  <td><img src="/images/GlobalRescueLogo.png" width="560" ></td>
</tr>
<tr>
  <td>&nbsp;</td>
	<td><br />
	  Global Rescue is the USST Official Supplier of aeromedical evacuation services, and the worldwide leader in travel and medical assistance. Services include 24 hour access to a Johns Hopkins Medicine supported medical hotline, as well as medical evacuations in the event of injury or illness requiring hospitalization. Total benefits of up to $500,000 per year. View more <a href="https://www.globalrescue.com/plans.cfm" target="_blank">Global Rescue Membership Information</a>.<br />
    <br /></td>
</tr>
<tr>
  <td>&nbsp;</td>
	<td><table class="ussa-standard-table" width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <th width="50">&nbsp;</th>
        <th>Global Rescue Membership Type</th>
        <th>Price</th>
	    </tr>
	  <tr class="odd">
	    <td align="center"><input type="radio" name="globalrescueradiobutton" id="globalrescueradiobutton" value="GRS" onclick="javascript:ShowFamilyMembershipsTableHide()" /></td>
	    <td>Annual Student Medical Membership</td>
	    <td><strong>$239.00</strong></td>
	    </tr>
	  <tr class="even">
	    <td align="center"><input type="radio" name="globalrescueradiobutton" id="globalrescueradiobutton2" value="GRI" onclick="javascript:ShowFamilyMembershipsTableHide()" /></td>
	    <td>Annual Individual Medical Membership</td>
	    <td><strong>$309.00</strong></td>
	    </tr>
	  <tr class="odd">
	    <td align="center"><input type="radio" name="globalrescueradiobutton" id="globalrescueradiobutton3" value="GRF" onclick="javascript:ShowFamilyMembershipsTableShow()" /></td>
	    <td>Annual Family Medical Membership</td>
	    <td><strong>$559.00</strong></td>
	    </tr>
        <tr class="even">
	    <td align="center"><input name="globalrescueradiobutton" type="radio" id="globalrescueradiobutton4" value="radio" checked="checked" onclick="javascript:ShowFamilyMembershipsTableHide()" /></td>
	    <td>None</td>
	    <td></td>
	    </tr>
    </table></td>
</tr>
<tr>
  <td>&nbsp;</td>
	<td> <br>
    	<div id="GlobalRescueFamilyMembership" style="display:none">
        <span style="font-weight:bold;">Fill-in the first name, last name, and date of birth for each family member you would like on the Family Membership plan.</span>
    	<table class="ussa-standard-table" width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <th>Description</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date of Birth</th>
        </tr>
	  <tr class="odd">
	    <td>You</td>
	    <td><input name="firstname1" type="text" disabled="disabled" id="firstname1" value="Jonathan" readonly="readonly" /></td>
	    <td><input name="lastname1" type="text" disabled="disabled" id="lastname1" value="Larson" readonly="readonly" /></td>
	    <td>10/05/1980</td>
	    </tr>
	  <tr class="even">
	    <td>Parent 2</td>
	    <td><input type="text" name="firstname2" id="firstname2" /></td>
	    <td><input type="text" name="lastname2" id="lastname2" /></td>
	    <td><select name="month2" id="month2">
	      <option value="Month..." selected="selected">Month...</option>
	      <option value="January">January</option>
	      <option value="February">February</option>
	      <option value="March">March</option>
	      <option value="April">April</option>
	      <option value="May">May</option>
	      <option value="June">June</option>
	      <option value="July">July</option>
	      <option value="August">August</option>
	      <option value="September">Septemeber</option>
	      <option value="October">October</option>
	      <option value="November">November</option>
	      <option value="December">December</option>
	      </select>
	      <br />
	      <select name="day2" id="day2">
	        <option value="Day..." selected="selected">Day...</option>
	        <option value="1">1</option>
	        <option value="2">2</option>
	        <option value="3">3</option>
	        <option value="4">4</option>
	        <option value="5">5</option>
          </select>
	      <br />
	      <select name="year2" id="year2">
	        <option value="Year...">Year...</option>
	        <option value="1980">1980</option>
          </select></td>
	    </tr>
	  <tr class="odd">
	    <td>Dependant 1</td>
	    <td><input type="text" name="firstname3" id="firstname3" /></td>
	    <td><input type="text" name="lastname3" id="lastname3" /></td>
	    <td><select name="month3" id="month3">
	      <option value="Month..." selected="selected">Month...</option>
	      <option value="January">January</option>
	      <option value="February">February</option>
	      <option value="March">March</option>
	      <option value="April">April</option>
	      <option value="May">May</option>
	      <option value="June">June</option>
	      <option value="July">July</option>
	      <option value="August">August</option>
	      <option value="September">Septemeber</option>
	      <option value="October">October</option>
	      <option value="November">November</option>
	      <option value="December">December</option>
	      </select>
	      <br />
	      <select name="day3" id="day3">
	        <option value="Day..." selected="selected">Day...</option>
	        <option value="1">1</option>
	        <option value="2">2</option>
	        <option value="3">3</option>
	        <option value="4">4</option>
	        <option value="5">5</option>
          </select>
	      <br />
	      <select name="year3" id="year3">
	        <option value="Year...">Year...</option>
	        <option value="1980">1980</option>
          </select></td>
	    </tr>
        <tr class="even">
          <td>Dependant 2</td>
          <td><input type="text" name="firstname4" id="firstname4" /></td>
          <td><input type="text" name="lastname4" id="lastname4" /></td>
          <td><select name="month4" id="month4">
            <option value="Month..." selected="selected">Month...</option>
            <option value="January">January</option>
            <option value="February">February</option>
            <option value="March">March</option>
            <option value="April">April</option>
            <option value="May">May</option>
            <option value="June">June</option>
            <option value="July">July</option>
            <option value="August">August</option>
            <option value="September">Septemeber</option>
            <option value="October">October</option>
            <option value="November">November</option>
            <option value="December">December</option>
          </select>
          <br />
          <select name="day4" id="day4">
            <option value="Day..." selected="selected">Day...</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>
          <br />
          <select name="year4" id="year4">
            <option value="Year...">Year...</option>
            <option value="1980">1980</option>
          </select></td>
        </tr>
        <tr class="even">
          <td>Dependant 3</td>
          <td><input type="text" name="firstname5" id="firstname5" /></td>
          <td><input type="text" name="lastname5" id="lastname5" /></td>
          <td><select name="month5" id="month5">
            <option value="Month..." selected="selected">Month...</option>
            <option value="January">January</option>
            <option value="February">February</option>
            <option value="March">March</option>
            <option value="April">April</option>
            <option value="May">May</option>
            <option value="June">June</option>
            <option value="July">July</option>
            <option value="August">August</option>
            <option value="September">Septemeber</option>
            <option value="October">October</option>
            <option value="November">November</option>
            <option value="December">December</option>
          </select>
          <br />
          <select name="day5" id="day5">
            <option value="Day..." selected="selected">Day...</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>
          <br />
          <select name="year5" id="year5">
            <option value="Year...">Year...</option>
            <option value="1980">1980</option>
          </select></td>
        </tr>
        <tr class="even">
	    <td>Dependant 4</td>
	    <td><input type="text" name="firstname6" id="firstname6" /></td>
	    <td><input type="text" name="lastname6" id="lastname6" /></td>
	    <td><select name="month6" id="month6">
	      <option value="Month..." selected="selected">Month...</option>
	      <option value="January">January</option>
	      <option value="February">February</option>
	      <option value="March">March</option>
	      <option value="April">April</option>
	      <option value="May">May</option>
	      <option value="June">June</option>
	      <option value="July">July</option>
	      <option value="August">August</option>
	      <option value="September">Septemeber</option>
	      <option value="October">October</option>
	      <option value="November">November</option>
	      <option value="December">December</option>
	      </select>
	      <br />
	      <select name="day6" id="day6">
	        <option value="Day..." selected="selected">Day...</option>
	        <option value="1">1</option>
	        <option value="2">2</option>
	        <option value="3">3</option>
	        <option value="4">4</option>
	        <option value="5">5</option>
          </select>
	      <br />
	      <select name="year6" id="year6">
	        <option value="Year...">Year...</option>
	        <option value="1980">1980</option>
          </select></td>
	    </tr>
    </table>
    </div>
    </td>
</tr>
<tr>
  <td>&nbsp;</td>
	<td><br />	  
	  This special pricing is offered as part of USSA registration only.<br />
    <br /></td>
</tr><tr>
	<td width="25" valign="top"><input type="checkbox" name="agreetoglobalrescue" id="agreetoglobalrescue" /></td>
	<td>I have read and agree to the <a href="https://www.globalrescue.com/assets/pdf/GlobalRescue-MSA.pdf" target="_blank">Member Services Agreement</a>. I attest that as of the date of enrollment, I am in good health, am not hospitalized or anticipating hospitalization, travel abroad less than 45 days continuously each trip and am not traveling to the polar Arctic (above the 80th parallel North) or Antarctic (below the 60th parallel South). Other Global Rescue memberships are available for these conditions, visit <a href="http://www.globalrescue.com/ussa" target="_blank">www.globalrescue.com/ussa</a> for information on polar and longer-term abroad memberships.</td>
</tr>
</table>
	<!-- End: Global Rescue HTML -->
	
	<fieldset class="buttons">
		<label></label>
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
		<!--<input type="submit" class="btn-green" name="_eventId_update" value="Update Cart">-->
		<input class="btn-green" type="button" onclick="submitFormWithInputButton(this);" value="Add Selection to Cart" name="_eventId_add"/>
		<div class="button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
		<div class="button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
	</fieldset>
</form:form>
</div>

<!-- RIGHT column -->
<div id="stg-twocol-secondary">
	<!-- BOX (START) -->
	<div class="stg-bl">
		<div class="stg-br">
			<div class="stg-tl">
				<div class="stg-tr">
					<div></div>
					<%@ include file="/includes/shoppingCart.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>


</body>


