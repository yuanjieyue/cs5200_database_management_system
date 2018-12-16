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

<title>Find Board Game</title>
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
    <div class="container theme-showcase" role="main">
    	<div class="jumbotron text-center">
			<h1>Search for Board Game</h1>
		</div>
		<div class="row align-items-center">
    		<div class="col-12"> 
    			<h4><label for="year">Search By Year</label></h4>
    			<form action="findboardgames" method="post" class="form-inline">
					<input class="form-control" id="year" name="year" value="${fn:escapeXml(param.year)}" type="text">
					<button type="submit" class="btn btn-md btn-primary">Search</button>
				</form>
			</div>
		</div>		
		<br/>

	<div class="col-md-12">
	<br/>
	<div class="alert alert-info" role="alert">
	<h4><span id="successMessage"><b>${messages.success}</b></span></h4>
	</div>
	<br/>
	<h2>Matching BoardGames</h2>
        <table class="table table-striped">
            <thead><tr>
                <th>Title</th>
                <th>Year Released</th>
                <th>Rating</th>
                <th>Price</th>
                <!-- <th>Delete BoardGame</th> -->
                <th>Update BoardGame</th>
                <th>Place Order</th>
            </tr></thead>
            <c:forEach items="${boardGames}" var="boardGame" >
                <tbody><tr>
                    <td><c:out value="${boardGame.getTitle()}" /></td>
                    <td><c:out value="${boardGame.getYearReleased()}" /></td>
                    <td><c:out value="${boardGame.getRatingScore()}" /></td>
                    <td><c:out value="${boardGame.getPrice()}" /></td>

                    <%-- <td><a href="boardgamedelete?title=<c:out value="${boardGame.getTitle()}"/>">Delete</a></td> --%>
                    <td><a href="boardgameupdate?title=<c:out value="${boardGame.getTitle()}"/>">Update</a></td>
                    <td><a href="placeorder?title=<c:out value="${boardGame.getTitle()}"/>">Place Order</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
       </div>
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <!-- <script src="js/bootstrap.min.js"></script> -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
      
</body>
</html>
