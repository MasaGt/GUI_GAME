/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

/**
 * Contant prizes for each round
 * @author Masaomi
 */
public enum Prize {
    
    Stage0(0),
    STAGE1(100),
    STAGE2(200),
    STAGE3(300),
    STAGE4(500),
    STAGE5(1000),
    STAGE6(2000),
    STAGE7(4000),
    STAGE8(8000),
    STAGE9(16000),
    STAGE10(32000),
    STAGE11(64000),
    STAGE12(125000),
    STAGE13(250000),
    STAGE14(500000),
    STAGE15(1000000);
    
    private final int prize;
    
    private Prize(int prize) {
        this.prize = prize;
    }

    /**
     * @return the prize
     */
    public int getPrize() {
        return this.prize;
    }
    
    /**
     * Return the prize for a passed round.
     * @param round
     * @return prize
     */
    public static int getPrizeFor(int round) {
        Prize[] prizes = Prize.values();
        return prizes[round].getPrize();
    }
}
