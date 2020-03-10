-- liquibase formatted sql
-- changeset admin:insert_brands.sql dbms:h2

INSERT INTO BRAND VALUES (default, 'HUAWEI', null, 'CN');
INSERT INTO BRAND VALUES (default, 'XIAOMI', null, 'CN');
INSERT INTO BRAND VALUES (default, 'APPLE', null, 'US');
commit;