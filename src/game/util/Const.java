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
    
    //the name of components
    public static final String NEW_GAME_BUTTON = "New game";
    public static final String CONTINUE_BUTTON = "Continue";
    public static final String START_BUTTON = "Start";
    public static final String RANKING_BUTTON = "Ranking";
    public static final String REGISTER_BUTTON = "Register";
    public static final String CLOSE_BUTTON = "Close";
    public static final String QUIT_BUTTON = "Quit";
    public static final String OPTION1_BUTTON = "1";
    public static final String OPTION2_BUTTON = "2";
    public static final String OPTION3_BUTTON = "3";
    public static final String OPTION4_BUTTON = "4";
    public static final String AUDIENCE = "Audience";
    public static final String TELEPHONE = "Telephone";
    public static final String FIFTY_FIFTY = "50/50";
    public static final String VALID_CHAR_FOR_PLAYER = "[0-9a-zA-Z]+";
    
    /**
     * Return a text which can be wrapped.
     * @param text
     * @return 
     */
    public static String wrapText(String text) {
        return "<html>" + text + "</html>";
    }
}
