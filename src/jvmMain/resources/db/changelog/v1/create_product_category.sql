-- liquibase formatted sql
-- changeset admin:create_product_category_table.sql dbms:h2

create table PRODUCT_CATEGORY
(
    ID                    NUMBER(10) PRIMARY KEY AUTO_INCREMENT,
    NAME                  VARCHAR2(250) NOT NULL,
    CODE                  VARCHAR2(50),
    PARENT_ID             NUMBER(10),
    IS_ENABLED            BOOL NOT NULL
);
