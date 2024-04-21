CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    email varchar(30) NOT NULL UNIQUE,
    password varchar(150) NOT NULL,
    first_name varchar(30) NOT NULL,
    last_name varchar(30) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    profile_image varchar(100) DEFAULT 'default.png' NOT NULL,
    role varchar(10) DEFAULT 'user' NOT NULL
);