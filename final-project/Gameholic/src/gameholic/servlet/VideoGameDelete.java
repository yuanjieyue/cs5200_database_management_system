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


@WebServlet("/videogamedelete")
public class VideoGameDelete extends HttpServlet {

	protected VideoGamesDao videoGamesDao;

	@Override
	public void init() throws ServletException {
		videoGamesDao = VideoGamesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete VideoGame");
        req.getRequestDispatcher("/VideoGameDelete.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("title", "Invalid title");
            messages.put("disableSubmit", "true");
            
        } else {
	       
	        try {
	        	VideoGames videoGame = videoGamesDao.getVideoGameByTitle(title);
	        	videoGame = videoGamesDao.delete(videoGame);
	        	// Update the message.
		        if (videoGame == null) {
		            messages.put("title", "Successfully deleted " + title);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + title);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/VideoGameDelete.jsp").forward(req, resp);
    }
}
