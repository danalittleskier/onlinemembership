<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code -->
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
<!-- END ProvideSupport.com Graphics Chat Button Code -->
</div>
<!-- End: Live Chat Support -->

<div id="stg-pagetitle">Bonus Packs & Decals </div>

<form:form commandName="accountBean" name="accountBean">

<!-- LEFT column -->
<div id="stg-twocol-primary">

<%@ include file="/includes/messages.jsp"%>


<div class="extras-header">
	<p>Bonus Packs</p>
</div>
<p>Bonus Packs include a high quality 100% cotton shirt ~ The U.S. Ski Team packs include a decal strip 
	 and a 2015 collectors pin ~ The U.S. Snowboarding pack includes a decal strip and 2 die cut logo decals.
	T-shirts available in <strong>Adult</strong> sizes only: S, M, L, and XL. &nbsp;&nbsp;Packs are $25 each (includes shipping) multiple bonus packs are shipped separately.</p>
<p>Choose from 6 styles: U.S. Ski Team, Alpine, Freestyle, Cross Country, Jumping
	Nordic Combined and Snowboarding. <strong>Bonus Packs available to U.S.
	residents only.</strong> </p>
<div class="tshirt-row">
	<!-- Start T-shirt row -->
	<div class="tshirt-1">
		<p>Alpine</p>
		<a href="<c:url value='/images/AlpineTee_Lrg.jpg'/>" title="Alpine Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/AlpineTee_Sm.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-2">
		<p>Freestyle</p>
		<a href="<c:url value='/images/freestyleTee_Lrg.jpg'/>" title="Freestyle Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/freestyleTee_Sm.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-3">
		<p>Cross Country</p>
		<a href="<c:url value='/images/XC_Lrg.jpg'/>" title="Cross Country Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/XC_Sm.jpg'/>" height="141" width="145" alt="Enlarge Image">
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
		<a href="<c:url value='/images/Jump-NC_Lrg.jpg'/>" title="Jumping N/C Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/Jump-NC_Sm.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-2">
		<p>US Ski Team General</p>
		<a href="<c:url value='/images/generalTee_Lrg.jpg'/>" title="US Ski Team General Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/generalTee_Sm.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-3">
		<p>Snowboarding</p>
		<a href="<c:url value='/images/snowboardingTee_Lrg.jpg'/>" title="Snowboarding Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/snowboardingTee_Sm.jpg'/>" height="141" width="145" alt="Enlarge Image">
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
<p><img src="<c:url value='/images/13BP_MagWebFile.jpg'/>" style="float: right;" height="126" width="199">
	<strong>ADDED BONUS!</strong><br/>
	A one-year subscription to SKI Magazine (6-issues, a $9 value) is included with any ski Bonus
	Pack purchase. </p>
<p>	Magazine and refund instructions will be sent via postcard.</p>
<p>* One per household. Valid in the U.S. only.</p>

<p> No Bonus Pack Magazine <br>
<form:select path="extrasBean.noBonusPackMag">
	<form:option value=""></form:option>
	<form:option value="<%=Inventory.INV_ID_BONUS_PACK_NOMAG %>">NO MAGAZINE</form:option>
</form:select>
<input type="hidden" name="extrasBean.noBonusPackMagQty" value="1"/>
</p>

<fieldset class="buttons" style="padding-left:200px;">
	<br>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	
	<div class="ussa-button-blue"><span><input type="button" name="_eventId_add" value="Add Selections to Cart" onclick="submitFormWithInputButton(this);" style="background-color: transparent;border: medium none;color: #FFFFFF;cursor: pointer;font-size: 14px;font-weight:bold;float:none;"></span></div>
	<br><br>
		<div class="ussa-button gray"><span><input type="button" class="btn-submit" name="_eventId_back" value="Back" onclick="submitFormWithInputButton(this);"></span></div>
		<div class="ussa-button green"><span><input type="submit" class="btn-submit" name="_eventId_next" value="Continue"></span></div>
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
		<strong>Decals are $5 for a pack of 5</strong> bumper strips, each with multiple decals.
	</p>
	<div class="decal-row">
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalSkiOption" value="<%=Inventory.INV_ID_BONUS_DECALS_SKI_TEAM%>"/>
				<form:input path="extrasBean.decalSkiQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/13_USST_DecalSheet.jpg'/>" width="195" height="101" /> </div>
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalBoardOption" value="<%=Inventory.INV_ID_BONUS_DECALS_SNOWBOARD%>"/>
				<form:input path="extrasBean.decalBoardQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/13_USS_DecalSheet_SB.jpg'/>" width="195" height="101" />
		</div>
	</div>

</div>

</form:form>

<div class="clear"></div>

</body>
