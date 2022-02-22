package portal.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.api.exception.BadRequestException;
import portal.api.exception.UserFoundException;
import portal.api.exception.UserNotFoundException;
import portal.api.service.ExamResultService;
import portal.api.service.QuizeService;
import portal.store.entity.ExamResult;
import portal.store.entity.QuestionEntity;
import portal.store.entity.QuizeEntity;
import portal.store.entity.User;
import portal.store.repository.ExamResultRepository;
import portal.store.repository.QuestionRepository;
import portal.store.repository.UserRepository;

@Service
public class ExamResultServiceImpl implements ExamResultService {

	private UserRepository userDao;
	private ExamResultRepository examResultDao;
	private QuestionRepository questionDao;
	private QuizeService quizeService;
	
	@Autowired
	public ExamResultServiceImpl(UserRepository userDao, ExamResultRepository examResultDao,
			QuestionRepository questionDao, QuizeService quizeService) {
		super();
		this.userDao = userDao;
		this.examResultDao = examResultDao;
		this.questionDao = questionDao;
		this.quizeService = quizeService;
	}

	@Override
	public List<ExamResult> getAllResultFromQuize(Long quizeId) {
//		return examResultDao.findAllByQuize(quizeService.getQuizeAdmin(quizeId));
		return null;
	}

	@Override
	public List<ExamResult> checkUserResultExam(String userName, Long quizeId) {
//		return examResultDao.findAllByUserAndQuize(
//				findUserByUserName(userName), 
//				quizeService.getQuize(quizeId)
//			);
		return null;
	}

	@Override
	public void removeExamResult(Long id) {
		examResultDao.deleteById(id);		
	}


//	@Override
//	public ExamResult getExamResult(String userName, List<Question> questions) {
//		
//		int validQuestion=0;
//		int invalidQuestion=0;
//		int skipQuestion=0;
//		User user = userDao.findByUserName(userName).orElseThrow(()->
//			new UserNotFoundException(String.format(
//					"Пользователь под ником \"%s\" не найден",
//					userName)
//					));
//		QuizeEntity quize = questions.get(0).getQuize();
//		if(!examResultDao.findAllByUserAndQuize(user, quize).isEmpty()) {
//			throw new UserFoundException(
//					String.format(
//							"Пользователь под ником \"%s\" уже решал этот тест",
//							user.toString())
//				);
//		}
//		
//		Map<String, Map<String, String>> allExamResult = new HashMap<>();
//		for(Question q: questions){
//			Map<String, String> givenAndAnswer = new HashMap<>();
//			Question question = questionDao.findById(q.getQuestionId())
//					.orElseThrow(()->new BadRequestException());
//			if(q.getGivenAnswer()==null||q.getGivenAnswer().trim()=="") {
//				++skipQuestion;
//			} 
//			else if(question.getAnswer()!=null&&question
//					.getAnswer().trim().equals(q.getGivenAnswer().trim())) { 
//				++validQuestion;
//			} else {
//				++invalidQuestion;
//			}
//			givenAndAnswer.put(q.getGivenAnswer(), q.getAnswer());
//			allExamResult.put(q.getContent(), givenAndAnswer);
//		}
//		ExamResult result = new ExamResult();
//		result.setInvalidQuestion(invalidQuestion);
//		result.setSkipQuestion(skipQuestion);
//		result.setValidQustion(validQuestion);
//		result.setCountPoints(validQuestion*quize.getMaxMarks()/quize.getCountOfQuestion()); 
//		result.setUser(user);
//		result.setQuize(quize);
//		result.setQuestionsAndGivenAnswer(allExamResult);
//		examResultDao.save(result);
//		
//		return result;
//	}

	@Override
	public List<ExamResult> checkUserAllResultExam(String userName) throws UserNotFoundException {
		return findUserByUserName(userName).getResults();
	}
	
	
	private User findUserByUserName(String userName) {
		return userDao.findByUserName(userName).orElseThrow(()->
			new UserNotFoundException(String.format("Пользователь с ником \"%s\" не найден", userName)));
	}

	@Override
	public ExamResult getExamResult(String name, List<QuestionEntity> questions) {
		// TODO Auto-generated method stub
		return null;
	}

}
