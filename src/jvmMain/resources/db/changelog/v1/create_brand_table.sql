-- liquibase formatted sql
-- changeset admin:create_brand_table.sql dbms:h2

create table BRAND
(
    ID                    NUMBER(10) PRIMARY KEY AUTO_INCREMENT,
    NAME                  VARCHAR2(250) NOT NULL,
    LOGO                  BLOB,
    COUNTRY               VARCHAR2(2) NOT NULL
);
