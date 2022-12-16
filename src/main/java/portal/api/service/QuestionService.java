package portal.api.service;

import portal.api.dto.QuestionRequestDto;

public interface QuestionService {

    void create(QuestionRequestDto question);
}
