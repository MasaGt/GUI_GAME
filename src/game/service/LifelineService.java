/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.entity.Lifeline;
import game.entity.QuizInfo;

/**
 * Business Logic about Lifeline.
 * @author Masaomi
 */
public class LifelineService {
    
    /**
     * Execute the selected lifeline
     * @param lifeline selected lifeline
     * @param quiz quiz info
     */
    public void execute(Lifeline lifeline, QuizInfo quiz) {
        lifeline.execute(quiz);
        lifeline.disable();
    }
}
