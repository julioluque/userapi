# USERAPI

Bienvenido al projecto USERAPI de Nisum! 
Este readme provee informacion para buildear y ejecutar el proyecto.

### VERSION:
    release/0.0.5


## Ambiente

### Pre Requisitos

Se requiere tener la siguietne configuracion

- Java JDK 17
- Maven 3.8.2

### Tecnolgias

La aplicacion hace uso de las siguientes tecnolgias
- Java 17
- Maven 3.8.2
- Spring 3.2.0
- H2
- Swagger
- Spring Security latest
- JWT TOken
- UUID and Serializacion
- Docker


### Building and Running

| Accion   | Comando               | Descripción                                                            |
|----------|-----------------------|------------------------------------------------------------------------|
| compilar | `mvn clean package`   | **Armar proyecto**, Limpia y construye el projecto                     |
| ejecutar | `mvn spring-boot:run` | **Ejecuta el proyecto**, Ejecucion del proyecto springboot por consola |
| test     | `mvn test `           | **Ejecucion de los test** Ejecuta los test unitarios de forma local    |
 

## DATABASE

### H2 Configuration

    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=sa

### Scripting

```bash
DROP TABLE IF EXISTS USER_INFO;
DROP TABLE IF EXISTS USER_PHONE;
DROP TABLE IF EXISTS USUARIO;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS USUARIOS_ROLES;
```

```bash
CREATE TABLE  USER_INFO (
USR_ID BINARY(16) PRIMARY KEY,
USR_NAME VARCHAR(255) NOT NULL,
USR_EMAIL VARCHAR(255) NOT NULL UNIQUE,
USR_PASSWORD VARCHAR(255) NOT NULL,
USR_CREATED TIMESTAMP,
USR_MODIFIED TIMESTAMP,
USR_LAST_LOGIN TIMESTAMP,
USR_TOKEN VARCHAR(255),
USR_ACTIVE BOOLEAN,
USR_STATUS VARCHAR(255)
);
```

```bash
CREATE TABLE  USER_INFO (
USR_ID BINARY(16) PRIMARY KEY,
USR_NAME VARCHAR(255) NOT NULL,
USR_EMAIL VARCHAR(255) NOT NULL UNIQUE,
USR_PASSWORD VARCHAR(255) NOT NULL,
USR_CREATED TIMESTAMP,
USR_MODIFIED TIMESTAMP,
USR_LAST_LOGIN TIMESTAMP,
USR_TOKEN VARCHAR(255),
USR_ACTIVE BOOLEAN,
USR_STATUS VARCHAR(255)
);
```

```bash
CREATE TABLE USER_PHONE (
PHO_ID BINARY(16) PRIMARY KEY,
PHO_NUMBER VARCHAR(255) NOT NULL UNIQUE,
PHO_CITY VARCHAR(255) NOT NULL,
PHO_COUNTRY VARCHAR(255) NOT NULL,
USR_ID BINARY(16),
FOREIGN KEY (USR_ID) REFERENCES USER_INFO(USR_ID)
);
```

```bash
CREATE TABLE  ROLE (
ID_ROLE BIGINT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(255) NOT NULL
);
```

```bash
CREATE TABLE  USUARIO (
ID_USUARIO BIGINT PRIMARY KEY AUTO_INCREMENT,
USERNAME VARCHAR(255) NOT NULL,
PASSWORD VARCHAR(255) NOT NULL,
CONSTRAINT UK_USERNAME UNIQUE (USERNAME)
);
```

```bash
CREATE TABLE  USUARIOS_ROLES (
USUARIO_ID BIGINT,
ROLE_ID BIGINT,
PRIMARY KEY (USUARIO_ID, ROLE_ID),
FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID_USUARIO),
FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID_ROLE)
);
```


### TEST

* UnitTest 95%
* IntegrationTest 91%

