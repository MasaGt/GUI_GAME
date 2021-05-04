/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.util.Const;
import java.util.Scanner;

/**
 * Register View asking player's name.
 * @author Masaomi
 */
public class RegisterView implements Validation{
    
    /**
     * Ask player's name.
     * @param scanner
     * @return player's name
     */
    public String askName(Scanner scanner) {
        String input = "";
        while (!this.validate(input)) {
           System.out.println("Register your name");
           input = scanner.nextLine().trim();
        }
        
        return input;
    }

    /**
     * Check if plaer's name is safe to register.
     * @param input
     * @return true if input is valid. Otherwise, false.
     */
    @Override
    public boolean validate(String input) {
        return !(input.isEmpty() || input.contains(Const.DELIMITER));
    }

}
