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

<title>Find Orders</title>
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
            <a class="nav-link" href="/Gameholic/signup">Sign Up</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">Log In</a>
          </li>
        </ul>
      </div>
    </nav>
	
	<br/>
	<br/>
    <div class="container theme-showcase" role="main">
    	<div class="jumbotron text-center">
			<h1>Search for Order</h1>
		</div>
		<h4><label for="username">Search By UserName</label></h4>
	<form action="findorders" method="post" class="form-inline">
		<input id="username" name="username" value="${fn:escapeXml(param.username)}" type="text" class="form-control">
		<button type="submit" class="btn btn-sm btn-primary">Search</button>
		<br/>
	</form>

	<div class="col-md-12">
	<br/>
	<div class="alert alert-info" role="alert">
	<h4><span id="successMessage"><b>${messages.success}</b></span></h4>
	</div>
	<br/>
	<h1>Matching Orders</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>Game</th>
                <th>UserName</th>
                <th>Order Date</th>
                <th>Cancel Option</th>
            </tr></thead>
            
            <c:forEach items="${orders}" var="order" >
                <tbody><tr>
                    <td><c:out value="${order.getGame().getTitle()}" /></td>
                    <td><c:out value="${order.getUser().getUserName()}" /></td>
                    <td><c:out value="${order.getPlaceOrderDate()}" /></td>
                    
                    <td>
                    	<a href="cancelorder?title=<c:out value="${order.getGame().getTitle()}"/>&username=<c:out value="${order.getUser().getUserName()}"/>">
                    		Cancel Order
                    	</a>
                    </td>
                </tr></tbody>
            </c:forEach>
       </table>
       </div>
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
       
</body>
</html>
