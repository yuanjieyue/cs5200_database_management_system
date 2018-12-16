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
public class OrdersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static OrdersDao instance = null;
	protected OrdersDao() {
		connectionManager = new ConnectionManager();
	}
	public static OrdersDao getInstance() {
		if(instance == null) {
			instance = new OrdersDao();
		}
		return instance;
	}

	/**
	 * Save the Accounts instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Orders create(Orders order) throws SQLException {
		String insertOrder = "INSERT INTO Orders(UserId, GameId, PlaceOrderDate, ReceiverName, ShipAddress, ShippedDate, PaymentMethod, OrderStatus, ShippingOptions) VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOrder,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, order.getUser().getUserId());
			System.out.println(order.getGame() == null);
			insertStmt.setInt(2, order.getGame().getGameId());
			insertStmt.setTimestamp(3, new Timestamp(order.getPlaceOrderDate().getTime()));
			insertStmt.setString(4, order.getReceiverName());
			insertStmt.setString(5, order.getShipAddress());
			insertStmt.setTimestamp(6, new Timestamp(order.getShippedDate().getTime()));
			insertStmt.setString(7, order.getPaymentMethod());
			insertStmt.setString(8, order.getOrderStatus());
			insertStmt.setString(9, order.getShippingOption());

			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int orderId = -1;
			if(resultKey.next()) {
				orderId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			order.setOrderId(orderId);
			return order;
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
	public Orders delete(Orders order) throws SQLException {
		String deleteOrder = "DELETE FROM Orders WHERE OrderId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteOrder);
			deleteStmt.setInt(1, order.getOrderId());
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
	public Orders getOrderByOrderId(int orderId) throws SQLException {
		String selectOrder = "SELECT OrderId, UserId, GameId, PlaceOrderDate, ReceiverName, "
				+ "ShipAddress, ShippedDate, PaymentMethod, OrderStatus, ShippingOptions FROM Orders WHERE OrderId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOrder);
			selectStmt.setInt(1, orderId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			GamesDao gamesDao = GamesDao.getInstance();
			if(results.next()) {
				int id = results.getInt("OrderId");
				int userId = results.getInt("UserId");
				int gameId = results.getInt("GameId");
				Date placeOrderDate = results.getDate("PlaceOrderDate");
				String receiverName = results.getString("ReceiverName");
				String shippingAddress = results.getString("ShipAddress");
				Date shippedDate = results.getDate("ShippedDate");
				String paymentMethod = results.getString("PaymentMethod");
				String orderStatus = results.getString("OrderStatus");
				String shippingOption = results.getString("ShippingOptions");
				Users user = usersDao.getUserByUserId(userId);
				Games game = gamesDao.getGameByGameId(gameId);
				Orders order = new Orders(id, user, game, placeOrderDate, receiverName, shippingAddress, shippedDate, paymentMethod, orderStatus, shippingOption);				
				return order;		
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
	
	/**
	 * Get the Accounts record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Accounts instance.
	 */
	public List<Orders> getOrdersByUserName(String userName) throws SQLException {
		List<Orders> orders = new ArrayList<>();
		String selectOrder = "SELECT OrderId, Orders.UserId, UserName, GameId, PlaceOrderDate, ReceiverName, ShipAddress, ShippedDate, PaymentMethod, OrderStatus, ShippingOptions "
				+ "FROM Orders Inner Join Users "
				+ "On Orders.UserId = Users.UserId "
				+ "WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOrder);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			GamesDao gamesDao = GamesDao.getInstance();
			while(results.next()) {
				int id = results.getInt("OrderId");
				int gameId = results.getInt("GameId");
				Date placeOrderDate = results.getDate("PlaceOrderDate");
				String receiverName = results.getString("ReceiverName");
				String shippingAddress = results.getString("ShipAddress");
				Date shippedDate = results.getDate("ShippedDate");
				String paymentMethod = results.getString("PaymentMethod");
				String orderStatus = results.getString("OrderStatus");
				String shippingOption = results.getString("ShippingOptions");
				Users user = usersDao.getUserByUserName(userName);
				Games game = gamesDao.getGameByGameId(gameId);
				Orders order = new Orders(id, user, game, placeOrderDate, receiverName, shippingAddress, shippedDate, paymentMethod, orderStatus, shippingOption);				
				orders.add(order);		
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
		return orders;
	}
	
	/**
	 * Get the Accounts record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Accounts instance.
	 */
	public Orders getOrderByUserNameAndTitle(String userName, String title) throws SQLException {
		String selectOrder = "SELECT OrderId, Orders.UserId, Orders.GameId, UserName, Title, PlaceOrderDate, "
				+ "ReceiverName, ShipAddress, ShippedDate, PaymentMethod, OrderStatus, ShippingOptions "
				+ "FROM Orders Inner Join Users "
				+ "On Orders.UserId = Users.UserId "
				+ "Inner Join Games "
				+ "On Orders.GameId = Games.GameId "
				+ "WHERE UserName=? And Title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOrder);
			selectStmt.setString(1, userName);
			selectStmt.setString(2, title);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			GamesDao gamesDao = GamesDao.getInstance();
			if(results.next()) {
				int id = results.getInt("OrderId");
				Date placeOrderDate = results.getDate("PlaceOrderDate");
				String receiverName = results.getString("ReceiverName");
				String shippingAddress = results.getString("ShipAddress");
				Date shippedDate = results.getDate("ShippedDate");
				String paymentMethod = results.getString("PaymentMethod");
				String orderStatus = results.getString("OrderStatus");
				String shippingOption = results.getString("ShippingOptions");
				Users user = usersDao.getUserByUserName(userName);
				Games game = gamesDao.getGameByTitle(title);
				Orders order = new Orders(id, user, game, placeOrderDate, receiverName, shippingAddress, shippedDate, paymentMethod, orderStatus, shippingOption);				
				return order;		
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
