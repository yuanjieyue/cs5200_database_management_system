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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<title>Create BoardGame</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="#">GameHolic</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="/Gameholic/findvideogames">VideoGame<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Gameholic/findboardgames">BoardGame</a>
          </li>
        </ul>
              
        <ul class="navbar-nav mt-2 mt-md-0">
          <li class="nav-item active">
            <a class="nav-link" href="/Gameholic/findvideogames">Sign Up</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">Log In</a>
          </li>
        </ul>
      </div>
    </nav>
	
	<br/>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron text-center">
	<h1>Create BoardGame</h1>
	</div>
	<form action="boardgamecreate" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label" for="intro">Intro</label>
			<input id="intro" name="intro" value="">
		</div>
		<div class="form-group">
			<label for="isOutOfStock">IsOutOfStock</label>
			<input id="isOutOfStock" name="isOutOfStock" value="">
		</div>
		<div class="form-group">
			<label for="stockNumber">StockNumber</label>
			<input id="stockNumber" name="stockNumber" value="">
		</div>
		<div class="form-group">
			<label for="pictureUrl">PictureUrl</label>
			<input id="pictureUrl" name="pictureUrl" value="">
		</div>
		<div class="form-group">
			<label for="price">Price</label>
			<input id="price" name="price" value="">
		</div>
		<div class="form-group">
			<label for="minPlayer">MinPlayer</label>
			<input id="minPlayer" name="minPlayer" value="">
		</div>
		<div class="form-group">
			<label for="maxPlayer">MaxPlayer</label>
			<input id="maxPlayer" name="maxPlayer" value="">
		</div>
		<div class="form-group">
			<label for="averageTime">AverageTime</label>
			<input id="averageTime" name="averageTime" value="">
		</div>
		<div class="form-group">
			<label for="yearReleased">YearReleased</label>
			<input id="yearReleased" name="yearReleased" value="">
		</div>
		<div class="form-group">
			<label for="ratingScore">RatingScore</label>
			<input id="ratingScore" name="ratingScore" value="">
		</div>
		<div class="form-group">
			<label for="mechanics">Mechanics</label>
			<input id="mechanics" name="mechanics" value="">
		</div>
		<div class="form-group">
			<label for="owned">Owned</label>
			<input id="owned" name="owned" value="">
		</div>
		<div class="form-group">
			<label for="theme">Theme</label>
			<input id="theme" name="theme" value="">
		</div>
		<div class="form-group">
			<label for="designerName">DesignerName</label>
			<input id="designerName" name="designerName" value="">
		</div>
		<div class="form-group">
			<label for="weight">Weight</label>
			<input id="weight" name="weight" value="">
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-md btn-primary">
		</div>
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
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    
</body>
</html>
