/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

/**
 * This class is to dislpay sub content, such as ranking, register display.
 * @author Masaomi
 */
public class SubFrame extends Frame {

    /**
     * open this sub frame with specifying width and height.
     * When the close button is clicked, this frame will be displosed.
     * @param width
     * @param height 
     */
    @Override
    public void open(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
    }
}
