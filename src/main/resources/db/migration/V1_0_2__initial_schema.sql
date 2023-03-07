
CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;
DROP TABLE IF EXISTS  transaction;
create TABLE transaction (
    "id" VARCHAR(255) NOT NULL,
    "created" TIMESTAMP NOT NULL,
    "updated" TIMESTAMP NOT NULL,
    "version" INT8 DEFAULT 0,
    "amount" VARCHAR(255) NOT NULL,
    "transaction_type" VARCHAR(255)  NULL,
    "source_account" VARCHAR(255)  NULL,
    "destination_account" VARCHAR(255)  NULL,
    "otp" VARCHAR(255)  NULL,
    "otp_status" VARCHAR(255)  NULL,
    "status" VARCHAR(255)  NULL,
    "meta_data" VARCHAR(255)  NULL,
    primary key (id)
);