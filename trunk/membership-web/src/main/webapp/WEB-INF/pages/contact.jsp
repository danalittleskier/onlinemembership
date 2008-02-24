<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<style type="text/css">
<!--
body {
	font-family: Arial, Helvetica, sans-serif;
	color: #333333;
	font-size: 12px;
}
a img {
	border: none;
}
.pageHeader {
	font-size: 13px;
	font-weight: bold;
}
.pageFrame {
	background-color: #FFFFFF;
	border: 1px solid #000000;
	width: 900px;
	height: 430px;
	padding: 30px;
}
.greydientLeft {
	margin: 30px 0 0 0;
	height: 182px;
	width: 442px;
	background-image: url("frame-gradientBox01.png");
	background-repeat: no-repeat;
	float:left;
	text-align:center;
	padding-top: 20px;
}
.greydientRight {
	margin: 30px 0 0 0;
	height: 182px;
	width: 442px;
	background-image: url("frame-gradientBox01.png");
	background-repeat: no-repeat;
	float:right;
	text-align:center;
	padding-top: 20px;
}
-->
</style>
</head>
<body>
<div class="pageFrame">
  <div class="pageHeader"><p align="left"><font size="3">Membership Information</font></div>

<body id=""/>



  <div class="greydientLeft" style="width: 442px; height: 813px">

<form:form commandName="member" name="member">
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
		</table>



	<p style="text-align: left">

	<!-- added validation -->

      	* <b><font size="1">indicates required field</font></b><p>&nbsp;</div>
  <div class="greydientRight" style="width: 347px; height: 316px">
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
	</div>
</div>
	<div class="section3">
<p align="center">


	         <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    		<input type="hidden" name="_eventId" value="submit">
    		<input type="submit" value="next" />&nbsp;

                </div>


	<p>
</p>

</form:form>
</body>

</html></html>