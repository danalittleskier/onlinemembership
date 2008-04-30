<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Bonus Packs & Decals </div>

<form:form commandName="accountBean" name="accountBean">

<!-- LEFT column -->
<div id="stg-twocol-primary">

<%@ include file="/includes/messages.jsp"%>


<div class="extras-header">
	<p>Bonus Packs</p>
</div>
<p>Bonus Packs include a high quality 100% cotton U.S. Ski Team or U.S.
	Snowboarding t-shirt, decal pack and a 2009 collectors lapel pin (or snowboarding
	die-cut decal). <strong>Each pack is $20 including shipping.</strong>&nbsp;&nbsp;Please allow 4-6 weeks for delivery. Each pack is shipped separately.</p>
<p>Choose from 6 styles: U.S. Ski Team, Alpine, Freestyle, Cross Country, Jumping
	Nordic Combined and Snowboarding. <strong>Bonus Packs available to U.S.
	residents only.</strong> </p>
<!-- T-shirt popup -->
<div style="display: none;" id="bonus-popup">
	<div class="popup-title"><span id="title-text">Cross Country</span><span class="popup-subtitle">Bonus
          Pack</span>
		<input name="close" value="Close" class="btn-green-sm" onclick="document.getElementById('bonus-popup').style.display = 'none';" type="button">
	</div>
	<div class="crosscountry" id="popup-content"></div>
	<!--[if lte IE 6.5]><iframe></iframe><![endif]-->
</div>
<div class="tshirt-row">
	<!-- Start T-shirt row -->
	<div class="tshirt-1">
		<p>Alpine</p>
		<a href="<c:url value='/images/popup-alpine.gif'/>" title="Alpine Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/bp-alpine.gif'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-2">
		<p>Freestyle</p>
		<a href="<c:url value='/images/popup-freestyle.gif'/>" title="Freestyle Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/bp-freestyle.gif'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-3">
		<p>Cross Country</p>
		<a href="<c:url value='/images/popup-crosscountry.gif'/>" title="Cross Country Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/bp-crosscountry.gif'/>" height="141" width="145" alt="Enlarge Image">
			</a>
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.alpineOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_ALPINE_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_ALPINE_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_ALPINE_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_ALPINE_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.alpineQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.freestyleOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESTYLE_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESTYLE_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESTYLE_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESTYLE_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.freestyleQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.crossCountryOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_CROSS_COUNTRY_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_CROSS_COUNTRY_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_CROSS_COUNTRY_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_CROSS_COUNTRY_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.crossCountryQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="clear"></div>
	<!-- End T-shirt row -->
</div>
<div class="tshirt-row">
	<!-- Start T-shirt row -->
	<div class="tshirt-1">
		<p>Jumping N/C</p>
		<a href="<c:url value='/images/popup-jumpingnc.gif'/>" title="Jumping N/C Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/bp-jumpingnc.gif'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-2">
		<p>US Ski Team General</p>
		<a href="<c:url value='/images/popup-skigeneral.gif'/>" title="US Ski Team General Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/bp-skigeneral.gif'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-3">
		<p>Snowboarding</p>
		<a href="<c:url value='/images/popup-snowboarding.gif'/>" title="Snowboarding Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/bp-snowboarding.gif'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.jumpingOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_JUMPING_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_JUMPING_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_JUMPING_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_JUMPING_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.jumpingQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.generalOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_LOGO_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_LOGO_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_LOGO_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_LOGO_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.generalQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.boardingOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SNOWBOARD_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SNOWBOARD_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SNOWBOARD_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SNOWBOARD_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.boardingQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="clear"></div>
</div>
<p><img src="<c:url value='/images/magazines.jpg'/>" style="float: right;" height="165" width="176">
	<strong>ADDED BONUS!</strong><br/>
	A one-year subscription to SKI Magazine (7-issues, a $9 value) or Transworld
	Snowboarding Magazine (9-issues, a $5 value) is included with your bonus
	Pack purchase. Offer is refundable. Instructions will be mailed.</p>
<p>* One per household. Valid in the U.S. only.</p>

<fieldset class="buttons">
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="btn-green" name="_eventId_add" value="Add Selections to Cart">
	<div style="float: left; margin-left: 8px;">
		<input type="submit" class="btn-green" name="_eventId_back" value="Back">
		<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
	</div>
</fieldset>
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

	<br />
	<br />
	<div class="extras-header">
		<p>5 for $5 Decal Packs</p>
	</div>
	<p>
		Choose the U.S. Ski Team and/or U.S. Snowboarding packs.<br />
		<strong>Decals are $5 for a pack of 5</strong> bumper strips each with multiple decals.
	</p>
	<div class="decal-row">
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalSkiOption" value="<%=Inventory.INV_ID_BONUS_DECALS_SKI_TEAM%>"/>
				<form:input path="extrasBean.decalSkiQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/decal_skiteam.gif'/>" width="195" height="101" /> </div>
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalBoardOption" value="<%=Inventory.INV_ID_BONUS_DECALS_SNOWBOARD%>"/>
				<form:input path="extrasBean.decalBoardQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/decal_snowboarding.gif'/>" width="195" height="101" />
		</div>
	</div>

</div>

</form:form>

<div class="clear"></div>

</body>
