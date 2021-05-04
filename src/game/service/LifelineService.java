/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.entity.Lifeline;
import game.entity.QuizInfo;

/**
 *
 * @author Masaomi
 */
public class LifelineService {
    
    public void execute(Lifeline lifeline, QuizInfo quiz) {
        lifeline.execute(quiz);
        lifeline.disable();
    }
}
