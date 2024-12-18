package com.helix.ConceptQuiz.controller;


import com.helix.ConceptQuiz.modal.QuestionPrint;
import com.helix.ConceptQuiz.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class QuestionController {
    @Autowired
    private final GetData getData;

    public QuestionController(GetData getData){
        this.getData = getData;
    }

    @GetMapping("getQuestions")
    public ResponseEntity<List<QuestionPrint>> getQuestions() {
        return getData.Data();
    }
}
