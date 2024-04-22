CREATE TABLE IF NOT EXISTS results
(
    id                          uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    given_answer_by_question_id jsonb                                      NOT NULL,
    quiz_id                     uuid                                       NOT NULL,
    user_id                     uuid                                       NOT NULL,
    CONSTRAINT quizzes_results_fk
        FOREIGN KEY (quiz_id) REFERENCES quizzes (id),
    CONSTRAINT users_results_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
);