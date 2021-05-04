/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

/**
 * Super class of each lifeline.
 * @author Masaomi
 */
public abstract class Lifeline {
    
    private boolean isAvailable;
    private String name;
    
    public Lifeline(String name) {
        this.isAvailable = true;
        this.name = name;
    }
    
    abstract public void execute(QuizInfo quiz);
    
    public void disable () {
        this.isAvailable = false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the isAvailable
     */
    public boolean getIsAvailable() {
        return isAvailable;
    }
    
    
}
