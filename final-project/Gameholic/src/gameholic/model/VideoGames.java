package gameholic.model;

public class VideoGames extends Games{
	private String console;
	private double USSales;
	private int yearReleased;
	private String publisher;
	private String developerName;
	private double averageRating;
	private int maxPlayers;
	private boolean isOnline;
	private boolean isLicensed;
	private String contentRating;
	private Genres genre;
	private Languages language;
	
	public VideoGames(int gameId, String title, String intro, boolean isOutOfStock, int stockNumber, String pictureUrl,
			double price, String console, double uSSales, int yearReleased, String publisher, String developerName,
			double averageRating, int maxPlayers, boolean isOnline, boolean isLicensed, String contentRating,
			Genres genre, Languages language) {
		super(gameId, title, intro, isOutOfStock, stockNumber, pictureUrl, price);
		this.console = console;
		USSales = uSSales;
		this.yearReleased = yearReleased;
		this.publisher = publisher;
		this.developerName = developerName;
		this.averageRating = averageRating;
		this.maxPlayers = maxPlayers;
		this.isOnline = isOnline;
		this.isLicensed = isLicensed;
		this.contentRating = contentRating;
		this.genre = genre;
		this.language = language;
	}
	
	public VideoGames(String title, String intro, boolean isOutOfStock, int stockNumber, String pictureUrl,
			double price, String console, double uSSales, int yearReleased, String publisher, String developerName,
			double averageRating, int maxPlayers, boolean isOnline, boolean isLicensed, String contentRating,
			Genres genre, Languages language) {
		super(title, intro, isOutOfStock, stockNumber, pictureUrl, price);
		this.console = console;
		USSales = uSSales;
		this.yearReleased = yearReleased;
		this.publisher = publisher;
		this.developerName = developerName;
		this.averageRating = averageRating;
		this.maxPlayers = maxPlayers;
		this.isOnline = isOnline;
		this.isLicensed = isLicensed;
		this.contentRating = contentRating;
		this.genre = genre;
		this.language = language;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public double getUSSales() {
		return USSales;
	}

	public void setUSSales(double uSSales) {
		USSales = uSSales;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isLicensed() {
		return isLicensed;
	}

	public void setLicensed(boolean isLicensed) {
		this.isLicensed = isLicensed;
	}

	public String getContentRating() {
		return contentRating;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public Genres getGenre() {
		return genre;
	}

	public void setGenre(Genres genre) {
		this.genre = genre;
	}

	public Languages getLanguage() {
		return language;
	}

	public void setLanguage(Languages language) {
		this.language = language;
	}
}
