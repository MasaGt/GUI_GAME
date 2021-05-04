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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Business Logic about Quiz.
 * @author Masaomi
 */
public class QuizService {
    
    /**
     * Get quizes by specified level.
     * @param level
     * @return list of quizes
     */
    public List<QuizInfo> getQuizes(int level) {
        IQuizDao quizDao = new QuizDao();
        List<QuizDto> quizes = quizDao.getByLevel(level);
        quizes = shuffle(quizes);
        
        IOptionDao optionDao = new OptionDao();
        List<OptionDto> options;
        List<QuizInfo> quizInfos = new ArrayList<>();
        
        //Transfer quiz and option dtos to QuizInfo
        for (QuizDto quiz : quizes) {
            int id = quiz.getId();
            options = optionDao.getById(id);
            //shuffle options
            Collections.shuffle(options);
            
            QuizInfo info = new QuizInfo();
            info.setQuiz(quiz);
            info.setOption(options);
            quizInfos.add(info);
        }
        
        return quizInfos;
    }
    
    /**
     * Return shuffled quizes.
     * Becasue the game has a limited cretain rounds, the game does not give all the quiz stored.
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
}
