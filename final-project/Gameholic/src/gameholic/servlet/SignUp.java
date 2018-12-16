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

@WebServlet("/signup")
public class SignUp extends HttpServlet {
  protected UsersDao usersDao;

  @Override
  public void init() throws ServletException {
    usersDao = usersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.   
    req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    String username = req.getParameter("username");
    String password = req.getParameter("password");
    if (username == null || password == null ||
    	username.trim().isEmpty() || password.trim().isEmpty()) {
        messages.put("success", "Invalid input");
    } else {
    	
      String firstname = req.getParameter("firstname");
      String lastname = req.getParameter("lastname");
      String phone = req.getParameter("phone");
      String email = req.getParameter("email");

      try {
        Users user = new Users(username, password, firstname, lastname, phone, email);
        user = usersDao.create(user);

      	messages.put("success", "Successfully created " + username);
      } catch (SQLException e) {
		e.printStackTrace();
		throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
  }
}
