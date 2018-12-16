package gameholic.model;

import java.util.Date;

public class Accounts {
	private int accountId;
	private String billingAddress;
	private boolean isClosed;
	private Date createdAt;
	private Date closedAt;
	private boolean isPremium;
	private Users user;
	
	
	public Accounts(int accountId, String billingAddress, boolean isClosed, Date createdAt, Date closedAt,
			boolean isPremium, Users user) {
		super();
		this.accountId = accountId;
		this.billingAddress = billingAddress;
		this.isClosed = isClosed;
		this.createdAt = createdAt;
		this.closedAt = closedAt;
		this.isPremium = isPremium;
		this.user = user;
	}
	
	public Accounts(int accountId) {
		this.accountId = accountId;
	}
	
	public Accounts(String billingAddress, boolean isClosed, Date createdAt, Date closedAt,
			boolean isPremium, Users user) {
		this.billingAddress = billingAddress;
		this.isClosed = isClosed;
		this.createdAt = createdAt;
		this.closedAt = closedAt;
		this.isPremium = isPremium;
		this.user = user;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
}
