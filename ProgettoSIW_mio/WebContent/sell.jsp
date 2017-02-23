
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
										value="${model }" placeholder="${model }" required /> <i
										class="fa fa-eur"></i> Minimum buy price <input type="number"
										min="0.5" step="0.5" name="minimum_buy_price"
										id="minimum_buy_price" value="${minimum_buy_price }"
										placeholder="${minimum_buy_price }" required /><i
										class="fa fa-bars"></i> Category <select name="category"
										id="category">
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
											onclick="getPreview(1);" name="file" required />
									</div>

									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview2" class="item_preview">
										<input type="file" id="fileinput2" accept="image/*"
											onclick="getPreview(2);" name="file" required />
									</div>

									<i class="fa fa-picture-o"></i> Upload image
									<div id="preview3" class="item_preview">
										<input type="file" id="fileinput3" accept="image/*"
											onclick="getPreview(3);" name="file" required />
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
	<c:if test="${message == null}">
		<script src="assets/js/sell.js"></script>
		<script src="assets/js/gallery.js"></script>
	</c:if>


</body>
</html>