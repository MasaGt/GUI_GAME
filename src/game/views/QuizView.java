/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.entity.OptionDto;
import game.entity.QuizInfo;
import java.util.List;

/**
 * Dislpay a quiz and options.
 * @author Masaomi
 */
public class QuizView {
    
    /**
     * Display Quiz and options.
     * @param quiz This holds quiz and options.
     */
    public void displayQuiz(QuizInfo quiz) {
        
        System.out.println("\nQuiz:" + quiz.getQuiz().getStatement());
        
        List<OptionDto> options = quiz.getOption();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ": " + options.get(i).getStatement());
        }
    }
}
