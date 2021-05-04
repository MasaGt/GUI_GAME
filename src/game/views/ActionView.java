/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.entity.Lifeline;
import game.util.Const;
import java.util.List;
import java.util.Scanner;

/**
 * Display a view to ask player's action.
 * @author Masaomi
 */
public class ActionView implements Validation {

    private int availableLifelines;

    /**
     * Ask player to act(answer, lifeline, or quit)
     *
     * @param lifelines
     * @param scanner
     * @return player's action
     */
    public int askAction(List<Lifeline> lifelines, Scanner scanner) {
        //count available lifelines
        this.availableLifelines = 0;
        for (Lifeline lifeline : lifelines) {
            if (lifeline.getIsAvailable()) {
                this.availableLifelines++;
            }
        }

        String input = "";

        System.out.println("What to do?");
        boolean is_valid = false;

        while (!is_valid) {
            System.out.print("1: Answer ");
            if (this.availableLifelines > 0) {
                System.out.print("2: Use a lifeline ");
            }
            System.out.println("3: QUIT");
            input = scanner.nextLine();
            is_valid = validate(input.trim());

            if (!is_valid) {
                System.out.println("Invalid input.");
            }
        }
        return Integer.parseInt(input);
    }

    /**
     * Check if player's answer is 1:answer, 2:lifeline, or 3:quit
     * When all the lifeline is not available, player have to select answer or quit.
     * @param input
     * @return true if player's input is valid. otherwise, not.
     */
    @Override
    public boolean validate(String input) {

        try {
            int intInput = Integer.parseInt(input);
            if (this.availableLifelines > 0) {
                return (intInput >= Const.ANSWER_QUIZ && intInput <= Const.QUIT);
            } else {
                //when player uses all the lifelines.
                return (intInput == Const.ANSWER_QUIZ || intInput == Const.QUIT);
            }
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
