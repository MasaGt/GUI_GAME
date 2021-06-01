/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.util.Const;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for Telephone.
 * @author Masaomi
 */
public class TelephonePanel extends Panel implements Observer {

    //components
    private JLabel label;
    private JButton closeBtn;
    private JPanel buttonPanel;
    

    //seize variables
    private final int PANEL_WIDTH = 300;
    private final int PANEL_HEIGHT = 100;

    public TelephonePanel() {

        super(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        closeBtn = new JButton(Const.CLOSE_BUTTON);
        buttonPanel.add(closeBtn);

    }

    @Override
    public Object getParam() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addController(GameController controller) {
        closeBtn.addActionListener(controller);
    }

    /**
     * receive a telephone text from Telephone.java.
     * @param o
     * @param arg telephone text.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            render((String) arg);
        }
    }

    /**
     * render a telephone text.
     * @param telText 
     */
    private void render(String telText) {
        //enable lable to wrap text.
        label.setText("<html>" + telText + "</html>");
    }
}
