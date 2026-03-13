CREATE TABLE IF NOT EXISTS answers
(
    id          uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    reply       varchar(255)                             NOT NULL,
    correctly   boolean                                  NOT NULL,
    question_id uuid                                       NOT NULL,
    CONSTRAINT questions_answers_fk
        FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE
);