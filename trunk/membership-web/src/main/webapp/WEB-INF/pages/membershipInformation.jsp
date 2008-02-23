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
<p>  &nbsp;</p>

<body id=""/>



  <div class="greydientLeft" style="width: 672px; height: 270px">

<form>

	<table border="0" width="122%" id="table1">
		<tr>
			<td width="421"><font size="2">Are you a US Citizen?</font></td>
			<td><select size="1" name="D2">
			<option selected value="Yes">Yes</option>
			<option>No</option>
			</select></td>
		</tr>
		<tr>
			<td width="421"><font size="2">If no, select nation code</font></td>
			<td><select size="1" name="D1"></select></td>
		</tr>
		<tr>
			<td width="421">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="421">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="421">If your state and club have not changed, click next</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="421">
			<p align="center">State</td>
			<td><select size="1" name="D3"></select></td>
		</tr>
		<tr>
			<td width="421">
			<p align="center">Club</td>
			<td><select size="1" name="D4"></select></td>
		</tr>
		</table>



	<p style="text-align: left">

	<!-- added validation -->

      	<p>&nbsp;</div>
</div>
	<div class="section3">
<p align="center">


	         <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    		<input type="hidden" name="_eventId" value="submit">
    		<input type="submit" value="next" />&nbsp;

                </div>


	<p>
</p>

</form>
</body>

</html></html>