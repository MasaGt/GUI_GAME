/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.controller.GameController;

/**
 * The startinig point.
 * @author Masaomi
 */
public class Main {

    public static void main(String[] args) {

        GameController controller = new GameController();
        
        controller.showStartDisplay();
    }
}
