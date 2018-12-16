CREATE SCHEMA IF NOT EXISTS Gameholic;
USE Gameholic;


DROP TABLE IF EXISTS Genres;
DROP TABLE IF EXISTS Languages;
DROP TABLE IF EXISTS ShippingOptions;
DROP TABLE IF EXISTS OrderStatus;

DROP TABLE IF EXISTS CartItem;
DROP TABLE IF EXISTS ShoppingCart;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS BoardGames;
DROP TABLE IF EXISTS VideoGames;
DROP TABLE IF EXISTS Games;
DROP TABLE IF EXISTS BoardGameDesigners;
DROP TABLE IF EXISTS GameDevelopers;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Users;


-- CREATE TABLE GamePublishers(
--   PubliserName VARCHAR(255),
--   About LONGTEXT
-- );

CREATE TABLE Users(
  UserId INT AUTO_INCREMENT,
  UserName VARCHAR(255),
  Password VARCHAR(255),
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  Phone VARCHAR(255),
  Email VARCHAR(255),
  Street1 VARCHAR(255),
  Street2 VARCHAR(255),
  City VARCHAR(255),
  State VARCHAR(255),
  ZipCode VARCHAR(255),
  CONSTRAINT pk_Users_UserId PRIMARY KEY(UserId)
);

CREATE TABLE CreditCards(
  CardNumber BIGINT,
  Expiration DATE NOT NULL,
  UserId INT,
  CONSTRAINT pk_CreditCards_CardNumber PRIMARY KEY(CardNumber),
  CONSTRAINT fk_CreditCards_UserId FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Accounts(
  AccountId INT AUTO_INCREMENT,
  BillingAddress VARCHAR(1024),
  isClosed BOOLEAN,
  CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ClosedAt TIMESTAMP DEFAULT '1970-01-01 00:00:01',
  isPremium BOOLEAN,
  UserId INT,
  CONSTRAINT pk_Accounts_AccountId PRIMARY KEY(AccountId),
  CONSTRAINT fk_Accounts_UserId FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Games(
  GameId INT auto_increment,
  Intro LONGTEXT,
  IsOutOfStock BOOLEAN,
  StockNumber INT,
  PictureURL VARCHAR(255),
  Price decimal(10,2),
  CONSTRAINT pk_Games_GameId PRIMARY KEY(GameId)
);

CREATE TABLE VideoGames(
  GameId INT,
  Title VARCHAR(255),
  Console VARCHAR(255),
  USSales decimal(10,2),
  YearReleased INT,
  Publisher VARCHAR(255),
  DeveloperName VARCHAR(255),
  AverageRating Decimal(10,2),
  MaxPlayers INT,
  IsOnline BOOLEAN DEFAULT FALSE,
  Licensed BOOLEAN,
  ContentRating VARCHAR(255) NOT NULL DEFAULT 'T',
  CONSTRAINT pk_VideoGames_GameId PRIMARY KEY(GameId),
  CONSTRAINT fk_VideoGames_GameId FOREIGN KEY(GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE CASCADE
    
);

CREATE TABLE BoardGames(
	
  CONSTRAINT pk_BoardGames_GameId PRIMARY KEY(GameId),
  CONSTRAINT fk_BoardGames_GameId FOREIGN KEY(GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE CASCADE
    
);

CREATE TABLE Reviews(
  ReviewId INT AUTO_INCREMENT,
  Content LONGTEXT,
  Created TIMESTAMP NOT NULL,
  Rating INT,
  GameId INT,
  UserId INT,
  CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY(ReviewId),
  CONSTRAINT fk_Reviews_UserId FOREIGN KEY(UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Reviews_GameId FOREIGN KEY(GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Orders(
  OrderId INT AUTO_INCREMENT,
  UserId INT,
  GameId INT,
  PlaceOrderData TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ReceiverName VARCHAR(255),
  ShipAddress VARCHAR(255),
  ShippedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PaymentMethod VARCHAR(255),
  CONSTRAINT pk_Orders_OrderId PRIMARY KEY(OrderId),
  CONSTRAINT fk_Orders_UserId FOREIGN KEY(UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Orders_GameId FOREIGN KEY(GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE ShoppingCart(
  ShoppingCartId INT auto_increment,
  AccountId INT,
  Created TIMESTAMP,
  CONSTRAINT pk_ShoppingCart_ShoppingCartId PRIMARY KEY(ShoppingCartId),
  CONSTRAINT fk_ShoppingCart_AccountId FOREIGN KEY(AccountId)
    REFERENCES Accounts(AccountId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CartItem(
  CartItemId INT auto_increment,
  ShoppingCartId INT,
  Quantity INT,
  OrderId INT,
  Price DECIMAL(10,2),
  GameId INT,
  CONSTRAINT pk_CartItem_CartItemId PRIMARY KEY(CartItemId),
  CONSTRAINT fk_CartItem_GameId FOREIGN KEY(GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_CartItem_OrderId FOREIGN KEY(OrderId)
    REFERENCES Orders(OrderId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_CartItem_ShoppingCartId FOREIGN KEY(ShoppingCartId)
    REFERENCES ShoppingCart(ShoppingCartId)
    ON UPDATE CASCADE ON DELETE SET NULL
);
# Enum classes
CREATE TABLE OrderStatus (
    OrderId int,
    NewOrder boolean,
    Hold boolean,
    Shipped boolean,
    Delivered boolean,
    Cancelled boolean,
    CONSTRAINT fk_OrderStatus_OrderId FOREIGN KEY (OrderId)
    REFERENCES Orders(OrderId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ShippingOptions(
    OrderId int,
    Ground boolean,
    Express boolean,
    TwoDays boolean,
    OneDay boolean,

    CONSTRAINT fk_ShippingOptions_OrderId FOREIGN KEY (OrderId)
    REFERENCES Orders(OrderId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Genres(
    GameId int,
    Action boolean,
    Strategy boolean,
    Sports boolean,
    Simulation boolean,
    Racing boolean,
    RolePlaying boolean,
    Adventure boolean,
    Fighting boolean,
    Shooter boolean,
    Casual boolean,
    MusicParty boolean,
    CONSTRAINT fk_Genre_GameId FOREIGN KEY (GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Languages(
    GameId int,
    English boolean,
    Chinese boolean,
    Spanish boolean,
    French boolean,
    Italian boolean,
    German boolean,
    CONSTRAINT fk_Languages_GameId FOREIGN KEY (GameId)
    REFERENCES Games(GameId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


