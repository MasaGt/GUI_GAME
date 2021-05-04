/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

/**
 * Constant levels.
 * @author Masaomi
 */
public enum Level {
    VERY_EASY(1),
    EASY(2),
    MODERATE(3),
    HARD(4),
    VERY_HARD(5);
    
    private int levelId;

    private Level(int levelId) {
        this.levelId = levelId;
    }
    
    public int getLevel() {
        return this.levelId;
    }
    
    /**
     * Retuen next level
     * @param level
     * @return next level
     */
    public static Level getNext(Level level) {
        Level[] levels = Level.values();
        int index = (level.ordinal() + 1) % levels.length;
        
        return levels[index];
    }
    
}
