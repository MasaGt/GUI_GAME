/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * This class is to display main contents, such as quiz.
 *
 * @author Masaomi
 */
public class MainFrame extends Frame {

    /**
     * open this frame which fit the content's size.
     */
    @Override
    public void open() {
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        //set fram in center
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
