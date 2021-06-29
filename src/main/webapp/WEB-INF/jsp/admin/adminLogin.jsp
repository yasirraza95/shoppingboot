<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, maximum-scale=1.0, minimum-scale=1.0, initial-scale=1.0">
	<title>Admin Login</title>
	

	<style type="text/css">
		@font-face {
			font-family: 'black_jackregular';
			src: url('../fonts/blackjack-webfont.eot');
			src: url('../fonts/blackjack-webfont.eot?#iefix') format('embedded-opentype'),
			url('../fonts/blackjack-webfont.woff') format('woff'),
			url('../fonts/blackjack-webfont.ttf') format('truetype'),
			url('../fonts/blackjack-webfont.svg#black_jackregular') format('svg');
			font-weight: normal;
			font-style: normal;
		}
		body {
			background: #0b3b69;
			font-family: arial;
			font-size: 14px;
			padding: 0;
			margin: 0;
			width: 100%;
		}
		/*<========CSS Reset Properties========>*/
		h1, p, form, input, div, img, label {
			padding: 0;
			margin: 0;
		}
		a {
			text-decoration: none;
			color: rgba(220,220,220,1);
			padding: 0;
			margin: 0;
		}
		/*<===============================================>*/
		/*<========Top to Bottom ordered CSS rules========>*/
		.container {
			overflow: hidden;
		}
		.loginForm {
			margin: 1em auto 0 auto;
			width: 22.8em;
			height: 25em;
		}
		header h1 {
			color: rgba(240,240,240,1);
			font-family: 'black_jackregular';
			font-weight: bold;
			font-size: 8em;
			font-variant: small-caps;
			line-height: 1.5;
			padding: 0.2em 0 0 0;
			height: 1.5em;
			width: 100%;
			text-align: center;
			text-shadow: 1px 1px 4px rgba(10,10,10,1);
		}
		input {
			background: rgba(228,232,243, 0.8) url("../images/sprite.png") no-repeat;
			background-size: 20px 80px;
			background-position: 0.5em 0.6em;
			border: none;
			color: rgba(80,80,80,1);
			padding: 0 0 0 4em;
			margin: 0 0 1em 0;
			width: 20em;
			height: 2.8em;
			outline: none;
			-webkit-transition: background-color 0.4s;
			transition: background-color 0.4s;
		}
		input:hover {
			background-color: rgba(255,255,255, 0.8);
		}
		input:focus {
			background-color: rgba(255,255,255, 0.8);
		}
		label {
			color: rgba(220,220,220,1);
		}
		.profile {
			height: 8em;
			width: 8.5em;
			margin: 0 0 2em 7em;
		}
		.password {
			background-position: 0.5em -3.8em;
		}
		.remember {
			background: none;
			width: 1em;
			height: 1em;
			margin: 0.5em 0.5em 0 0;
		}
		.recovery {
			display: block;
			margin: -6.3em 0 0 14.5em;
		}
		.recovery:hover {
			text-decoration: underline;
		}
		.submit {
			background: rgba(35,35,35, 0.6);
			color: rgba(220,220,220,1);
			padding: 0 0 0 0;
			margin: 2.5em 0 0 7em;
			width: 10em;
			text-transform: uppercase;
			cursor: pointer;
			-webkit-transition: background 0.4s;
			transition: background 0.4s;
		}
		.submit:hover {
			background: rgba(223,32,77, 0.8);
		}
		.submit:focus {
			background: rgba(223,32,77, 0.8);
		}
		@media only screen and (max-width: 480px) {
			header h1 {
				font-size: 4em;
			}
			.loginForm {
				margin: 0.5em auto 0 auto;
			}
		}

	</style>


</head>
<body>
	<div class="container">
		<header>
			<h1>Admin</h1>
		</header>
		<div class="loginForm">
			<img id="profile" class="profile" src="<c:url value="/assets/images/profile.png"></c:url>" alt="Profile Picture">
			<c:if test="${error != null}">
				<p style="text-align: center; color: red; font-size: 16px;">
					${error}
				<p>
				<br/>
			</c:if>
			<c:remove var="error" scope="request" />
			
			<%-- <sf:form action="${pageContext.request.contextPath}/admin" method="POST" modelAttribute="user"> --%>
			<c:url var="processingURL" value="/admin/spring_security_login"/>
			<form action="${processingURL}" method="POST">
				<input type="text" name="username" class="username"  placeholder="Enter username" /><br>
				<%-- <sf:errors path="username" cssClass="error" /> --%>
				
				<input type="password" name="password" class="password" placeholder="Enter password" /><br>
				<%-- <sf:errors path="password" cssClass="error" /> --%>
				
				<input id="submit" class="submit" type="submit" name="submit" value="Login">
			</form>
		
		</div>
	</div>
</body>
</html>