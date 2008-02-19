<%@ include file="/common/taglibs.jsp"%>
<h2><b>CONTACT PAGE</b></h2>
<br/>

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

	    <tr><td>&nbsp;</td><td colspan="2">&nbsp;</td></tr>
	    <tr>
	      <td class="label" colspan="1">First:</td>
	      <td colspan="2">
	        <form:input path="FIRST_NAME" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Last:</td>
	      <td colspan="2">
	        <form:input path="LAST_NAME" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr><td>&nbsp;</td><td colspan="2">&nbsp;</td></tr>
	    <tr>
	      <td class="label" colspan="1">Birthdate:</td>
	      <td colspan="2">
	        <form:input path="BIRTHDATE" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Email:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
       <tr>
         <td><br/></td>
         <td><br/></td>
       </tr>
       <tr>
         <td align="left"></td>
         <td><br/></td>
       </tr>
       <tr>
         <td><br/></td>
         <td><br/></td>
       </tr>
       <tr>
         <td><br/></td>
         <td>

	         <input type="submit" value="<fmt:message key='button.save' />" />&nbsp;
         <td>
       </tr>
    </tbody>
  </table>
 </form:form>
