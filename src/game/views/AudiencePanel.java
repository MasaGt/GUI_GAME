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
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Masaomi
 */
public class AudiencePanel extends Panel implements Observer {


    //components
    private JLabel option1;
    private JLabel option2;
    private JLabel option3;
    private JLabel option4;
    private final JButton closeButton;
    private GraphPanel graphPanel;
    private JPanel buttonPanel;
    
    //seize variables
    private final int LABEL_WIDTH = 100;
    private final int LABEL_HEIGHT = 50;
    private final int MARGIN = 20;
    private final int PANEL_LEN = 300;
    private final int GRAPH_PANEL_LEN = 200;
    private final int BTN_WIDTH = 100;
    private final int BTN_HEIGHT = 50;
    
    public AudiencePanel() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_LEN, PANEL_LEN));
        graphPanel = new GraphPanel();
        graphPanel.setPreferredSize(new Dimension(GRAPH_PANEL_LEN,GRAPH_PANEL_LEN));
        add(graphPanel, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        
        closeButton = new JButton(Const.CLOSE_BUTTON);
        closeButton.setSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        buttonPanel.add(closeButton);
    }

    /**
     * Add action lister to the close button.
     * @param controller 
     */
    @Override
    public void addController(GameController controller) {
        closeButton.addActionListener(controller);
    }

    /**
     * Receive the result of Audience.
     * @param o
     * @param arg Map<optionId, percentage>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Map) {
            graphPanel.setPercentage((Map<Integer, Integer>) arg);
        }
    }
}
