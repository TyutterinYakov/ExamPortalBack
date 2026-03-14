ALTER TABLE questions DROP CONSTRAINT quizzes_questions_fk;
ALTER TABLE questions ADD CONSTRAINT quizzes_questions_fk FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE;