-- Create Table T_RESOURCE
CREATE TABLE T_RESOURCE (
  R_ID INT(11) NOT NULL AUTO_INCREMENT,
  R_NAME VARCHAR(20) NOT NULL,
  R_PARENT_ID INT(11) DEFAULT 0 NOT NULL,
  R_IDENTIFIER VARCHAR (50) NOT NULL,
  R_TYPE INT(1) NOT NULL,
  R_URL VARCHAR(200) DEFAULT '' NOT NULL,
  R_LEVEL INT(1) DEFAULT 0 NOT NULL,
  R_DESCRIPTION VARCHAR(100) NULL,
  PRIMARY KEY (R_ID)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;