<%@ include file="/common/taglibs.jsp"%>
<body>
<!-- Progress bar -->
<div id="stg-progress"><img src="<c:url value='/images/progress_1.gif'/>" width="917" height="53" /></div>
<div id="stg-pagetitle">Extras</div>

<!-- LEFT column -->
<div id="stg-twocol-primary">
<form:form commandName="accountBean" name="accountBean">

<%@ include file="/includes/messages.jsp"%>


<div class="extras-header">
	<p>Shirts</p>
</div>
<p style="text-align:center;">T-shirts are <strong>$20 each</strong> and are available in S, M, L, XL and XXL sizes.</p>
<div class="tshirt-row">
	<div class="tshirt-1">
		<p>Alpine</p>
		<img src="images/t-shirt.jpg" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>Freestyle</p>
		<img src="images/t-shirt.jpg" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>Cross Country</p>
		<img src="images/t-shirt.jpg" width="145" height="136" />
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<select>
					<option selected></option>
					<option value="">S</option>
					<option value="">M</option>
					<option value="">L</option>
					<option value="">XL</option>
					<option value="">XXL</option>
				</select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<input name="" type="text" value="" /></label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<select>
					<option selected></option>
					<option value="">S</option>
					<option value="">M</option>
					<option value="">L</option>
					<option value="">XL</option>
					<option value="">XXL</option>
				</select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<input name="" type="text" value="" /></label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<select>
					<option selected></option>
					<option value="">S</option>
					<option value="">M</option>
					<option value="">L</option>
					<option value="">XL</option>
					<option value="">XXL</option>
				</select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<input name="" type="text" value="" /></label>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div class="tshirt-row">
	<div class="tshirt-1">
		<p>Jumping N/C</p>
		<img src="images/t-shirt.jpg" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>Snowboarding</p>
		<img src="images/t-shirt.jpg" width="145" height="136" />
	</div>
	<div class="tshirt-1">
		<p>US Ski Team General</p>
		<img src="images/t-shirt.jpg" width="145" height="136" />
	</div>
	<div class="tshirt-menu-1">
		<div class="tshirt-size">
			<label>Size:
				<select>
					<option selected></option>
					<option value="">S</option>
					<option value="">M</option>
					<option value="">L</option>
					<option value="">XL</option>
					<option value="">XXL</option>
				</select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<input name="" type="text" value="" /></label>
		</div>
	</div>
	<div class="tshirt-menu-2">
		<div class="tshirt-size">
			<label>Size:
				<select>
					<option selected></option>
					<option value="">S</option>
					<option value="">M</option>
					<option value="">L</option>
					<option value="">XL</option>
					<option value="">XXL</option>
				</select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<input name="" type="text" value="" /></label>
		</div>
	</div>
	<div class="tshirt-menu-3">
		<div class="tshirt-size">
			<label>Size:
				<select>
					<option selected></option>
					<option value="">S</option>
					<option value="">M</option>
					<option value="">L</option>
					<option value="">XL</option>
					<option value="">XXL</option>
				</select>
			</label>
		</div>
		<div class="tshirt-qty">
			<label>Qty:
				<input name="" type="text" value="" /></label>
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
		<img src="images/decal_skiteam.gif" width="106" height="97" />
	</div>
	<div class="decal-1">
		<p>Snowboarding</p>
		<img src="images/decal_snowboarding.gif" width="149" height="97" />
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

<fieldset class="buttons">
	<label></label>
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
	<input type="submit" class="btn-green" name="_eventId_add" value="Add Selections to Cart">
	<input type="submit" class="btn-green" name="_eventId_next" value="Continue">
	<input type="submit" class="btn-green" name="_eventId_back" value="Back">
</fieldset>

</div>
<div class="clear"></div>

</body>
