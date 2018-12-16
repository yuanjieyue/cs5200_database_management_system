package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gameholic.model.Accounts;
import gameholic.model.Accounts;
import gameholic.model.Users;

public class AccountsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AccountsDao instance = null;
	protected AccountsDao() {
		connectionManager = new ConnectionManager();
	}
	public static AccountsDao getInstance() {
		if(instance == null) {
			instance = new AccountsDao();
		}
		return instance;
	}

	/**
	 * Save the Accounts instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Accounts create(Accounts account) throws SQLException {
		String insertAccount = "INSERT INTO Accounts(BillingAddress, isClosed, CreatedAt, ClosedAt, isPremium, UserId) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAccount,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, account.getBillingAddress());
			insertStmt.setBoolean(2, account.isClosed());
			insertStmt.setDate(3, (java.sql.Date)account.getCreatedAt());
			insertStmt.setDate(4, (java.sql.Date)account.getClosedAt());
			insertStmt.setBoolean(5, account.isPremium());
			insertStmt.setInt(6, account.getUser().getUserId());
			
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			account.setAccountId(reviewId);
			return account;
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
	 * Delete the Accounts instance.
	 * This runs a DELETE statement.
	 */
	public Accounts delete(Accounts account) throws SQLException {
		String deletePerson = "DELETE FROM Accounts WHERE AccountId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1, account.getAccountId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Accounts instance.
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
	 * Get the Accounts record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Accounts instance.
	 */
	public Accounts getAccountByAccountId(int accountId) throws SQLException {
		String selectPerson = "SELECT AccountId, BillingAddress, isClosed, CreatedAt, ClosedAt, isPremium, UserId FROM Accounts WHERE AccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, accountId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				int id = results.getInt("AccountId");
				String billingAddress = results.getString("BillingAddress");
				boolean isClosed = results.getBoolean("isClosed");
				java.util.Date createdAt = (java.util.Date) results.getDate("CreatedAt");
				java.util.Date closedAt = (java.util.Date) results.getDate("ClosedAt");
				boolean isPremium = results.getBoolean("isPremium");
				int userId = results.getInt("UserId");
				Users user = usersDao.getUserByUserId(userId);
				Accounts account = new Accounts(id, billingAddress, isClosed, createdAt, closedAt, isPremium, user);
				return account;
				
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
	public List<Accounts> getAccountsByUserId(int userId) throws SQLException{
		List<Accounts> accounts = new ArrayList<Accounts>();
		String selectAccounts =
			"SELECT AccountId, BillingAddress, isClosed, CreatedAt, ClosedAt, isPremium, Accounts.UserId " +
			"FROM Accounts INNER JOIN Users " +
			"  ON Accounts.UserId = Users.UserId " +
			"WHERE Users.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAccounts);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int id = results.getInt("AccountId");
				String billingAddress = results.getString("BillingAddress");
				boolean isClosed = results.getBoolean("isClosed");
				java.util.Date createdAt = (java.util.Date) results.getDate("CreatedAt");
				java.util.Date closedAt = (java.util.Date) results.getDate("ClosedAt");
				boolean isPremium = results.getBoolean("isPremium");
				Users user = usersDao.getUserByUserId(userId);
				Accounts account = new Accounts(id, billingAddress, isClosed, createdAt, closedAt, isPremium, user);
				accounts.add(account);
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
		return accounts;
	}

}
