/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import javax.swing.JFrame;

/**
 * This is super frame class. In this game, we use this frame to show the content.
 * @author Masaomi
 */
public abstract class Frame extends JFrame {

    private Panel panel;

    /**
     * set parameter panel to this frame.
     * @param panel 
     */
    public void setPanel(Panel panel) {
        if (this.panel != null) {
            this.remove(this.panel);
        }
        this.panel = panel;
        this.add(panel);
        this.revalidate();
        this.repaint();
    }

    /**
     * get panel
     * @return 
     */
    public Panel getPanel() {
        return this.panel;
    }
    
    /**
     * open this panel.
     */
    public abstract void open();
}
