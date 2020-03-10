-- liquibase formatted sql
-- changeset admin:insert_category.sql dbms:h2

INSERT INTO PRODUCT_CATEGORY VALUES (default, 'MOBILE PHONE', 'MOBILE', null, true);
commit;