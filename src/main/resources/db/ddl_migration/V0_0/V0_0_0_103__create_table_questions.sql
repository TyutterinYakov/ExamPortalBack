CREATE TABLE IF NOT EXISTS questions
(
    id      uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    content varchar(4000)                              NOT NULL,
    answers jsonb                                      NOT NULL,
    quiz_id uuid                                       NOT NULL,
    marks   integer                                    NOT NULL,
    time    integer                                    NOT NULL,
    CONSTRAINT quizzes_questions_fk
        FOREIGN KEY (quiz_id) REFERENCES quizzes (id)
);