package gameholic.model;

import java.util.Date;

public class ShoppingCart {
	private int shoppingCartId;
	private Accounts account;
	private Date created;
	public ShoppingCart(int shoppingCartId, Accounts account, Date created) {
		this.shoppingCartId = shoppingCartId;
		this.account = account;
		this.created = created;
	}
	
	public ShoppingCart(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	
	public ShoppingCart(Accounts account, Date created) {
		this.account = account;
		this.created = created;
	}

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
