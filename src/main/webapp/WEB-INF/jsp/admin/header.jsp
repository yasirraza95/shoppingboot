<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>


<c:url var="adminIndexURL" value="/admin/" />
<c:url var="adminProfileURL" value="/admin/adminprofile" />
<c:url var="adminPasswordURL" value="/admin/adminpassword" />
<c:url var="adminAddProductURL" value="/admin/addproduct" />
<c:url var="adminTopProductsURL" value="/admin/adminproducts?type=top" />
<c:url var="adminVegetablesURL" value="/admin/adminproducts?type=vegetables" />
<c:url var="adminFruitsURL" value="/admin/adminproducts?type=fruits" />
<c:url var="adminAddProviderURL" value="/admin/addprovider" />
<c:url var="adminProviderURL" value="/admin/adminproviders" />
<c:url var="adminAllOrdersURL" value="/admin/adminorders?type=all" />
<c:url var="adminPendingOrdersURL" value="/admin/adminorders?type=pending" />
<c:url var="adminProcessingOrdersURL" value="/admin/adminorders?type=processing" />
<c:url var="adminDeliverdOrdersURL" value="/admin/adminorders?type=delivered" />
<c:url var="adminCancelledOrdersURL" value="/admin/adminorders?type=cancelled" />
<c:url var="adminEditProductURL" value="/admin/editproduct?id=${param.id}" />
<c:url var="adminEditProviderURL" value="/admin/editprovider?id=${param.id}" />

<c:url var="adminLink" value="/assets/vendors/base/" />
<c:url var="adminCssLink" value="/assets/vendors/mdi/css" />
<c:url var="adminJsLink" value="/assets/vendors/mdi/css" />
<c:url var="adminImgLink" value="/assets/vendors/mdi/css" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>TGT Admin</title>
<link rel="stylesheet" href="${adminLink}/materialdesignicons.min.css">
<link rel="stylesheet" href="${adminCssLink}/vendor.bundle.base.css">
<link rel="stylesheet" href="${adminCssLink}/style.css">
<link rel="shortcut icon" href="${adminImgLink}/favicon.png" />
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
</head>

<body>
	<div class="container-scroller">
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="navbar-brand-wrapper d-flex justify-content-center">
				<div
					class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
					<a class="navbar-brand brand-logo" href="${adminIndexURL}"><img
						src="${adminImgLink}/logo.svg" alt="logo" /></a>
						<a class="navbar-brand brand-logo-mini" href="index.jsp">
					 <img src="${adminImgLink}/logo-mini.svg" alt="logo" /></a>
					<button class="navbar-toggler navbar-toggler align-self-center"
						type="button" data-toggle="minimize">
						<span class="mdi mdi-sort-variant"></span>
					</button>
				</div>
			</div>
			<div
				class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<ul class="navbar-nav mr-lg-4 w-100">
					<li class="nav-item nav-search d-none d-lg-block w-100">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="search"> <i
									class="mdi mdi-magnify"></i>
								</span>
							</div>
							<input type="text" class="form-control" placeholder="Search now"
								aria-label="search" aria-describedby="search">
						</div>
					</li>
				</ul>
				<ul class="navbar-nav navbar-nav-right">
					<li class="nav-item dropdown mr-1"><a
						class="nav-link count-indicator dropdown-toggle d-flex justify-content-center align-items-center"
						id="messageDropdown" href="#" data-toggle="dropdown"> <i
							class="mdi mdi-message-text mx-0"></i> <span class="count"></span>
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="messageDropdown">
							<p class="mb-0 font-weight-normal float-left dropdown-header">Messages</p>
							<a class="dropdown-item">
								<div class="item-thumbnail">
									<img src="${adminImgLink}/faces/face4.jpg" alt="image" class="profile-pic">
								</div>
								<div class="item-content flex-grow">
									<h6 class="ellipsis font-weight-normal">David Grey</h6>
									<p class="font-weight-light small-text text-muted mb-0">
										The meeting is cancelled</p>
								</div>
							</a> <a class="dropdown-item">
								<div class="item-thumbnail">
									<img
										src="${adminImgLink}/faces/face2.jpg"
										alt="image" class="profile-pic">
								</div>
								<div class="item-content flex-grow">
									<h6 class="ellipsis font-weight-normal">Tim Cook</h6>
									<p class="font-weight-light small-text text-muted mb-0">
										New product launch</p>
								</div>
							</a> <a class="dropdown-item">
								<div class="item-thumbnail">
									<img
										src="${adminImgLink}/faces/face3.jpg"
										alt="image" class="profile-pic">
								</div>
								<div class="item-content flex-grow">
									<h6 class="ellipsis font-weight-normal">Johnson</h6>
									<p class="font-weight-light small-text text-muted mb-0">
										Upcoming board meeting</p>
								</div>
							</a>
						</div></li>
					<li class="nav-item dropdown mr-4"><a
						class="nav-link count-indicator dropdown-toggle d-flex align-items-center justify-content-center notification-dropdown"
						id="notificationDropdown" href="#" data-toggle="dropdown"> <i
							class="mdi mdi-bell mx-0"></i> <span class="count"></span>
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="notificationDropdown">
							<p class="mb-0 font-weight-normal float-left dropdown-header">Notifications</p>
							<a class="dropdown-item">
								<div class="item-thumbnail">
									<div class="item-icon bg-success">
										<i class="mdi mdi-information mx-0"></i>
									</div>
								</div>
								<div class="item-content">
									<h6 class="font-weight-normal">Application Error</h6>
									<p class="font-weight-light small-text mb-0 text-muted">
										Just now</p>
								</div>
							</a> <a class="dropdown-item">
								<div class="item-thumbnail">
									<div class="item-icon bg-warning">
										<i class="mdi mdi-settings mx-0"></i>
									</div>
								</div>
								<div class="item-content">
									<h6 class="font-weight-normal">Settings</h6>
									<p class="font-weight-light small-text mb-0 text-muted">
										Private message</p>
								</div>
							</a> <a class="dropdown-item">
								<div class="item-thumbnail">
									<div class="item-icon bg-info">
										<i class="mdi mdi-account-box mx-0"></i>
									</div>
								</div>
								<div class="item-content">
									<h6 class="font-weight-normal">New user registration</h6>
									<p class="font-weight-light small-text mb-0 text-muted">2
										days ago</p>
								</div>
							</a>
						</div></li>
					<li class="nav-item nav-profile dropdown"><a
						class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
						id="profileDropdown"> <img
							src="${adminImgLink}/faces/face5.jpg"
							alt="profile" /> <span class="nav-profile-name">${'Admin'}</span>
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="profileDropdown">
							<!-- <a class="dropdown-item"> <i
								class="mdi mdi-settings text-primary"></i> Settings -->
							</a> <a href="logout" class="dropdown-item"> <i
								class="mdi mdi-logout text-primary"></i> Logout
							</a>
						</div></li>
				</ul>
				<button
					class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
					type="button" data-toggle="offcanvas">
					<span class="mdi mdi-menu"></span>
				</button>
			</div>
		</nav>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.jsp -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#account" aria-expanded="false"
						aria-controls="account"> <i class="mdi mdi-account menu-icon"></i>
							<span class="menu-title">Account info</span> <i
							class="menu-arrow"></i>
					</a>



						<div class="collapse" id="account">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item">
									
									<a class="nav-link" href="${adminProfileURL}"> Profile </a></li>
								<li class="nav-item">

								<a class="nav-link" href="${adminPasswordURL}"> Password </a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="${adminAddProductURL}"> <i
							class="mdi mdi-home menu-icon"></i> <span class="menu-title">Add
								Product</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#products" aria-expanded="false"
						aria-controls="products"> <i class="mdi mdi-account menu-icon"></i>
							<span class="menu-title">Products</span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="products">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="${adminTopProductsURL}">
										Top Products </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminVegetablesURL}">
										Vegetables </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminFruitsURL}">
										Fruits </a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#providers" aria-expanded="false"
						aria-controls="providers"> <i
							class="mdi mdi-account menu-icon"></i> <span class="menu-title">Providers</span>
							<i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="providers">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="${adminAddProviderURL}"> Add
										Providers </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminProviderURL}">
										View Providers </a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#orders" aria-expanded="false"
						aria-controls="orders"> <i class="mdi mdi-account menu-icon"></i>
							<span class="menu-title">Orders</span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="orders">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="${adminAllOrdersURL}">
										All Orders </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminPendingOrdersURL}">
										Pending Orders </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminProcessingOrdersURL}">
										Processing Orders </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminDeliverdOrdersURL}">
										Delivered Orders </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${adminCancelledOrdersURL}">
										Cancelled Orders </a></li>
							</ul>
						</div></li>
					<%-- <li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath() %>/AdminMinOrd"> <i
							class="mdi mdi-home menu-icon"></i> <span class="menu-title">Set
								Minimum Order</span>
					</a></li> --%>

				</ul>
			</nav>