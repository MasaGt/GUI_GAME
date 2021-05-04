/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.entity.Player;
import java.util.Scanner;

/**
 * Dsiplay ending.
 * @author Masaomi
 */
public class EndingView implements Validation{
    /**
     * Display player's score.
     * @param player 
     */
    public void displayResult (Player player) {
        System.out.println(player.getName() + "'s score was " + player.getScore());
    }
    
    /**
     * Ask if player play more or not
     * @param scanner
     * @return true if player chose to play again. Otherwise, no.
     */
    public boolean askContinue(Scanner scanner) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            System.out.println("Play again? Y:yes, N:no\n");
            input = scanner.nextLine();
            isValid = validate(input);
        }
        
        return (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES"));
        
    }

    /**
     * Check if player' input is correct.
     * @param input
     * @return true if input is equal to yes or no ignoring case. Otherwise no.
     */
    @Override
    public boolean validate(String input) {
        return (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO"));
    }
}
