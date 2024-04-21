//package portal.api.service.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import portal.api.dto.ExamResultDto;
//import portal.api.dto.QuestionDto;
//import portal.api.dto.mapper.ExamResultMapper;
//import portal.api.exception.BadRequestException;
//import portal.api.exception.NotFoundException;
//import portal.api.exception.UserNotFoundException;
//import portal.api.service.ExamResultService;
//import portal.store.entity.ExamResult;
//import portal.store.entity.Question;
//import portal.store.entity.QuizeEntity;
//import portal.store.entity.User;
//import portal.store.repository.ExamResultRepository;
//import portal.store.repository.QuestionRepository;
//import portal.store.repository.QuizeRepository;
//import portal.store.repository.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class ExamResultServiceImpl implements ExamResultService {
//
//	private final UserRepository userDao;
//	private final ExamResultRepository examResultDao;
//	private final QuizeRepository quizeDao;
//	private final QuestionRepository questionDao;
//	private final ExamResultMapper examResultDtoFactory;
//
//	@Override
//	public List<ExamResult> getAllResultFromQuize(Long quizeId) {
//		QuizeEntity quize = quizeDao.findById(quizeId).orElseThrow(()->
//				new NotFoundException(String.format("Тест с идентификатором \"%s\" не найден", quizeId)));
//		return examResultDao.findAllByQuize(quize);
//	}
//
//
//	@Override
//	public List<ExamResultDto> checkUserResultExam(String userName, Long quizeId) {
//		return examResultDtoFactory
//				.toDto(
//						findUserByUserName(userName)
//							.getResults()
//							.stream()
//							.filter((r)->
//							r.getQuize()
//							.getQuizeId()==quizeId)
//							.collect(Collectors.toList()));
//	}
//
//	@Override
//	public void removeExamResult(Long id) {
//		examResultDao.deleteById(id);
//	}
//
//
//	@Override
//	public ExamResultDto getExamResult(String userName, List<QuestionDto> questions) {
//
//		int validQuestions=0;
//		int invalidQuestions=0;
//		int skipQuestions=0;
//
//		if(questions.isEmpty()) {
//			throw new BadRequestException("Возникла ошибка, отсутствует список вопросов");
//		}
//		User user = findUserByUserName(userName);
//		QuizeEntity quize = quizeDao.findByActiveAndQuizeId(true,
//				questions.get(0).getQuizeDto().getQuizeId()).orElseThrow(()->
//					new NotFoundException("Тест отсутствует или закрыт"));
//		checkExamResultByUserAndQuize(user, quize);
//
//		Map<String, Map<String, String>> allExamResult = new HashMap<>();
//		for(QuestionDto q: questions){
//			Map<String, String> givenAndAnswer = new HashMap<>();
//			Question question = questionDao.findById(q.getQuestionId())
//					.orElseThrow(()->new BadRequestException());
//
//			if(q.getGivenAnswer()==null||q.getGivenAnswer().trim()=="") {
//				++skipQuestions;
//			}
//			else if(question.getAnswer()!=null&&question
//					.getAnswer().trim().equals(q.getGivenAnswer().trim())) {
//				++validQuestions;
//			} else {
//				++invalidQuestions;
//			}
//			givenAndAnswer.put(q.getGivenAnswer(), question.getAnswer());
//			allExamResult.put(q.getContent(), givenAndAnswer);
//		}
//
//		return examResultDtoFactory
//					.toDto(
//							examResultDao.saveAndFlush(
//									new ExamResult(
//											validQuestions,
//											invalidQuestions,
//											skipQuestions,
//											validQuestions*quize.getMaxMarks()/
//											quize.getCountOfQuestion(),
//											user,
//											quize,
//											allExamResult
//											)
//								)
//						);
//	}
//
//	@Override
//	public List<ExamResultDto> checkUserAllResultExam(String userName) throws UserNotFoundException {
//		return examResultDtoFactory.toDto(findUserByUserName(userName).getResults());
//	}
//
//
//	private User findUserByUserName(String userName) {
//		return userDao.findByUserName(userName).orElseThrow(()->
//			new UserNotFoundException(String.format("Пользователь с ником \"%s\" не найден", userName)));
//	}
//
//	private void checkExamResultByUserAndQuize(User user, QuizeEntity quize) {
//		examResultDao.findByUserAndQuize(user, quize).ifPresent((us)->{
//			throw new BadRequestException(String.format(
//					"Пользователь с ником \"%s\" уже решал тест \"%s\" ",
//					user.getUserName(), quize.getQuizeId()));
//				}
//			);
//	}
//
//
//
//
//}
