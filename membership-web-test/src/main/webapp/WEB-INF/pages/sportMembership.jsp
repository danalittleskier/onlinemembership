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



  <div class="greydientLeft" style="width: 442px; height: 212px">

<form>

	<table border="0" width="100%" id="table1">
		<tr>
			<td width="18%"><font size="2">Sport</font></td>
			<td width="79%"><select size="1" name="D1"></select></td>
		</tr>
		<tr>
			<td width="18%"><font size="2">Membership</font></td>
			<td width="79%"><select size="1" name="D2"></select></td>
		</tr>
		<tr>
			<td width="18%">&nbsp;</td>
			<td width="79%">&nbsp;</td>
		</tr>
		<tr>
			<td width="18%">&nbsp;</td>
			<td width="79%">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><input type="radio" value="V1" checked name="R1"><font size="2">Click
			here to subscribe to the email list</font></td>
		</tr>
		<tr>
			<td colspan="2"><input type="radio" name="R1" value="V2"><font size="2">Click
			here to be placed on Mail Privacy Status.</font></td>
		</tr>
		<tr>
			<td width="18%">&nbsp;</td>
			<td width="79%">&nbsp;</td>
		</tr>
	</table>
	<p style="text-align: left">

	<!-- added validation -->

      	* Indicate required field<p>&nbsp;</div>
  <div class="greydientRight" style="width: 347px; height: 316px">
    <table border="1" width="100%" id="table2">
		<tr>
			<td colspan="2">
			<p align="center"><b><font size="2">Membership / Items</font></b></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><font size="2">Alpine Master</font></td>
			<td>
			<p align="right"><font size="2">$100.00</font></td>
		</tr>
		<tr>
			<td><font size="2">State Dues</font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><font size="2">Late Fees</font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td height="22">&nbsp;</td>
			<td height="22">&nbsp;</td>
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
			<td>
			<p align="right"><font size="2">Total</font></td>
			<td>
			<p align="right"><b><font size="2">$100.00</font></b></td>
		</tr>
	</table>
	</div>
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