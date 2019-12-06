-- liquibase formatted sql
-- changeset author:girum runOnChange:true



ALTER TABLE user MODIFY COLUMN UserId INT auto_increment;

ALTER TABLE user MODIFY COLUMN CreatedDate TIMESTAMP    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp;