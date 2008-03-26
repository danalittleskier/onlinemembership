<body>

<!-------------------------------------------- Content ------------------------------------------------->
<!-- Container -->
<div id="stg-content">
<%@ include file="/common/taglibs.jsp"%>
	<!-- Progress bar -->

  <div id="stg-progress"><img src="${ctx}/images/progress_1.gif" width="917" height="53" /></div>
  <div id="stg-pagetitle">Member Information</div>

	<!-- LEFT column -->
	<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<spring:bind path="accountBean.*">
			<c:if test="${not empty status.errorMessages}">
				<c:set var="sectionHasFormErrors" scope="request" value="true"/>
						<c:forEach var="errorMsg" items="${status.errorMessages}">
							<c:out value="${errorMsg}" escapeXml="false" />
						</c:forEach>
			</c:if>
		</spring:bind>

<fieldset>
	<legend>Membership  Information</legend>

	<table border="0" width="100%" id="table1">
		<tr>
			<td width="180"><font size="2">Magazine Subscription</font></td>
			<td><select size="1" name="D1">
			<option value="MAGSB">Snowboard Magazine</option>
			<option value="MRCE">Ski Racing Magazine</option>
			<option value="MRLTD">Ski Racing Magazine-Limited Issues</option>
			<option value="MTRX">Ski Trax Magazine</option>
			<option value="NONE">No Magazine</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Contribution</font></td>
			<td><input type="text" name="T1" size="20" value="$0.00"></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><b><font size="2">Shirts ( $20 each )</font></b></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><font size="2">Alpine </font><i><font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			view image</font></i></td>
			<td><select size="1" name="D3">
			<option selected value="S">Small</option>
			<option value="M">Medium</option>
			<option value="L">Large</option>
			<option value="X">X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Freestyle </font><i><font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			view image</font></i></td>
			<td><select size="1" name="D4">
			<option value="S">Small</option>
			<option value="M">Medium</option>
			<option value="L">Large</option>
			<option value="X">X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Cross Country </font><i>
			<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; view image</font></i></td>
			<td><select size="1" name="D5">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Jumping / NC </font><i>
			<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			view image</font></i></td>
			<td><select size="1" name="D6">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">Snowboarding </font><i>
			<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; view
			image</font></i></td>
			<td><select size="1" name="D7">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">U.S. Ski Team General</font></td>
			<td><select size="1" name="D8">
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
			<option>X-Large</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><font size="2"><b>Decals (pack of 5 for $5)</b></font></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="180"><font size="2">U.S. Snowboarding</font></td>
			<td><select size="1" name="D9">
			<option>5</option>
			<option>12</option>
			<option>20</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		<tr>
			<td width="180"><font size="2">U.S. Ski Team</font></td>
			<td><select size="1" name="D10">
			<option>5</option>
			<option>12</option>
			<option>20</option>
			</select></td>
			<td><font size="2">add</font></td>
		</tr>
		</table>

</fieldset>

<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
	<input type="submit" class="btn-green" name="_eventId_back" value="Back">
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
					<p class="stg-omr-header">Your Membership Fees</p>

					<display:table name="accountBean.shoppingCart" requestURI="" sort="list" defaultsort="1" id="cart">
						<display:column property="description" title="Description" sortable="false" class="item"/>
						<display:column property="amount" title="Amount" sortable="false" class="price"/>
					</display:table>

					<table id="carttotal">
						<tr>
							<td class="total">Total</td>
							<td class="price">
								<c:out value="${accountBean.cartBean.totalCost}"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- BOX (END) -->
</div>
<div class="clear"></div>
</div>

</body>

