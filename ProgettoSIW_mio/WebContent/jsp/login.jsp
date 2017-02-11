<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html lang="en">
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login | E-Shopper</title>
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/font-awesome.min.css" rel="stylesheet">
<link href="../assets/css/prettyPhoto.css" rel="stylesheet">
<link href="../assets/css/price-range.css" rel="stylesheet">
<link href="../assets/css/animate.css" rel="stylesheet">
<link href="../assets/css/main.css" rel="stylesheet">
<link href="../assets/css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="../assets/js/html5shiv.js"></script>
    <script src="../assets/js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="../favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../images/ico/apple-touch-icon-57-precomposed.png">
</head>
<!--/head-->
<body>
	<header id="header"> <!--header-->
	<div class="header_top">
		<!--header_top-->
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li><a href="tel:+393427375290"><i class="fa fa-phone"></i>
									+39 342 73 74 290</a></li>
							<li><a href="mailto:davide.larosa90@gmail.com"><i
									class="fa fa-envelope"></i> info@e-shopper.com</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="social-icons pull-right">
						<ul class="nav navbar-nav">
							<li><a href=""><i class="fa fa-facebook"></i></a></li>
							<li><a href=""><i class="fa fa-twitter"></i></a></li>
							<li><a href=""><i class="fa fa-linkedin"></i></a></li>
							<li><a href=""><i class="fa fa-dribbble"></i></a></li>
							<li><a href=""><i class="fa fa-google-plus"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header_top-->

	<div class="header-middle">
		<!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="logo pull-left">
						<a href="../index.html"><img src="../images/home/logo.png"
							alt="" /></a>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<li><a href=""><i class="fa fa-user"></i> <c:choose>
										<c:when test="${username == null }">
											Account
										</c:when>
										<c:otherwise>
											Welcome ${username }
										</c:otherwise>
									</c:choose> </a></li>
							<li><a href="../html/checkout.html"><i
									class="fa fa-crosshairs"></i> Checkout</a></li>
							<li><a href="../html/cart.html"><i
									class="fa fa-shopping-cart"></i> Cart</a></li>
							<li><a href="login.jsp" class="active"> <c:choose>
										<c:when test="${username == null }">
											<i class="fa fa-unlock"></i> Login
										</c:when>
										<c:otherwise>
											<i class="fa fa-lock"></i> Logout
										</c:otherwise>
									</c:choose>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-middle-->

	<div class="header-bottom">
		<!--header-bottom-->
		<div class="container">
			<div class="row">
				<div class="col-sm-9">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<div class="mainmenu pull-left">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a href="../index.html">Home</a></li>
							<li class="dropdown"><a href="#">Shop<i
									class="fa fa-angle-down"></i></a>
								<ul role="menu" class="sub-menu">
									<li><a href="shop.html">Products</a></li>
									<li><a href="product-details.html">Product Details</a></li>
									<li><a href="checkout.html">Checkout</a></li>
									<li><a href="cart.html">Cart</a></li>
									<li><a href="login.html" class="active">Login</a></li>
								</ul></li>
							<li class="dropdown"><a href="#">Blog<i
									class="fa fa-angle-down"></i></a>
								<ul role="menu" class="sub-menu">
									<li><a href="../html/blog.html">Blog List</a></li>
									<li><a href="../html/blog-single.html">Blog Single</a></li>
								</ul></li>
							<li><a href="../html/contact-us.html">Contact</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="search_box pull-right">
						<input type="text" placeholder="Search" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-bottom--> </header>
	<!--/header-->

	<section id="login-form"> <!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-1">
				<div class="login-form">
					<!--login form-->
					<h2>Login to your account</h2>
					<div class="error_message" id="error_message">
						<c:if test="${errorMessage!=null }">
							<c:out value="${errorMessage}"></c:out>
						</c:if>
					</div>
					<form id="login-form" action="../login" method="POST">
						<input type="email" placeholder="Email Address" name="email"
							required /> <input type="password" placeholder="Password"
							name="password" required /> <span> <input id="keepsigned"
							type="checkbox" class="checkbox"> Keep me signed in
						</span>
						<button type="submit" id="login" class="btn btn-default">Login</button>
					</form>

				</div>
				<!--/login form-->
			</div>
			<div class="col-sm-1">
				<h2 class="or">OR</h2>
			</div>
			<div class="col-sm-4">
				<div class="signup-form">
					<!--sign up form-->
					<h2>New User Signup!</h2>
					<c:choose>
						<c:when test="${error_message != null }">
							<c:out value="${error_message }"></c:out>
						</c:when>
						<c:when test="${ok_message != null }">
							<c:out value="${ok_message }"></c:out>
						</c:when>
					</c:choose>
					<form id="signup_form_data" name="signup_form_data"
						action="../signup" method="POST">
						
						<input type="text" placeholder="Name" name="name" required /> <input
							type="text" placeholder="Surname" name="surname" required /> <input
							type="email" placeholder="Email Address" name="email" required />
						<input type="text" placeholder="Phone" name="phone" required /> <input
							type="text" placeholder="Address" name="address" required />

						<c:choose>
							<c:when test="${error_message != null }">
								<input class="error" type="password" placeholder="Password"
									name="password" style="border: 2px solid #E77471" required />
								<input class="error" type="password"
									placeholder="Confirm Password" name="confirm"
									style="border: 2px solid #E77471" required />
							</c:when>
							<c:when test="${ok_message != null }">
								<input class="error" type="password" placeholder="Password"
									name="password" required />
								<input class="error" type="password"
									placeholder="Confirm Password" name="confirm" required />
							</c:when>
						</c:choose>
						<span> <input type="checkbox" class="checkbox"
							name="seller"> I'm a seller!
						</span>
						<button type="submit" id="signup" class="btn btn-default">Signup</button>
					</form>
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
	</section>
	<!--/form-->


	<footer id="footer"> <!--Footer-->
	<div class="footer-top">
		<div class="container">
			<div class="row">
				<div class="col-sm-2">
					<div class="companyinfo">
						<h2>
							<span>e</span>-shopper
						</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing
							elit,sed do eiusmod tempor</p>
					</div>
				</div>
				<div class="col-sm-7">
					<div class="col-sm-3">
						<div class="video-gallery">
							<div class="iframe-img">
								<a href="#"> <img src="../images/home/iframe1.png" alt="" />
								</a>
							</div>
							<div class="overlay-icon">
								<a href="#"> <i class="fa fa-play-circle-o"></i>
								</a>
							</div>
							<div class="iframe-description">
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="video-gallery">
							<div class="iframe-img">
								<a href="#"> <img src="../images/home/iframe2.png" alt="" />
								</a>
							</div>
							<div class="overlay-icon">
								<a href="#"> <i class="fa fa-play-circle-o"></i>
								</a>
							</div>
							<div class="iframe-description">
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="video-gallery">
							<div class="iframe-img">
								<a href="#"> <img src="../images/home/iframe3.png" alt="" />
								</a>
							</div>
							<div class="overlay-icon">
								<a href="#"> <i class="fa fa-play-circle-o"></i>
								</a>
							</div>
							<div class="iframe-description">
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="video-gallery">
							<div class="iframe-img">
								<a href="#"> <img src="../images/home/iframe4.png" alt="" />
								</a>
							</div>
							<div class="overlay-icon">
								<a href="#"> <i class="fa fa-play-circle-o"></i>
								</a>
							</div>
							<div class="iframe-description">
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="address">
						<img src="../images/home/map.png" alt="" />
						<p>Worldwide Company</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="footer-widget">
		<div class="container">
			<div class="row">
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>Service</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="">Online Help</a></li>
							<li><a href="">Contact Us</a></li>
							<li><a href="">Order Status</a></li>
							<li><a href="">Change Location</a></li>
							<li><a href="">FAQ’s</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>Quock Shop</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="">T-Shirt</a></li>
							<li><a href="">Mens</a></li>
							<li><a href="">Womens</a></li>
							<li><a href="">Gift Cards</a></li>
							<li><a href="">Shoes</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>Policies</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="">Terms of Use</a></li>
							<li><a href="">Privecy Policy</a></li>
							<li><a href="">Refund Policy</a></li>
							<li><a href="">Billing System</a></li>
							<li><a href="">Ticket System</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="single-widget">
						<h2>About Shopper</h2>
						<ul class="nav nav-pills nav-stacked">
							<li><a href="">Company Information</a></li>
							<li><a href="">Careers</a></li>
							<li><a href="">Store Location</a></li>
							<li><a href="">Affillate Program</a></li>
							<li><a href="">Copyright</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-3 col-sm-offset-1">
					<div class="single-widget">
						<h2>About Shopper</h2>
						<form action="#" class="searchform">
							<input type="text" placeholder="Your email address" />
							<button type="submit" class="btn btn-default">
								<i class="fa fa-arrow-circle-o-right"></i>
							</button>
							<p>
								Get the most recent updates from <br />our site and be updated
								your self...
							</p>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="footer-bottom">
		<div class="container">
			<div class="row">
				<p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights
					reserved.</p>
				<p class="pull-right">
					Designed by <span><a target="_blank"
						href="http://www.themeum.com">Themeum</a></span>
				</p>
			</div>
		</div>
	</div>

	</footer>
	<!--/Footer-->



	<script src="../assets/js/jquery.js"></script>
	<script src="../assets/js/price-range.js"></script>
	<script src="../assets/js/jquery.scrollUp.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/jquery.prettyPhoto.js"></script>
	<script src="../assets/js/main.js"></script>
	<!-- <script src="../assets/js/login.js"></script> -->
</body>
</html>