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

public class ShoppingCartDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ShoppingCartDao instance = null;
	protected ShoppingCartDao() {
		connectionManager = new ConnectionManager();
	}
	public static ShoppingCartDao getInstance() {
		if(instance == null) {
			instance = new ShoppingCartDao();
		}
		return instance;
	}

	/**
	 * Save the Accounts instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public ShoppingCart create(ShoppingCart shoppingCart) throws SQLException {
		String insertShoppingCart = "INSERT INTO ShoppingCart(Account, Created) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertShoppingCart,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, shoppingCart.getAccount().getAccountId());
			insertStmt.setTimestamp(2, new Timestamp(shoppingCart.getCreated().getTime()));
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int shoppingCartId = -1;
			if(resultKey.next()) {
				shoppingCartId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			shoppingCart.setShoppingCartId(shoppingCartId);
			return shoppingCart;
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
	public ShoppingCart delete(ShoppingCart shoppingCart) throws SQLException {
		String deleteShoppingCart = "DELETE FROM ShoppingCart WHERE shoppingCartId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteShoppingCart);
			deleteStmt.setInt(1, shoppingCart.getShoppingCartId());
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
	public ShoppingCart getShoppingCartByShoppingCartId(int shoppingCartId) throws SQLException {
		String selectShoppingCart = "SELECT ShoppingCartId, Account, Created FROM ShoppingCart WHERE ShoppingCartId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectShoppingCart);
			selectStmt.setInt(1, shoppingCartId);
			results = selectStmt.executeQuery();
			AccountsDao accountsDao = AccountsDao.getInstance();
			if(results.next()) {
				int accountId = results.getInt("Account");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Accounts account = accountsDao.getAccountByAccountId(accountId);
				ShoppingCart shoppingCart = new ShoppingCart(shoppingCartId, account, created);
				return shoppingCart;
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
	public List<ShoppingCart> getShoppingCartByAccountId(int accountId) throws SQLException{
		List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
		String selectShoppingCarts = "SELECT ShoppingCartId, Account, Created FROM ShoppingCart WHERE AccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectShoppingCarts);
			selectStmt.setInt(1, accountId);
			results = selectStmt.executeQuery();
			AccountsDao accountsDao = AccountsDao.getInstance();
			while(results.next()) {
				int shoppingCartId = results.getInt("ShoppingCartId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				Accounts account = accountsDao.getAccountByAccountId(accountId);
				ShoppingCart shoppingCart = new ShoppingCart(shoppingCartId, account, created);
				shoppingCarts.add(shoppingCart);
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
		return shoppingCarts;
	}

}
