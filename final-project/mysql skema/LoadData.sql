USE Gameholic;
-- show variables like 'secure_file_priv';
set foreign_key_checks = 0;
LOAD DATA INFILE 'F:/CS 5200/Project/csv tables/Videogames.csv' INTO TABLE VideoGames
  FIELDS TERMINATED BY ';' 
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

select * from VideoGames;

LOAD DATA INFILE 'F:/CS 5200/Project/csv tables/Boardgames.csv' INTO TABLE BoardGames
  FIELDS TERMINATED BY ';' 
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

select * from BoardGames;


LOAD DATA INFILE 'F:/CS 5200/Project/csv tables/Genres.csv' INTO TABLE Genres
  FIELDS TERMINATED BY ';' 
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
select * from Genres;

LOAD DATA INFILE 'F:/CS 5200/Project/csv tables/Reviews.csv' INTO TABLE Reviews
  FIELDS TERMINATED BY ';' 
  OPTIONALLY enclosed by '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

select * from Reviews;

LOAD DATA INFILE 'F:/CS 5200/Project/csv tables/Languages.csv' INTO TABLE Languages
  FIELDS TERMINATED BY ';' 
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
select * from Languages;