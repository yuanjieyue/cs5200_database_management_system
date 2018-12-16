package gameholic.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gameholic.model.*;


public class CartItemsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CartItemsDao instance = null;
	protected CartItemsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CartItemsDao getInstance() {
		if(instance == null) {
			instance = new CartItemsDao();
		}
		return instance;
	}

	/**
	 * Save the CartItem instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public CartItem create(CartItem cartItem) throws SQLException {
		String insertCartItem = "INSERT INTO CartItem(ShoppingCartId, Quantity, OrderId, Price, GameId) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCartItem,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, cartItem.getShoppingCartId());
			insertStmt.setInt(2, cartItem.getQuantity());
			insertStmt.setInt(3, cartItem.getOrderId());
			insertStmt.setDouble(4, cartItem.getPrice());
			insertStmt.setInt(5, cartItem.getGameId());
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int cartItemId = -1;
			if(resultKey.next()) {
				cartItemId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cartItem.setCartItemId(cartItemId);
			return cartItem;
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
	 * Delete the CartItem instance.
	 * This runs a DELETE statement.
	 */
	public CartItem delete(CartItem cartItem) throws SQLException {
		String deleteCartItem = "DELETE FROM CartItem WHERE CartItemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCartItem);
			deleteStmt.setInt(1, cartItem.getCartItemId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Users instance.
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
	 * Get the CartItem record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public CartItem getCartItemByCartItemId(int cartItemId) throws SQLException {
		String selectCartItem = "SELECT CartItemId, ShoppingCartId, Quantity, OrderId, Price, GameId FROM CartItem WHERE CartItemId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCartItem);
			selectStmt.setInt(1, cartItemId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultCartItemId = results.getInt("CartItemId");
				int shippingCartId = results.getInt("ShoppingCartId");
				int quantity = results.getInt("Quantity");
				int orderId = results.getInt("OrderId");
				double price = results.getDouble("Price");
				int gameId = results.getInt("GameId");
				CartItem cartItem = new CartItem(resultCartItemId, shippingCartId, quantity, orderId, price, gameId);
				return cartItem;			
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
