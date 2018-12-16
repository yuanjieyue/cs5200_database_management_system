package gameholic.model;


public class CartItem {
	
	protected int cartItemId;
	protected int shoppingCartId;
	protected int quantity;
	protected int orderId;
	protected double price;
	protected int gameId;
	
	public CartItem(int cartItemId, int shoppingCartId, int quantity, int orderId, double price, int gameId) {
		super();
		this.cartItemId = cartItemId;
		this.shoppingCartId = shoppingCartId;
		this.quantity = quantity;
		this.orderId = orderId;
		this.price = price;
		this.gameId = gameId;
	}
	
	public CartItem(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	
	public CartItem(int shoppingCartId, int quantity, int orderId, double price, int gameId) {
		this.shoppingCartId = shoppingCartId;
		this.quantity = quantity;
		this.orderId = orderId;
		this.price = price;
		this.gameId = gameId;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}