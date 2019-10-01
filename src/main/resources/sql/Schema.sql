
CREATE SCHEMA IF NOT EXISTS vegadatabase;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS listeners;
DROP TABLE IF EXISTS speakers;

CREATE TABLE users
( userLogin varchar(100) NOT NULL,

  userEmail varchar(50) UNIQUE NOT NULL,

  userPassword varchar(30) NOT NULL,

  PRIMARY KEY (userLogin)
);
CREATE TABLE speakers
( speakerId serial  PRIMARY  KEY,
  speakerName VARCHAR (50) NOT NULL,
  speakerSurname VARCHAR (60)  NULL,
  speakerNativeLanguage VARCHAR (100)  NOT NULL,
  speakerAbout VARCHAR (300) NULL,
  userLogin varchar(100) NOT NULL,
  CONSTRAINT userLogin_speakerId_fkey FOREIGN KEY (userLogin)
      REFERENCES users (userLogin) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE listeners
( listenerId serial  PRIMARY  KEY,
  userLogin varchar(100) NOT NULL,
  CONSTRAINT userLogin_listenerId_fkey FOREIGN KEY (userLogin)
      REFERENCES users (userLogin) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);