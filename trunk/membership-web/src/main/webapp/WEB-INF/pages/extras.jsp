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
<p style="text-align:center;">T-shirts are <strong>$20 each</strong> and are available in S, M, L, XL and XXL sizes.</p>
<div class="tshirt-row">
	<div class="tshirt-1">
		<p>Alpine</p>
		<img src="<c:url value='/images/t-shirt.jpg'/>" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>Freestyle</p>
		<img src="<c:url value='/images/t-shirt.jpg'/>" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>Cross Country</p>
		<img src="<c:url value='/images/t-shirt.jpg'/>" width="145" height="136" />
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<form:select path="extrasBean.alpineOption">
					<form:option value=""></form:option>
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
</div>
<div class="tshirt-row">
	<div class="tshirt-1">
		<p>Jumping N/C</p>
		<img src="<c:url value='/images/t-shirt.jpg'/>" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>Snowboarding</p>
		<img src="<c:url value='/images/t-shirt.jpg'/>" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>US Ski Team General</p>
		<img src="<c:url value='/images/t-shirt.jpg'/>" width="145" height="136" />
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
	<div class="tshirt-menu-3">
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
	<div class="clear"></div>
</div>

<div class="extras-header">
	<p>Decals</p>
</div>
<p style="text-align:center;">Decals <strong>start at $5/pack of 5</strong> and are approximately 4 inches across in size.</p>

<div class="decal-row">
	<div class="decal-1">
		<p>US Ski Team</p>
		<img src="<c:url value='/images/decal_skiteam.gif'/>" width="106" height="97" />
	</div>
	<div class="decal-1">
		<p>Snowboarding</p>
		<img src="<c:url value='/images/decal_snowboarding.gif'/>" width="149" height="97" />
	</div>
	<div class="decal-menu-1">
		<div class="decal-pack">
			<label>Qty / Price:
				<select>
					<option selected></option>
					<option value="">5 for $5</option>
					<option value="">12 for $10</option>
					<option value="">20 for $15</option>
				</select>
			</label>
		</div>
		<!-- Hide for now ...
		<div class="decal-qty">
		<label>Qty:
			<input name="" type="text" value="" /></label>
		</div>
		... End Hiding -->
	</div>
	<div class="decal-menu-2">
		<div class="decal-pack">
			<label>Qty / Price:
				<select>
					<option selected></option>
					<option value="">5 for $5</option>
					<option value="">12 for $10</option>
					<option value="">20 for $15</option>
				</select>
			</label>
		</div>
		<!-- Hide for now ...
		<div class="decal-qty">
		<label>Qty:
			<input name="" type="text" value="" /></label>
		</div>
		... End Hiding -->
	</div>
</div>
<p style="text-align:center; clear:both;"><em>Bonus Packs not available for shipment outside the U.S. <br />
	Please allow 4-6 weeks for delivery.</em></p>

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

<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="btn-green" name="_eventId_add" value="Add Selections to Cart">
	<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
	<input type="submit" class="btn-green" name="_eventId_back" value="Back">
</fieldset>

</div>

</form:form>

<div class="clear"></div>

</body>
