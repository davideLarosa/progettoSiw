
<%@page import="java.util.concurrent.ThreadLocalRandom"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.tomcat.util.http.fileupload.*"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.*"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.*"%>

<%@page import="org.apache.tomcat.util.*"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="persistence.DBManager"%>
<%@page import="model.Category"%>


<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sell | E-Shopper</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<link href="assets/css/prettyPhoto.css" rel="stylesheet">
<link href="assets/css/price-range.css" rel="stylesheet">
<link href="assets/css/animate.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
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
						<a href="index.jsp"><img src="images/home/logo.png" alt="" /></a>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<li><c:choose>
									<c:when test="${username == null }">
										<a href="login.jsp"><i class="fa fa-user"></i> Account</a>
									</c:when>
									<c:otherwise>
										<a href="modify"><i class="fa fa-user"></i> Welcome
											${username } </a>
									</c:otherwise>
								</c:choose></li>
							<li><a href="html/checkout.html"><i
									class="fa fa-crosshairs"></i> Checkout</a></li>
							<li>
								<%
									if (request.getSession().getAttribute("email") != null
											&& !request.getSession().getAttribute("email").equals(""))
										out.print("<a href=\"cart.jsp\">");
									else {
										out.print("<a href=\"login.jsp\">");
									}
								%> <i class="fa fa-shopping-cart"></i> Cart </a>
							</li>
							<li><c:choose>
									<c:when test="${username == null }">
										<a href="login.jsp" class="active"><i class="fa fa-unlock"></i>
											Login</a>
									</c:when>
									<c:otherwise>
										<a href="logout" class="active"><i class="fa fa-lock"></i>
											Logout</a>
									</c:otherwise>
								</c:choose></li>
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
				<div class="col-sm-8">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse" style="float: left;">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<div class="mainmenu pull-left">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a href="index.jsp">Home</a></li>
							<li class="dropdown"><a href="#">Shop<i
									class="fa fa-angle-down"></i></a>
								<ul role="menu" class="sub-menu">
									<li><a href="shop.html">Products</a></li>
									<li><a href="product-details.html">Product Details</a></li>
									<li><a href="checkout.html">Checkout</a></li>
									<li>
										<%
											if (request.getSession().getAttribute("email") != null
													&& !request.getSession().getAttribute("email").equals("")) {
												out.print("<a href=\"cart.jsp\">Cart</a>");
											} else {
												out.print("<a href=\"login.jsp\">Cart</a>");
											}
										%>
									</li>
									<li><a href="login.jsp" class="active">Login</a></li>
								</ul></li>

							<li><a href="html/contact-us.html">Contact</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="search_box pull-right">
						<form action="search" method="post">
							<span> <input type="text" placeholder="Search"
								name="search" class="search" />
								<button type="submit" class="searchButton">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-bottom--> </header>

	<!--/header-->

	<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<div class="left-sidebar">
					<h2>My account</h2>
					<div class="panel-group category-products" id="accordian">
						<!--category-productsr-->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">

									<%
										if (request.getSession().getAttribute("email") != null
												&& !request.getSession().getAttribute("email").equals(""))
											out.print("<a href=\"modify\">Info</a>");
										else {
											out.print("<a href=\"login.jsp\">Info</a>");
										}
									%>

								</h4>
							</div>

						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">

									<%
										if (request.getSession().getAttribute("email") != null
												&& !request.getSession().getAttribute("email").equals(""))
											out.print("<a href=\"mailingList\">Notifications</a>");
										else {
											out.print("<a href=\"login.jsp\">Notifications</a>");
										}
									%>

								</h4>
							</div>

						</div>

						<div class="panel panel-default">

							<div class="panel-heading">
								<h4 class="panel-title">
									<i class="fa fa-angle-right"></i>
									<%
										if (request.getSession().getAttribute("email") != null
												&& !request.getSession().getAttribute("email").equals(""))
											out.print("<a href=\"sell\">Sell</a>");
										else {
											out.print("<a href=\"login.jsp\">Sell</a>");
										}
									%>
								</h4>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<%
										if (request.getSession().getAttribute("email") != null
												&& !request.getSession().getAttribute("email").equals(""))
											out.print("<a href=\"myItems.jsp\">My items</a>");
										else {
											out.print("<a href=\"login.jsp\">My items</a>");
										}
									%>

								</h4>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<%
										if (request.getSession().getAttribute("email") != null
												&& !request.getSession().getAttribute("email").equals(""))
											out.print("<a href=\"cart.jsp\">Cart</a>");
										else {
											out.print("<a href=\"login.jsp\">Cart</a>");
										}
									%>
								</h4>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<%
										if (request.getSession().getAttribute("email") != null
												&& !request.getSession().getAttribute("email").equals(""))
											out.print("<a href=\"checkout.jsp\">Checkout</a>");
										else {
											out.print("<a href=\"login.jsp\">Checkout</a>");
										}
									%>
								</h4>
							</div>
						</div>
					</div>
					<!--/category-productsr-->
				</div>
			</div>

			<c:choose>
				<c:when test="${message != null && message != '' }">

					<div class="ok_message">
						<%
							out.print(request.getAttribute("message"));
						%>
					</div>
					<div class="text-center">
						<p>
							Now you can go back to your <a href="modify">Account</a>, <a
								href="sell">Sell</a> another item or check you <a
								href="myItems.jsp">items</a>.
						</p>
					</div>

				</c:when>
				<c:otherwise>

					<div class="col-sm-9 padding-right">
						<div class="features_items">
							<!--features_items-->
							<h2 class="title-details text-center">Sell</h2>
							<div class="text-center">
								<c:if test="${email == null }">
									<div class="error_message">
										<p>
											You must <a id="error_a" class="error_a" href="login.jsp">login</a>
											first
										</p>
									</div>
								</c:if>
								<c:if test="${message == null && email != null}">
									<p>Seller page</p>
									<p>In this page you can sell your products</p>
								</c:if>
							</div>


							<!-- Product info -->
							<div class="col-sm-8 col-sm-offset-1 signup-form">
								<container id="modify_form_data"> </container>

								<form id="itemSell" method="post" action="sell"
									enctype="multipart/form-data">
									<i class="fa fa-product-hunt"></i> Producer<input type="text"
										name="producer" id="producer" value="${producer }"
										placeholder="${producer }" required /> <i class="fa fa-eye"></i>
									Model<input type="text" name="model" id="model"
										value="${model }" placeholder="${model }"required /> <i
										class="fa fa-eur"></i> Minimum buy price <input type="number"
										min="0.5" step="0.5" name="minimum_buy_price"
										id="minimum_buy_price" value="${minimum_buy_price }"
										placeholder="${minimum_buy_price }" required /><i class="fa fa-bars"></i>
									Category <select name="category" id="category">
										<%
											List<Category> categories = DBManager.getInstance().getCategoryDAO().findAll();
													if (categories != null) {
														for (Category c : categories) {
															out.print("<option>" + c.getName() + "</option>");
														}
													}
										%>

									</select> <i class="fa fa-calendar-check-o"></i> Expiration time <select
										name="time" id="time">
										<option>1 Month</option>
										<option>2 Months</option>
										<option>3 Months</option>
									</select>
									<div class="checkout-options">
										<span>
											<ul class="nav">
												<li><label> <input type="checkbox" name="bid"
														id="bid"><i class="fa fa-gavel" id="bid"></i>Bid
												</label></li>
												<li><label> <input type="checkbox"
														name="buy_now" id="buy_now" checked="checked"><i
														class="fa fa-money" id="buy_now"></i>Buy now
												</label></li>
											</ul>
										</span>
									</div>

									<i class="fa fa-paperclip"></i> Description (max 200 chars)
									<textarea cols="10" rows="10" maxlength="200"
										name="description" id="description"
										placeholder="${description }" required></textarea>



									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview1" class="item_preview">
										<input type="file" id="fileinput1" accept="image/*"
											onclick="getPreview(1);" name="file" required/>
									</div>

									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview2" class="item_preview">
										<input type="file" id="fileinput2" accept="image/*"
											onclick="getPreview(2);" name="file" required/>
									</div>

									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview3" class="item_preview">
										<input type="file" id="fileinput3" accept="image/*"
											onclick="getPreview(3);" name="file" required/>
									</div>

									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview4" class="item_preview">
										<input type="file" id="fileinput4" accept="image/*"
											onclick="getPreview(4);" name="file" />
									</div>

									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview5" class="item_preview">
										<input type="file" id="fileinput5" accept="image/*"
											onclick="getPreview(5);" name="file" />
									</div>
									<br />

									<button type="submit" class="btn btn-default" id="save_btn"
										name="save_btn">Upload &amp; Save</button>
								</form>

							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			<!--features_items-->
		</div>
	</div>
	</section>


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
								<a href="#"> <img src="images/home/iframe1.png" alt="" />
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
								<a href="#"> <img src="images/home/iframe2.png" alt="" />
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
								<a href="#"> <img src="images/home/iframe3.png" alt="" />
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
								<a href="#"> <img src="images/home/iframe4.png" alt="" />
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
						<img src="images/home/map.png" alt="" />
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


	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.scrollUp.min.js"></script>
	<script src="assets/js/price-range.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
	<script src="assets/js/main.js"></script>
	<c:if test="${message == null}">
		<script src="assets/js/sell.js"></script>
		<script src="assets/js/gallery.js"></script>
	</c:if>
</body>
</html>