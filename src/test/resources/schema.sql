CREATE TABLE ROLE (
                    CODE VARCHAR (10) NOT NULL,
                    NAME VARCHAR (50) NOT NULL,
                    DESCRIPTION VARCHAR (255) NULL,
                    PRIMARY KEY (CODE)
);

CREATE TABLE USERS (
                     ID INT NOT NULL,
                     NAME VARCHAR (50) NOT NULL ,
                     ROLE_CODE VARCHAR (10) NOT NULL,
                     PRIMARY KEY (ID),
                     FOREIGN KEY (ROLE_CODE) REFERENCES ROLE(CODE)
);
