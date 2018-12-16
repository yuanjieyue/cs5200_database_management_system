package gameholic.model;

public class Games {
	private int gameId;
	private String title;
	private String intro;
	private boolean isOutOfStock;
	private int stockNumber;
	private String pictureUrl;
	private double price;
	
	public Games(int gameId, String title, String intro, boolean isOutOfStock, int stockNumber, String pictureUrl, double price) {
		this.gameId = gameId;
		this.title = title;
		this.intro = intro;
		this.isOutOfStock = isOutOfStock;
		this.stockNumber = stockNumber;
		this.pictureUrl = pictureUrl;
		this.price = price;
	}
	
	public Games(int gameId) {
		this.gameId = gameId;
	}
	
	public Games(String title, String intro, boolean isOutOfStock, int stockNumber, String pictureUrl, double price) {
		this.title = title;
		this.intro = intro;
		this.isOutOfStock = isOutOfStock;
		this.stockNumber = stockNumber;
		this.pictureUrl = pictureUrl;
		this.price = price;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public boolean isOutOfStock() {
		return isOutOfStock;
	}

	public void setOutOfStock(boolean isOutOfStock) {
		this.isOutOfStock = isOutOfStock;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
