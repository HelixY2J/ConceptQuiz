package com.helix.ConceptQuiz.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.helix.ConceptQuiz.modal.QuestionWrapper;
import com.helix.ConceptQuiz.modal.AnswerPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.helix.ConceptQuiz.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private final QuizService quizService;

    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }


    // Start a new quiz session
    @GetMapping("/start")
    public ResponseEntity<ResponseEntity<String>> startQuiz() {
        // Start a new quiz session and get the quiz details
        ResponseEntity<String> quiz = quizService.createNewQuizSession();
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
            return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Map<String, Object>> submitQuiz(@PathVariable Integer id, @RequestBody List<AnswerPrint> responses) {
        Map<String, Object> result =  quizService.getResults(id, responses);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
