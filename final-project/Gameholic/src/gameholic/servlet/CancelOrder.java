package gameholic.servlet;

import gameholic.dal.*;
import gameholic.model.*;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/cancelorder")
public class CancelOrder extends HttpServlet {

	protected OrdersDao ordersDao;

	@Override
	public void init() throws ServletException {
		ordersDao = OrdersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Cancel Order");
        req.getRequestDispatcher("/CancelOrder.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String title = req.getParameter("title");
        String username = req.getParameter("username");
        if (title == null || username == null 
        	|| title.trim().isEmpty() || username.trim().isEmpty()) {
            messages.put("title", "Invalid Input");
            messages.put("disableSubmit", "true");
        } else { 
	        try {
	        	Orders order = ordersDao.getOrderByUserNameAndTitle(username, title);
	        	order = ordersDao.delete(order);
	        	// Update the message.
		        if (order == null) {
		            messages.put("title", "Successfully Cancelled the order");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to cancel the order");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/CancelOrder.jsp").forward(req, resp);
    }
}
