<%@ include file="header.jsp" %>

<section class="maincontent s-color1" style="background: url(<c:url value="/resources/images/login/background.jpg"/>) no-repeat center center">
    <div class="s_star_header_bg" style="background: url(<c:url value="/resources/images/login/background.jpg"/>) no-repeat center center">
    </div>
	<div class="wrap reg">
		<h3>Login</h3>
		<form class="reg_f" action="login" method="POST">
			<ul>
				<li><span class="inp_field_name">E-mail</span><input type="text" name="username"></li>
				<li><span class="inp_field_name">Password</span><input type="password" name="password"></li>
				<input type="hidden" name="from" value="${referrer}">
				<c:if test="${not empty requestScope.errorMessage}">
					<font color='red'><span>Your login attempt was not successful, try again.
					Reason: ${requestScope.errorMessage}</span></font>
				</c:if> 
				<li><div class="login_pad">
					<div class="login_zone">
						<div class="register" data-hover="SignIn">
							<input class="btn1" name="commit" type="submit" value="SignIn"/>
							<!-- <span type="submit">SignIn</span> -->
						</div>
					</div>
				</div></li>
			</ul>
		</form>
	</div>
	
</section>


<%@ include file="footer.jsp" %>
