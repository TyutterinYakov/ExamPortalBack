package ru.pet.portal.api.service;

import ru.pet.portal.api.controller.dto.QuizDto;
import ru.pet.portal.api.controller.dto.QuizRequestDto;

import java.util.List;

public interface QuizService {

    void add(QuizRequestDto quizDto);

    void deleteById(long quizId);
    
    List<QuizDto> getAllByCategory(long categoryId);

    QuizDto getById(Long quizeId);

    void update(QuizRequestDto quizDto);

    List<QuizDto> getAll();
}
