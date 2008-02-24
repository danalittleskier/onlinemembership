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
  <div class="pageHeader"><p align="left"><font size="3">Medical Insurance</font></div>
<p>  <font size="2">PRIMARY MEDICAL INSURANCE INFORMATION</font></p>

<body id=""/>



  <div class="greydientLeft" style="width: 442px; height: 212px">

<form>

	<table border="0" width="100%" id="table1">
		<tr>
			<td><font size="2">* Primary Medical Insurance Co.</font></td>
			<td><input type="text" name="T1" size="20"></td>
		</tr>
		<tr>
			<td><font size="2">* Policy Number</font></td>
			<td><input type="text" name="T2" size="20"></td>
		</tr>
		<tr>
			<td><font size="2">Phone Number</font></td>
			<td><input type="text" name="T3" size="20"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><font size="1">Image</font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		</table>



	<p style="text-align: left">

	<!-- added validation -->

      	* Indicate required field<p>&nbsp;</div>
  <div class="greydientRight" style="width: 347px; height: 316px">
    <b>Medical Insurance</b><p>Members must have and maintain Medical/Accident
	insurance for duration of membership year.&nbsp; Failure to provide accurate
	information demonstration the existence of such insurance coverage for
	Member will prevent processing of this application and cause termination of
	membership and suspension of all rights to participate in U.S. Ski and
	Snowboard Association activities, unless Member timely returns a fully
	executed Medial Exception Agreement ( which can be obtained by contacting
	Member Services at&nbsp; 435.647.2666 or via fax at 435.647.2052).&nbsp; </div>
</div>
	<div class="section3">
<p align="center">


	         <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
             <input type="submit" class="button" name="_eventId_back" value="<fmt:message key='button.back'/>">
             <input type="submit" class="button" name="_eventId_next" value="<fmt:message key='button.next'/>">


                </div>


	<p>
</p>

</form>
</body>

</html></html>