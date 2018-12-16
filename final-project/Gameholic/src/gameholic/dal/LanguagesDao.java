package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import gameholic.model.Languages;

public class LanguagesDao {
	protected ConnectionManager connectionManager;

	private static LanguagesDao instance = null;
	protected LanguagesDao() {
		connectionManager = new ConnectionManager();
	}
	public static LanguagesDao getInstance() {
		if(instance == null) {
			instance = new LanguagesDao();
		}
		return instance;
	}
	
	public Languages create(Languages language, int gameId) throws SQLException{
		String insertLanguage =
			"INSERT INTO Languages(GameId, English, Chinese, Spanish, French, Italian, German) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			Map<String, Boolean> map = language.getLanguageMap();
			insertStmt = connection.prepareStatement(insertLanguage);
			insertStmt.setInt(1, gameId);
			insertStmt.setBoolean(2, map.get("English"));
			insertStmt.setBoolean(3, map.get("Chinese"));
			insertStmt.setBoolean(4, map.get("Spanish"));
			insertStmt.setBoolean(5, map.get("French"));
			insertStmt.setBoolean(6, map.get("Italian"));
			insertStmt.setBoolean(7, map.get("German"));

			insertStmt.executeUpdate();
			
			return language;
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
	public Languages getLanguageById(int gameId) throws SQLException {
		String selectLanguages =
			"SELECT GameId, GameId, English, Chinese, Spanish, French, Italian, German " +
			"FROM Languages " +
			"WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLanguages);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				boolean english = results.getBoolean("English");
				boolean chinese = results.getBoolean("Chinese");
				boolean spanish = results.getBoolean("Spanish");
				boolean french = results.getBoolean("French");
				boolean italian = results.getBoolean("Italian");
				boolean german = results.getBoolean("German");
				
				
				
				Languages language = new Languages(english, chinese, spanish, french, italian, german);
				return language;
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
	
	public Languages delete(int gameId) throws SQLException{
		String deleteLanguage = "DELETE FROM Languages WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLanguage);
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
