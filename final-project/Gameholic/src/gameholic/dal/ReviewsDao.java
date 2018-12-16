package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gameholic.model.*;


public class ReviewsDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReview = "INSERT INTO Reviews(Content,Created,Rating,Game,User) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, review.getContent());
			insertStmt.setTimestamp(2, new Timestamp(review.getCreated().getTime()));
			insertStmt.setDouble(3, review.getRating());
			insertStmt.setInt(4, review.getGame().getGameId());
			insertStmt.setInt(5, review.getUser().getUserId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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

	public Reviews getReviewByReviewId(int reviewId) throws SQLException{
		String selectReview = "SELECT ReviewId,Content,Created,Rating,Game,User FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setLong(1, reviewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			GamesDao gamesDao = GamesDao.getInstance();
			if(results.next()) {
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				int rating = results.getInt("Rating");
				Users user = usersDao.getUserByUserId(results.getInt("User"));
				Games game = gamesDao.getGameByGameId(results.getInt("Game"));
				Reviews review = new Reviews(reviewId, content, created, rating, game, user);
				return review;
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

	public List<Reviews> getReviewsByUserId(int userId) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		String selectReviews =
			"SELECT ReviewId,Created,Content,Rating,Game,User " +
			"FROM Reviews " +
			"WHERE User=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			GamesDao gamesDao = GamesDao.getInstance();
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				int rating = results.getInt("Rating");
				Users user = usersDao.getUserByUserId(userId);
				Games game = gamesDao.getGameByGameId(results.getInt("Game"));
				Reviews review = new Reviews(resultReviewId, content, created, rating, game, user);
				reviews.add(review);
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
		return reviews;
	}
	
	public List<Reviews> getReviewsByGameId(int gameId) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		String selectReviews =
			"SELECT ReviewId,Created,Content,Rating,Game,User " +
			"FROM Reviews " +
			"WHERE Game=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			GamesDao gamesDao = GamesDao.getInstance();
			while(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				int rating = results.getInt("Rating");
				Users user = usersDao.getUserByUserId(results.getInt("User"));
				Games game = gamesDao.getGameByGameId(gameId);
				Reviews review = new Reviews(resultReviewId, content, created, rating, game, user);
				reviews.add(review);
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
		return reviews;
	}

	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

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
