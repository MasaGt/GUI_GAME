/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * FiftyFifty Object.
 * @author Masaomi
 */
public class FiftyFifty extends Lifeline {

    public FiftyFifty(String name) {
        super(name);
    }

    /**
     * Remove 2 wrong options.
     * @param quiz 
     */
    @Override
    public void execute(QuizInfo quiz) {
        
        if (!this.getIsAvailable()) {
            return;
        }
        
        List<OptionDto> options = quiz.getOption();
        int answer = quiz.getQuiz().getAnswer();
        //removeTargets hold option id.
        Set<Integer> removeTargets = new HashSet<Integer>();
        Random rand = new Random();
        int randNum;
        
        //generate 2 remove target id.
        while(removeTargets.size() < 2) {
            //generate random number from 1 to 4.
            randNum = rand.nextInt(4) + 1;
            if (randNum != answer) {
                removeTargets.add(randNum);
            }
        }
        
        //remove 2 oprions form 4
        for (int i = options.size() - 1; i >= 0; i--) { 
            if (removeTargets.contains(options.get(i).getId())) {
                options.remove(i);
            }
        }
    }
}
