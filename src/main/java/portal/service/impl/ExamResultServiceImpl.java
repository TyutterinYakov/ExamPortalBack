package portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import portal.dao.ExamResultRepository;
import portal.dao.QuestionRepository;
import portal.dao.UserRepository;
import portal.exception.InvalidDataException;
import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;
import portal.model.Quize;
import portal.model.User;
import portal.service.ExamResultService;

@Service
public class ExamResultServiceImpl implements ExamResultService {

	private UserRepository userDao;
	private ExamResultRepository examResultDao;
	private QuestionRepository questionDao;
	
	@Autowired
	public ExamResultServiceImpl(UserRepository userDao, ExamResultRepository examResultDao, QuestionRepository questionDao) {
		super();
		this.userDao = userDao;
		this.examResultDao = examResultDao;
		this.questionDao = questionDao;
	}

	@Override
	public List<ExamResult> getAllResultFromQuize(Long id) {
		Quize quize = new Quize();
		quize.setQuizeId(id);		
		return examResultDao.findAllByQuize(quize);
	}

	@Override
	public List<ExamResult> checkUserResultExam(String name, Long id) throws UserNotFoundException {
		User user = userDao.findByUserName(name).orElseThrow(()->
			new UserNotFoundException("Проверка на прохождение теста! Пользователь не найден")
	);
		Quize quize = new Quize();
		quize.setQuizeId(id);
	
	return examResultDao.findAllByUserAndQuize(user, quize);
	}

	@Override
	public void removeExamResult(Long id) {
		examResultDao.deleteById(id);		
	}


	@Override
	public ExamResult getExamResult(String name, List<Question> questions) throws UserNotFoundException, InvalidDataException {
		int validQuestion=0;
		int invalidQuestion=0;
		int skipQuestion=0;
		
		Map<String, Map<String, String>> allExamResult = new HashMap<>();
		Quize quize = questions.get(0).getQuize();
		for(Question q: questions){
			Map<String, String> givenAndAnswer = new HashMap<>();
			Question question = questionDao.findById(q.getQuestionId()).orElseThrow(()->new InvalidDataException());
			if(q.getGivenAnswer().trim()==""||q.getGivenAnswer()==null) {
				++skipQuestion;
			} 
			else if(question.getAnswer()!=null&&question.getAnswer().trim().equals(q.getGivenAnswer().trim())) { 
				++validQuestion;
			} else {
				++invalidQuestion;
			}
			givenAndAnswer.put(q.getGivenAnswer(), q.getAnswer());
			allExamResult.put(q.getContent(), givenAndAnswer);
			
			
		}
		ExamResult result = new ExamResult();
		result.setInvalidQuestion(invalidQuestion);
		result.setSkipQuestion(skipQuestion);
		result.setValidQustion(validQuestion);
		result.setCountPoints(validQuestion*quize.getMaxMarks()/quize.getCountOfQuestion()); 
		User user = userDao.findByUserName(name).orElseThrow(()->
			new UserNotFoundException("Результат теста не записан! Пользователь не найден"));
		result.setUser(user);
		result.setQuize(quize);
		result.setQuestionsAndGivenAnswer(allExamResult);
		examResultDao.save(result);
		
		return result;
	}

	@Override
	public List<ExamResult> checkUserAllResultExam(String name) throws UserNotFoundException {
		User user = userDao.findByUserName(name).orElseThrow(()->
		new UserNotFoundException("Пользователь не найден при поиске результатов теста"));
		
		return user.getResults();
	}

}
