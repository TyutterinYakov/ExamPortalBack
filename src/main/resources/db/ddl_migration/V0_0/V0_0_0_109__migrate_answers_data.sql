-- Migrate existing answers data from questions table to answers table
INSERT INTO answers (id, reply, correctly, question_id)
SELECT
    gen_random_uuid(),
    jsonb_array_elements(answers)->>'reply',
    (jsonb_array_elements(answers)->>'correctly')::boolean,
    id
FROM questions
WHERE jsonb_typeof(answers) = 'array';