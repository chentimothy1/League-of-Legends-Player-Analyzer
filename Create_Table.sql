# Annie Pan
# Timothy Chen
# CS 5200 DBMS
# Summer 2020
# PM2 Create Table

CREATE SCHEMA IF NOT EXISTS LOLChampions;
USE LOLChampions;

DROP TABLE IF EXISTS MatchNA1;
DROP TABLE IF EXISTS MatchEUW1;
DROP TABLE IF EXISTS MatchEUN1;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Summoner;
DROP TABLE IF EXISTS Maps;
DROP TABLE IF EXISTS SkillOrder;
DROP TABLE IF EXISTS SummonerSpell;
DROP TABLE IF EXISTS RunePage;
DROP TABLE IF EXISTS Items;
DROP TABLE IF EXISTS LOLChampions;

CREATE TABLE LOLChampions (
	ChampionID INT,
    ChampionName VARCHAR(45) NOT NULL,
    ChampionTitle VARCHAR(50),
    ChampionBlub TEXT,
	ChampionHP Decimal(5,2),
    ChampionAttack INT,
    ChampionDefense INT,
    ChampionMagic INT,
    
    CONSTRAINT pk_Champion_ID PRIMARY KEY (ChampionID)
);

CREATE TABLE Items (
	ItemID	INT,
    ItemName VARCHAR(45),
    ItemDesc TEXT,
    ItemBlurb TEXT,
    ItemPrice INT,
    ItemSell INT,
    
    CONSTRAINT pk_Item_ID PRIMARY KEY (ItemID)
);

CREATE TABLE RunePage (
	RunePageID	INT,
    RunePageName VARCHAR(45),
    RunePath VARCHAR(45),
    RunePageKey VARCHAR(45),
    RunePageDesc TEXT,
    
    CONSTRAINT pk_RunePage_ID PRIMARY KEY (RunePageID)
);

CREATE TABLE SummonerSpell (
	SummonerSpellID	VARCHAR(45),
    SummonerSpellName VARCHAR(45),
    SummonerSpellDesc VARCHAR(300),
    
    CONSTRAINT pk_SummonerSpell_ID PRIMARY KEY (SummonerSpellID)
);

CREATE TABLE Maps (
	MapID	INT,
    MapName VARCHAR(45),
    MapDesc VARCHAR(45),
    
    CONSTRAINT pk_Map_ID PRIMARY KEY (MapID)
);

CREATE TABLE Summoner (
	SummonerID VARCHAR(70),
    SummonerName VARCHAR(255),
    Wins INT,
    Losses INT,
    
    CONSTRAINT pk_Summoner_ID PRIMARY KEY (SummonerID)
);
	
CREATE TABLE Accounts (
	AccountID VARCHAR (70),
    SummonerID VARCHAR(70),
    Region VARCHAR(25),
    
    CONSTRAINT pk_Account_ID PRIMARY KEY (AccountID),
    CONSTRAINT fk_Account_Summoner FOREIGN KEY (SummonerID) REFERENCES Summoner (SummonerID)
		ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE MatchNA1 (
	MatchID VARCHAR(15),
    AccountID VARCHAR(70),
    ChampionID INT,
    PlayedRole ENUM ('Solo','Duo','Duo_Support','Duo_Carry','None'),
    PlayedPosition ENUM ('Top','Mid','Bottom','Jungle','None'),
    
    CONSTRAINT pk_MatchEUW1_ID PRIMARY KEY (MatchID, AccountID),
    CONSTRAINT fk_MatchNA_Account FOREIGN KEY (AccountID) REFERENCES Accounts (AccountID)
		ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_MatchNA_Champion FOREIGN KEY (ChampionID) REFERENCES LOLChampions (ChampionID)
		ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE MatchEUN1 (
	MatchID VARCHAR(15),
    AccountID VARCHAR(70),
    ChampionID INT,
    PlayedRole ENUM ('Solo','Duo','Duo_Support','Duo_Carry','None'),
    PlayedPosition ENUM ('Top','Mid','Bottom','Jungle','None'),
    
    CONSTRAINT pk_MatchEUW1_ID PRIMARY KEY (MatchID, AccountID),
    CONSTRAINT fk_MatchEUN1_Account FOREIGN KEY (AccountID) REFERENCES Accounts (AccountID)
		ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_MatchEUN1_Champion FOREIGN KEY (ChampionID) REFERENCES LOLChampions (ChampionID)
		ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE MatchEUW1 (
	MatchID VARCHAR(15),
    AccountID VARCHAR(70),
    ChampionID INT,
    PlayedRole ENUM ('Solo','Duo','Duo_Support','Duo_Carry','None'),
    PlayedPosition ENUM ('Top','Mid','Bottom','Jungle','None'),
    
    CONSTRAINT pk_MatchEUW1_ID PRIMARY KEY (MatchID, AccountID),
    CONSTRAINT fk_MatchEUW1_Account FOREIGN KEY (AccountID) REFERENCES Accounts (AccountID)
		ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_MatchEUW1_Champion FOREIGN KEY (ChampionID) REFERENCES LOLChampions (ChampionID)
		ON UPDATE CASCADE ON DELETE CASCADE 
);


#Staging tables
DROP TABLE IF EXISTS Summoner_Staging;
CREATE TABLE Summoner_Staging(
    entries__summonerId TEXT,
    entries__summonerName TEXT,
    entries__leaguePoints TEXT,
    entries__rank TEXT,
    entries__wins TEXT,
    entries__losses TEXT,
    entries__veteran TEXT,
    entries__inactive TEXT,
    entries__freshBlood TEXT,
    entries__hotStreak TEXT
);

DROP TABLE IF EXISTS Account_Staging;
CREATE TABLE Account_Staging (
	AccountID TEXT,
    SummonerID TEXT
);

DROP TABLE IF EXISTS Match_Staging;
CREATE TABLE Match_Staging (
	platformId TEXT,
    gameId TEXT,
    champion TEXT,
    queue TEXT,
    season TEXT,
    gametimestamp TEXT,
    gamerole TEXT,
    lane TEXT,
    accountID TEXT
);

DROP TABLE IF EXISTS Champion_Staging;
CREATE TABLE Champion_Staging(
	`data` TEXT,
    `dataversion` TEXT,
    `dataid` TEXT,
    `datakey` TEXT,
    `dataname` TEXT,
    `datatitle` TEXT,
    `datablurb` TEXT
);

DROP TABLE IF EXISTS ChampionStats_Staging;
CREATE TABLE ChampionStats_Staging (
	`Name` TEXT,
    `HP` Decimal(5,2),
    `Attack` int,
    `Defence` int,
    `Magic` int
);

DROP TABLE IF EXISTS Items_Staging;
CREATE TABLE Items_Staging(
	`ItemID` TEXT,
    `Name` TEXT,
    `Description` TEXT,
    `Plain Text` TEXT,
    `Purchase Gold` TEXT,
    `Sell Gold` TEXT
);

DROP TABLE IF EXISTS Spell_Staging;
CREATE TABLE Spell_Staging(
	data1 TEXT,
    dataid TEXT,
    dataname TEXT,
    datadescription TEXT
);

DROP TABLE IF EXISTS Rune_Staging;
CREATE TABLE Rune_Staging(
    runepath TEXT,
    runeID TEXT,
    runeskey TEXT,
    runename TEXT,
    runedesc TEXT

);

