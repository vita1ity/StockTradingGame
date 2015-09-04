<%@ include file="header.jsp" %>
	
<section class="maincontent s-color1">
    <div class="s_star_header" style="background: url(<c:url value="/resources/images/content/${stock.stock.imageName}"/>) no-repeat center center">
    	
        <div class="wrap s_star_header_contrast">

            <div class="star-item" style="background: url(<c:url value="/resources/images/content/${stock.stock.imageName}"/>) no-repeat center center">
            </div>

            <div class="star_desc_sp" id="starDescription">
                
				<h3>${stock.stock.firstName} ${stock.stock.lastName}</h3>
                <p>${stock.stock.description}</p>
				
				<div class="rates">
				
					<span class="lt"><p>Last Traded<br><span class="prices">$ ${stock.stock.lastTraded}</span></p></span>
					<span class="lt"><p>Best Buy Price<br><span class="prices">$ ${stock.stock.buyPrice}</span></p></span>
					<span class="lt"><p>Best Sell Price <br><span class="prices">$ ${stock.stock.sellPrice}</span></p></span>
					
				</div>
				<c:if test="${not empty loginUser}">
					<c:if test="${stock.relation == 'AVAILABLE'}">
						<div class="set_price">
							<p>Stock Order</p>
							<span class="prices_in">$</span>
							<input id="stockPrice" type="text" value="${stock.stock.sellPrice}" class="price_inp pos" id="price_inp">
							<c:url value="/buy-stock/${stock.stock.code}" var="buyStock"/>
							<div class="s_buybtn buybtn" id="buyStockPage" data-url="${buyStock}">
								<span>ORDER</span>
							</div>
						</div>
					</c:if>
					<c:if test="${stock.relation == 'BOUGHT'}">
						<div class="set_price">
							<p>Your Stock</p>
							<span class="prices_i">$</span>
							<input type="text" value="${stock.stock.sellPrice}" class="price_inp pos" id="price_inp">
							<c:url value="/sell-stock/${stock.stock.code}" var="sellStock"/>
							<div class="s_buybtn buybtn" id="sellStockPage" data-url="${sellStock}">
								<span>SELL</span>
							</div>
						</div>
					</c:if>
					<c:if test="${stock.relation == 'PLACED_ORDER'}"> 
						<div class="set_price">
							<p>Your Order</p>
							<span class="prices">$ ${stock.order.price}</span>
							<c:url value="/cancel-order/${stock.stock.code}" var="cancelOrder"/>
							<div class="s_buybtn buybtn" id="cancelOrderPage" data-url="${cancelOrder}">
								<span>CANCEL</span>
							</div>
						</div>
					</c:if>
					
				</c:if>
				
				
			</div>

        </div>
    </div>
	
	<div class="clear"></div>

</section>

<%@ include file="footer.jsp" %>