/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

import game.util.Const;
import game.util.Prize;
import java.util.ArrayList;

/**
 * Player information class
 * @author Masaomi
 */
public class Player {
    
    private String name;
    private int score;
    private ArrayList<Lifeline> lifelines;
    private int id;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.score = Prize.Stage0.getPrize();
        this.lifelines = new ArrayList<>();
        this.lifelines.add(new Audience(Const.AUDIENCE));
        this.lifelines.add(new Telephone(Const.TELEPHONE));
        this.lifelines.add(new FiftyFifty(Const.FIFTY_FIFTY));
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the lifelines
     */
    public ArrayList<Lifeline> getLifelines() {
        return lifelines;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * rest score and lifelines.
     * this will be called when a player continue the game
     */
    public void reset() {
        this.score = 0;
        
        for (Lifeline lifeline : lifelines) {
            lifeline.reset();
        }
    }
}
