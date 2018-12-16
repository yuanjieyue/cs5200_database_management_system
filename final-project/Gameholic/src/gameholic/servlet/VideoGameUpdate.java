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


@WebServlet("/videogameupdate")
public class VideoGameUpdate extends HttpServlet {

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

        String title = req.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	try {
        		VideoGames videoGame = videoGamesDao.getVideoGameByTitle(title);
        		if(videoGame == null) {
        			messages.put("success", "Title does not exist.");
        		}
        		req.setAttribute("videoGame", videoGame);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/VideoGameUpdate.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String title = req.getParameter("title");
        if ( title == null || title.trim().isEmpty()) {
            messages.put("success", "Please enter a valid title.");
        } else {
        	try {
        		VideoGames videoGame = videoGamesDao.getVideoGameByTitle(title);
        		if(videoGame == null) {
        			messages.put("success", "Game does not exist. No update to perform.");
        		} else {
        			String price = req.getParameter("price");
        			if (price == null || price.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid price.");
        	        } else {
        	        	videoGame = videoGamesDao.updatePrice(videoGame, Double.parseDouble(price));
        	        	messages.put("success", "Successfully updated " + title);
        	        }
        		}
        		req.setAttribute("videoGame", videoGame);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/VideoGameUpdate.jsp").forward(req, resp);
    }
}
