package portal.api.service;

import portal.api.dto.QuizDto;
import portal.api.dto.QuizRequestDto;

import java.util.List;

public interface QuizService {

    void add(QuizRequestDto quizDto);

    void deleteById(long quizId);
    
    List<QuizDto> getAllByCategory(long categoryId);

    QuizDto getById(Long quizeId);

    void update(QuizRequestDto quizDto);

    List<QuizDto> getAll();
}
