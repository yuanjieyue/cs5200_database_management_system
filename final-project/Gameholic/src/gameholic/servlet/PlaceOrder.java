package gameholic.servlet;

import gameholic.dal.*;
import gameholic.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/placeorder")
public class PlaceOrder extends HttpServlet {
	
	protected OrdersDao ordersDao;
	protected UsersDao usersDao;
	protected GamesDao gamesDao;

	@Override
	public void init() throws ServletException {
		ordersDao = OrdersDao.getInstance();
		usersDao = UsersDao.getInstance();
		gamesDao = GamesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
	    //Just render the JSP.   
	    req.getRequestDispatcher("/PlaceOrder.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    String title = req.getParameter("title");
	    String username = req.getParameter("username");
	    String address = req.getParameter("address");
	    if (title == null || username == null 
	    	|| title.trim().isEmpty() || username.trim().isEmpty()) {
	        messages.put("success", "Invalid Input");
	    } else { 	
//	      String intro = req.getParameter("intro");
//	      boolean isOutOfStock = Boolean.parseBoolean(req.getParameter("isOutOfStock"));
//	      int stockNumber = Integer.parseInt(req.getParameter("stockNumber"));
//	      String pictureUrl = req.getParameter("pictureUrl");
//	      double price = Double.parseDouble(req.getParameter("price"));
//	      int minPlayer = Integer.parseInt(req.getParameter("minPlayer"));
//	      int maxPlayer = Integer.parseInt(req.getParameter("maxPlayer"));
//	      int averageTime = Integer.parseInt(req.getParameter("averageTime"));
//	      int yearReleased = Integer.parseInt(req.getParameter("yearReleased"));
//	      double ratingScore = Double.parseDouble(req.getParameter("ratingScore"));
//	      String mechanics = req.getParameter("mechanics");
//	      String owned = req.getParameter("owned");
//	      String theme = req.getParameter("theme");
//	      String designerName = req.getParameter("designerName");
//	      Double weight = Double.parseDouble("weight");

	      try {
	    	  Users user = usersDao.getUserByUserName(username);
	    	  if (user == null) {
	    		  user = new Users(username);
	    		  user = usersDao.create(user);
	    	  }
	    	  System.out.println(title);
	    	  Games game = gamesDao.getGameByTitle(title);
	    	  
	    	  System.out.println(user.getUserName());
	    	  Orders order = new Orders(user, game, address); 
	    	  order = ordersDao.create(order);
	    	  
	      	messages.put("success", "Successfully created the order for" + username);
	      } catch (SQLException e) {
	    	  e.printStackTrace();
	    	  throw new IOException(e);
	      }
	    }

        req.getRequestDispatcher("/PlaceOrder.jsp").forward(req, resp);
    }
}
