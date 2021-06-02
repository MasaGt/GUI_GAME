/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.entity.GameData;
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
    public boolean judgeAnswer(int answer, int input) {
        return answer == input;
    }
    
    /**
     * Calculate player's score.
     * @param gameData
     */
    public void calcScore(GameData gameData) {
        Player player = gameData.getPlayer();
        player.setScore(Prize.getPrizeFor(gameData.getRound()));
    }
    
    /**
     * If the result is false, finish the game.Otherwise, continue.
     * @param resultCorrect result of checking player's answer,
     * @param gameData 
     */
    public void judgeCompletion(boolean resultCorrect, GameData gameData) {
        if (!resultCorrect) {
            gameData.fail();
        }
        gameData.incrementRound();
        
        setChanged();
        notifyObservers(gameData);
    }
}
