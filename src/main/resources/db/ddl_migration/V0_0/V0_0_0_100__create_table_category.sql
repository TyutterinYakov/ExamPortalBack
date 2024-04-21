CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS categories
(
    id          uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    title       varchar(75) UNIQUE,
    description varchar(1000)
);