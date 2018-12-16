<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Create VideoGame</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron text-center">
	<h1>Create VideoGame</h1>
	</div>
	<form action="videogamecreate" method="post">
		<p>
			<h2><label for="intro">Intro</label></h2>
			<input id="intro" name="intro" value="">
		</p>
		<p>
			<h2><label for="isOutOfStock">IsOutOfStock</label></h2>
			<input id="isOutOfStock" name="isOutOfStock" value="">
		</p>
		<p>
			<h2><label for="stockNumber">StockNumber</label></h2>
			<input id="stockNumber" name="stockNumber" value="">
		</p>
		<p>
			<h2><label for="pictureUrl">PictureUrl</label></h2>
			<input id="pictureUrl" name="pictureUrl" value="">
		</p>
		<p>
			<h2><label for="price">Price</label></h2>
			<input id="price" name="price" value="">
		</p>
		<p>
			<h2><label for="console">Console</label></h2>
			<input id="console" name="console" value="">
		</p>
		<p>
			<h2><label for="uSSales">USSales</label></h2>
			<input id="uSSales" name="uSSales" value="">
		</p>
		<p>
			<h2><label for="yearReleased">YearReleased</label></h2>
			<input id="yearReleased" name="yearReleased" value="">
		</p>
		<p>
			<h2><label for="publisher">Publisher</label></h2>
			<input id="publisher" name="publisher" value="">
		</p>
		<p>
			<h2><label for="developerName">DeveloperName</label></h2>
			<input id="developerName" name="developerName" value="">
		</p>
		<p>
			<h2><label for="averageRating">AverageRating</label></h2>
			<input id="averageRating" name="averageRating" value="">
		</p>
		<p>
			<h2><label for="maxPlayers">MaxPlayers</label></h2>
			<input id="maxPlayers" name="maxPlayers" value="">
		</p>
		<p>
			<h2><label for="isOnline">IsOnline</label></h2>
			<input id="isOnline" name="isOnline" value="">
		</p>
		<p>
			<h2><label for="isLicensed">IsLicensed</label></h2>
			<input id="isLicensed" name="isLicensed" value="">
		</p>
		<p>
			<h2><label for="contentRating">ContentRating</label></h2>
			<input id="contentRating" name="contentRating" value="">
		</p>
		<p>
			<input type="submit" class="btn btn-md btn-primary">
		</p>
	</form>
	<br/><br/>
	<p>
		<div class="alert alert-success" role="alert">
		<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</p>
	
	</div>
	
	<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>

