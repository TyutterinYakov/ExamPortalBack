DELETE FROM results;

ALTER TABLE results ADD COLUMN exam_answer_by_question_id jsonb;
ALTER TABLE results DROP COLUMN given_answer_by_question_id;

ALTER TABLE results ALTER COLUMN exam_answer_by_question_id SET NOT NULL;
ALTER TABLE results ALTER COLUMN valid_question SET NOT NULL;
ALTER TABLE results ALTER COLUMN invalid_question SET NOT NULL;
ALTER TABLE results ALTER COLUMN skip_question SET NOT NULL;
ALTER TABLE results ALTER COLUMN count_points SET NOT NULL;
ALTER TABLE results ALTER COLUMN max_marks SET NOT NULL;
