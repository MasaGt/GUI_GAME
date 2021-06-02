/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.dao.IOptionDao;
import game.dao.IQuizDao;
import game.dao.OptionDao;
import game.dao.QuizDao;
import game.entity.OptionDto;
import game.entity.QuizDto;
import game.entity.QuizInfo;
import game.util.Const;
import game.util.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Business Logic about Quiz.
 * @author Masaomi
 */
public class QuizService extends Observable {
    
    /**
     * Get All 15 quizes.
     */
    public void getAllQuizes() {
        
        List<QuizDto> container = new ArrayList<QuizDto>();
        for (Level level : Level.values()) {
            IQuizDao quizDao = new QuizDao();
            List<QuizDto> quizes = quizDao.getByLevel(level.getLevel());
            quizes = shuffle(quizes);
            addQuiz(container, quizes);
        }
        
        IOptionDao optionDao = new OptionDao();
        List<OptionDto> options;
        List<QuizInfo> quizInfos = new ArrayList<>();
        
        //Transfer quiz and option dtos to QuizInfo
        for (QuizDto quiz : container) {
            int id = quiz.getId();
            options = optionDao.getById(id);
            //shuffle options
            Collections.shuffle(options);
            
            QuizInfo info = new QuizInfo();
            info.setQuiz(quiz);
            info.setOption(options);
            quizInfos.add(info);
        }
        
        setChanged();
        notifyObservers(quizInfos);
        
    }
    
    /**
     * Return shuffled quizes.
     * Becasue the game has limited rounds, the game does not give all the quizes.
     * The number of quizes for each level will be ditermineed by Const.THE_NUMBER_OF_QUIZ_PER_LEVEL.
     * @param quizes
     * @return list of shuffled quizes
     */
    public List<QuizDto> shuffle(List<QuizDto> quizes) {
        Collections.shuffle(quizes);
        List<QuizDto> shuffled = new ArrayList<>();
        
        for (int i = 0; i < Const.THE_NUMBER_OF_QUIZ_PER_LEVEL; i++) {
            shuffled.add(quizes.get(i));
        }
        
        return shuffled;
    }
    
     public void addQuiz(List<QuizDto> container, List<QuizDto> quizes) {
        for (QuizDto quiz : quizes) {
            container.add(quiz);
        }
    }
}
