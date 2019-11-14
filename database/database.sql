

-- DROP ROLE IF EXISTS admin;
-- CREATE ROLE admin;
-- GRANT ALL PRIVILEGES ON *.* TO admin WITH GRANT OPTION;

-- DROP ROLE IF EXISTS grpLeader;
-- CREATE ROLE grpLeader;
-- -- GRANT EXECUTE ON PROCEDURE  TO grpLeader;

-- DROP ROLE IF EXISTS groupMembers;
-- CREATE ROLE groupMembers;
-- -- GRANT EXECUTE ON PROCEDURE  TO groupMembers;

-- Drop Table Groupsinfo;
-- CREATE TABLE IF NOT EXISTS Admins(
-- 	LOGIN VARCHAR(15) NOT NULL,
--     email VARCHAR(40) NOT NULL,
-- 	access_token ENUM( 'admin') NOT NULL,
-- 	PRIMARY KEY (LOGIN)
-- 	);
--     Drop Table groupLeaders;
-- CREATE TABLE IF NOT EXISTS groupLeaders(
-- 	LOGIN VARCHAR(15) NOT NULL,
--     group_id INT NOT NULL,
--     email VARCHAR(100) NOT NULL,
-- 	access_token ENUM( 'grpLeader') NOT NULL,
-- 	PRIMARY KEY (LOGIN)
-- 	);
-- CREATE TABLE IF NOT EXISTS groupMembers(
-- 	LOGIN VARCHAR(15) NOT NULL,
--     email VARCHAR(100) NOT NULL,
-- 	access_token ENUM( 'groupMembers') NOT NULL,
-- 	PRIMARY KEY (LOGIN)
--     );
-- CREATE TABLE IF NOT EXISTS Groupsinfo(
-- 	id  int auto_increment,
--     leader VARCHAR(15) NOT NULL,
--     info VARCHAR(100) NOT NULL,
-- 	PRIMARY KEY (id),
--     foreign key(leader)
--     References groupLeaders(LOGIN)
--     );
-- CREATE TABLE IF NOT EXISTS Scans(
-- 	id INT NOT NULL ,
-- 	DATA JSON DEFAULT NULL);
--     
Create DATABASE IF NOT EXISTS  db3s;
USE db3s;
Drop TABLE users;
-- CREATE TABLE users (
--   id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
--   LOGIN VARCHAR(20) NOT NULL UNIQUE,
--   psswrd VARCHAR(20) NOT NULL,
--   email VARCHAR(30) NOT NULL
-- );