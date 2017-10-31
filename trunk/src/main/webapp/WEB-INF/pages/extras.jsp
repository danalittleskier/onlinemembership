<%@ include file="/includes/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" /></div>
<!-- Start: Live Chat Support -->
<div style="float:right;margin-right:17px;">
<!-- BEGIN ProvideSupport.com Graphics Chat Button Code 
<div id="ciYq2A" style="z-index:100;position:absolute"></div><div id="scYq2A" style="display:inline"></div><div id="sdYq2A" style="display:none"></div><script type="text/javascript">var seYq2A=document.createElement("script");seYq2A.type="text/javascript";var seYq2As=(location.protocol.indexOf("https")==0?"https":"http")+"://image.providesupport.com/js/ussa/safe-standard.js?ps_h=Yq2A&ps_t="+new Date().getTime();setTimeout("seYq2A.src=seYq2As;document.getElementById('sdYq2A').appendChild(seYq2A)",1)</script><noscript><div style="display:inline"><a href="http://www.providesupport.com?messenger=ussa">Live Customer Support</a></div></noscript>
END ProvideSupport.com Graphics Chat Button Code -->

</div>
<!-- End: Live Chat Support -->

<div id="stg-pagetitle">Bonus Packs and Decals </div>

<form:form commandName="accountBean" name="accountBean">

<!-- LEFT column -->
<div id="stg-twocol-primary">

<%@ include file="/includes/messages.jsp"%>

<p>Bonus Packs include a high quality 100% cotton shirt for men or cotton/poly blend for women. The U.S. Ski Team packs include a decal strip 
	 and a 2018 collectors pin. The U.S. Snowboard and U.S. Freeski packs include a sport decal strip and US die-cut decal.
	T-shirts available in adult sizes only. Men's sizes available in S, M, L and XL and women's sizes available in XS, S, M, L and XL. Packs are $28 each (includes shipping). Each pack is shipped separately.</p>
<p>Choose from 6 styles: U.S. Logo Men's or Women's, U.S. Ski Team Men's or Women's, U.S. Freeski and U.S. Snowboard.
<strong>Bonus Packs available to U.S. residents only.</strong> </p>
<p>Click for larger view.</p>
<div class="tshirt-row">
	<!-- Start T-shirt row -->
	<div class="tshirt-1">
		<p>Logo Shirt - Men</p>
		(heather gray)
		<a href="<c:url value='/images/2018BonusPackUS_MensEnlarge.jpg'/>" title="Logo Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/2018BonusPackUS_MensThumbnail.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-2">
		<p>US Ski Team - Men</p>
		(heather gray)
		<a href="<c:url value='/images/2018BonusPackUSST_MensEnlarge.jpg'/>" title="US Ski Team Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/2018BonusPackUSST_MensThumbnail.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-3">
		<p>US Freeski</p>
		(heather gray)
		<a href="<c:url value='/images/2018BonusPackUSFS_MensEnlarge.jpg'/>" title="US Freeskiing Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/2018BonusPackUSFS_MensThumbnail.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.logoMenOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_MEN_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_MEN_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_MEN_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_MEN_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.logoMenQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.usSkiTeamMenOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_MEN_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_MEN_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_MEN_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_MEN_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.usSkiTeamMenQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.freeskiingOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESKIING_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESKIING_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESKIING_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_FREESKIING_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.freeskiingQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="clear"></div>
	<!-- End T-shirt row -->
</div>
<div class="tshirt-row">
	<!-- Start T-shirt row -->
	<div class="tshirt-1">
		<p>Logo Shirt - Women</p>
		(heather grey)
		<a href="<c:url value='/images/2018BonusPackUS_WomensEnlarge.jpg'/>" title="Logo Women Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/2018BonusPackUS_WomensThumbnail.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-2">
		<p>US Ski Team - Women</p>
		(heather grey)
		<a href="<c:url value='/images/2018BonusPackUSST_WomensEnlarge.jpg'/>" title="US Ski Team Women Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/2018BonusPackUSST_WomensThumbnail.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-3">
		<p>US Snowboard</p>
		(heather grey)
		<a href="<c:url value='/images/2018BonusPackUSSB_MensEnlarge.jpg'/>" title="US Snowboard Bonus Pack" rel="gb_imageset[images]">
			<img src="<c:url value='/images/2018BonusPackUSSB_MensThumbnail.jpg'/>" height="141" width="145" alt="Enlarge Image">
		</a>
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.logoWomenOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_WOMEN_XSMALL%>">XS</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_WOMEN_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_WOMEN_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_WOMEN_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_US_LOGO_WOMEN_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.logoWomenQty" maxlength="3"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
			<form:select path="extrasBean.usSkiTeamWomenOption">
					<form:option value=""></form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_WOMEN_XSMALL%>">XS</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_WOMEN_SMALL%>">S</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_WOMEN_MEDIUM%>">M</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_WOMEN_LARGE%>">L</form:option>
					<form:option value="<%=Inventory.INV_ID_BONUS_SKI_TEAM_WOMEN_EXTRA_LARGE%>">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.usSkiTeamWomenQty" maxlength="3"/>
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




<p><img src="<c:url value='/images/SkiMagazine.jpg'/>" style="float: right;" height="126" width="100">
	<strong>ADDED BONUS!</strong><br/>
	A one-year subscription to SKI Magazine (six issues, a $9 value) is included with any ski Bonus
	Pack purchase. Click below to opt out of the bonus pack magazine.</p>
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
		Choose the U.S. Ski Team, U.S. Freeski and/or U.S. Snowboard packs.<br />
		<strong>Decals are $5 for a pack of 5</strong> bumper strips, each with multiple decals.
	</p>
	<div class="decal-row">
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalSkiOption" value="<%=Inventory.INV_ID_BONUS_DECALS_SKI_TEAM%>"/>
				<form:input path="extrasBean.decalSkiQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/18_USST_DecalSheet.jpg'/>" width="195" height="101" /> </div>
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalBoardOption" value="<%=Inventory.INV_ID_BONUS_DECALS_SNOWBOARD%>"/>
				<form:input path="extrasBean.decalBoardQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/18_USSB_DecalSheet.jpg'/>" width="195" height="101" />
		</div>
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalFreeskiOption" value="<%=Inventory.INV_ID_BONUS_DECALS_FREESKI%>"/>
				<form:input path="extrasBean.decalFreeskiQty" maxlength="3"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/18_USFS_DecalSheet.jpg'/>" width="195" height="101" />
		</div>
		
	</div>

</div>

</form:form>

<div class="clear"></div>

</body>
