package portal.api.service;

import portal.api.dto.QuizRequestDto;

public interface QuizeService {
//	List<QuizeDto> getAnyQuiziesByCategoryId(Long categoryId);
//	List<QuizeDto> getAnyQuizies();
//    QuizeDto getQuizeAny(Long id);
//	QuizeDto updateQuize(QuizeDto quize);
//	void deleteQuizeById(Long quizeId);
//
//	List<QuizeDto> getActiveQuizies();
//	QuizeDto getActiveQuize(Long quizeId);
//	List<QuizeDto> getActiveQuiziesByCategoryId(Long categoryId);

    void add(QuizRequestDto quizDto);

    void deleteById(long quizId);
}
