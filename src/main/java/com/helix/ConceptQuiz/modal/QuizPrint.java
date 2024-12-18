package com.helix.ConceptQuiz.modal;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class QuizPrint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany
    private List<QuestionPrint> questions;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setQuestions(List<QuestionPrint> questions) {
        this.questions = questions;
    }

    public List<QuestionPrint> getQuestions() {
        return questions;
    }
}

