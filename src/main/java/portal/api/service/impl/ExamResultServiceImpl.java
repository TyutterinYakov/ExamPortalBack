package portal.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.api.dto.ExamResultDto;
import portal.api.dto.QuestionDto;
import portal.api.dto.factory.ExamResultDtoFactory;
import portal.api.exception.BadRequestException;
import portal.api.exception.NotFoundException;
import portal.api.exception.UserNotFoundException;
import portal.api.service.ExamResultService;
import portal.store.entity.ExamResultEntity;
import portal.store.entity.QuestionEntity;
import portal.store.entity.QuizeEntity;
import portal.store.entity.UserEntity;
import portal.store.repository.ExamResultRepository;
import portal.store.repository.QuestionRepository;
import portal.store.repository.QuizeRepository;
import portal.store.repository.UserRepository;

@Service
public class ExamResultServiceImpl implements ExamResultService {

	private final UserRepository userDao;
	private final ExamResultRepository examResultDao;
	private final QuizeRepository quizeDao;
	private final QuestionRepository questionDao;
	private final ExamResultDtoFactory examResultDtoFactory;
	

	@Autowired
	public ExamResultServiceImpl(UserRepository userDao, ExamResultRepository examResultDao, QuizeRepository quizeDao,
			QuestionRepository questionDao,
			ExamResultDtoFactory examResultDtoFactory) {
		super();
		this.userDao = userDao;
		this.examResultDao = examResultDao;
		this.quizeDao = quizeDao;
		this.questionDao = questionDao;
		this.examResultDtoFactory = examResultDtoFactory;
	}


	@Override
	public List<ExamResultEntity> getAllResultFromQuize(Long quizeId) {
		return examResultDao.findAllByQuize(quizeDao.findById(quizeId).orElseThrow(()->
				new NotFoundException(
						String.format(
								"Тест с идентификатором \"%s\" не найден", 
								quizeId)))
				);
	}


	@Override
	public List<ExamResultDto> checkUserResultExam(String userName, Long quizeId) {
		return examResultDtoFactory
				.createListExamResultDto(
						findUserByUserName(userName)
							.getResults()
							.stream()
							.filter((r)->
							r.getQuize()
							.getQuizeId()==quizeId)
							.collect(Collectors.toList()));
	}

	@Override
	public void removeExamResult(Long id) {
		examResultDao.deleteById(id);		
	}
	

	@Override
	public ExamResultDto getExamResult(String userName, List<QuestionDto> questions) {
		
		int validQuestions=0;
		int invalidQuestions=0;
		int skipQuestions=0;
		
		if(questions.isEmpty()) {
			throw new BadRequestException("Возникла ошибка, отсутствует список вопросов");
		}
		UserEntity user = findUserByUserName(userName);
		QuizeEntity quize = quizeDao.findByActiveAndQuizeId(true, 
				questions.get(0).getQuizeDto().getQuizeId()).orElseThrow(()->
					new NotFoundException("Тест отсутствует или закрыт"));
		checkExamResultByUserAndQuize(user, quize);
		
		Map<String, Map<String, String>> allExamResult = new HashMap<>();
		for(QuestionDto q: questions){
			Map<String, String> givenAndAnswer = new HashMap<>();
			QuestionEntity question = questionDao.findById(q.getQuestionId())
					.orElseThrow(()->new BadRequestException());
			
			if(q.getGivenAnswer()==null||q.getGivenAnswer().trim()=="") {
				++skipQuestions;
			} 
			else if(question.getAnswer()!=null&&question
					.getAnswer().trim().equals(q.getGivenAnswer().trim())) { 
				++validQuestions;
			} else {
				++invalidQuestions;
			}
			givenAndAnswer.put(q.getGivenAnswer(), question.getAnswer());
			allExamResult.put(q.getContent(), givenAndAnswer);
		}
		
		return examResultDtoFactory
					.createExamResultDto(
							examResultDao.saveAndFlush(
									new ExamResultEntity(
											validQuestions,
											invalidQuestions,
											skipQuestions,
											validQuestions*quize.getMaxMarks()/
											quize.getCountOfQuestion(),
											user,
											quize,
											allExamResult
											)
								)
						);
	}

	@Override
	public List<ExamResultDto> checkUserAllResultExam(String userName) throws UserNotFoundException {
		return examResultDtoFactory.createListExamResultDto(findUserByUserName(userName).getResults());
	}
	
	
	private UserEntity findUserByUserName(String userName) {
		return userDao.findByUserName(userName).orElseThrow(()->
			new UserNotFoundException(String.format("Пользователь с ником \"%s\" не найден", userName)));
	}
	
	private void checkExamResultByUserAndQuize(UserEntity user, QuizeEntity quize) {
		examResultDao.findByUserAndQuize(user, quize).ifPresent((us)->{
			throw new BadRequestException(String.format(
					"Пользователь с ником \"%s\" уже решал тест \"%s\" ",
					user.getUserName(), quize.getQuizeId()));
				}
			);
	}




}
