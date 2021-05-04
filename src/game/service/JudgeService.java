/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.entity.Player;
import game.entity.QuizInfo;
import game.util.Prize;

/**
 *
 * @author Masaomi
 */
public class JudgeService {
    
    public boolean judge(int answer, int input, Player player, int round) {
        if (answer == input) {
            //TODO enumを使って、インクリメンタルにスコアを上げていく方法を考える
            player.setScore(Prize.getPrizeFor(round));
            return true;
        } else {
            return false;
        }
    }
}
