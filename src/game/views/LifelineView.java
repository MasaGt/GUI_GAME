/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.entity.Lifeline;
import java.util.List;
import java.util.Scanner;

/**
 * Display available lifelines and ask which to use.
 * @author Masaomi
 */
public class LifelineView implements Validation{
    
    private List<Lifeline> lifelines; 
    
    /**
     * Ask which lifelne to use.
     * @param lifelines
     * @param scanner
     * @return Selected lifeline
     */
    public Lifeline chooseLifeline(List<Lifeline> lifelines, Scanner scanner) {
        
        this.lifelines = lifelines;
        boolean is_valid = false;
        String input = "";
        
        //show available lifelines
        for (int i = 0; i < lifelines.size(); i++) {
            if (lifelines.get(i).getIsAvailable()) {
                System.out.println(i + 1 + ": " + lifelines.get(i).getName());
            }
        }
        
        while(!is_valid) {
            input = scanner.nextLine();
            is_valid = validate(input);
            if (!is_valid) {
                System.out.println("Wrong input");
            }
        }
        
        //user will input 1, 2, or 3. Convert the input to index of an array of lifelines.
        int index = Integer.parseInt(input) - 1;
        return lifelines.get(index);
    }

    /**
     * Check if player choose available lifeline.
     * @param input
     * @return if the chosen lifeline is available. Otherwise, false.
     */
    @Override
    public boolean validate(String input) {
        try {
            int intInput = Integer.parseInt(input);
            
            if (this.lifelines.size() < intInput) {
                return false;
            } else {
                return this.lifelines.get(intInput - 1).getIsAvailable();
            }
            
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
