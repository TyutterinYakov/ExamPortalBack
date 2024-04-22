DELETE FROM results;

ALTER TABLE results DROP COLUMN exam_answer_by_question_id;

ALTER TABLE results ADD COLUMN exam_answers jsonb;
ALTER TABLE results ALTER COLUMN exam_answers SET NOT NULL;
