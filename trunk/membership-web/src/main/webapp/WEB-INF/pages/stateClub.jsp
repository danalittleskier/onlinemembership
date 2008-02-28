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
<script type="text/javascript">
function changeState(index)
{
    form = document.accountBean;  // set up for easy access
  //alert("state["+form.stateAffiliation +"]");
  alert("Orig affil["+form.stateAffiliation.value +"]");
  alert("state["+index +"]");
  form.stateAffiliation.value = index;
  alert("New affil["+form.stateAffiliation.value +"]");
  //alert('state['+${accountBean.stateAffiliation}+']');
}
</script>


</head>
<body>
 <form:form commandName="accountBean" name="accountBean">

<div class="pageFrame">
  <div class="pageHeader"><p align="left"><font size="3">Membership Information</font></div>
	<body id=""/>



  <div class="greydientLeft" style="width: 442px; height: 212px">

   <table class="formtable">
	<tbody>
		<spring:bind path="accountBean.*">
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
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="user.address.province"/>:</td>
	      <td colspan="2" align="left">
          <spring:bind path="accountBean.stateAffiliation"> <!-- onchange="javascript: changeState(this.options[this.selectedIndex].value);accountBean.submit();"  -->
          <!-- onchange="javascript: changeState(this.options[this.selectedIndex].value);accountBean.submit();" -->
            <select name="stateAffiliation" onchange="javascript: accountBean.submit();">
            <!-- <select name="${status.expression}" value="${status.value[index]}">
	    	<select name="stateId" onChange="javaScript: showAlert();accountBean.submit();"> -->
	          <option value=""></option>
	          <c:forEach var="o" items="${accountBean.usStates}">
	            <option value="${o.id}"
	              <c:if test="${o.id == status.value}">selected</c:if>>${o.id}</option>
	          </c:forEach>
	        </select>
	      </spring:bind>
	      <input type="submit" class="button" name="_eventId_changeState" value="Update">
	      </td>
	    </tr>
  <c:choose>
	  <c:when test="${fn:length(accountBean.clubsForState) != 0}">
		<tr>
			<td><font size="2">Club:</font></td>
	        <td colspan="2" align="left">
	          <spring:bind path="accountBean.clubId">
		    	<select name="clubId">
		          <option value=""></option>
		          <c:forEach var="o" items="${accountBean.clubsForState}">
		            <option value="${o.id}"
		              <c:if test="${o.id == status.value}">selected</c:if>>${o.name}</option>
		          </c:forEach>
		        </select>
		      </spring:bind>
		    </td>
		</tr>
      </c:when>
    </c:choose>
		</table>



	<p style="text-align: left">

	<!-- added validation -->

      	<p>&nbsp;</div>
</div>
	<div class="section3">
<p align="center">


	         <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
             <input type="submit" class="button" name="_eventId_back" value="<fmt:message key='button.back'/>">
             <input type="submit" class="button" name="_eventId_next" value="<fmt:message key='button.next'/>">
             <%-- <input type="hidden" name="_eventId_changeState">--%>
                </div>


	<p>
</p>

</form:form>
</body>

</html>