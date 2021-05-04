/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

import java.util.List;

/**
 * Comprehensive quiz information object.
 * @author Masaomi
 */
public class QuizInfo {
    private QuizDto quiz;
    private List<OptionDto> options;

    /**
     * @return the quiz
     */
    public QuizDto getQuiz() {
        return quiz;
    }

    /**
     * @param quiz the quiz to set
     */
    public void setQuiz(QuizDto quiz) {
        this.quiz = quiz;
    }

    /**
     * @return the options
     */
    public List<OptionDto> getOption() {
        return options;
    }

    /**
     * @param option the options to set
     */
    public void setOption(List<OptionDto> option) {
        this.options = option;
    }
    
    
}
