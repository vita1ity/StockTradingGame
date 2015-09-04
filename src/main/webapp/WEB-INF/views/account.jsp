<%@ include file="header.jsp" %>

<section class="maincontent s-color1">
	<div class="wrap">
		<div class="main_section">
			<div class="usr">
				<div class="user_photo" style="background: url(<c:url value="/resources/images/${loginUser.imageName}"/>) no-repeat center center"></div>
				<ul>
					<li><span class="s_text">Name: </span>${loginUser.firstName} ${loginUser.lastName}</li>
					<li><span class="s_text">E-mail: </span>${loginUser.email}</li>
				</ul>
				<h3 class="balance">Balance: $${loginUser.accountBalance}</h3>
			</div>
			
			<hr>
			
			<h3 class="mt">My Stocks</h3>
			
			<div class="slider-wrap slide-1">
				<div class="slider" id="userStocks">
					<c:forEach var="stockOrder" items="${userStocks}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0}">
							<div class="slide">
								<ul class="stars-list">
								
						</c:if>
						
						<li class="star-item" id="${stockOrder.key.code}">
							<div class="star-item_no_a" style="background: url(<c:url value="/resources/images/content/${stockOrder.key.imageName}"/>) no-repeat center center">
						
								<span class="star-hover bought">
									<div class="desc">
									
										<div class="option">Your Stock</div>
										<c:url value="/sell-stock/${stockOrder.key.code}" var="sellStock"/>
										<div class="buybtn" id="sellStockAccount" data-url="${sellStock}">
											<span>SELL</span>
										</div>
										
										<form class="desc_container">
											<h2 class="name">
												<a href="<c:url value="/stock/${stockOrder.key.code}"/>">${stockOrder.key.firstName}
												<br />${stockOrder.key.lastName}</a>
											</h2>
											<span class="star-price-icon rightcolumn">$</span>
											<input type="text" value="${stockOrder.key.sellPrice}" class="price_inp rightcolumn" id="price_inp">
										</form>
										
									</div>
								</span>

								<div class="star-name">
									<h2>
										<span>${stockOrder.key.firstName}<br />${stockOrder.key.lastName}</span>
									</h2>
									<span class="star-price">$ ${stockOrder.key.sellPrice}</span>
								</div>
							</div>
						</li>
						<c:if test="${loop.index % 4 == 3}">
								</ul>
							</div>
						</c:if>
						
					</c:forEach>
				
				</div>
			</div>
			</div>
			<hr>
			
			<h3 class="mt">My Pending Orders</h3>
			
			<div class="slider-wrap slide-1">
				<div class="slider" id="pendingOrders">
				<c:forEach var="stockOrder" items="${requestedStocks}" varStatus="loop">
					<c:if test="${loop.index % 4 == 0}">
						<div class="slide">
							<ul class="stars-list">
						
					</c:if>
						
					<li class="star-item" id="${stockOrder.key.code}">
						<div class="star-item_no_a" style="background: url(<c:url value="/resources/images/content/${stockOrder.key.imageName}"/>) no-repeat center center">
							<span class="star-hover ordered">
								<div class="desc">
								
									<div class="option">Your Order</div>
									<c:url value="/cancel-order/${stockOrder.key.code}" var="cancelOrder"/>
									<div class="buybtn" id="cancelOrderAccount" data-url="${cancelOrder}">
										<span>CANCEL</span>
									</div>
									
									<span class="desc_container">
										<h2 class="name"><a href="<c:url value="/stock/${stockOrder.key.code}"/>">${stockOrder.key.firstName} <br />${stockOrder.key.lastName}</a></h2>
										<div class="star-price rightcolumn">$${stockOrder.value.price}</div>
										
									</span>
									
								</div>
							</span>
							<div class="star-name">
								<h2>
									<span>${stockOrder.key.firstName}<br />${stockOrder.key.lastName}</span>
								</h2>
								<span class="star-price">$ ${stockOrder.key.sellPrice}</span>
							</div>
						</div>
					</li>
						
					<c:if test="${loop.index % 4 == 3}">	
							</ul>
						</div>
					</c:if>
				</c:forEach>
				</div>
			</div>
			
		</div>
	</div>
</section>

<%@ include file="footer.jsp" %>