
<%@ include file="header.jsp" %>

<section class="market-page maincontent catalog s-color1">
<div class="market_bg" style="background: url(<c:url value="/resources/images/bg.png"/>) no-repeat center center">
<div class="wrap market_normal">
<div class="leftcontent">
<div class="tabs_wrap main_section">
    <h3>Market</h3>
    
    <input id="refreshUrl" type="hidden" name="refreshUrl" value=<c:url value="/refresh-stocks"/>>
    <marquee id="allStockScroll">
    	<c:forEach var="stock" items="${allStocks}">
    		
			<span class="stock_name">${stock.firstName} ${stock.lastName}</span>
			<span class="tr_price">
				<c:if test="${stock.up}">
					<div class="arr" style="background: url(<c:url value="/resources/images/up.png"/>) no-repeat center center"></div>
				</c:if>
				<c:if test="${not stock.up}">
					<div class="arr" style="background: url(<c:url value="/resources/images/down.png"/>) no-repeat center center"></div>
				</c:if>
				Traded Price: <span class="money">$${stock.lastTraded}</span>
			</span>
			
		</c:forEach>
	
	</marquee>
   
    <ul id="starList" class="stars-list">
    	<c:forEach var="userStock" items="${stockSet}" varStatus="loop">
    		<%-- <input id="index" type="hidden" name="index" value="${loop.index}"> --%>
			<li class="star-item" id="${userStock.stock.code}">
				<div class="star-item_no_a" style="background: url(<c:url value="/resources/images/content/${userStock.stock.imageName}"/>) no-repeat center center">
	                <c:if test="${not empty loginUser}">
	                	
	                	<c:if test="${userStock.relation == 'AVAILABLE'}">
			                <span class="star-hover">
			                
			                	<div class="msg">
									<p></p>
								</div>
								
			                    <div class="desc">
			                    	
			                    	
			                    	<c:url value="/buy-stock/${userStock.stock.code}" var="buyStock"/>
									<div class="buybtn" id="buyStock" data-url="${buyStock}">
										<span>BUY</span>
									</div>
									
									<span class="desc_container">
										
										<h2 class="name"><a href="<c:url value="/stock/${userStock.stock.code}"/>">${userStock.stock.firstName} <br />${userStock.stock.lastName}</a></h2>
										<span class="star-price-icon rightcolumn">$</span>
										<input type="text" <%-- placeholder="${userStock.stock.sellPrice}" --%> class="price_inp rightcolumn" id="buyPrice" value="${userStock.stock.sellPrice}">
									</span>
									
								</div>
								
			                </span>	
		                </c:if>
		                <c:if test="${userStock.relation == 'BOUGHT'}">
			                <span class="star-hover bought">
			                	<div class="msg">
									<p></p>
								</div>
	                    		<div class="desc">
						
									
						
									<div class="option">Your Stock</div>
									<c:url value="/sell-stock/${userStock.stock.code}" var="sellStock"/>
									<div class="buybtn" id="sellStock" data-url="${sellStock}">
										<span>SELL</span>
									</div>
									
									<span class="desc_container">
										<h2 class="name"><a href="<c:url value="/stock/${userStock.stock.code}"/>">${userStock.stock.firstName} <br />${userStock.stock.lastName}</a></h2>
										<span class="star-price-icon rightcolumn">$</span>
										<input type="text" value="${userStock.stock.sellPrice}" class="price_inp rightcolumn" id="price_inp">
									</span>
								
								</div>
		               	    </span>
		               	    
						</c:if>
						<c:if test="${userStock.relation == 'PLACED_ORDER'}">               	    
		               	    <span class="star-hover ordered">
	               	    		<div class="msg">
									<p></p>
								</div>
			                    <div class="desc">
								
									<div class="option">Your Order</div>
									<c:url value="/cancel-order/${userStock.stock.code}" var="cancelOrder"/>
									<div class="buybtn" id="cancelOrder" data-url="${cancelOrder}">
										<span>CANCEL</span>
									</div>
									
									<span class="desc_container">
										<h2 class="name"><a href="<c:url value="/stock/${userStock.stock.code}"/>">${userStock.stock.firstName} <br />${userStock.stock.lastName}</a></h2>
										<div class="star-price rightcolumn">$${userStock.order.price}</div>
									</span>
									
								</div>
			                </span>
		                </c:if>
	                </c:if>	
	                <c:if test="${empty loginUser}">
	                	<div class="star-name_new">
							<h2>
								<span><a href="<c:url value="/stock/${userStock.stock.code}"/>">${userStock.stock.firstName} <br />${userStock.stock.lastName}</a></span>
							</h2>
							<span class="star-price">$ ${userStock.stock.sellPrice}</span>
		                </div>
	                </c:if>
	                <c:if test="${not empty loginUser}">
		                <div class="star-name">
							<h2>
								<span>${userStock.stock.firstName} <br />${userStock.stock.lastName}</span>
							</h2>
							<c:if test="${userStock.relation == 'BOUGHT'}">
								<span class="star-price">$ ${userStock.stock.buyPrice}</span>
							</c:if>
							<c:if test="${userStock.relation != 'BOUGHT'}">
								<span class="star-price">$ ${userStock.stock.sellPrice}</span>
							</c:if>
		                </div>
	                </c:if>
	            </div>
	        </li>
        
		</c:forEach>
    </ul>

	
	<input id="numOfPages" type="hidden" name="pages" value="${numberOfPages}">
	<input id="currentPage" type="hidden" name="currentPage" value="${currentPage}">
	<input id="pageUrl" type="hidden" name="pageUrl" value=<c:url value="/marketPage?page=${currentPage}"/>>
	
    <ul id="pager" class="pager">
		<c:if test="${currentPage != 1}">
			<c:url value="/marketPage?page=${currentPage - 1}" var="page"/>
			<li class="prev pager_nav pager_item" data-page="${page}"><a>Prev</a></li>
	    </c:if>
		<c:forEach begin="1" end="${numberOfPages}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<li class="pager_item"><a>${i}</a></li>
				</c:when>
				<c:otherwise>
					<c:url value="/marketPage?page=${i}" var="page"/>
					<li class="pager_item" data-page="${page}"><a>${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage lt numberOfPages}">
			<c:url value="/marketPage?page=${currentPage + 1}" var="page"/>
			<li class="next pager_nav pager_item" data-page="${page}"><a>Next</a></li>
		</c:if>
	</ul>
</div>

</div>
</div>
</div>
</section>

<%@ include file="footer.jsp" %>
