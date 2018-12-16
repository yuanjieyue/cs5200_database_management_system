USE Restaurant;

INSERT INTO Users (UserName,FirstName,LastName,PassWord,Email,PhoneNumber)
  VALUES('David','David','Green', '123456', 'david@gmail.com', '206123456');
INSERT INTO Users (UserName,FirstName,LastName,PassWord,Email,PhoneNumber)
  VALUES('Henry','Henry','Brown', '456789', 'henry@gmail.com', '206456789');

INSERT INTO Companies (CompanyName,Description)
  VALUES('DingTaiFeng','DingTaiFeng is the most popular Chinese food restaurant brand in USA.'); 
INSERT INTO Companies (CompanyName,Description)
  VALUES('HaiDiLao','HaiDiLao is the largest hotpot food suppliers in the world.');

INSERT INTO Restaurants (RestaurantName,Description,Menu,Hours,ActiveOrNot,Street1,Street2,City,State,ZipCode,CuisineTypes,CompanyName)
  VALUES('DingTaiFengSeattle','Welcome to here to have American food in Seattle','1.Burger 2.Sandwich','10:00:00 - 17:30:00',TRUE,'Dexter Ave 245','No.12','Seattle','WA',98105,'American','DingTaiFeng');
INSERT INTO Restaurants (RestaurantName,Description,Menu,Hours,ActiveOrNot,Street1,Street2,City,State,ZipCode,CuisineTypes,CompanyName)
  VALUES('DingTaiFengPortland','Welcome to here to have African food in Portland','1.Rice 2.Wheat','11:00:00 - 18:30:00',TRUE,'Harrison Ave 36 N','No.295','Portland','OR',37504,'African','DingTaiFeng');
INSERT INTO Restaurants (RestaurantName,Description,Menu,Hours,ActiveOrNot,Street1,Street2,City,State,ZipCode,CuisineTypes,CompanyName)
  VALUES('HaiDiLaoSeattle','Welcome to here to have Asian food in Seattle','1.Hotpot 2.Buffee','09:00:00 - 16:30:00',TRUE,'Dexter Ave 435','No.26','Seattle','WA',98105,'Asian','HaiDiLao');
INSERT INTO Restaurants (RestaurantName,Description,Menu,Hours,ActiveOrNot,Street1,Street2,City,State,ZipCode,CuisineTypes,CompanyName)
  VALUES('HaiDiLaoPortland','Welcome to here to have European food in Portland','1.ItalianNoodles 2.FrenchRoastedBeef','09:15:00 - 18:30:00',TRUE,'Harrison Ave 386','No.30','Portland','OR',37502,'European','HaiDiLao');
INSERT INTO Restaurants (RestaurantName,Description,Menu,Hours,ActiveOrNot,Street1,Street2,City,State,ZipCode,CuisineTypes,CompanyName)
  VALUES('HaiDiLaoSF','Welcome to here to have Hispanic food in SF','1.MecxicanChips 2.BrasilFries','08:00:00 - 15:30:00',TRUE,'Aloha Street NE','No.17','San Francisco','CA',25026,'Hispanic','HaiDiLao');
    
  
INSERT INTO Recommendations (UserName,RestaurantId)
  VALUES('David', 2);
INSERT INTO Recommendations (UserName,RestaurantId)
  VALUES('henry', 3);
  
INSERT INTO Reviews (WrittenReviews,Rating,UserName,RestaurantId)
  VALUES('This place is wonderful, highly recommended',4.50,'David',1);
INSERT INTO Reviews (WrittenReviews,Rating,UserName,RestaurantId)
  VALUES('This place is so good, the food here is delicious',4.0,'Henry',5);

INSERT INTO FoodCartRestaurants (RestaurantId,LicensedOrNot)
  VALUES(1,TRUE);
INSERT INTO FoodCartRestaurants (RestaurantId,LicensedOrNot)
  VALUES(3,TRUE);
  
INSERT INTO TakeOutRestaurants (RestaurantId,MaxWaitTime)
  VALUES(4,30);
INSERT INTO SitDownRestaurants (RestaurantId,Capacity)
  VALUES(2,50); 
INSERT INTO SitDownRestaurants (RestaurantId,Capacity)
  VALUES(5,40);

INSERT INTO Reservations (StartTime,EndTime,PartySize,UserName,RestaurantId)
  VALUES('2018-10-01 10:30:00','2018-10-01 13:45:00',30,'David',2);
INSERT INTO Reservations (StartTime,EndTime,PartySize,UserName,RestaurantId)
  VALUES('2018-10-11 11:30:00','2018-10-11 15:50:00',35,'Henry',5);   
  
LOAD DATA INFILE 'E:/ProgramData/MySQL/MySQL Server 5.7/Data/creditcards.csv' INTO TABLE CreditCards
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  