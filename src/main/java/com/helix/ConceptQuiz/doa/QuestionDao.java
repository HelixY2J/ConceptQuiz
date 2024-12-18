package com.helix.ConceptQuiz.doa;

import com.helix.ConceptQuiz.modal.QuestionPrint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<QuestionPrint,Integer> {

    @Query(value = "SELECT * FROM question_print ORDER BY RAND() LIMIT 5;",nativeQuery = true)
    List<QuestionPrint> findRandomQuestions();
}
