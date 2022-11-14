DROP TABLE IF EXISTS UserInfo;
DROP TABLE IF EXISTS UserPreferenceList;
DROP TABLE IF EXISTS UserDesiresRanking;
DROP TABLE IF EXISTS UserLogin;
DROP TABLE IF EXISTS UserSelfRanking;

CREATE TABLE UserLogin (
FirstName varchar(255), 
LastName varchar(255),
username varchar(255), 
password varchar(255)
);

INSERT INTO UserLogin
VALUES ("Sid", "Bansal", "sid_bansal", "I_love_youg_and_it's_a_problem"),
("Sanjana", "Ilango", "sanjana123", "aditya <3");

CREATE TABLE UserInfo (
FirstName varchar(255), 
LastName varchar(255), 
Age int, 
SexualOrientation char(1), 
Instagram varchar(255), 
UserDescription varchar(255));

INSERT INTO UserInfo
VALUES ("Sid", "Bansal", 19, "S", "@sid_is_awesome", "We love Sid. Man's the whole package. 10/10 would recommend. And don't get me started on his ramen...."),
("Sanjana", "Ilango", 19, "S", "@otters_are_cute", "I would rant about how amazing Sanjana is, but unfortunately our girl is taken. Aditya's a lucky man.");

CREATE TABLE UserPreferenceList(
FirstName varchar(255), 
LastName varchar(255), 
PreferenceList varchar(255));

INSERT INTO UserPreferenceList
VALUES ("Sid", "Bansal", "Youg for some reason that's beyond me. The other preferences will be added after I figure out how to go about this cuz SQL doesn't have a simple array type"),
("Sanjana", "Ilango", "Aditya");

CREATE TABLE UserDesiresRanking (
FirstName varchar(255), 
LastName varchar(255), 
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
VALUES ("Sid", "Bansal", 5, 2, 3, 3, 4, 4, 3, 4, 5, 5),
("Sanjana", "Ilango", 3, 4, 3, 4, 5, 2, 3, 3, 4, 5);


CREATE TABLE UserSelfsRanking (
FirstName varchar(255), 
LastName varchar(255), 
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
VALUES ("Sid", "Bansal", 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
("Sanjana", "Ilango", 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);

SELECT * FROM UserInfo;

SELECT * FROM UserPreferenceList;

SELECT * FROM UserDesiresRanking;

SELECT * FROM UserLogin;

SELECT * FROM UserSelfRanking;