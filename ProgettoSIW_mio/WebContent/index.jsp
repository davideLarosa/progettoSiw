<%@page import="model.Item"%>
<%@page import="model.CompleteItem"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.InputStream"%>
<%@page import="persistence.DBManager"%>
<%@page import="com.mysql.cj.mysqlx.protobuf.MysqlxCrud.Find"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home | E-Shopper</title>
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
					<div></div>
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
							<li><c:choose>
									<c:when test="${username == null }">
										<a href="login.jsp"><i class="fa fa-crosshairs"></i>
											Checkout</a>
									</c:when>
									<c:otherwise>
										<a href="checkout.jsp"><i class="fa fa-crosshairs"></i>
											Checkout</a>
									</c:otherwise>
								</c:choose></li>
							<li>
								<%
									if (request.getSession().getAttribute("email") != null
											&& !request.getSession().getAttribute("email").equals("")) {
										out.print("<a href=\"cart.jsp\">");
										int itemsNumber = DBManager.getInstance().getUserDAO()
												.getCartPaths((String) session.getAttribute("email")).size();
										out.println("<label class=\"lblCartCount\">" + itemsNumber + "</label>");
									} else {
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
							<li class="dropdown"><a>Shop<i class="fa fa-angle-down"></i></a>
								<ul role="menu" class="sub-menu">
									<li><c:choose>
											<c:when test="${username == null }">
												<a href="login.jsp"> Sell</a>
											</c:when>
											<c:otherwise>
												<a href="sell"> Sell</a>
											</c:otherwise>
										</c:choose></li>
									<li>
									<li><c:choose>
											<c:when test="${username == null }">
												<a href="login.jsp"> Cart</a>
											</c:when>
											<c:otherwise>
												<a href="cart.jsp"> Cart</a>
											</c:otherwise>
										</c:choose></li>
									<li>
									<li><c:choose>
											<c:when test="${username == null }">
												<a href="login.jsp"> My info</a>
											</c:when>
											<c:otherwise>
												<a href="modify"> My info</a>
											</c:otherwise>
										</c:choose></li>
									<li>
									<li><c:choose>
											<c:when test="${username == null }">
												<a href="login.jsp"> My items</a>
											</c:when>
											<c:otherwise>
												<a href="myItems.jsp"> My items</a>
											</c:otherwise>
										</c:choose></li>
									<li>
									<li><c:choose>
											<c:when test="${username == null }">
												<a href="login.jsp"> Checkout</a>
											</c:when>
											<c:otherwise>
												<a href="checkout.jsp"> Checkout</a>
											</c:otherwise>
										</c:choose></li>
									<li><c:choose>
											<c:when test="${username == null }">
												<a href="login.jsp"> Notifications</a>
											</c:when>
											<c:otherwise>
												<a href="mailingList"> Notifications</a>
											</c:otherwise>
										</c:choose></li>
									<li>
								</ul></li>
							<li><a href="contact.jsp">Contact</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="search_box pull-right">
						<form action="search" method="post" class="searchForm">
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


	<section id="slider"> <!--slider-->
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div id="slider-carousel" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#slider-carousel" data-slide-to="0"
							class="active"></li>
						<li data-target="#slider-carousel" data-slide-to="1"></li>
						<li data-target="#slider-carousel" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">


						<%
							ArrayList<CompleteItem> upPreview = DBManager.getInstance().getItemDAO().findSomeItems(3);

							long currentDate = System.currentTimeMillis();

							if (!upPreview.isEmpty()) {

								for (CompleteItem item : upPreview) {
									if (item.getItem().getTimeToLive().getTime() > currentDate) {

										if (item.equals(upPreview.get(0))) {
											out.println("<div class=\"item active\">");
										} else {
											out.println("<div class=\"item\">");
										}
										out.println("<div class=\"col-sm-6\">");
										out.println("<h1>Preview</h1>");
										out.println("<h2>Lorem ipsum dolor sit amet</h2>");
										out.println("<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,"
												+ "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>");
										out.println("</br></br></br>");
										out.println("</div>");
										out.println("<div class=\"col-sm-6\">");
										out.println("<img src=\"" + item.getPath(0) + "\" class=\"girl img-responsive\" alt=\"\" />");

										out.println("</div>");
										out.println("</div>");
									}
								}
							}
						%>

					</div>

					<a href="#slider-carousel" class="left control-carousel hidden-xs"
						data-slide="prev"> <i class="fa fa-angle-left"></i>
					</a> <a href="#slider-carousel"
						class="right control-carousel hidden-xs" data-slide="next"> <i
						class="fa fa-angle-right"></i>
					</a>
				</div>

			</div>
		</div>
	</div>
	</section>
	<!--/slider-->

	<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<div class="left-sidebar">
					<h2>Category</h2>
					<div class="panel-group category-products" id="accordian">
						<!--category-productsr-->

						<%
							List<Category> categories = DBManager.getInstance().getCategoryDAO().findAll();
							for (Category category : categories) {

								out.println("<div class=\"panel panel-default\">");
								out.println("<div class=\"panel-heading\">");
								out.println("<h4 class=\"panel-title\">");
								out.print("<a href=\"search?category=" + category.getName() + "&from=index.jsp\">" + category.getName()
										+ "</a>");
								out.println("</h4>");
								out.println("</div>");
								out.println("</div>");
							}
						%>

					</div>

					<h2>Producer</h2>
					<div class="panel-group category-products" id="accordian">
						<!--category-productsr-->

						<%
							ArrayList<String> producers = DBManager.getInstance().getItemDAO().findAllProducers();
							for (String producer : producers) {

								out.println("<div class=\"panel panel-default\">");
								out.println("<div class=\"panel-heading\">");
								out.println("<h4 class=\"panel-title\">");
								out.print("<a href=\"search?producer=" + producer + "&from=index.jsp\">" + producer + "</a>");
								out.println("</h4>");
								out.println("</div>");
								out.println("</div>");
							}
						%>

					</div>

					<!--/category-productsr-->

				</div>
			</div>

			<div class="col-sm-9 padding-right">
				<div class="features_items">
					<!--features_items-->
					<h2 class="title text-center">In sell</h2>
					<%
						ArrayList<CompleteItem> inSell = DBManager.getInstance().getItemDAO().findSomeItems(10);

						if (!inSell.isEmpty()) {

							for (CompleteItem item : inSell) {
								if (item.getItem().getTimeToLive().getTime() > currentDate) {
									out.print("<div class=\"col-sm-4\">");
									out.print("<div class=\"product-image-wrapper\">");
									out.print("<div class=\"single-products\">");
									out.print("<div class=\"productinfo text-center\">");
									out.print("<img src=\"" + item.getPaths().getPath(0) + "\" alt=\"\" />");

									if (item.getItem().getPrice() > item.getItem().getLastBid()) {
										out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
									} else {
										out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
									}

									out.print("<p>" + item.getItem().getProducer() + " " + item.getItem().getModel() + "</p>");

									if (request.getSession().getAttribute("email") != null
											&& !request.getSession().getAttribute("email").equals("")) {
										if (item.getItem().isBuy_now()) {
											out.print("<a href=\"addToCart?id=" + (item.getItem().getId() + 1029384756)
													+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
											out.print("<i class=\"fa fa-shopping-cart\"></i>Add to cart</a>");
										} else if (item.getItem().isBid()) {
											out.print("<a href=\"makeBid?id=" + (item.getItem().getId() + 1029384756)
													+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
											out.print("<i class=\"fa fa-gavel\"></i>Make your bid</a>");
										}
									} else {
										out.print("<a href=\"login.jsp\" class=\"btn btn-default add-to-cart\">");
										out.print("<i class=\"fa fa-shopping-cart\"></i>Login first</a>");
									}

									out.print("</div>");
									out.print("<div class=\"product-overlay\">");
									out.print("<div class=\"overlay-content\">");
									out.print("<p>" + item.getItem().getDescription() + "</p>");

									if (item.getItem().getPrice() > item.getItem().getLastBid()) {
										out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
									} else {
										out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
									}

									if (request.getSession().getAttribute("email") != null
											&& !request.getSession().getAttribute("email").equals("")) {
										if (item.getItem().isBuy_now()) {
											out.print("<a href=\"addToCart?id=" + (item.getItem().getId() + 1029384756)
													+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
											out.print("<i class=\"fa fa-shopping-cart\"></i>Add to cart</a>");
										} else if (item.getItem().isBid()) {
											out.print("<a href=\"makeBid?id=" + (item.getItem().getId() + 1029384756)
													+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
											out.print("<i class=\"fa fa-gavel\"></i>Make your bid</a>");
										}
									} else {
										out.print("<a href=\"login.jsp\" class=\"btn btn-default add-to-cart\">");
										out.print("<i class=\"fa fa-shopping-cart\"></i>Login first</a>");
									}
									out.print("</div>");
									out.print("</div>");
									out.print("</div>");
									out.print("</div>");
									out.print("</div>");
								}
							}
						} else {
							out.print("<div class=\"text-center\"> No items found :( </div>");
						}
					%>

				</div>
				<!--features_items-->


				<div class="recommended_items">
					<!--recommended_items-->
					<h2 class="title text-center">recommended items</h2>
					<div id="recommended-item-carousel" class="carousel slide"
						data-ride="carousel">
						<div class="carousel-inner">
							<div class="item active">
								<%
									ArrayList<CompleteItem> completeItems = DBManager.getInstance().getItemDAO().findSomeItems(6);
									int firstSlideEnd = completeItems.size() / 2;
									int secondSlideEnd = completeItems.size();

									if (!completeItems.isEmpty()) {

										for (int i = 0; i < firstSlideEnd; i++) {
											CompleteItem item = completeItems.get(i);
											if (item.getItem().getTimeToLive().getTime() > currentDate) {
												out.println("<div class=\"col-sm-4\">");
												out.println("<div class=\"product-image-wrapper\">");
												out.println("<div class=\"single-products\">");
												out.println("<div class=\"productinfo text-center\">");
												out.print("<img src=\"" + item.getPaths().getPath(0) + "\" alt=\"\" />");

												if (item.getItem().getPrice() > item.getItem().getLastBid()) {
													out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
												} else {
													out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
												}

												out.print("<p>" + item.getItem().getProducer() + " " + item.getItem().getModel() + "</p>");

												if (request.getSession().getAttribute("email") != null
														&& !request.getSession().getAttribute("email").equals("")) {
													if (item.getItem().isBuy_now()) {
														out.print("<a href=\"addToCart?id=" + (item.getItem().getId() + 1029384756)
																+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
														out.print("<i class=\"fa fa-shopping-cart\"></i>Add to cart</a>");
													} else if (item.getItem().isBid()) {
														out.print("<a href=\"makeBid?id=" + (item.getItem().getId() + 1029384756)
																+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
														out.print("<i class=\"fa fa-gavel\"></i>Make your bid</a>");
													}
												} else {
													out.print("<a href=\"login.jsp\" class=\"btn btn-default add-to-cart\">");
													out.print("<i class=\"fa fa-shopping-cart\"></i>Login first</a>");
												}

												out.print("</div>");

												out.print("</div>");
												out.print("</div>");
												out.print("</div>");
											}
										}
									}
								%>
							</div>
							<div class="item">
								<%
									if (!completeItems.isEmpty()) {
										for (int i = firstSlideEnd; i < secondSlideEnd; i++) {
											CompleteItem item = completeItems.get(i);
											if (item.getItem().getTimeToLive().getTime() > currentDate) {
												out.println("<div class=\"col-sm-4\">");
												out.println("<div class=\"product-image-wrapper\">");
												out.println("<div class=\"single-products\">");
												out.println("<div class=\"productinfo text-center\">");
												out.print("<img src=\"" + item.getPaths().getPath(0) + "\" alt=\"\" />");

												if (item.getItem().getPrice() > item.getItem().getLastBid()) {
													out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
												} else {
													out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
												}

												out.print("<p>" + item.getItem().getProducer() + " " + item.getItem().getModel() + "</p>");

												if (request.getSession().getAttribute("email") != null
														&& !request.getSession().getAttribute("email").equals("")) {
													if (item.getItem().isBuy_now()) {
														out.print("<a href=\"addToCart?id=" + (item.getItem().getId() + 1029384756)
																+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
														out.print("<i class=\"fa fa-shopping-cart\"></i>Add to cart</a>");
													} else if (item.getItem().isBid()) {
														out.print("<a href=\"makeBid?id=" + (item.getItem().getId() + 1029384756)
																+ "&from=index.jsp\" class=\"btn btn-default add-to-cart\">");
														out.print("<i class=\"fa fa-gavel\"></i>Make your bid</a>");
													}
												} else {
													out.print("<a href=\"login.jsp\" class=\"btn btn-default add-to-cart\">");
													out.print("<i class=\"fa fa-shopping-cart\"></i>Login first</a>");
												}

												out.print("</div>");

												out.print("</div>");
												out.print("</div>");
												out.print("</div>");
											}
										}
									}
								%>
							</div>
						</div>
						<a class="left recommended-item-control"
							href="#recommended-item-carousel" data-slide="prev"> <i
							class="fa fa-angle-left"></i>
						</a> <a class="right recommended-item-control"
							href="#recommended-item-carousel" data-slide="next"> <i
							class="fa fa-angle-right"></i>
						</a>
					</div>
				</div>
				<!--/recommended_items-->

			</div>
		</div>
	</div>
	</section>

	<footer id="footer"> <!--Footer-->

	<div class="footer-widget">
		<div class="container">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1"></div>
				<div class="col-sm-3" style="float: left;">
					<div class="single-widget">
						<h2>Service</h2>
						<ul class="nav nav-pills nav-stacked">

							<li><a href="contact.jsp">Contact Us</a></li>

						</ul>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="single-widget">
						<h2>Quick Shop</h2>
						<ul class="nav nav-pills nav-stacked">

							<%
								for (Category category : categories) {
									out.println("<li>");
									out.print("<a href=\"search?category=" + category.getName() + "&from=index.jsp\">" + category.getName()
											+ "</a>");
									out.println("</li>");
								}
							%>
						</ul>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="single-widget">
						<ul class="nav nav-pills nav-stacked">
							<h2>About Shopper</h2>
							<li>
								<%
									String uri = request.getRequestURI();
									String pageName = uri.substring(uri.lastIndexOf("/") + 1);
								%>
								<form action="mailingList" class="searchform" method="post">
									<input type="email" name="email"
										placeholder="Your email address" id="mailingList" /> <input
										type="hidden" value="<%=pageName%>" name="from" />
									<button type="submit" class="btn btn-default" id="mailingList">
										<i class="fa fa-arrow-circle-o-right"></i>
									</button>
									<p>

										Get the most recent updates from <br />our site and be
										updated your self...
									</p>
								</form>

							</li>
						</ul>
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
		</div>
	</div>
	<div class="footer-bottom"><p>Davide Larosa 131437</p></div>


	</footer>
	<!--/Footer-->



	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.scrollUp.min.js"></script>
	<script src="assets/js/price-range.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>