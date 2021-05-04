/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.entity.OptionDto;
import java.util.List;
import java.util.Scanner;

/**
 * Dispaly asks answer.
 * @author Masaomi
 */
public class AnswerView implements Validation{
    
    private int optionSize;
    
    /**
     * Ask player's answer.
     * Answer is converted in optionId so that the program can judge compare it to answerId.
     * @param options
     * @param scanner
     * @return option_id which player chose.
     */
    public int askAnswer(List<OptionDto> options, Scanner scanner) {
        
        this.optionSize = options.size();
        boolean is_valid = false;
        String input = "";
        
        while (!is_valid) {
            System.out.println("Choose your answer");
            input = scanner.nextLine();
            
            is_valid = validate(input.trim());

            if (!is_valid) {
                System.out.println("Invalid answer");
            }
        }
        
        //Reason for (input) - 1: player will select from 1 to 4. This will casuse array index out of bounds.
        //Convert player's input to array index.
        int chosenOptionId = options.get(Integer.parseInt(input) - 1).getId();
        return chosenOptionId;
        
    }

    /**
     * Chekc if player's answer is in the range of options.
     * @param input
     * @return true if player's answer is in the range of options. Otherwise, not.
     */
    @Override
    public boolean validate(String input) {
        try {
            int int_input = Integer.parseInt(input);
            return (0 < int_input && int_input <= this.optionSize);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
