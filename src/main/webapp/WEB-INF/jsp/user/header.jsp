<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:url var="userIndexURL" value="/user/" />
<c:url var="userAboutURL" value="/user/about" />
<c:url var="userVegetablesURL" value="/user/products?prod=vegetables" />
<c:url var="userFruitsURL" value="/user/products?prod=fruits" />
<c:url var="userContactURL" value="/user/contact" />
<c:url var="userProfileURL" value="/user/profile" />
<c:url var="userPasswordURL" value="/user/password" />
<c:url var="userOrdersURL" value="/user/orders" />
<c:url var="userDetailURL" value="/user/detail" />
<c:url var="checkoutURL" value="/user/checkout" />
<c:url var="userCartURL" value="/user/cart" />
<c:url var="userLoginURL" value="/user/login" />
<c:url var="userSignupURL" value="/user/signup" />
<c:url var="userLogoutURL" value="/user/logout" />

<c:url var="userCssLink" value="/user/assets/css" />
<c:url var="userJsLink" value="/user/assets/js" />
<c:url var="userImgLink" value="/user/assets/images" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>TGT</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="${userImgLink}/favicon.ico" type="image/x-icon">
	<link rel="apple-touch-icon" href="${userImgLink}/apple-touch-icon.png">
	<link rel="stylesheet" href="${userCssLink}/bootstrap.min.css">
	<link rel="stylesheet" href="${userCssLink}/style.css">
	<link rel="stylesheet" href="${userCssLink}/responsive.css">
	<link rel="stylesheet" href="${userCssLink}/custom.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<style>
.error {
	color:red;
}
</style>	
</head>

<body>

    <header class="main-header">
        <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                    <a class="navbar-brand" href="${userIndexURL}"><img src="${userImgLink}/logo.png" class="logo" alt=""></a>
                </div>




                <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="${userIndexURL}">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="${userAboutURL}">About Us</a></li>
                        <li class="dropdown">
                            <a href="javascript:void(0)" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Products</a>
                            <ul class="dropdown-menu">
								<li><a href="${userVegetablesURL}">Vegetables</a></li>
								<li><a href="${userFruitsURL}">Fruits</a></li>
                                <!-- <li><a href="cart.jsp">Cart</a></li>
                                <li><a href="checkout.jsp">Checkout</a></li>
                                <li><a href="account.jsp">My Account</a></li>
                                <li><a href="wishlist.jsp">Wishlist</a></li> -->
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="${userContactURL}">Contact Us</a></li>
                       
                       <sec:authorize access="isAuthenticated() and hasRole('USER')">
                       <li class="dropdown">
                            <a href="#" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">Account</a>
                            <ul class="dropdown-menu">
								<li><a href="${userProfileURL}">Profile</a></li>
								<li><a href="${userPasswordURL}">Password</a></li>
                                <li><a href="${userOrdersURL}">Orders</a></li>
                            </ul>
                        </li>
                        </sec:authorize>
                        
                        <sec:authorize access="!isAuthenticated()">
						    <li class="nav-item"><a class="nav-link" href="${userLoginURL}">Login</a></li>
    	                    <li class="nav-item"><a class="nav-link" href="${userSignupURL}">Sign Up</a></li> 
						</sec:authorize>
                        
                        <sec:authorize access="isAuthenticated() and hasRole('USER')">
						    <li class="nav-item"><a class="nav-link" href="${userLogoutURL}">Logout</a></li> 
						</sec:authorize>
                        
                    </ul>
                </div>
                <div class="attr-nav">
                    <ul>
                        <li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
                        
                        <sec:authorize access="isAuthenticated() and hasRole('USER')">
						<li>
							<a href="${userCartURL}">
								<i class="fa fa-shopping-bag"></i>
								
								<span class="badge">${customerSession.cartQty}</span>
								<%-- <c:forEach var="row" items="${cartRes.rows}">
									<span class="badge">${row.total}</span>
								</c:forEach> --%>
							<p>My Cart</p>
							</a>
						</li>
                        </sec:authorize>
                        
                    </ul>
                </div>
            </div>

            <div class="side">
                <a href="#" class="close-side"><i class="fa fa-times"></i></a>
                <li class="cart-box">
                    <ul class="cart-list">
                        <li>
                            <a href="#" class="photo"><img src="${userImgLink}/img-pro-01.jpg" class="cart-thumb" alt="" /></a>
                            <h6><a href="#">Delica omtantur </a></h6>
                            <p>1x - <span class="price">$80.00</span></p>
                        </li>
                        <li>
                            <a href="#" class="photo"><img src="${userImgLink}/img-pro-02.jpg" class="cart-thumb" alt="" /></a>
                            <h6><a href="#">Omnes ocurreret</a></h6>
                            <p>1x - <span class="price">$60.00</span></p>
                        </li>
                        <li>
                            <a href="#" class="photo"><img src="${userImgLink}/img-pro-03.jpg" class="cart-thumb" alt="" /></a>
                            <h6><a href="#">Agam facilisis</a></h6>
                            <p>1x - <span class="price">$40.00</span></p>
                        </li>
                        <li class="total">
                            <a href="#" class="btn btn-default hvr-hover btn-cart">VIEW CART</a>
                            <span class="float-right"><strong>Total</strong>: $180.00</span>
                        </li>
                    </ul>
                </li>
            </div>
        </nav>
    </header>

    <div class="top-search">
        <div class="container">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
            </div>
        </div>
    </div>