package gameholic.servlet;

import gameholic.dal.*;
import gameholic.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findorders")
public class FindOrders extends HttpServlet {
  protected OrdersDao ordersDao;

  @Override
  public void init() throws ServletException {
    ordersDao = OrdersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<Orders> orders = new ArrayList<>();

    String username = req.getParameter("username");
    if (username == null || username.trim().isEmpty()) {
      messages.put("success", "Please enter a valid username");
    } else {
      try {
        orders = ordersDao.getOrdersByUserName(username);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + username);
      messages.put("previousUser", username);
    }
    
    req.setAttribute("orders", orders);
    req.getRequestDispatcher("/FindOrders.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<Orders> orders = new ArrayList<>();

    String username = req.getParameter("username");
    if (username == null || username.trim().isEmpty()) {
      messages.put("success", "Please enter a valid username");
    } else {
      try {
        orders = ordersDao.getOrdersByUserName(username);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + username);
    }
	
    req.setAttribute("orders", orders);
    req.getRequestDispatcher("/FindOrders.jsp").forward(req, resp);
  }
}
