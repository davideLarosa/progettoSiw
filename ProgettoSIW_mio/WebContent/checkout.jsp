<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="model.Item"%>
<%@page import="model.Paths"%>
<%@page import="model.Cart"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.CompleteItem"%>
<%@page import="servlets.Sell"%>
<%@page import="persistence.DBManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout | E-Shopper</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<link href="assets/css/prettyPhoto.css" rel="stylesheet">
<link href="assets/css/price-range.css" rel="stylesheet">
<link href="assets/css/animate.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">
<link href="assets/css/checkout.css" rel="stylesheet">
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
										<!--/header--> <section>
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
									<i class="fa fa-angle-right"></i>
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

			<div class="col-sm-9 padding-right">
				<div class="features_items">
					<!--features_items-->
					<h2 class="title text-center">My Items</h2>


					<%
						ArrayList<CompleteItem> cartItems = DBManager.getInstance().getUserDAO()
								.getCartPaths((String) request.getSession().getAttribute("email"));

						long currentDate = System.currentTimeMillis();

						if (!cartItems.isEmpty()) {
							for (CompleteItem item : cartItems) {
								if (item.getItem().getTimeToLive().getTime() > currentDate) {
									out.print("<div class=\"col-sm-4\">");
									out.print("<div class=\"product-image-wrapper\">");
									out.print("<div class=\"single-products\">");
									out.print("<div class=\"productinfo text-center\">");
									if (!item.getPaths().getPaths().isEmpty()) {
										out.print("<img src=\"" + item.getPaths().getPath(0) + "\" alt=\"\" />");
									}

									if ((Integer) session.getAttribute("userId") == item.getItem().getSeller()) {
										if (item.getItem().isBid()) {
											if (item.getItem().getLastBid() >= item.getItem().getPrice()) {
												out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
											} else {
												out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
											}
										} else {
											out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
										}
									} else {
										if (item.getItem().getLastBid() >= item.getItem().getPrice()) {
											out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
										} else {
											out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
										}
									}

									out.print("<p>" + item.getItem().getProducer() + " " + item.getItem().getModel() + "</p>");
									out.print("<a href=\"delete?cartItemId=" + (item.getItem().getId() + 1029384756)
											+ "\" class=\"btn btn-default add-to-cart\">");
									out.print("<i class=\"fa fa-trash-o\"></i>Remove from cart</a>");
									out.print("</div>");
									out.print("<div class=\"product-overlay\">");
									out.print("<div class=\"overlay-content\">");
									out.print("<p>" + item.getItem().getDescription() + "</p>");

									if ((Integer) session.getAttribute("userId") == item.getItem().getSeller()) {
										if (item.getItem().isBid()) {
											if (item.getItem().getLastBid() >= item.getItem().getPrice()) {
												out.print("<p>Original Price</p>");
												out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
												out.print("<p>Last bid</p>");
												out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
											} else {
												out.print("<p>Original Price</p>");
												out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
												out.print("<p>No bid yet</p>");
											}
										} else {
											out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
										}
									} else {
										if (item.getItem().getLastBid() >= item.getItem().getPrice()) {
											out.print("<h2>&euro;" + item.getItem().getLastBid() + "</h2>");
										} else {
											out.print("<h2>&euro;" + item.getItem().getPrice() + "</h2>");
										}
									}

									out.print("<a href=\"delete?cartItemId=" + (item.getItem().getId() + 1029384756)
											+ "\" class=\"btn btn-default add-to-cart\">");
									out.print("<i class=\"fa fa-trash-o\"></i>Remove from cart</a>");
									out.print("</div>");
									out.print("</div>");
									out.print("</div>");
									out.print("</div>");
									out.print("</div>");
								}
							}
						} else {
							out.print("<div class=\"text-center\"> Your cart is empty!</a></div>");
						}
					%>

				</div>
				<!--features_items-->

				<!-- review and payment -->
				<%
					if (!cartItems.isEmpty()) {

						out.println("<div class=\"review-payment\">");
						out.println("<h2>Review &amp; Payment</h2>");
						out.println("</div>");
						out.println("<div class=\"cart_info\">");

						out.println("<table class=\"table table-condensed\">");
						out.println("<thead>");
						out.println("<tr class=\"cart_menu\">");
						out.println("<td class=\"image\">Item</td>");
						out.println("<td class=\"description\">Description</td>");
						out.println("<td class=\"price\">Price</td>");
						out.println("<td class=\"cart_delete\"></td>");
						out.println("</tr>");

						out.println("</thead>");
						out.println("<tbody>");

						for (CompleteItem item : cartItems) {

							out.println("<tr>");
							out.println("<td class=\"cart_product\">");

							out.println(
									"<img src=\"" + item.getPaths().getPath(0) + "\" alt=\"\" class=\"checkoutPreview\"></td>");

							out.println("<td class=\"cart_description\">");
							out.println("<h4 class=\"text-center\">");
							out.println("<a>" + item.getItem().getProducer() + " " + item.getItem().getModel() + "</a>");
							out.println("</h4>");
							out.println("</td>");
							out.println("<td class=\"cart_price\">");

							if (item.getItem().isBid()) {
								if (item.getItem().getLastBid() > item.getItem().getPrice()) {
									out.println("<p>" + item.getItem().getLastBid() + "</p>");
								} else {
									out.println("<p>" + item.getItem().getPrice() + "</p>");
								}
							} else if (item.getItem().isBuy_now()) {
								out.println("<p>" + item.getItem().getPrice() + "</p>");
							}

							out.println("</td>");
							out.println("<td class=\"cart_delete\">");
							out.println("<a class=\"cart_quantity_delete\" href=\"delete?cartItemId="
									+ (item.getItem().getId() + 1029384756) + "&from=checkout.jsp\">");

							out.println("<i class=\"fa fa-times\">");
							out.println("</i>");
							out.println("</a>");
							out.println("</td>");
							out.println("</tr>");

						}

						out.println("</tbody>");
						out.println("<tfoot>");
						out.println("<tr>");
						out.println("<td>&nbsp;</td>");
						out.println("<td class=\"text-right\">Cart Sub Total</td>");

						float subTotal = 0;
						for (CompleteItem item : cartItems) {
							if (item.getItem().isBid()) {
								if (item.getItem().getLastBid() > item.getItem().getPrice()) {
									subTotal += item.getItem().getLastBid();
								} else {
									subTotal += item.getItem().getPrice();
								}
							} else {
								subTotal += item.getItem().getPrice();
							}
						}
						out.println("<td>" + subTotal + "&euro;</td>");
						out.println("<td></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>&nbsp;</td>");
						out.println("<td class=\"text-right\">Tax</td>");

						out.println("<td>22%</td>");
						out.println("<td></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>&nbsp;</td>");
						out.println("<td class=\"text-right\">Total</td>");

						float total = subTotal + (subTotal * 22 / 100);

						out.println("<td>" + total + "&euro;</td>");
						out.println("<td></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td></td>");
						out.println("<td></td>");
						out.println("<td></td>");
						out.println("<td><button class=\"btn btn-default\" value=\"Confirm\"</button>Confirm</td>");
						out.println("</tr>");
						out.println("</tfoot>");
						out.println("</table>");
						out.println("</div>");
						out.println("<div class=\"payment-options\">");
						out.println("<span> <label>Only cash payments</label>");
						out.println("</span>");
						out.println("</div>");
					}
				%>

				<!-- /review and payment -->
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
							List<Category> categories = DBManager.getInstance().getCategoryDAO().findAll();
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