-- liquibase formatted sql
-- changeset admin:create_product_table.sql dbms:h2

create table PRODUCT
(
    ID                    NUMBER(10) PRIMARY KEY AUTO_INCREMENT,
    NAME                  VARCHAR2(250) NOT NULL,
    CODE                  VARCHAR2(250) NOT NULL,
    IMAGE                 BLOB,
    PRICE                 NUMBER(10, 2) NOT NULL,
    BRAND_ID              NUMBER(10, 2),
    PRODUCT_CATEGORY_ID   NUMBER(10),
    DESCRIPTION           VARCHAR2(10000) NOT NULL,

    FOREIGN KEY (PRODUCT_CATEGORY_ID) REFERENCES PRODUCT_CATEGORY(ID),
    FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID)
);

CREATE UNIQUE INDEX PRODUCT_UNIQ_CODE ON PRODUCT(CODE);

