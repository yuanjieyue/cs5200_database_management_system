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

<title>Sign Up</title>
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
	<h1>Register</h1>
	</div>
	<form action="signup" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label" for="username">UserName</label>
			<input id="username" name="username" value="">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input id="password" name="password" value="">
		</div>
		<div class="form-group">
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="">
		</div>
		<div class="form-group">
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="">
		</div>
		<div class="form-group">
			<label for="phone">Phone</label>
			<input id="phone" name="phone" value="">
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input id="email" name="email" value="">
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-md btn-primary">Register</button>
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
