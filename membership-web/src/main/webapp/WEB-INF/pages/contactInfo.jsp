<%@ include file="/common/taglibs.jsp"%>
<h2><b>CONTACT INFO</b></h2>
<br/>

 <form:form commandName="accountBean" name="accountBean">
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    <input type="hidden" name="_eventId" value="submit">

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
         <td>is a member - need to pass in info</td>
         <td></td>
       </tr>
       <tr>
	    <tr>
	      <td class="label" colspan="1">USSA Member #:</td>
	      <td colspan="2">
	        <form:input path="member.id" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="registration.firstName"/>:</td>
	      <td colspan="2">
	        <form:input path="member.firstName" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="registration.lastName"/>:</td>
	      <td colspan="2">
	        <form:input path="member.lastName" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">City:</td>
	      <td colspan="2">
	        <form:input path="address.city" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Zip:</td>
	      <td colspan="2">
	        <form:input path="address.postalCode" size="30" maxlength="30"/>
	      </td>
	    </tr>


	    <%--
	    <tr><td>&nbsp;</td><td colspan="2">&nbsp;</td></tr>
	    <tr>
	      <td class="label" colspan="1">MI:</td>
	      <td colspan="2">
	        <form:input path="BIRTHDATE" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Last Name:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Suffix:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Company:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Address 1:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Address 2:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">City:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">State DROP DOWN:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Country:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Zip / Postal Code:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Home Phone:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Other Phone:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Fax Phone:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
         <td>if new member display the following fields</td>
         <td></td>
       </tr>
       <tr>

	    <tr>
	      <td class="label" colspan="1">Gender:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Nation:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Ethnicity:</td>
	      <td colspan="2">
	        <form:input path="EMAIL" size="30" maxlength="30"/>
	      </td>
	    </tr>
--%>
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
