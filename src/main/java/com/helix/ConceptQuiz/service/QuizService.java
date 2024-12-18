package com.helix.ConceptQuiz.service;

import com.helix.ConceptQuiz.doa.QuestionDao;
import com.helix.ConceptQuiz.doa.QuizDao;
import com.helix.ConceptQuiz.modal.AnswerPrint;
import com.helix.ConceptQuiz.modal.QuestionPrint;
import com.helix.ConceptQuiz.modal.QuestionWrapper;
import com.helix.ConceptQuiz.modal.QuizPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuizDao quizDao;

    public ResponseEntity<String> createNewQuizSession() {
      QuizPrint  currentQuiz = new QuizPrint();
        currentQuiz.setTitle("Sample quiz");

        List<QuestionPrint> questions = questionDao.findRandomQuestions();
        currentQuiz.setQuestions(questions);
        quizDao.save(currentQuiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
       Optional<QuizPrint> currentQuiz = quizDao.findById(id);
       List<QuestionPrint> questionsFromDB = currentQuiz.get().getQuestions();
       List<QuestionWrapper> questionForUser = new ArrayList<>();

       for(QuestionPrint que : questionsFromDB) {
           QuestionWrapper questionWrapper = new QuestionWrapper(que.getId(),que.getCategory(),que.getDifficultyLevel(),que.getQuestionTitle(),
                   que.getOption1(),que.getOption2(),que.getOption3(), que.getOption4());
           questionForUser.add(questionWrapper);
       }

       return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public Map<String,Object> getResults(Integer id, List<AnswerPrint> responses){

        Optional<QuizPrint> optionalQuiz = quizDao.findById(id);

        if (optionalQuiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }
        int count = 0;


        QuizPrint currentQuiz = optionalQuiz.get();
        List<QuestionPrint> questions = currentQuiz.getQuestions();
        int total = questions.size();

        Map<Integer, String> correctAnswersMap = questions.stream()
                .collect(Collectors.toMap(QuestionPrint::getId, QuestionPrint::getCorrectAnswer));


        for (AnswerPrint answerPrint : responses) {
            String correctAnswer = correctAnswersMap.get(answerPrint.getId());
            if (correctAnswer != null && correctAnswer.equals(answerPrint.getResponse())) {
                count++;
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("score",count);
        resultMap.put("totalQuestions",total);

        return resultMap;
    }
}
