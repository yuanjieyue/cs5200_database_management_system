package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gameholic.model.CreditCards;
import gameholic.model.Users;


public class CreditCardsDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	public CreditCards create(CreditCards creditCard) throws SQLException{
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber,Expiration,UserId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);

			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setDate(2, new java.sql.Date(creditCard.getExpiration().getTime()));
			insertStmt.setInt(3, creditCard.getUser().getUserId());

			insertStmt.executeUpdate();
			return creditCard;
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
	public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException{
		String selectCreditCard = "SELECT CardNumber,Expiration,UserId FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();

			if(results.next()) {
				long resultCardNumber = results.getLong("CardNumber");
				Date expiration = (java.util.Date)results.getDate("Expiration");
				Users user = usersDao.getUserByUserId(results.getInt("UserId"));
				CreditCards card = new CreditCards(resultCardNumber, expiration, user);
				return card;
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
	public List<CreditCards> getCreditCardsByUserId(int userId) throws SQLException{
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards =
			"SELECT CardNumber, Expiration, CreditCards.UserId AS UserId " +
			"FROM CreditCards INNER JOIN Users " +
			"  ON CreditCards.UserId = Users.UserId " +
			"WHERE Users.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				long cardNumber = results.getLong("CardNumber");
				Date expiration = (java.util.Date) results.getDate("Expiration");
				Users user = usersDao.getUserByUserId(results.getInt("UserId"));
				CreditCards card = new CreditCards(cardNumber, expiration, user);
				creditCards.add(card);
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
		return creditCards;
	}
//	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException{
//		String updatePerson = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
//		Connection connection = null;
//		PreparedStatement updateStmt = null;
//		java.sql.Date sqlExpiration = new java.sql.Date(newExpiration.getTime());
//		try {
//			connection = connectionManager.getConnection();
//			updateStmt = connection.prepareStatement(updatePerson);
//			
//			updateStmt.setDate(1, sqlExpiration);
//			updateStmt.setLong(2, creditCard.getCardNumber());
//			updateStmt.executeUpdate();
//			
//			// Update the person param before returning to the caller.
//			creditCard.setExpiration(newExpiration);
//			return creditCard;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(updateStmt != null) {
//				updateStmt.close();
//			}
//		}
//	}
	public CreditCards delete(CreditCards creditCard) throws SQLException{
		String deleteCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCard);
			deleteStmt.setLong(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the CreditCards instance.
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
