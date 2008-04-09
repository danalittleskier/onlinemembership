<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Extras</div>

<form:form commandName="accountBean" name="accountBean">

<!-- LEFT column -->
<div id="stg-twocol-primary">

<%@ include file="/includes/messages.jsp"%>


<div class="extras-header">
	<p>Shirts</p>
</div>
<p>Bonus Packs include a high quality 100% cotton U.S. Ski Team or U.S.
	Snowboarding t-shirt, decal pack and a 2009 collectors lapel pin (or snowboarding
	die-cut decal). <strong>Each pack is $20 including shipping.</strong></p>
<p>Choose from 6 styles: U.S. Ski Team, Freestyle, Cross Country, Jumping
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
		<a href="javascript:void(0);" title="Enlarge Image" onclick="document.getElementById('bonus-popup').style.display = 'block',document.getElementById('popup-content').className = 'alpine',document.getElementById('title-text').innerHTML = 'Alpine';">
			<img src="<c:url value='/images/bp-alpine.gif'/>" height="141" width="145">
			</a>
	</div>
	<div class="tshirt-2">
		<p>Freestyle</p>
		<a href="javascript:void(0);" title="Enlarge Image" onclick="document.getElementById('bonus-popup').style.display = 'block',document.getElementById('popup-content').className = 'freestyle',document.getElementById('title-text').innerHTML = 'Freestyle';">
			<img src="<c:url value='/images/bp-freestyle.gif'/>" height="141" width="145">
		</a>
	</div>
	<div class="tshirt-3">
		<p>Cross Country</p>
		<a href="javascript:void(0);" title="Enlarge Image" onclick="document.getElementById('bonus-popup').style.display = 'block',document.getElementById('popup-content').className = 'crosscountry',document.getElementById('title-text').innerHTML = 'Cross Country';">
			<img src="<c:url value='/images/bp-crosscountry.gif'/>" height="141" width="145">
			</a>
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.alpineOption">
					<form:option value=""></form:option>
					<%-- TODO: add these invetory ids as contants in Inventory.java --%>
					<form:option value="BPAS">S</form:option>
					<form:option value="BPAM">M</form:option>
					<form:option value="BPAL">L</form:option>
					<form:option value="BPAX">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.alpineQty"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.freestyleOption">
					<form:option value=""></form:option>
					<form:option value="BPFS">S</form:option>
					<form:option value="BPFM">M</form:option>
					<form:option value="BPFL">L</form:option>
					<form:option value="BPFX">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.freestyleQty"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.crossCountryOption">
					<form:option value=""></form:option>
					<form:option value="BPXS">S</form:option>
					<form:option value="BPXM">M</form:option>
					<form:option value="BPXL">L</form:option>
					<form:option value="BPXX">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.crossCountryQty"/>
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
		<a href="javascript:void(0);" title="Enlarge Image" onclick="document.getElementById('bonus-popup').style.display = 'block',document.getElementById('popup-content').className = 'jumpingnc',document.getElementById('title-text').innerHTML = 'Jumping N/C';">
			<img src="<c:url value='/images/bp-jumpingnc.gif'/>" height="141" width="145">
		</a>
	</div>
	<div class="tshirt-2">
		<p>US Ski Team General</p>
		<a href="javascript:void(0);" title="Enlarge Image" onclick="document.getElementById('bonus-popup').style.display = 'block',document.getElementById('popup-content').className = 'skigeneral',document.getElementById('title-text').innerHTML = 'US Ski Team General';">
			<img src="<c:url value='/images/bp-skigeneral.gif'/>" height="141" width="145">
		</a>
	</div>
	<div class="tshirt-3">
		<p>Snowboarding</p>
		<a href="javascript:void(0);" title="Enlarge Image" onclick="document.getElementById('bonus-popup').style.display = 'block',document.getElementById('popup-content').className = 'snowboarding',document.getElementById('title-text').innerHTML = 'Snowboarding';">
			<img src="<c:url value='/images/bp-snowboarding.gif'/>" height="141" width="145">
		</a>
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.jumpingOption">
					<form:option value=""></form:option>
					<form:option value="BPFS">S</form:option>
					<form:option value="BPFM">M</form:option>
					<form:option value="BPFL">L</form:option>
					<form:option value="BPFX">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.jumpingQty"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.generalOption">
					<form:option value=""></form:option>
					<form:option value="BPFS">S</form:option>
					<form:option value="BPFM">M</form:option>
					<form:option value="BPFL">L</form:option>
					<form:option value="BPFX">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.generalQty"/>
			</label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.boardingOption">
					<form:option value=""></form:option>
					<form:option value="BPFS">S</form:option>
					<form:option value="BPFM">M</form:option>
					<form:option value="BPFL">L</form:option>
					<form:option value="BPFX">XL</form:option>
				</form:select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<form:input path="extrasBean.boardingQty"/>
			</label>
		</div>
	</div>
	<div class="clear"></div>
</div>
<p><img src="<c:url value='/images/magazines.jpg'/>" style="float: right;" height="165" width="176"><strong>ADDED
	BONUS!</strong><br>
	A one-year subscription to SKI Magazine (7-issues, a $9 value) or Transworld
	Snowboarding Magazine (9-issues, a $5 value) is included with your bonus
	Pack purchase. Offer is refundable. Instructions will be mailed.</p>
<p>* One per household. Valid in the U.S. only.</p>

<fieldset class="buttons">
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="btn-green" name="_eventId_add" value="Add Selections to Cart">
	<div style="float: left; margin-left: 8px;">
		<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
		<input type="submit" class="btn-green" name="_eventId_back" value="Back">
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
		<p>Decals</p>
	</div>
	<p>Choose from 2 decal packs: U.S. Ski Team and US Snowboarding. <br />
		<strong>Decal are $5 for a pack of 5.</strong></p>
	<div class="decal-row">
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalSkiOption" value="DECSKI"/>
				<form:input path="extrasBean.decalSkiQty"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/decal_skiteam.gif'/>" width="195" height="101" /> </div>
		<div class="decal">
			<div class="decal-qty">
				<label>Qty:</label>
				<input type="hidden" name="extrasBean.decalBoardOption" value="DECSB"/>
				<form:input path="extrasBean.decalBoardQty"/>
				<span class="packs-of-five">Pack(s) of 5</span> </div>
			<img src="<c:url value='/images/decal_snowboarding.gif'/>" width="195" height="101" />
		</div>
	</div>

</div>

</form:form>

<div class="clear"></div>

</body>
