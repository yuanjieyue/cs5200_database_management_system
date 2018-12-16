package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameholic.model.*;
// tODO: get list of boardgames by genre, language and isOnline, licensed....
public class BoardGamesDao extends GamesDao{
	private static BoardGamesDao instance = null;
	protected BoardGamesDao() {
		super();
	}
	public static BoardGamesDao getInstance() {
		if(instance == null) {
			instance = new BoardGamesDao();
		}
		return instance;
	}
	
	public BoardGames create(BoardGames boardGame) throws SQLException {
		// Insert into the superclass table first.
		Games newCreated = create(new Games(
				boardGame.getTitle(),
				boardGame.getIntro(),
				boardGame.isOutOfStock(),
				boardGame.getStockNumber(),
				boardGame.getPictureUrl(),
				boardGame.getPrice()));
		String insertBoardGames = "INSERT INTO BoardGames(GameId, MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DesignerName, Weight) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBoardGames);
			insertStmt.setInt(1, newCreated.getGameId());
			insertStmt.setInt(2, boardGame.getMinPlayer());
			insertStmt.setInt(3, boardGame.getMaxPlayer());
			insertStmt.setInt(4, boardGame.getAverageTime());
			insertStmt.setInt(5, boardGame.getYearReleased());
			insertStmt.setDouble(6, boardGame.getRatingScore());
			insertStmt.setString(7, boardGame.getMechanics());
			insertStmt.setString(8, boardGame.getOwned());
			insertStmt.setString(9, boardGame.getTheme());
			insertStmt.setString(10, boardGame.getDesignerName());
			insertStmt.setDouble(11, boardGame.getWeight());
			
			insertStmt.executeUpdate();
			return boardGame;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the Price of the BoardGames instance.
	 * This runs a UPDATE statement.
	 */
	public BoardGames updatePrice(BoardGames boardGames, double newPrice) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updatePrice(boardGames, newPrice);
		return boardGames;
	}

	public BoardGames getBoardGameById(int boardGameId) throws SQLException{
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
				+ "MinPlayer,MaxPlayers,AverageTime,YearReleased,RatingScore,Mechanics,Owned,Theme,DesignerName,Weight" +
				"FROM BoardGames INNER JOIN Games " +
				"  ON BoardGames.GameId = Games.GameId " +
				"WHERE BoardGames.GameId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectBoardGame);
				selectStmt.setInt(1, boardGameId);
				results = selectStmt.executeQuery();
				if(results.next()) {
					int resultGameId = results.getInt("GameId");
					String title = results.getString("Title");
					String intro = results.getString("Intro");
					boolean isOutOfStock = results.getBoolean("IsOutOfStock");
					int stockNumber = results.getInt("StockNumber");
					String pictureUrl = results.getString("PictureURL");
					double price = results.getDouble("Price");
					
					int minPlayer = results.getInt("MinPlayer");
					int maxPlayer = results.getInt("MaxPlayer");
					int averageTime = results.getInt("AverageTime");
					int yearReleased = results.getInt("YearReleased");
					
					double ratingScore = results.getDouble("RatingScore");
					String mechanics = results.getString("Mechanics");
					String owned = results.getString("Owned");
					String theme = results.getString("Theme");
					String designerName = results.getString("DesignerName");
					double weight = results.getDouble("Weight");
					
					BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price, 
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
					return boardGame;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
	}
	
	public BoardGames getBoardGameByTitle(String title) throws SQLException{
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
						+ "MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DesignerName, Weight " +
						"FROM BoardGames INNER JOIN Games " +
						"  ON BoardGames.GameId = Games.GameId " +
						"WHERE Title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBoardGame);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultGameId = results.getInt("GameId");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				
				int minPlayer = results.getInt("MinPlayer");
				int maxPlayer = results.getInt("MaxPlayer");
				int averageTime = results.getInt("AverageTime");
				int yearReleased = results.getInt("YearReleased");
				
				double ratingScore = results.getDouble("RatingScore");
				String mechanics = results.getString("Mechanics");
				String owned = results.getString("Owned");
				String theme = results.getString("Theme");
				String designerName = results.getString("DesignerName");
				double weight = results.getDouble("Weight");
				
				BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price, 
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
				return boardGame;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
	public List<BoardGames> getBoardGamesByYearReleased(int yearReleased) throws SQLException{
		System.out.println("Enter Getboardgames by year released!");
		List<BoardGames> boardGames = new ArrayList<BoardGames>();
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
						+ "MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DesignerName, Weight " +
						"FROM BoardGames INNER JOIN Games " +
						"  ON BoardGames.GameId = Games.GameId " +
						"WHERE BoardGames.YearReleased=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			System.out.println("Enter try!");
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBoardGame);
			selectStmt.setInt(1, yearReleased);
			results = selectStmt.executeQuery();
			while(results.next()) {
				System.out.println("Enter while loop!");
				int resultGameId = results.getInt("GameId");
				String title = results.getString("title");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				
				int minPlayer = results.getInt("MinPlayer");
				int maxPlayer = results.getInt("MaxPlayer");
				int averageTime = results.getInt("AverageTime");
				
				double ratingScore = results.getDouble("RatingScore");
				String mechanics = results.getString("Mechanics");
				String owned = results.getString("Owned");
				String theme = results.getString("Theme");
				String designerName = results.getString("DesignerName");
				double weight = results.getDouble("Weight");
				
				BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price,
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
//				System.out.println(boardGame);
				boardGames.add(boardGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return boardGames;
	}
	
	
	public List<BoardGames> getBoardGamesByMechanics(String mechanics) throws SQLException{
		List<BoardGames> boardGames = new ArrayList<BoardGames>();
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
						+ "MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DesignerName, Weight " +
						"FROM BoardGames INNER JOIN Games " +
						"  ON BoardGames.GameId = Games.GameId " +
						"WHERE BoardGames.Mechanics=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBoardGame);
			selectStmt.setString(1, mechanics);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultGameId = results.getInt("GameId");
				String title = results.getString("Title");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				int minPlayer = results.getInt("MinPlayer");
				int maxPlayer = results.getInt("MaxPlayer");
				int averageTime = results.getInt("AverageTime");
				int yearReleased = results.getInt("YearReleased");
				
				double ratingScore = results.getDouble("RatingScore");
				String owned = results.getString("Owned");
				String theme = results.getString("Theme");
				String designerName = results.getString("DesignerName");
				double weight = results.getDouble("Weight");
				
				BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price, 
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
				boardGames.add(boardGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return boardGames;
	}
	
	public List<BoardGames> getBoardGamesByOwned(String owned) throws SQLException{
		List<BoardGames> boardGames = new ArrayList<BoardGames>();
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
						+ "MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DesignerName, Weight " +
						"FROM BoardGames INNER JOIN Games " +
						"  ON BoardGames.GameId = Games.GameId " +
						"WHERE BoardGames.Owned=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBoardGame);
			selectStmt.setString(1, owned);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultGameId = results.getInt("GameId");
				String title = results.getString("Title");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");	
				
				int minPlayer = results.getInt("MinPlayer");
				int maxPlayer = results.getInt("MaxPlayer");
				int averageTime = results.getInt("AverageTime");
				int yearReleased = results.getInt("YearReleased");
				
				double ratingScore = results.getDouble("RatingScore");
				String mechanics = results.getString("Mechanics");
				String theme = results.getString("Theme");
				String designerName = results.getString("DesignerName");
				double weight = results.getDouble("Weight");
				
				BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price, 
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
				boardGames.add(boardGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return boardGames;
	}

	public List<BoardGames> getBoardGamesByTheme(String theme) throws SQLException{
		List<BoardGames> boardGames = new ArrayList<BoardGames>();
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
						+ "MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DesignerName, Weight " +
						"FROM BoardGames INNER JOIN Games " +
						"  ON BoardGames.GameId = Games.GameId " +
						"WHERE BoardGames.Theme=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBoardGame);
			selectStmt.setString(1, theme);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultGameId = results.getInt("GameId");
				String title = results.getString("Title");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				
				int minPlayer = results.getInt("MinPlayer");
				int maxPlayer = results.getInt("MaxPlayer");
				int averageTime = results.getInt("AverageTime");
				int yearReleased = results.getInt("YearReleased");
				
				double ratingScore = results.getDouble("RatingScore");
				String mechanics = results.getString("Mechanics");
				String owned = results.getString("Owned");
				String designerName = results.getString("DesignerName");
				double weight = results.getDouble("Weight");
				
				BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price,
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
				boardGames.add(boardGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return boardGames;
	}

	public List<BoardGames> getBoardGamesByDesignerName(String designerName) throws SQLException{
		List<BoardGames> boardGames = new ArrayList<BoardGames>();
		String selectBoardGame=
				"SELECT BoardGames.GameId AS GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price, "
						+ "Title, MinPlayer, MaxPlayer, AverageTime, YearReleased, RatingScore, Mechanics, Owned, Theme, DeveloperName, Weight " +
						"FROM BoardGames INNER JOIN Games " +
						"  ON BoardGames.GameId = Games.GameId " +
						"WHERE BoardGames.DesignerName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBoardGame);
			selectStmt.setString(1, designerName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultGameId = results.getInt("GameId");
				String title = results.getString("Title");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				
				int minPlayer = results.getInt("MinPlayer");
				int maxPlayer = results.getInt("MaxPlayer");
				int averageTime = results.getInt("AverageTime");
				int yearReleased = results.getInt("YearReleased");
				
				double ratingScore = results.getDouble("RatingScore");
				String mechanics = results.getString("Mechanics");
				String owned = results.getString("Owned");
				String theme = results.getString("Theme");
				double weight = results.getDouble("Weight");
				
				BoardGames boardGame = 
							new BoardGames(resultGameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price,
								minPlayer, maxPlayer, averageTime, yearReleased, ratingScore, mechanics, owned, theme, designerName, weight);
				boardGames.add(boardGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return boardGames;
	}
	
	
	public BoardGames delete(BoardGames boardGame) throws SQLException {
		String deleteBoardGame = "DELETE FROM BoardGames WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBoardGame);
			deleteStmt.setInt(1, boardGame.getGameId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Administrators first.
			super.delete(boardGame);
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
