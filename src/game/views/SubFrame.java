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
     * open this frame which fit the content's size.
     */
    @Override
    public void open() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
