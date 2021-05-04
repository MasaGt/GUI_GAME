/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.util.Const;
import game.util.Prize;

/**
 * Display player's result.
 * @author Masaomi
 */
public class ResultView {
    
    /**
     * Show player's result.
     * @param isFinised
     * @param round 
     */
    public void showResult(boolean isFinised, int round) {
        
        if (isFinised) {
            System.out.println("Your answer was wrong.");
        } else {
            if (round < Const.FINAL_ROUND) {
                System.out.println("Your answer is correct!");
                System.out.println("You got " + Prize.getPrizeFor(round));
            } else {
                System.out.println("************************************");
                System.out.println("You got all the answer correct!!");
                System.out.println("It is awesome!");
                System.out.println("************************************\n");
            }
        } 
    }
}
