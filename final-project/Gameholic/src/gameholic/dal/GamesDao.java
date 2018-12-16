package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import gameholic.model.Games;

public class GamesDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static GamesDao instance = null;
	protected GamesDao() {
		connectionManager = new ConnectionManager();
	}
	public static GamesDao getInstance() {
		if(instance == null) {
			instance = new GamesDao();
		}
		return instance;
	}

	/**
	 * Save the Games instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Games create(Games game) throws SQLException {
		String insertGames = "INSERT INTO Games(Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGames,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, game.getTitle());
			insertStmt.setString(2, game.getIntro());
			insertStmt.setBoolean(3, game.isOutOfStock());
			insertStmt.setInt(4, game.getStockNumber());
			insertStmt.setString(5, game.getPictureUrl());
			insertStmt.setDouble(6, game.getPrice());

			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int gameId = -1;
			if(resultKey.next()) {
				gameId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			game.setGameId(gameId);
			return game;
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
	 * Delete the Games instance.
	 * This runs a DELETE statement.
	 */
	public Games delete(Games game) throws SQLException {
		String deleteGame = "DELETE FROM Games WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGame);
			deleteStmt.setInt(1, game.getGameId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Games instance.
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

	/**
	 * Update the Price of the Games instance.
	 * This runs a UPDATE statement.
	 */
	public Games updatePrice(Games game, double newPrice) throws SQLException {
		String updateGame = "UPDATE Games SET Price=? WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateGame);
			updateStmt.setDouble(1, newPrice);
			updateStmt.setInt(2, game.getGameId());
			updateStmt.executeUpdate();
			
			// Update the game param before returning to the caller.
			game.setPrice(newPrice);
			return game;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	/**
	 * Get the Games record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Games instance.
	 */
	public Games getGameByGameId(int gameId) throws SQLException {
		String selectGame = "SELECT GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, Price FROM Games WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGame);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
	
			if(results.next()) {
				
				int id = results.getInt("GameId");
				String title = results.getString("Title");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				Games game = new Games(id, title, intro, isOutOfStock, stockNumber, pictureUrl, price);
				return game;
				
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
	
	public Games getGameByTitle(String title) throws SQLException {
		String selectGame = "SELECT GameId, Title, Intro, IsOutOfStock, StockNumber, PictureURL, "
				+ "Price FROM Games WHERE Title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGame);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
	
			if(results.next()) {			
				int id = results.getInt("GameId");
				String intro = results.getString("Intro");
				boolean isOutOfStock = results.getBoolean("IsOutOfStock");
				int stockNumber = results.getInt("StockNumber");
				String pictureUrl = results.getString("PictureURL");
				double price = results.getDouble("Price");
				Games game = new Games(id, title, intro, isOutOfStock, stockNumber, pictureUrl, price);
				return game;
				
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
	
}
