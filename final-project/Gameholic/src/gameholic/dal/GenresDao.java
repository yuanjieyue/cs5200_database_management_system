package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gameholic.model.Genres;
import gameholic.model.VideoGames;


public class GenresDao {
	protected ConnectionManager connectionManager;

	private static GenresDao instance = null;
	protected GenresDao() {
		connectionManager = new ConnectionManager();
	}
	public static GenresDao getInstance() {
		if(instance == null) {
			instance = new GenresDao();
		}
		return instance;
	}
	
	public Genres create(Genres genre, int gameId) throws SQLException{
		String insertGenre =
			"INSERT INTO Genres(GameId, Action, Strategy, Sports, Simulation, Racing, RolePlaying, Adventure, Fighting, Shooter, Casual, MusicParty) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			Map<String, Boolean> map = genre.getGenreMap();
			insertStmt = connection.prepareStatement(insertGenre);
			insertStmt.setInt(1, gameId);
			insertStmt.setBoolean(2, map.get("Action"));
			insertStmt.setBoolean(3, map.get("Strategy"));
			insertStmt.setBoolean(4, map.get("Sports"));
			insertStmt.setBoolean(5, map.get("Simulation"));
			insertStmt.setBoolean(6, map.get("Racing"));
			insertStmt.setBoolean(7, map.get("Role Playing"));
			insertStmt.setBoolean(8, map.get("Adventure"));
			insertStmt.setBoolean(9, map.get("Fighting"));
			insertStmt.setBoolean(10, map.get("Shooter"));
			insertStmt.setBoolean(11, map.get("Casual"));
			insertStmt.setBoolean(12, map.get("Music Party"));
			insertStmt.executeUpdate();
			
			return genre;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	public Genres getGenreById(int gameId) throws SQLException {
		String selectGenres =
			"SELECT GameId, Action, Strategy, Sports, Simulation, Racing, RolePlaying, Adventure, Fighting, Shooter, Casual, MusicParty " +
			"FROM Genres " +
			"WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGenres);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				boolean action = results.getBoolean("Action");
				boolean strategy = results.getBoolean("Strategy");
				boolean sports = results.getBoolean("Sports");
				boolean simulation = results.getBoolean("Simulation");
				boolean racing = results.getBoolean("Racing");
				boolean rolePlaying = results.getBoolean("RolePlaying");
				boolean adventure = results.getBoolean("Adventure");
				boolean fighting = results.getBoolean("Fighting");
				boolean shooter = results.getBoolean("Shooter");
				boolean casual = results.getBoolean("Casual");
				boolean musicParty = results.getBoolean("MusicParty");
				
				
				Genres genre = new Genres(action, strategy, sports, simulation, racing, rolePlaying, adventure, fighting, shooter, casual, musicParty);
				return genre;
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
	
	public Genres delete(int gameId) throws SQLException{
		String deleteGenre = "DELETE FROM Genres WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGenre);
			deleteStmt.setInt(1, gameId);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
