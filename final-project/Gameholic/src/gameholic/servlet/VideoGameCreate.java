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

@WebServlet("/videogamecreate")
public class VideoGameCreate extends HttpServlet {
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
    //Just render the JSP.   
    req.getRequestDispatcher("/VideoGameCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    String title = req.getParameter("title");
    if (title == null || title.trim().isEmpty()) {
        messages.put("success", "Invalid title");
    } else {
    	
    	String intro = req.getParameter("intro");
      boolean isOutOfStock = Boolean.parseBoolean(req.getParameter("isOutOfStock"));
      int stockNumber = Integer.parseInt(req.getParameter("stockNumber"));
      String pictureUrl = req.getParameter("pictureUrl");
      double price = Double.parseDouble(req.getParameter("price"));
      String console = req.getParameter("console");
      double uSSales = Double.parseDouble(req.getParameter("uSSales"));
      int yearReleased = Integer.parseInt(req.getParameter("yearReleased"));
      String publisher = req.getParameter("publisher");
      String developerName = req.getParameter("developerName");
      double averageRating = Double.parseDouble(req.getParameter("averageRating"));
      int maxPlayers = Integer.parseInt(req.getParameter("maxPlayers"));
      boolean isOnline = Boolean.parseBoolean(req.getParameter("isOnline"));
      boolean isLicensed = Boolean.parseBoolean(req.getParameter("isLicensed"));
      String contentRating = req.getParameter("contentRating");

      Genres genre = null;
      Languages language = null;

      try {
        VideoGames videogame = new VideoGames( title, intro, isOutOfStock, stockNumber, pictureUrl, price, 
          console, uSSales, yearReleased, publisher, developerName, averageRating, maxPlayers, isOnline, 
          isLicensed, contentRating, genre, language);
      	videogame = videoGamesDao.create(videogame);

      	messages.put("success", "Successfully created " + title);
      } catch (SQLException e) {
		      e.printStackTrace();
		      throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/VideoGameCreate.jsp").forward(req, resp);
  }
}
