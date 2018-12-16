USE ReviewApplication;
#1. What is the average review rating for each of the top 10 ranked restaurants? Include the restaurant name in the result set.


SELECT Restaurants.restaurantid, Restaurants.name, AVG(rating) AS NUM
FROM Reviews INNER JOIN Restaurants
	ON Reviews.RESTAURANTID = Restaurants.RestaurantId
GROUP BY Restaurants.RestaurantId, Restaurants.Name
ORDER BY AVG(rating) DESC, Restaurants.RestaurantId ASC, Restaurants.Name ASC
LIMIT 10;

#2. How many users have created more than one review?
SELECT COUNT(*) AS MORE_THAN_ONE_REVIEW
FROM (
	SELECT Reviews.username, COUNT(*) AS NUM
	FROM Reviews
	GROUP BY Reviews.username
    HAVING COUNT(*) > 1
	ORDER BY COUNT(*) DESC) AS NAME_TO_NUM;
    
    
#3. What day of the week is most popular for making a reservation? Use the DAYOFWEEK() function on the ‘Start’ column.
SELECT (DAYOFWEEK(Reservations.start)) AS MOST_POPULAR_DAY_OF_WEEK, COUNT(*) AS RESERVE_NUM
FROM Reservations
GROUP BY DAYOFWEEK(Reservations.start)
ORDER BY COUNT(*) DESC
LIMIT 1;

#4. Which UserNames have made multiple reservations at the same SitDownRestaurant?
SELECT username, restaurantid, COUNT(*) AS RESERVE_NUM
FROM Reservations
GROUP BY username, restaurantid
ORDER BY COUNT(*) DESC, username, restaurantid;


#5.Identify the number of credit cards per provider. The credit card provider is determined by the leading digit(s) of the CardNumber. 
# Use this mapping for card providers:
#Provider
#CardNumber Starts With
#American Express
#34, 37
#Discover
#6011, 644, 645, 646, 647, 648, 649, 65
#MasterCard
#51, 52, 53, 54, 55
#Visa
#4
#NA
#(Everything else)
SELECT cardprovider, COUNT(*) AS PROVIDER_NUM
FROM(
	 SELECT cardnumber,
     CASE 
		WHEN
		  LEFT(CAST(Cardnumber AS CHAR),2) IN ('34','37') 
		THEN 'American Express'
        WHEN 
		  LEFT(CAST(Cardnumber AS CHAR),4) IN ('6011') OR
          LEFT(CAST(Cardnumber AS CHAR),3) IN ('644', '645', '646', '647', '648','649') OR
          LEFT(CAST(Cardnumber AS CHAR),2) IN ('65')
		THEN 'Discover'
        WHEN
		  LEFT(CAST(Cardnumber AS CHAR),2) IN ('51', '52','53','54','55')
		THEN 'Master Card'
        WHEN
          LEFT(CAST(Cardnumber AS CHAR),1) IN ('4')
		THEN 'Visa'
        ELSE 'NA'
	END AS CardProvider
    FROM Creditcards) AS CARD_NUMBER_TO_PROVIDER
GROUP BY cardprovider
ORDER BY COUNT(*) DESC, CardProvider ASC;

#6. What is the total number of active restaurants for each type of restaurant (SitDownRestaurant, TakeOutRestaurant, FoodCartRestaurant)?
SELECT 'SitDown' AS RESTAURANT_TYPE, COUNT(*) AS RESTAURANT_NUM
FROM Restaurants 
	INNER JOIN Sitdownrestaurant
    ON Restaurants.restaurantid = Sitdownrestaurant.restaurantid
WHERE Restaurants.active = true
UNION
SELECT 'TakeOut' AS RESTAURANT_TYPE, COUNT(*) AS RESTAURANT_NUM
FROM Restaurants
	INNER JOIN Takeoutrestaurant
    ON Restaurants.restaurantId = Takeoutrestaurant.restaurantId
WHERE Restaurants.active = true
UNION
SELECT 'FoodCart' AS RESTAURANT_TYPE, COUNT(*) AS RESTAURANT_NUM
FROM Restaurants
	INNER JOIN Foodcartrestaurant
    ON Restaurants.restaurantId = Foodcartrestaurant.restaurantId
WHERE Restaurants.active = true
ORDER BY RESTAURANT_NUM DESC;

#7. Which UserNames have not created a review, nor created a recommendation, nor created a reservation? 
# In other words, users that have not created any of the following: reviews, recommendations, reservations.

SELECT Users.username
FROM Users
	LEFT OUTER JOIN Reviews
    ON Users.username = Reviews.username
    LEFT OUTER JOIN Recommendations
    ON Users.username = Recommendations.username
    LEFT OUTER JOIN Reservations
    ON Users.username = Reservations.username
WHERE reviewId is null AND recommendationId is null AND reservationId is null;

#8. What is the ratio of the total number of recommendations to the total number of reviews?  
SELECT (Recommendation.RECOMMENDATION_CNT / Review.REVIEW_CNT) AS RATIO
FROM( 
	(SELECT COUNT(*) AS RECOMMENDATION_CNT
    FROM Recommendations) AS Recommendation
    CROSS JOIN
    (SELECT COUNT(*) AS REVIEW_CNT
	FROM Reviews) AS Review);
   
   
   
#9. Of the users that have created a reservation at a SitDownRestaurant, 
# what is the percentage that the user has recommended that SitDownRestaurant?    
SELECT 100.0 * SUM(IF(RECOMMENDATION_RESTAURANT is null, 0, 1))
		/ COUNT(*) AS PERCENTAGE
FROM (
	SELECT username AS RESERVATION_USER, restaurantId AS RESERVATION_RESTAURANT
	FROM Reservations
	GROUP BY Reservations.username, Reservations.restaurantId) AS RESERVATION_USER_TO_RESTAURANT
	LEFT OUTER JOIN (
		SELECT Recommendations.username AS RECOMMENDATION_USER, Recommendations.restaurantId AS RECOMMENDATION_RESTAURANT
		FROM Recommendations
			INNER JOIN SitDownRestaurant
		ON Recommendations.restaurantId = SitDownRestaurant.restaurantId) AS RECOMMENDATION_USER_TO_RESTAURANT
	ON RESERVATION_USER_TO_RESTAURANT.RESERVATION_USER = RECOMMENDATION_USER_TO_RESTAURANT.RECOMMENDATION_USER
	AND RESERVATION_USER_TO_RESTAURANT.RESERVATION_RESTAURANT = RECOMMENDATION_USER_TO_RESTAURANT.RECOMMENDATION_RESTAURANT;


#10. Which UserNames have created more than twice the number of recommendations than number of reviews? \
# Also take into account users that have not created recommendations or reviews 
-- if a user has create one recommendation but zero reviews, then that user should be included in the result set.

SELECT username AS TWICE_RECOMMENDATION_THAN_REVIEW_USER
FROM (
	SELECT username, 
		   IF(RECOMMENDATION_NUM is null, 0, RECOMMENDATION_NUM) AS RECOMMENDATION_NUM,
		   IF(REVIEW_NUM is null, 0, REVIEW_NUM) AS REVIEW_NUM
	FROM Users
		LEFT OUTER JOIN (
			SELECT username AS RECOMMENDATION_USER, COUNT(*) AS RECOMMENDATION_NUM
			FROM Recommendations
			GROUP BY username) AS RECOMMENDATIONS
		ON Users.username = RECOMMENDATIONS.RECOMMENDATION_USER
		LEFT OUTER JOIN (
			SELECT username AS REVIEW_USER, COUNT(*) AS REVIEW_NUM
			FROM Reviews
			GROUP BY username) AS REVIEWS
		ON Users.username = REVIEWS.REVIEW_USER) AS USER_RECOMMENDATION_NUM_AND_REVIEW_NUM
WHERE RECOMMENDATION_NUM > 2 * REVIEW_NUM
ORDER BY RECOMMENDATION_NUM > 2 * REVIEW_NUM DESC, username ASC;


