DROP TABLE IF EXISTS User;
CREATE TABLE User (
  id IDENTITY,
  firstname VARCHAR(20) NOT NULL ,
  lastname VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL
);