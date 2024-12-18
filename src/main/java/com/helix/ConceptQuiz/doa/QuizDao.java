package com.helix.ConceptQuiz.doa;

import com.helix.ConceptQuiz.modal.QuizPrint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<QuizPrint,Integer> {
}
