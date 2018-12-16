package gameholic.model;

public class BoardGames extends Games{
	private int minPlayer;
	private int maxPlayer;
	private int averageTime;
	private int yearReleased;
	private double ratingScore;
	private String mechanics;
	private String owned;
	private String theme;
	private String designerName;
	private double weight;
	
	public BoardGames(int gameId, String title, String intro, boolean isOutOfStock, int stockNumber, String pictureUrl,
			double price, int minPlayer, int maxPlayer, int averageTime, int yearReleased, double ratingScore,
			String mechanics, String owned, String theme, String designerName, double weight) {
		super(gameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price);
		this.minPlayer = minPlayer;
		this.maxPlayer = maxPlayer;
		this.averageTime = averageTime;
		this.yearReleased = yearReleased;
		this.ratingScore = ratingScore;
		this.mechanics = mechanics;
		this.owned = owned;
		this.theme = theme;
		this.designerName = designerName;
		this.weight = weight;
	}
	
	public BoardGames(String title, String intro, boolean isOutOfStock, int stockNumber, String pictureUrl,
			double price, int minPlayer, int maxPlayer, int averageTime, int yearReleased, double ratingScore,
			String mechanics, String owned, String theme, String designerName, double weight) {
		super(title, intro, isOutOfStock, stockNumber, pictureUrl, price);
		this.minPlayer = minPlayer;
		this.maxPlayer = maxPlayer;
		this.averageTime = averageTime;
		this.yearReleased = yearReleased;
		this.ratingScore = ratingScore;
		this.mechanics = mechanics;
		this.owned = owned;
		this.theme = theme;
		this.designerName = designerName;
		this.weight = weight;
	}

	public int getMinPlayer() {
		return minPlayer;
	}

	public void setMinPlayer(int minPlayer) {
		this.minPlayer = minPlayer;
	}

	public int getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public int getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(int averageTime) {
		this.averageTime = averageTime;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public double getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(double ratingScore) {
		this.ratingScore = ratingScore;
	}

	public String getMechanics() {
		return mechanics;
	}

	public void setMechanics(String mechanics) {
		this.mechanics = mechanics;
	}

	public String getOwned() {
		return owned;
	}

	public void setOwned(String owned) {
		this.owned = owned;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
