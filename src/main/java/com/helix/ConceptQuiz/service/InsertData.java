package com.helix.ConceptQuiz.service;

import com.helix.ConceptQuiz.doa.QuestionDao;
import com.helix.ConceptQuiz.modal.QuestionPrint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InsertData implements CommandLineRunner {
    private final QuestionDao que;

    public InsertData(QuestionDao que){
        this.que = que;
    }
    @Override
    public void run(String... args) throws Exception{
        if(que.count() == 0) {
            String[][] data = {
                    {"Geography", "Medium", "What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "Paris"},
                    {"Geography", "Medium", "What is the largest country by area?", "Canada", "Russia", "China", "United States", "Russia"},
                    {"Geography", "Easy", "Which ocean is the largest?", "Atlantic Ocean", "Pacific Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},
                    {"Geography", "Hard", "Which country has the most pyramids?", "Mexico", "Egypt", "Sudan", "Peru", "Sudan"},
                    {"Geography", "Medium", "Which is the longest river in the world?", "Amazon River", "Nile River", "Yangtze River", "Mississippi River", "Nile River"},
                    {"Geography", "Easy", "What is the capital of Japan?", "Seoul", "Beijing", "Tokyo", "Osaka", "Tokyo"},
                    {"Geography", "Medium", "Which continent is the Sahara Desert located on?", "Africa", "Asia", "Australia", "South America", "Africa"},
                    {"Geography", "Hard", "Which is the smallest country in the world?", "Monaco", "Vatican City", "San Marino", "Liechtenstein", "Vatican City"},
                    {"Geography", "Medium", "Which mountain is known as the highest point on Earth?", "Mount Everest", "K2", "Mount Kilimanjaro", "Mount Fuji", "Mount Everest"},
                    {"Geography", "Hard", "What is the largest desert in the world?", "Sahara Desert", "Arabian Desert", "Gobi Desert", "Antarctic Desert", "Antarctic Desert"},
                    {"Geography", "Medium", "Which country has the most islands?", "Canada", "Sweden", "Norway", "Finland", "Sweden"},


                    {"Physics", "Medium", "What is the unit of force?", "Joule", "Newton", "Pascal", "Watt", "Newton"},
                    {"Physics", "Medium", "What is the speed of light in vacuum?", "3 × 10^8 m/s", "2 × 10^8 m/s", "1 × 10^8 m/s", "4 × 10^8 m/s", "3 × 10^8 m/s"},
                    {"Physics", "Medium", "Which law states that every object in motion stays in motion unless acted upon by an external force?", "Newton's First Law", "Newton's Second Law", "Newton's Third Law", "Gravitational Law", "Newton's First Law"},
                    {"Physics", "Medium", "What is the formula for calculating kinetic energy?", "1/2 mv^2", "mv^2", "mgh", "Fv", "1/2 mv^2"},
                    {"Physics", "Medium", "Which particle has a positive charge?", "Electron", "Neutron", "Proton", "Photon", "Proton"},


                    {"History", "Medium", "Who was the first president of the United States?", "George Washington", "Thomas Jefferson", "Abraham Lincoln", "John Adams", "George Washington"},
                    {"History", "Hard", "In which year did the Titanic sink?", "1910", "1912", "1905", "1920", "1912"},
                    {"History", "Medium", "Who was the leader of the Soviet Union during World War II?", "Joseph Stalin", "Vladimir Lenin", "Mikhail Gorbachev", "Leon Trotsky", "Joseph Stalin"},
                    {"History", "Medium", "What year did World War I begin?", "1912", "1914", "1916", "1918", "1914"},
                    {"History", "Easy", "Which empire was ruled by Julius Caesar?", "Roman Empire", "Mongol Empire", "Ottoman Empire", "Byzantine Empire", "Roman Empire"}
            };

            ArrayList<QuestionPrint> questions = new ArrayList<>();

            for(String[] row : data) {
                QuestionPrint question = new QuestionPrint();
                question.setCategory(row[0]);
                question.setDifficultyLevel(row[1]);
                question.setQuestionTitle(row[2]);
                question.setOption1(row[3]);
                question.setOption2(row[4]);
                question.setOption3(row[5]);
                question.setOption4(row[6]);
                question.setCorrectAnswer(row[7]);
                questions.add(question);
            }

            que.saveAll(questions);
        }
    }

}
