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

@WebServlet("/findboardgames")
public class FindBoardGames extends HttpServlet {
  protected BoardGamesDao boardGamesDao;

  @Override
  public void init() throws ServletException {
    boardGamesDao = BoardGamesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<BoardGames> boardGames = new ArrayList<>();

    String year = req.getParameter("year");
    if (year == null || year.trim().isEmpty()) {
      messages.put("success", "Please enter a valid year");
    } else {
      try {
        boardGames = boardGamesDao.getBoardGamesByYearReleased(Integer.parseInt(year));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + year);
      messages.put("previousYear", year);
    }
    
    req.setAttribute("boardGames", boardGames);
    req.getRequestDispatcher("/FindBoardGames.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<BoardGames> boardGames = new ArrayList<>();

    String year = req.getParameter("year");
    if (year == null || year.trim().isEmpty()) {
      messages.put("success", "Please enter a valid year");
    } else {
      try {
        boardGames = boardGamesDao.getBoardGamesByYearReleased(Integer.parseInt(year));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + year);
      //messages.put("success", "post: result list's size is " + boardGames.size());
    }
	
    req.setAttribute("boardGames", boardGames);
    req.getRequestDispatcher("/FindBoardGames.jsp").forward(req, resp);
  }
}
