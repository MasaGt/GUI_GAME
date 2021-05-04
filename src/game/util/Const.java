/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

/**
 * Other constant to avid magic number.
 * @author Masaomi
 */
public class Const {
    public static final int FINAL_ROUND = 15;
    public static final int ANSWER_QUIZ = 1;
    public static final int USE_LIFELINE = 2;
    public static final int QUIT = 3;
    public static final String DELIMITER = "/";
    public static final int RANKING_RECORD_LIMIT = 10;
    public static final int THE_NUMBER_OF_QUIZ_PER_LEVEL = FINAL_ROUND / Level.values().length;
}
