DROP TABLE IF EXISTS USER_INFO;
DROP TABLE IF EXISTS USER_PHONE;

CREATE TABLE USER_INFO (
    USR_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    USR_NAME VARCHAR(255) NOT NULL,
    USR_EMAIL VARCHAR(255) NOT NULL UNIQUE,
    USR_PASSWORD VARCHAR(255) NOT NULL
);

CREATE TABLE USER_PHONE (
    PHO_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    PHO_NUMBER VARCHAR(255) NOT NULL UNIQUE,
    PHO_CITY VARCHAR(255) NOT NULL,
    PHO_COUNTRY VARCHAR(255) NOT NULL,
    PHO_USR_ID BIGINT,
    FOREIGN KEY (PHO_USR_ID) REFERENCES USER_INFO(USR_ID)
);

INSERT INTO USER_INFO (USR_NAME, USR_EMAIL, USR_PASSWORD) VALUES
('julio luque', 'julio.luque@example.com', '1234abcd'),
('juan perez', 'juan.perez@example.com', '0000aaaa');

INSERT INTO USER_PHONE (PHO_NUMBER, PHO_CITY, PHO_COUNTRY, PHO_USR_ID) VALUES
('123456789', 'CityA', 'CountryX', 1),
('987654321', 'CityB', 'CountryY', 1),
('555666777', 'CityC', 'CountryZ', 2);