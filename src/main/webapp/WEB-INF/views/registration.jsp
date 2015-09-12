<%@ include file="header.jsp" %>

<section class="maincontent s-color1" style="background: url(<c:url value="/resources/images/login/background.jpg"/>) no-repeat center center">
	
    <div class="wrap">
		<div class="reg">
			<form class="reg_f" action="registration" method="POST">
				
				<h3>Registration</h3>
				<ul>
					
					<li>
						<span class="inp_field_name">First Name</span>
						<input type="text" required name="firstName">
					</li>
					<li>
						<span class="inp_field_name">Last Name</span>
						<input type="text" required name="lastName">
					</li>
					<li>
						<span class="inp_field_name">E-mail</span>
						<input type="text" required name="email">
					</li>
					<li>
						<span class="inp_field_name">Password</span>
						<input type="password" required name="password">
					</li>
					<li>
						<span class="inp_field_name">Repeat Password</span>
						<input type="password" required name="passwordConfirmation">
					</li>
					<li>
						<input type="hidden" name="from" value="${referrer}">
					</li>
					<li>
						<input id="firstStock" type="hidden" name="firstStock" value="">
					</li>
					<li>
						<input id="secondStock" type="hidden" name="secondStock" value="">
					</li>
					<li>
						<input id="thirdStock" type="hidden" name="thirdStock" value="">
					</li>
					<li>
						<input id="forthStock" type="hidden" name="forthStock" value="">
					</li>
					<li>
						<input id="fifthStock" type="hidden" name="fifthStock" value="">
					</li>
				</ul>
				<c:if test="${not empty requestScope.errorMessage}">
					<span class="error-register-message">${requestScope.errorMessage}</span>
				</c:if> 
				
				
				<div class="register">
					<div class="login_zone">
						<div class="register" data-hover="Rgister" >
							
							<input id="registerButton" class="btn1 disabledRegister" name="commit" type="submit" value="Register"/>
							<!-- <span>Register</span> -->
						</div>
					</div>
				</div>
			</form>
		</div>
			
		<div class="oh">		
			
			<hr>
				
			<h3>Get 5 Stars For Free!</h3>
			
			<div class="slider-wrap slide-1">
				<div class="slider" id="allStocks">
					<c:forEach var="stock" items="${allStocks}" varStatus="loop">
						<c:if test="${loop.index % 4 == 0}">
							<div class="slide">
								<ul class="stars-list">
								
						</c:if>
						
						<li class="star-item" id="${stock.code}">
							<div class="star-item_no_a" style="background: url(<c:url value="/resources/images/content/${stock.imageName}"/>) no-repeat center center">
						
								<span class="star-hover bought">
								
									<div class="msg">
										<p></p>
									</div>
									
									<div class="desc">
									
										<div class="option">Available Stock</div>
										<c:url value="/buy-stock/${stock.code}" var="addStock"/>
										<div class="buybtn" id="addStock" data-url="${addStock}">
											<span>ADD</span>
										</div>
										
										<form class="desc_container">
											<h2 class="name">
												<a href="<c:url value="/stock/${stock.code}"/>">${stock.firstName}
												<br />${stock.lastName}</a>
											</h2>
											
											<input type="hidden" value="${stock.sellPrice}" class="price_inp rightcolumn" id="price_inp">
										</form>
										
									</div>
								</span>
	
								<div class="star-name">
									<h2>
										<span>${stock.firstName}<br />${stock.lastName}</span>
									</h2>
									<span class="star-price">$ ${stock.sellPrice}</span>
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
		<span class="error-message" id="disabledMessage"></span>
	</div>
	
</section>

<%@ include file="footer.jsp" %>
