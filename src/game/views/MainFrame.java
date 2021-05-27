/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

/**
 * This class is to display main contents, such as quiz.
 * @author Masaomi
 */
public class MainFrame extends Frame {

    /**
     * open this frame with specifying width and height.
     * @param width
     * @param height 
     */
    @Override
    public void open(int width, int height) {
        this.setSize(width, height);
        this.setResizable(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
