package gameholic.model;

import java.util.Date;


public class Orders {
	private int orderId;
	private Users user;
	private Games game;
	private Date placeOrderDate;
	private String receiverName;
	private String shipAddress;
	private Date shippedDate;
	private String paymentMethod;
	private String orderStatus;
	private String shippingOption;
	
	public Orders(int orderId, Users user, Games game, Date placeOrderDate, String receiverName, String shipAddress,
			Date shippedDate, String paymentMethod, String orderStatus, String shippingOption) {
		this.orderId = orderId;
		this.user = user;
		this.game = game;
		this.placeOrderDate = placeOrderDate;
		this.receiverName = receiverName;
		this.shipAddress = shipAddress;
		this.shippedDate = shippedDate;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.shippingOption = shippingOption;
	}
	
	public Orders(int orderId) {
		this.orderId = orderId;
	}
	
	public Orders(Users user, Games game, Date placeOrderDate, String receiverName, String shipAddress,
			Date shippedDate, String paymentMethod, String orderStatus, String shippingOption) {
		this.user = user;
		this.game = game;
		this.placeOrderDate = placeOrderDate;
		this.receiverName = receiverName;
		this.shipAddress = shipAddress;
		this.shippedDate = shippedDate;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.shippingOption = shippingOption;
	}
	
	public Orders(Users user, Games game, String shipAddress) {
		this.user = user;
		this.game = game;
		this.placeOrderDate = new Date();
		this.receiverName = null;
		this.shipAddress = shipAddress;
		this.shippedDate = new Date();
		this.paymentMethod = null;
		this.orderStatus = null;
		this.shippingOption = null;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}

	public Date getPlaceOrderDate() {
		return placeOrderDate;
	}

	public void setPlaceOrderDate(Date placeOrderDate) {
		this.placeOrderDate = placeOrderDate;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}
	
	
}
