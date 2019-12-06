-- liquibase formatted sql
-- changeset author:girum runOnChange:true



ALTER TABLE department MODIFY COLUMN DepartmentId INT auto_increment;

ALTER TABLE department MODIFY COLUMN CreatedDate TIMESTAMP    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp;