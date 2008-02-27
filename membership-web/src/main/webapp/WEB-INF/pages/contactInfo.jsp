<%@ include file="/common/taglibs.jsp"%>
<h2><b>CONTACT INFO</b></h2>
<br/>

 <form:form commandName="accountBean" name="accountBean">
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

	    <tr><td>&nbsp;</td><td colspan="2">&nbsp;</td></tr>
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
	      <td class="label" colspan="1">MI:</td>
	      <td colspan="2">
	        <input type="text" name="T1" size="30">
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="registration.lastName"/>:</td>
	      <td colspan="2">
	        <form:input path="member.lastName" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Suffix:</td>
	      <td colspan="2">
	        <input type="text" name="T1" size="30">
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Company:</td>
	      <td colspan="2">
	        <form:input path="address.company" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="user.address.address"/>:</td>
	      <td colspan="2">
	        <form:input path="address.deliveryAddress" size="30" maxlength="30"/>
	      </td>
	    </tr>
	     <tr>
	      <td class="label" colspan="1">Address 2:</td>
	      <td colspan="2">
	        <input type="text" name="T1" size="30">
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="user.address.city"/>:</td>
	      <td colspan="2">
	        <form:input path="address.city" size="30" maxlength="30"/>
	      </td>
	    </tr>
	     <tr>
	      <td class="label" colspan="1"><fmt:message key="user.address.province"/>:</td>
	      <td colspan="2" align="left">
          <spring:bind path="accountBean.address.stateCode">
	    	<select name="address.stateCode">
	          <option value=""></option>
	          <c:forEach var="o" items="${accountBean.usStates}">
	            <option value="<c:out value='${o.id}'/>"
	              <c:if test="${o.id == status.value}">selected</c:if>><c:out value='${o.id}'/></option>
	          </c:forEach>
	        </select>
	      </spring:bind>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1"><fmt:message key="user.address.postalCode"/>:</td>
	      <td colspan="2">
	        <form:input path="address.postalCode" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Home Phone:</td>
	      <td colspan="2">
	        <form:input path="address.phoneHome" size="30" maxlength="30"/>
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Other Phone:</td>
	      <td colspan="2">
	        <input type="text" name="T1" size="30">
	      </td>
	    </tr>
	    <tr>
	      <td class="label" colspan="1">Fax Phone:</td>
	      <td colspan="2">
	        <input type="text" name="T1" size="30">
	      </td>
	    </tr>

	      <tr>
	      <td class="label" colspan="1">Birthdate:</td>
	      <td colspan="2">
	        <form:input path="member.birthDate" size="30" maxlength="30"/>
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
	         <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
             <input type="submit" class="button" name="_eventId_next" value="<fmt:message key='button.next'/>">
         <td>
       </tr>
    </tbody>
  </table>
 </form:form>
