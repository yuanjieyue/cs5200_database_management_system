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

@WebServlet("/boardgamecreate")
public class BoardGameCreate extends HttpServlet {
  protected BoardGamesDao boardGamesDao;

  @Override
  public void init() throws ServletException {
    boardGamesDao = BoardGamesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.   
    req.getRequestDispatcher("/BoardGameCreate.jsp").forward(req, resp);
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
      int minPlayer = Integer.parseInt(req.getParameter("minPlayer"));
      int maxPlayer = Integer.parseInt(req.getParameter("maxPlayer"));
      int averageTime = Integer.parseInt(req.getParameter("averageTime"));
      int yearReleased = Integer.parseInt(req.getParameter("yearReleased"));
      double ratingScore = Double.parseDouble(req.getParameter("ratingScore"));
      String mechanics = req.getParameter("mechanics");
      String owned = req.getParameter("owned");
      String theme = req.getParameter("theme");
      String designerName = req.getParameter("designerName");
      Double weight = Double.parseDouble("weight");

      try {
        BoardGames boardgame = new BoardGames(title, intro, isOutOfStock, stockNumber, pictureUrl, price,  
          minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
        boardgame = boardGamesDao.create(boardgame);

      	messages.put("success", "Successfully created " + title);
      } catch (SQLException e) {
		      e.printStackTrace();
		      throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/BoardGameCreate.jsp").forward(req, resp);
  }
}
