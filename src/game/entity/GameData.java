/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

import game.util.Const;

/**
 *
 * @author Masaomi
 */
public class GameData {

    private Player player;
    private int round;
    private boolean isFaild;
    private boolean isFinished;

    public GameData() {
        initGameData();
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the round
     */
    public int getRound() {
        return round;
    }

    /**
     */
    public void incrementRound() {
        this.round++;
    }

    /**
     * @return the isFinished
     */
    public boolean isFinished() {
        return round > Const.FINAL_ROUND;
    }

    public boolean isFailed() {
        return isFaild;
    }

    public void fail() {
        this.isFaild = true;
    }

    public void initGameData() {
        this.round = 1;
        this.isFaild = false;
        this.isFinished = false;
    }

}
