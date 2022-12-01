# DROP TABLE IF EXISTS UserInfo;
# DROP TABLE IF EXISTS UserPreferenceList;
# DROP TABLE IF EXISTS UserDesiresRanking;
# DROP TABLE IF EXISTS UserLogin;
# DROP TABLE IF EXISTS UserSelfRanking;
# Thanks to Anisha!!!

# Might need to delete these if not needed? -Sid
# creates a schema and uses the so that all below will work on a specific database
DROP DATABASE IF EXISTS UserDatabase;
CREATE DATABASE UserDatabase;
USE UserDatabase;
# 


CREATE TABLE UserLogin (
FirstName varchar(255), 
LastName varchar(255),
username varchar(255), 
password varchar(255)
);

INSERT INTO UserLogin
VALUES ("Sid", "Bansal", "sid@gmail.com", "yougy"),
("Sanjana", "Ilango", "sanjana@bing.com", "sanjay");

CREATE TABLE UserInfo (
username varchar(255),
FirstName varchar(255), 
LastName varchar(255), 
Age int, 
SexualOrientation char(1),
Gender char(1), 
Instagram varchar(255), 
UserDescription varchar(255));

INSERT INTO UserInfo
VALUES ("sid@gmail.com", "Sid", "Bansal", 19, "F", "M", "@sid_is_awesome", "We love Sid. Man's the whole package. 10/10 would recommend. And don't get me started on his ramen...."),
("sanjana@bing.com", "Sanjana", "Ilango", 19, "M", "F", "@otters_are_cute", "I would rant about how amazing Sanjana is, but unfortunately our girl is taken. Aditya's a lucky man.");

#####
# Below should also be changed to support username instead of first and last name
########


CREATE TABLE UserPreferenceList(
username varchar(255), 
PreferenceList varchar(255));

INSERT INTO UserPreferenceList
VALUES ("sid@gmail.com", "sanjana@bing.com,10"),
("sanjana@bing.com", "sid@gmail.com,10");


#####
# CHANGE TO THE BELOW
######

CREATE TABLE UserDesiresRanking (
username varchar(255), 
Extroverted int,
Humor int, 
Adventurous int, 
Ambitious int, 
Artistic int, 
WordsOfAffirmation int, 
PhysicalTouch int, 
ReceivingGifts int, 
QualityTime int, 
ActsOfService int);

INSERT INTO UserDesiresRanking
VALUES ("sid@gmail.com", 5, 2, 3, 3, 4, 4, 3, 4, 5, 5),
("sanjana@bing.com", 3, 4, 3, 4, 5, 2, 3, 3, 4, 5);

CREATE TABLE Matches(
username varchar(255),
matchname varchar(255)
);

INSERT INTO Matches
VALUES ("sid@gmail.com", "sanjana@bing.com"),
("sanjana@bing.com", "sid@gmail.com");

CREATE TABLE UserSelfRanking (
username varchar(255), 
Extroverted int,
Humor int, 
Adventurous int, 
Ambitious int, 
Artistic int, 
WordsOfAffirmation int, 
PhysicalTouch int, 
ReceivingGifts int, 
QualityTime int, 
ActsOfService int);

INSERT INTO UserSelfRanking
VALUES ("sid@gmail.com", 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
("sanjana@bing.com", 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);



CREATE TABLE ImageMap
(
username VARCHAR(255),
path VARCHAR(255)
);


# INSERT INTO ImageMap VALU




#SELECT * FROM UserInfo;

#SELECT * FROM UserPreferenceList;

#SELECT * FROM UserDesiresRanking;

#SELECT * FROM UserLogin;

#SELECT * FROM UserSelfRanking;
