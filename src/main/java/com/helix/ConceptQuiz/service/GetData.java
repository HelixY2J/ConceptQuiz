package com.helix.ConceptQuiz.service;

import com.helix.ConceptQuiz.doa.QuestionDao;
import com.helix.ConceptQuiz.modal.QuestionPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetData {

    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<List<QuestionPrint>> Data() {
        try{
            List<QuestionPrint> result = questionDao.findAll();
            if(result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e) {
            System.err.println("Oops something broke while fetching the questions :"+e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

