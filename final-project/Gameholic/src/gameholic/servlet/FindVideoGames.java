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

@WebServlet("/findvideogames")
public class FindVideoGames extends HttpServlet {
  protected VideoGamesDao videoGamesDao;

  @Override
  public void init() throws ServletException {
    videoGamesDao = VideoGamesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<VideoGames> videoGames = new ArrayList<>();

    String year = req.getParameter("year");
    if (year == null || year.trim().isEmpty()) {
      messages.put("success", "Please enter a valid year");
    } else {
      try {
        videoGames = videoGamesDao.getVideoGamesByYearReleased(Integer.parseInt(year));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + year);
      messages.put("previousTitle", year);
    }

    req.setAttribute("videoGames", videoGames);
    req.getRequestDispatcher("/FindVideoGames.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    List<VideoGames> videoGames = new ArrayList<>();

    String year = req.getParameter("year");
    if (year == null || year.trim().isEmpty()) {
      messages.put("success", "Please enter a valid year");
    } else {
      try {
        videoGames = videoGamesDao.getVideoGamesByYearReleased(Integer.parseInt(year));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + year);
    }
    req.setAttribute("videoGames", videoGames);
    req.getRequestDispatcher("/FindVideoGames.jsp").forward(req, resp);
  }
}
