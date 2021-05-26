/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.entity.Player;
import game.util.Prize;
import java.util.Observable;

/**
 * This class is to plat a role in the judge.
 * @author Masaomi
 */
public class JudgeService extends Observable {

    /**
     * Judge if player's ansert is correct or not.
     * @param answer correct option id
     * @param input the option id which a player selected.
     * @return true if player's input is correct. Otherwise, false.
     */
    public boolean judge(int answer, int input) {
        setChanged();
        boolean result = answer == input;
        
        notifyObservers(result);
        return result;
    }
    
    /**
     * calc player's score.
     * @param player
     * @param round 
     */
    public void calcScore(Player player, int round) {
        player.setScore(Prize.getPrizeFor(round));
    }
}
