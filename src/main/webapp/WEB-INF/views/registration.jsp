<%@ include file="header.jsp" %>

<section class="maincontent s-color1" style="background: url(<c:url value="/resources/images/login/background.jpg"/>) no-repeat center center">
    <div class="s_star_header_bg" style="background: url(<c:url value="/resources/images/login/background.jpg"/>) no-repeat center center">
    </div>
    <div class="wrap reg">
		<h3>Registration</h3>
		<form class="reg_f" action="registration" method="POST">
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
				<c:if test="${not empty requestScope.errorMessage}">
					<font color='red'><span>Your registration was not successful, try again.
					Reason: ${requestScope.errorMessage}</span></font>
				</c:if> 
				<div class="register">
					<div class="login_zone">
						<div class="register" data-hover="Rgister">
							<input class="btn1" name="commit" type="submit" value="Register"/>
							<!-- <span>Register</span> -->
						</div>
					</div>
				</div>
			</ul>
		</form>
	</div>
	
</section>

<%@ include file="footer.jsp" %>
