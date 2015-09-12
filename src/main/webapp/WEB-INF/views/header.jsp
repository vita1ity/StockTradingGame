<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title></title>
    
    <%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"> --%>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
	
	<script src="<c:url value="/resources/js/jquery-1.7.2.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery.timers.js"/>" type="text/javascript"></script> 
    <script src="<c:url value="/resources/js/script.js"/>" type="text/javascript"></script> 
    <script src="<c:url value="/resources/js/ajax.js"/>" type="text/javascript"></script>
    
	<link href='http://fonts.googleapis.com/css?family=Roboto:700,500,400,300,100' rel='stylesheet' type='text/css'>

	<style type="text/css"></style>
</head>
<c:if test="${empty loginUser}">
	<body class="notLogg" data-pinterest-extension-installed="cr1.38.2">
	<header class="main_head">
	    <div class="wrap">
	        <a class="logo" href="/market.html">
				<img src="<c:url value="/resources/images/logo.png"/>"/>
			</a>
	        <ul class="main_nav">
	            <li class="color1"><a href="<c:url value="/market"/>">Market</a></li>
	            <li class="color2"><a href="<c:url value="/account"/>">Account</a></li>
	        </ul>
	        <div class="fl-r">
	            <div class="login_zone">
	                <div class="signin" data-hover="SignIn">
	                    <span><a href="<c:url value="/login"/>">SignIn</a></span>
	                </div>
	                <div class="signup" data-hover="SignUp">
	                    <span><a href="<c:url value="/registration"/>">SignUp</a></span>
	                </div>
	            </div>
	        </div>
	    </div>
	</header>
</c:if>
<c:if test="${not empty loginUser}">
	<body class="logged" data-pinterest-extension-installed="cr1.38.2">
	<header class="main_head">
	    <div class="wrap">
	        <a class="logo" href="<c:url value="/market"/>">
				<img src="<c:url value="/resources/images/logo.png"/>"/>
			</a>
	        <ul class="main_nav">
	            <li class="color1"><a href="<c:url value="/market"/>">Market</a></li>
	            <li class="color2"><a href="<c:url value="/account"/>">Account</a></li>
	        </ul>
	        <div class="fl-r">
				<div class="bank">
					<span class="fa-stack fa-lg">
						<i class="fa fa-usd fa-stack-1x"></i>
						<i class="fa fa-circle-thin fa-stack-2x"></i>
					</span>
					
					<p id="accountBalance">${loginUser.accountBalance}</p>
				</div>
	            <div class="login_zone">
					<a class="user_av" style="background: url(<c:url value="/resources/images/${loginUser.imageName}"/>) no-repeat center center" 
					href="<c:url value="/account"/>"></a>
	                <div class="logout-btn" data-hover="LogOut">
	                    <span><a href="<c:url value="/logout"/>">LogOut</a></span>
	                </div>
	            </div>
	        </div>
	    </div>
	</header>
</c:if>