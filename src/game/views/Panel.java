/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 * This is content class of a frame.
 * @author Masaomi
 */
public abstract class Panel extends JPanel {
    
    public Panel(LayoutManager layout) {
        super(layout);
    }
    
    public Panel() {
        super();
    }
    
    /**
     * retrun parameter to controller.
     * @return 
     */
    public abstract Object getParam();
    
    /**
     * set controller to a panel.
     * @param controller 
     */
    public abstract void addController(GameController controller);
}
