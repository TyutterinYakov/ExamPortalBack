CREATE TABLE IF NOT EXISTS quizzes_positions (
    quiz_id UUID NOT NULL,
    position_id UUID NOT NULL,
    PRIMARY KEY (quiz_id, position_id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE,
    FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE CASCADE
);