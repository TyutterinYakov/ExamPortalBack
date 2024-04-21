CREATE TABLE IF NOT EXISTS quizzes (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    title varchar(30) NOT NULL UNIQUE,
    description varchar(1000) NOT NULL,
    active boolean NOT NULL,
    category_id uuid NOT NULL,
    CONSTRAINT categories_quizzes_fk
    FOREIGN KEY (category_id) REFERENCES categories (id)
);