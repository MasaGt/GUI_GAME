/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.controller.GameController;
import java.util.Scanner;

/**
 * The startinig point.
 * @author Masaomi
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController(scanner);

        //continue until the player choose exit the game.
        while (!controller.isExited()) {
            controller.initGame();
            controller.registerPlayer();

            //keep giving a quietion until the player reach 15th round, fail or quit.
            while (!controller.isGameFinished()) {
                controller.stratQuiz();
            }
            controller.registData();
            controller.showEnding();
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
