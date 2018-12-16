# Before running, create the schema named Restaurant.
# Or just create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS Restaurant;
USE Restaurant;

# Drop tables if they are existing.
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS FoodCartRestaurants;
DROP TABLE IF EXISTS TakeOutRestaurants;
DROP TABLE IF EXISTS Reservations;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS SitDownRestaurants;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Companies;


CREATE TABLE Users (
  UserName VARCHAR(255) UNIQUE NOT NULL,
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  PassWord VARCHAR(255),
  Email VARCHAR(255),
  PhoneNumber VARCHAR(255),
  CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);

CREATE TABLE CreditCards (
  CardNumber BIGINT NOT NULL,
  ExpirationDate DATE,
  UserName VARCHAR(255),
  CONSTRAINT pk_CreditCards_CardNumber PRIMARY KEY (CardNumber),
  CONSTRAINT fk_CreditCards_UserName
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Companies (
  CompanyName VARCHAR(255) UNIQUE NOT NULL,
  Description LONGTEXT,
  CONSTRAINT pk_Companies_CompanyName PRIMARY KEY (CompanyName)
);

CREATE TABLE Restaurants (
  RestaurantId INT AUTO_INCREMENT,
  RestaurantName VARCHAR(255),
  Description LONGTEXT,
  Menu LONGTEXT,
  Hours VARCHAR(255),
  ActiveOrNot BOOLEAN DEFAULT FALSE,
  Street1 VARCHAR(255),
  Street2 VARCHAR(255),
  City VARCHAR(50),
  State VARCHAR(20),
  ZipCode INT,
  CuisineTypes ENUM('African', 'American', 'Asian', 'European', 'Hispanic'),
  CompanyName VARCHAR(255),
  CONSTRAINT pk_Restaurants_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_Restaurants_CompanyName 
    FOREIGN KEY (CompanyName)
	REFERENCES Companies(CompanyName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Recommendations (
  RecommendationId INT AUTO_INCREMENT,
  UserName VARCHAR(255),
  RestaurantId INT,
  CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
  CONSTRAINT fk_Recommendations_UserName 
    FOREIGN KEY (UserName)
    REFERENCES Users (UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Recommendations_RestaurantId
    FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants (RestaurantId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Reviews (
  ReviewId INT AUTO_INCREMENT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  WrittenReviews LONGTEXT,
  Rating DECIMAL,
  UserName VARCHAR(255),
  RestaurantId INT,
  CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT uq_Reviews_ReviewId
    UNIQUE (UserName,RestaurantId),
  CONSTRAINT fk_Reviews_UserName 
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Reviews_RestaurantId
    FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants (RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE FoodCartRestaurants (
  RestaurantId INT,
  LicensedOrNot BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_FoodCartRestaurants_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_FoodCartRestaurants_RestaurantId
    FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants (RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE TakeOutRestaurants (
  RestaurantId INT,
  MaxWaitTime INT,
  CONSTRAINT pk_TakeOutRestaurants_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_TakeOutRestaurants_RestaurantId
    FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants (RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE SitDownRestaurants (
  RestaurantId INT,
  Capacity INT,
  CONSTRAINT pk_SitDownRestaurants_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_SitDownRestaurants_RestaurantId
    FOREIGN KEY (RestaurantId)
    REFERENCES Restaurants (RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reservations (
  ReservationId INT AUTO_INCREMENT,
  StartTime TIMESTAMP,
  EndTime TIMESTAMP,
  PartySize INT,
  UserName VARCHAR(255),
  RestaurantId INT,
  CONSTRAINT pk_Reservations_ReservationId PRIMARY KEY (ReservationId),
  CONSTRAINT fk_Reservations_UserName 
    FOREIGN KEY (UserName)
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Reservations_RestaurantId
    FOREIGN KEY (RestaurantId)
    REFERENCES SitDownRestaurants (RestaurantId)
    ON UPDATE CASCADE ON DELETE CASCADE
);
